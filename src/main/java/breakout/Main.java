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



// bugs:
// - ball would go into the brick;
// - after the brick is removed, it seems like the ball is still sensing and bouncing off the brick
// - the number of hits don't match


/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Alisha Zhang
 */
public class Main extends Application {

    // useful names for constant values used
//    public static final String TITLE = "Breakout Game"; //changed page title
    public static final Color DUKE_BLUE = new Color(0, 0.188, 0.529, 1);
//    public static final double SPEED = 200; //initial speed of ball
//    public static final int FRAMES_PER_SECOND = 60;
//    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int PADDLE_X = 200; //top left corner
    public static final int PADDLE_Y = 480; //top left corner
//    public static final int PADDLE_WIDTH = 100;
//    public static final int PADDLE_HEIGHT = 10;
//    public static final int BALL_RADIUS = 10;
//    public Brick testBlock;
    private boolean isFirstClick = false;
//    public Group root;


    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        GameControl game = new GameControl();

//
//        scene.setOnMouseClicked(event -> handleMouseClick());
//        scene.setOnMouseMoved(event -> handleMouseMove(event));

    }

    public static void main(String[] args) {
        launch(args);
    }

//    private void handleMouseMove(MouseEvent event) {
//        if (isFirstClick) {
//            paddle.move(event.getX());
//        }
//    }

//    private void handleMouseClick(){
//        if (!isFirstClick) {
//            isFirstClick = true;
//        }
//    }

}
