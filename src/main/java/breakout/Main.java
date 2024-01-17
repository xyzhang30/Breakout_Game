package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Alisha Zhang
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Breakout Game"; //changed page title
    public static final Color DUKE_BLUE = new Color(0, 0.188, 0.529, 1);
    public static final Color BACKGROUND = new Color(0.3,0.3,0.3,1);
    public static final int SIZE = 500;
    public static final int SPEED = 200;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int PADDLE_X = 200; //top left corner
    public static final int PADDLE_Y = 480; //top left corner
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public static final int BALL_RADIUS = 10;
    public static final int BLOCK_WIDTH = 100;
    public static final int BLOCK_HEIGHT = 50;
    public Ball ball;
    public Paddle paddle;
    public Brick testBlock;
    private boolean isFirstClick = false;
    public Group root;


    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
//        ball = new Ball(20, 20, 10);
//        ball.setFill(Color.LIGHTSTEELBLUE);

//        paddle = new Paddle(PADDLE_X,PADDLE_Y,PADDLE_WIDTH,PADDLE_HEIGHT);
//        paddle.setFill(Color.BLACK);

        paddle = new Paddle(SIZE / 2 - 50, SIZE - 20, 100, 10);
        ball = new Ball(SIZE / 2, SIZE - 20 - BALL_RADIUS, BALL_RADIUS, Color.LIGHTSTEELBLUE);
        testBlock = new Brick(50,50,2, 0);

        root = new Group();
        root.getChildren().addAll(ball,paddle,testBlock);

        Scene scene = new Scene(root, SIZE, SIZE, BACKGROUND);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);

        scene.setOnMouseClicked(event -> handleMouseClick());
        scene.setOnMouseMoved(event -> handleMouseMove(event));

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void handleMouseMove(MouseEvent event) {
        if (isFirstClick) {
            paddle.move(event.getX());
        }
    }

    private void handleMouseClick(){
        if (!isFirstClick) {
            isFirstClick = true;
        }
    }
    public void removeBrick(Brick brick){
        root.getChildren().remove(brick);
    }

    private void step(double elapsedTime) {
//        ball.setCenterX(ball.getCenterX() + SPEED * elapsedTime);
        double newBallX = ball.getCenterX() + ball.getVelocityX() * elapsedTime;
        double newBallY = ball.getCenterY() + ball.getVelocityY() * elapsedTime;

        //checking if ball hits sidelines
        if (newBallX - BALL_RADIUS <= 0 || newBallX + BALL_RADIUS >= SIZE) {
            ball.setVelocityX(-ball.getVelocityX());
        }
        if (newBallY - BALL_RADIUS <= 0 || newBallY + BALL_RADIUS >= SIZE) {
            ball.setVelocityY(-ball.getVelocityY());
        }

        // Check if the ball hits the paddle
        if (newBallY + BALL_RADIUS >= paddle.getY() &&
                newBallY - BALL_RADIUS <= paddle.getY() + paddle.getHeight() &&
                newBallX + BALL_RADIUS >= paddle.getX() &&
                newBallX - BALL_RADIUS <= paddle.getX() + paddle.getWidth()) {
            // Bounce off the paddle
            ball.setVelocityY(-ball.getVelocityY());
        }

        // Check if the ball hits the brick
        if (newBallY - BALL_RADIUS <= testBlock.getY() + testBlock.getHeight() &&
                newBallY + BALL_RADIUS >= testBlock.getY() &&
                newBallX + BALL_RADIUS >= testBlock.getX() &&
                newBallX - BALL_RADIUS <= testBlock.getX() + testBlock.getWidth()) {
            // Bounce off the brick
            ball.setVelocityY(-ball.getVelocityY());
            testBlock.gotHit();
            if (testBlock.hits == 0){
                removeBrick(testBlock);
            }
        }

        // Update the ball's position
        ball.setCenterX(newBallX);
        ball.setCenterY(newBallY);
    }
}
