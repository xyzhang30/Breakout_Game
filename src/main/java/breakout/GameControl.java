package breakout;

import javafx.animation.KeyFrame;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    private boolean skipLevel = false;


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
        levelControl.getScene().setOnKeyPressed(event -> handleKeyPress(event.getCode()));

    }

    private void handleKeyPress(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE && !gameStarted) {
            startLevel();
            gameStarted = true;
        }
        if (gameStarted) {
            if (keyCode.isDigitKey()) {
                int numberPressed = Integer.parseInt(keyCode.getName());
                if (numberPressed > 3){
                    numberPressed = 3;
                }
                switch (numberPressed) {
                    case 1:
                        setLevel(1);
                        break;
                    case 2:
                        setLevel(2);
                        break;
                    case 3:
                        setLevel(3);
                        break;
                    default:
                        break;
                }
            }
            if (keyCode == KeyCode.P){
                handleGameWon();
            }
            if (levelControl != null) {
                levelControl.handleKeyPress(keyCode);
            }
        }
    }

    private void startLevel() {
        levelControl.setPauseGame(true);
        Scene scene = levelControl.getScene();
        stage.setScene(scene);
    }

    private void step(double secondDelay) {
        if (levelControl.getPauseGame()){
            return;
        }

        levelControl.getBall().move(secondDelay);
        levelControl.checkBallPaddleCollision(secondDelay, this.level, levelControl.getBall());
        if (levelControl.gotBallPowerup()){
            levelControl.getPowerupBall().move(secondDelay);
            levelControl.checkBallPaddleCollision(secondDelay, this.level, levelControl.getPowerupBall());
        }

        if (levelControl.checkBallMissed(secondDelay)) {
            levelControl.setPauseGame(true);
            return;
        }

        if (levelControl.getLives() <= 0){
            handleGameLost();
            return;
        }
        if (finishedLastLevel() && levelControl.levelCleared()) {
            handleGameWon();
            return;
        }
        if (levelControl.levelCleared()) {
//            handleLevelComplete();
            nextLevel();
//            levelControl = new LevelControl(this.level);
//            stage.setScene(levelControl.getScene());
//            levelControl.getScene().setOnKeyPressed(event -> handleKeyPress(event.getCode()));
//            levelControl.setPauseGame(true);
        }
//        if (finishedLastLevel()) {
//            handleGameWon();
//        }
    }


    public void nextLevel(){
        setLevel(this.level+1);
    }

    public void setLevel(int level){
        this.level = level;
        levelControl = new LevelControl(this.level);
        stage.setScene(levelControl.getScene());
        levelControl.getScene().setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        levelControl.setPauseGame(true);
//        this.levelControl.setLevelDisplay(this.level);
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
        levelControl.setPauseGame(true);
        displayYouWonScreen();
    }

    private void displayYouWonScreen() {
        Scene gameWon = displayControl.youWinScreen();
        stage.setScene(gameWon);
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
