package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
//    private LevelChangeListener levelChangeListener;


    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 35;
    public static final int SIZE = 540; //size of the screen
    private static final double SPEED = 200; //initial speed of ball
    private static final double SLOWED_SPEED_PERCENTAGE = 0.75; //
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
    private static final int PADDLE_HEIGHT = 10;
    private static final int BALL_INITIAL_X = SIZE / 2;
    private static final int BALL_INITIAL_Y = SIZE - 20 - BALL_RADIUS;
    private static final Color BALL_COLOR = Color.LIGHTSTEELBLUE;
    private static final double PADDLE_SPEED = 15.0; // Adjust the speed as needed
    private static final int LIVES_PER_LEVEL = 5;

    public LevelControl(int level) {
        System.out.printf("\n playing level %d\n", level);
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
//        if (keyEvent.getCode().isDigitKey()){
//            System.out.println("digital key pressed");
//            handleDigitKeyPress(keyEvent);
//        } else {
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
                ball.resetBall();
                paddle.resetPaddle();
                setPauseGame(true);
                break;
            case C:
                removeAllBricks();
            case S:
                ball.setVelocityX(ball.getVelocityX()*SLOWED_SPEED_PERCENTAGE);
                ball.setVelocityY(ball.getVelocityY()*SLOWED_SPEED_PERCENTAGE);
        }
    }

//    private void handleDigitKeyPress(KeyEvent keyEvent) {
//        int numberPressed = Integer.parseInt(keyEvent.getCode().getName());
//        if (numberPressed == 2) {
//            if (levelChangeListener != null) {
//                levelChangeListener.onLevelChange(numberPressed);
//            }
//        }
//    }


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
        paddle.resetPaddle();
    }

    public boolean levelCleared() {
        return brickList.isEmpty();
    }

    public Ball getBall() {
        return ball;
    }

    public void checkBallPaddleCollision(double secondDelay, int level) {
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
//            ball.bounceY();
//            if (newBallY + BALL_RADIUS >= paddle.getY() &&
//                    newBallY - BALL_RADIUS <= paddle.getY() + paddle.getHeight()) {

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
//            }
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
                    if (brick.isExplodingBrick()){
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
        double newBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
        double newBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;

        if (newBallY - BALL_RADIUS >= scene.getHeight()) {
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
}