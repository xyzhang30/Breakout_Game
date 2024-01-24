package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LevelControl {
    //contains level-level variables

    private Ball ball;
    private Paddle paddle;
    private Group root;
    private int lives;
    private Text livesDisplay;
    private Text levelDisplay;
    private List<Brick> brickList; //a list of all the blocks in current level
    private Scene scene;
    private boolean pauseGame = false;
    private boolean speedupPowerupObtained = false;
    private boolean paddlePowerupObtained = false;
    private boolean addBallPowerupObtained = false;
    private PowerUpIcon icons = new PowerUpIcon();
    private Ball powerupBall;
    public static final int SIZE = 540; //size of the screen //make it public
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 35;
    private static final double SPEED = 200; //initial speed of ball
    private static final double SLOWED_SPEED_PERCENTAGE = 0.75; //
    private static final double SPEED_UP_PERCENTAGE = 1.10;
    private static final int BALL_RADIUS = 10;
    private static final String LEVEL1 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelOneBricks";
    private static final String LEVEL2 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelTwoBricks";
    private static final String LEVEL3 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelThreeBricks";
    private static final Color BACKGROUND = new Color(0.3, 0.3, 0.3, 1);
    private static final int LEVEL_FONT_SIZE = 20;
    private static final int LEVEL_TEXT_Y_OFFSET = 20;
    private static final int LEVEL_TEXT_X_OFFSET = 10;
    private static final int PADDLE_INITIAL_X = SIZE / 2 - 50;
    private static final int PADDLE_INITIAL_Y = SIZE - 20;
    private static final int PADDLE_WIDTH = 100;
    private static final double PADDLE_LENGTHEN_PERCENTAGE = 1.5;
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_INITIAL_X = SIZE / 2;
    private static final int BALL_INITIAL_Y = SIZE - 20 - BALL_RADIUS;
    private static final Color BALL_COLOR = Color.LIGHTSTEELBLUE;
    private static final double PADDLE_SPEED = 15.0; // Adjust the speed as needed
    private static final int LIVES_PER_LEVEL = 5;

    public LevelControl(int level) {
        this.root = new Group();
        this.brickList = new ArrayList<>();
        this.paddle = new Paddle(PADDLE_INITIAL_X, PADDLE_INITIAL_Y, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_SPEED);
        this.ball = new Ball(BALL_INITIAL_X, BALL_INITIAL_Y, BALL_RADIUS, BALL_COLOR, SPEED, SPEED);
        this.lives = LIVES_PER_LEVEL;
        this.scene = new Scene(root, SIZE, SIZE, BACKGROUND);

        readBrickFormation(level);
        setLevelDisplay(level);
        setLivesDisplay();

        root.getChildren().addAll(ball, paddle, livesDisplay, levelDisplay);
    }

    public void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case LEFT:
                if (!getPauseGame()) {
                    paddle.moveLeft();
                }
                break;
            case RIGHT:
                if (!getPauseGame()) {
                    paddle.moveRight();
                }
                break;
            case SPACE:
                setPauseGame(!getPauseGame());
                break;
            case L:
                increaseLife();
                break;
            case R:
                prePaddleReset();
                ball.resetBall();
                paddle.resetPaddle();
                setPauseGame(true);
                removeExtraBall();
                break;
            case C:
                removeAllBricks();
            case S:
                ball.setVelocityX(ball.getVelocityX()*SLOWED_SPEED_PERCENTAGE);
                ball.setVelocityY(ball.getVelocityY()*SLOWED_SPEED_PERCENTAGE);
                if (addBallPowerupObtained) {
                    powerupBall.setVelocityX(powerupBall.getVelocityX() * SLOWED_SPEED_PERCENTAGE);
                    powerupBall.setVelocityY(powerupBall.getVelocityY() * SLOWED_SPEED_PERCENTAGE);
                }
        }
    }

    private void prePaddleReset() {
        paddle.setWidth(PADDLE_WIDTH);
        paddlePowerupObtained = false;
        root.getChildren().remove(icons.getPaddleIcon());
    }

    public Scene getScene() {
        return scene;
    }

    public void loseLive() {
        this.lives -= 1;
        updateLivesDisplay();
    }

    private void updateLivesDisplay() {
        root.getChildren().remove(livesDisplay);
        setLivesDisplay();
        root.getChildren().add(livesDisplay);
    }

    public void increaseLife() {
        this.lives += 1;
        updateLivesDisplay();
    }

    public void setLevelDisplay(int level) {
        String displayText = "Level ";
        String levelText = Integer.toString(level);
        String completeDisplayText = displayText.concat(levelText);

        levelDisplay = new Text();
        levelDisplay.setText(completeDisplayText);
        levelDisplay.setFill(Color.BLACK);
        levelDisplay.setFont(new Font(LEVEL_FONT_SIZE));

        levelDisplay.setX(LEVEL_TEXT_X_OFFSET);
        levelDisplay.setY(LEVEL_TEXT_Y_OFFSET);
    }

    public void setLivesDisplay() {
        //get the text node to display lives on the level screen
        String displayText = "Lives Remaining: ";
        String livesText = Integer.toString(this.lives);
        String completeDisplayText = displayText.concat(livesText);

        livesDisplay = new Text();
        livesDisplay.setText(completeDisplayText);
        livesDisplay.setFill(Color.BLACK);
        livesDisplay.setFont(new Font(LEVEL_FONT_SIZE));

        livesDisplay.setLayoutX(scene.getWidth() - livesDisplay.getLayoutBounds().getWidth() - 10);
        livesDisplay.setLayoutY(LEVEL_TEXT_Y_OFFSET);
    }

    public void readBrickFormation(int level) {
        //reads brick formation from datafile and display bricks on the screen when each new level loads
        brickList.clear();
        int rowCount = 0;
        try {
            Scanner scanner = new Scanner(new File(getLevelFileName(level)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] row = line.split(" ");
                for (int i = 0; i < row.length; i++) {
                    if (!row[i].equals("0") && !row[i].isEmpty()) {
                        try {
                            int brickX = i * BRICK_WIDTH; //the width of bricks to its left + their borders
                            int brickY = rowCount * BRICK_HEIGHT;
                            int numHits = Integer.parseInt(row[i]);
                            Brick newBrick = new Brick(brickX, brickY, numHits);
                            Color brickColor = newBrick.getBrickColor(numHits, level);
                            newBrick.setFill(brickColor);
                            root.getChildren().add(newBrick);
                            brickList.add(newBrick);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                rowCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private String getLevelFileName(int level) {
        return switch (level) {
            case 1 -> LEVEL1;
            case 2 -> LEVEL2;
            case 3 -> LEVEL3;
            default -> null;
        };
    }

    public void removeBrickItor(Iterator<Brick> iter) {
        iter.remove();
    }

    public void removeBrick(Brick brick) {
        root.getChildren().remove(brick);
        brickList.remove(brick);
    }

    public void removeAllBricks() {
        root.getChildren().removeAll(brickList);
        brickList.clear();
    }

    private void handleBallMissed() {
        loseLive();
        ball.resetBall();
        prePaddleReset();
        paddle.resetPaddle();
        removeExtraBall();
        removeSpeedup();
    }

    private void removeExtraBall(){
        addBallPowerupObtained = false;
        root.getChildren().remove(powerupBall);
        root.getChildren().remove(icons.getAddBallIcon());
    }

    private void removeSpeedup(){
        speedupPowerupObtained = false;
        root.getChildren().remove(icons.getSpeedUpIcon());
    }

    public boolean levelCleared() {
        return brickList.isEmpty();
    }

    public Ball getBall() {
        return ball;
    }

    public void checkBallPaddleCollision(double secondDelay, int level, Ball ball) {
        List<Brick> toBeRemoved = new ArrayList<>();

        double newBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
        double newBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;

        double leftThird = paddle.getX() + paddle.getWidth() / 3;
        double rightThird = paddle.getX() + 2 * (paddle.getWidth() / 3);

//        check collision with paddle
        if (newBallY + BALL_RADIUS >= paddle.getY() &&
                newBallY - BALL_RADIUS <= paddle.getY() + paddle.getHeight() &&
                newBallX + BALL_RADIUS >= paddle.getX() &&
                newBallX - BALL_RADIUS <= paddle.getX() + paddle.getWidth()) {

                if (newBallX - BALL_RADIUS <= leftThird) {
                    // Ball hits left 1/3 of the paddle
                    ball.bounceY();
                    ball.bounceX();
                } else if (newBallX + BALL_RADIUS >= rightThird) {
                    // Ball hits right 1/3 of the paddle
                    ball.bounceY();
                    ball.bounceX();
                } else if (newBallX - BALL_RADIUS > leftThird && newBallX + BALL_RADIUS < rightThird) {
                    // Ball hits middle 1/3 of the paddle
                    ball.bounceY();
                }
        }
        //check collision with each brick remaining on the screen
        Iterator<Brick> brickListIterator = brickList.iterator();
        while (brickListIterator.hasNext()) {
            Brick brick = brickListIterator.next();
            if (brick.getX() - BALL_RADIUS <= newBallX && newBallX <= brick.getX() + BRICK_WIDTH + BALL_RADIUS
                    && brick.getY() - BALL_RADIUS <= newBallY && newBallY <= brick.getY() + BRICK_HEIGHT + BALL_RADIUS) {
                //if ball is colliding from the top or bottom of the brick
                if (newBallY + BALL_RADIUS <= brick.getY() + BALL_RADIUS || newBallY - BALL_RADIUS >= brick.getY() + BRICK_HEIGHT - BALL_RADIUS) {
                    ball.bounceY();
                } else { //if ball is colliding from the left or right of the brick
                    ball.bounceX();
                }
                brick.gotHit(level);
                if (brick.getNumHits() <= 0) {
                    toBeRemoved.add(brick);
                    if (brick.isPowerupBrick()){
                        handlePowerUp(brick);
                    }
                    else if (brick.isExplodingBrick()){
                        toBeRemoved.addAll(handleExplode(brick));
                    }
                    removeBrickItor(brickListIterator);
                }
            }
        }
        for (Brick brick : toBeRemoved) {
            removeBrick(brick);
        }
    }

    private void handlePowerUp(Brick brick) {
        switch (brick.getPowerupType()){
            case 1: //speeds up the ball by 0.25 times of the current speed (bad powerup), can be sped up multiple times
                ball.setVelocityX(ball.getVelocityX() * SPEED_UP_PERCENTAGE);
                ball.setVelocityY(ball.getVelocityY() * SPEED_UP_PERCENTAGE);
                if (addBallPowerupObtained) {
                    powerupBall.setVelocityX(powerupBall.getVelocityX() * SPEED_UP_PERCENTAGE);
                    powerupBall.setVelocityY(powerupBall.getVelocityY() * SPEED_UP_PERCENTAGE);
                }
                if (!speedupPowerupObtained) {
                    root.getChildren().add(icons.getSpeedUpIcon());
                    speedupPowerupObtained = true;
                }
                break;
            case 2: //lengthens the paddle by 1.25 times the original width, you lose the paddle upgrade upon paddle reset.
                if (!paddlePowerupObtained){
                    paddle.setWidth(PADDLE_WIDTH * PADDLE_LENGTHEN_PERCENTAGE);
                    paddle.setPaddleX(paddle.getX() - ((PADDLE_WIDTH * PADDLE_LENGTHEN_PERCENTAGE)-PADDLE_WIDTH)/2);
                    root.getChildren().add(icons.getPaddleIcon());
                    paddlePowerupObtained = true;
                }
                break;
            case 3: //gets a new ball
                if (!addBallPowerupObtained) {
                    powerupBall = new Ball(ball.getCenterX() - BALL_RADIUS - BALL_RADIUS, ball.getCenterY() - BALL_RADIUS - BALL_RADIUS, BALL_RADIUS, BALL_COLOR, ball.getVelocityX(), ball.getVelocityY());
                    root.getChildren().add(powerupBall);
                    addBallPowerupObtained = true;
                    root.getChildren().add(icons.getAddBallIcon());
                }
        }
    }

    public boolean gotBallPowerup(){
        return addBallPowerupObtained;
    }

    private List<Brick> handleExplode(Brick brick) {
        List<Brick> bricksBombed = new ArrayList<>();

        double brickX = brick.getX();
        double brickY = brick.getY();

        for (Brick b : brickList){
            if (b.getY() == brickY || brickX == b.getX()){
                bricksBombed.add(b);
            }
        }
        return bricksBombed;
    }

    public boolean checkBallMissed(double secondDelay) {
        double mainBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
        double mainBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;
        boolean mainBallOffScreen = mainBallY - BALL_RADIUS >= scene.getHeight();

        boolean powerupBallOffScreen = true;
        if (addBallPowerupObtained && powerupBall != null) {
            double powerupBallY = powerupBall.getCenterY() + powerupBall.getVelocityY() * secondDelay;
            powerupBallOffScreen = powerupBallY - BALL_RADIUS >= scene.getHeight();
        }

        if (mainBallOffScreen && powerupBallOffScreen) {
            handleBallMissed();
            return true;
        }
        return false;
    }

    public int getLives() {
        return lives;
    }

    public boolean getPauseGame() {
        return pauseGame;
    }

    public void setPauseGame(boolean newPauseGame) {
        pauseGame = newPauseGame;
    }

    public Ball getPowerupBall(){
        return powerupBall;
    }
}