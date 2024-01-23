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
    private boolean gameStarted = false;


    private static final String TITLE = "Breakout Game";
    private static final int FRAMES_PER_SECOND = 60;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int LEVELCOUNT = 3;

    public GameControl(){
        stage = new Stage();
        setLevel(1);
        displayControl = new DisplayControl();
        levelControl = new LevelControl(this.level);

        Scene startScene = displayControl.startOfGameScreen();
        stage.setScene(startScene);
        levelControl.setPauseGame(true);

        stage.setTitle(TITLE);
        stage.show();
        stage.setResizable(false);

        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();

        // Add key press event handler for SPACE key
        startScene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
    }

    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.SPACE) {
            startLevel();
        }
    }

    private void startLevel() {
        levelControl.setPauseGame(false);
        Scene scene = levelControl.getScene();
        stage.setScene(scene);
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
            return;
        }

        if (levelControl.levelCleared()) {
//            handleLevelComplete();
            nextLevel();
            levelControl = new LevelControl(this.level);
            stage.setScene(levelControl.getScene());
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

//    public void handleLevelComplete(){
//        levelControl.setPauseGame(true);
//        displayLevelCompleteScreen();
//        levelCompleteDisplayed = true;
//    }

//    private void displayLevelCompleteScreen() {
//        Scene levelComplete = displayControl.levelCompleteScreen();
//        stage.setScene(levelComplete);
//    }

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

//    private void handleKeyPress(KeyEvent event) {
//        if (event.getCode() == KeyCode.SPACE && levelCompleteDisplayed) {
//            levelCompleteDisplayed = false;
//            nextLevel();
//            levelControl = new LevelControl(this.level);
//            stage.setScene(levelControl.getScene());
//        }
//    }


    //OLD CONSTRUCTOR, JUST IN CASE THE NEW ONE BREAK
    //    public GameControl(){
//        stage = new Stage();
//        setLevel(1);
//        displayControl = new DisplayControl();
//        levelControl = new LevelControl(this.level);
//
//        if (!gameStarted) {
//            Scene startScene = displayControl.startOfGameScreen();
//            stage.setScene(startScene);
//            levelControl.setPauseGame(true);
//        } else {
//            Scene scene = levelControl.getScene();
//            stage.setScene(scene);
//        }
//
//        stage.setTitle(TITLE);
//        stage.show();
//        stage.setResizable(false);
//
//        animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
//        animation.play();
//
//        gameStarted = true;
//        System.out.printf("levelnum %d", level);
//    }



}
