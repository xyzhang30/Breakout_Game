package breakout;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Feel free to completely change this code or delete it entirely.
 *
 * @author Alisha Zhang
 */
public class Main extends Application {

    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        GameControl game = new GameControl();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
