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
    public static final int SPEED = 100;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int PADDLE_X = 200; //top left corner
    public static final int PADDLE_Y = 480; //top left corner
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;
    public Ball ball;
    public Paddle paddle;
    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        ball = new Ball(20, 20, 10);
        ball.setFill(Color.LIGHTSTEELBLUE);

        paddle = new Paddle(PADDLE_X,PADDLE_Y,PADDLE_WIDTH,PADDLE_HEIGHT);
        paddle.setFill(Color.BLACK);

        Group root = new Group();
        root.getChildren().add(ball);
        root.getChildren().add(paddle);

        Scene scene = new Scene(root, SIZE, SIZE, BACKGROUND);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);

        scene.setOnMouseMoved(event -> handleMouseMove(event));


//        Timeline animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
//        animation.play();
    }

    private void handleMouseMove(MouseEvent event) {
        paddle.move(event.getX());
    }

//    private void step(double elapsedTime) {
//
//        ball.setCenterX(ball.getCenterX() + SPEED * elapsedTime);
//
//    }
}
