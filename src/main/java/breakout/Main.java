package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
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
    public static final int SIZE = 400;
    public static final int SPEED = 100;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public Circle ball;


    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        ball = new Circle(20, 20, 20);
        ball.setFill(Color.LIGHTSTEELBLUE);

        Group root = new Group();
        root.getChildren().add(ball);


        Scene scene = new Scene(root, SIZE, SIZE, BACKGROUND);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double elapsedTime) {

        ball.setCenterX(ball.getCenterX() + SPEED * elapsedTime);

    }
}
