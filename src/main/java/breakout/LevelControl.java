package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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


    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 35;
    public static final int BRICK_BORDER_WIDTH = 1;
    public static final int SIZE = 540; //size of the screen
    public static final double SPEED = 200; //initial speed of ball
    public static final int BALL_RADIUS = 10;
    private static final String LEVEL1 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelOneBricks";
    private static final String LEVEL2 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelTwoBricks";
    private static final String LEVEL3 = ".\\src\\main\\java\\breakout\\DataFiles\\LevelThreeBricks";
    public static final Color BACKGROUND = new Color(0.3,0.3,0.3,1);
    public static final int LEVEL_FONT_SIZE = 20;
    public static final int LEVEL_TEXT_Y_OFFSET = 20;
    public static final int LEVEL_TEXT_X_OFFSET = 10;


    public LevelControl(int level){
        this.root = new Group();
        this.brickList = new ArrayList<>();
        this.paddle = new Paddle(SIZE / 2 - 50, SIZE - 20, 100, 10);
        this.ball = new Ball(SIZE / 2, SIZE - 20 - BALL_RADIUS, BALL_RADIUS, Color.LIGHTSTEELBLUE, SPEED, SPEED);
        this.lives = 5;
        this.scene = new Scene(root, SIZE, SIZE, BACKGROUND);

        readBrickFormation(level);
        setLevelDisplay(level);
        setLivesDisplay();

        root.getChildren().addAll(ball,paddle,livesDisplay,levelDisplay);
//        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
    }

    public Scene getScene(){
        return scene;
    }

    public void loseLive(){
        this.lives -= 1;
    }

    public void increaseLife(){
        this.lives += 1;
    }

    public void setLevelDisplay(int level){
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

    public void setLivesDisplay(){
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

    public void setLevelDisplay(){

    }

    public void readBrickFormation(int level){
        //reads brick formation from datafile and display bricks on the screen when each new level loads
        brickList.clear();
        int rowCount = 0;
        try {
            Scanner scanner = new Scanner(new File(getLevelFileName(level)));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] row = line.split(" ");
                for (int i = 0; i < row.length; i ++){
                    if (!row[i].equals("0")) {
                        int brickX = i*BRICK_WIDTH; //the width of bricks to its left + their borders
                        int brickY = rowCount*BRICK_HEIGHT;
                        int numHits =  Integer.parseInt(row[i]);
                        Brick newBrick = new Brick(brickX, brickY, numHits);
                        Color brickColor = newBrick.getBrickColor(numHits, level);
                        newBrick.setFill(brickColor);
                        root.getChildren().add(newBrick);
                        brickList.add(newBrick);
                    }
                }
                rowCount ++;
            }
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }


    }

    public void restartLevel(){ //for cheatkey

    }

    private String getLevelFileName(int level) {
        return switch (level) {
            case 1 -> LEVEL1;
            case 2 -> LEVEL2;
            case 3 -> LEVEL3;
            default -> null;
        };
    }

    public void removeBrick(Brick brick){
        root.getChildren().remove(brick);
        brickList.remove(brick);
    }

    public void removeAllBricks(){
        root.getChildren().removeAll(brickList);
        brickList.clear();
    }

    public void handleBallMissed(){
        //call levelControl.loselife(), then call ball.resetBall() and paddle.resetPaddle()
    }

    public boolean levelCleared(){
        return brickList.isEmpty();
    }

    public Ball getBall(){
        return ball;
    }

    public void checkBallPaddleCollision(double secondDelay){
        double newBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
        double newBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;
        if (newBallY + BALL_RADIUS >= paddle.getY() &&
                newBallY - BALL_RADIUS <= paddle.getY() + paddle.getHeight() &&
                newBallX + BALL_RADIUS >= paddle.getX() &&
                newBallX - BALL_RADIUS <= paddle.getX() + paddle.getWidth()){
            ball.bounceY();
        }
    }

//    public void checkBallBrickCollision(double secondDelay){
//        double newBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
//        double newBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;
//        for (Brick brick : brickList) {
//            if (newBallY + BALL_RADIUS >= brick.getY() &&
//                    newBallY - BALL_RADIUS <= brick.getY() + Brick.BRICK_HEIGHT &&
//                    newBallX + BALL_RADIUS >= brick.getX() &&
//                    newBallX - BALL_RADIUS <= brick.getX() + Brick.BRICK_WIDTH) {
//                handleBrickCollision(brick);
//                // Update the ball's position based on the new velocity after collision
//                newBallX = ball.getCenterX() + ball.getVelocityX() * secondDelay;
//                newBallY = ball.getCenterY() + ball.getVelocityY() * secondDelay;
//            }
//        }
//
//    }

//    private void handleBrickCollision(Brick brick) {
//        // Calculate the normal vector of the brick's surface
//        double brickCenterX = brick.getX() + Brick.BRICK_WIDTH / 2.0;
//        double brickCenterY = brick.getY() + Brick.BRICK_HEIGHT / 2.0;
//        double deltaX = ball.getCenterX() - brickCenterX;
//        double deltaY = ball.getCenterY() - brickCenterY;
//
//        // Calculate the dot product of the ball's velocity vector and the normal vector
//        double dotProduct = deltaX * ball.getVelocityX() + deltaY * ball.getVelocityY();
//
//        // Reflect the velocity vector based on the normal vector
//        ball.setVelocityX(ball.getVelocityX() - 2 * dotProduct * deltaX);
//        ball.setVelocityY(ball.getVelocityY() - 2 * dotProduct * deltaY);
//    }


}
