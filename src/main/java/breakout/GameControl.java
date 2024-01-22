package breakout;

import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameControl {
    //contains game-level variables

    private LevelControl levelControl;
    private int level;
    private Stage stage;
    private Timeline animation;
    private boolean pauseGame = false;

    public static final String TITLE = "Breakout Game";
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int LEVELCOUNT = 3;


    public GameControl(){
        stage = new Stage();
        setLevel(1);
        levelControl = new LevelControl(this.level);

        Scene scene = levelControl.getScene();
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);
//        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
//        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void step(double secondDelay) {
        //go to next level if current level cleared

        //add check for step pause (for splash screen), turn on pause after a level is passed, and turn off pause on mouse click
//        while (!pauseGame) {

            levelControl.getBall().move(secondDelay);
            levelControl.checkBallPaddleCollision(secondDelay, this.level);
            if (levelControl.checkBallMissed(secondDelay)) {
                levelControl.loseLive();
                pauseGame = true;
            }

            if (levelControl.levelCleared()) {
                nextLevel();
                levelControl = new LevelControl(this.level);
            }
            if (finishedLastLevel()) {
                handleGameWon();
            }
//        }
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
        //DisplayControl.splashScreenFinishedLevel();
    }

    public void handleGameWon(){

    }

    public void handleGameLost(){

    }

    public boolean finishedLastLevel(){
        return this.level == LEVELCOUNT;
    }

}
