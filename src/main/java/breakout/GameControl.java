package breakout;

import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameControl {
    //contains game-level variables

    private LevelControl levelControl;
    private DisplayControl displayControl;
    private int level;
    private Stage stage;
    private Timeline animation;

    public static final String TITLE = "Breakout Game";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int LEVELCOUNT = 3;


    public GameControl(){
        stage = new Stage();
        setLevel(1);
        levelControl = new LevelControl(this.level);
        displayControl = new DisplayControl();

        Scene scene = levelControl.getScene();
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);
//        scene.setOnKeyPressed(event -> handleSpacePressed(event.getCode()));
//        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double secondDelay) {
        if (levelControl.getPauseGame()){
            return;
        }
        levelControl.getBall().move(secondDelay);
        levelControl.checkBallPaddleCollision(secondDelay, this.level);
        if (levelControl.checkBallMissed(secondDelay)) {
            levelControl.setPauseGame(true);
            return;
        }
        if (levelControl.getLives() <= 0){
            handleGameLost();
        }

        if (levelControl.levelCleared()) {
            nextLevel();
            levelControl = new LevelControl(this.level);
        }
        if (finishedLastLevel()) {
            handleGameWon();
        }
    }


    public void nextLevel(){
        this.level += 1;
//        this.levelControl.setLevelDisplay(this.level);
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void handleLevelFinished(){
        nextLevel();
    }

    public void handleGameWon(){

    }

    public void handleGameLost(){
        levelControl.setPauseGame(true);
        displayYouLostScreen();
    }

    private void displayYouLostScreen() {
        Scene gameLost = displayControl.youLostScreen();
        stage.setScene(gameLost);
    }

    public boolean finishedLastLevel(){
        return this.level == LEVELCOUNT;
    }

}
