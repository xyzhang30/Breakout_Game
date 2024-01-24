package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class DisplayControl {
    private static final int DISPLAY_FONT_SIZE = 30;
    private static final int INSTRUCTION_FONT_SIZE = 16;
    private static final int SIZE = 540; //size of the screen //make it public
    private static final Color BACKGROUND = new Color(0.3,0.3,0.3,1);
    private static final double MIDDLE_OF_SCREEN = (double) SIZE /2;
    private static final int Y_OFFSET = 150;

    public Scene youLostScreen() {
        Text youLostText = new Text("You Lost :(");
        return setTextScene(youLostText);
    }

    public Scene youWinScreen() {
        Text youWinText = new Text("YOU WON :)");
        return setTextScene(youWinText);
    }

    private Scene setTextScene(Text youWinText) {
        youWinText.setFont(new Font(DISPLAY_FONT_SIZE));
        youWinText.setX(MIDDLE_OF_SCREEN-youWinText.getLayoutBounds().getWidth()/2);
        youWinText.setY(MIDDLE_OF_SCREEN);

        Group displayScene = new Group();
        Scene splashScene = new Scene(displayScene, SIZE , SIZE, BACKGROUND);

        displayScene.getChildren().add(youWinText);

        return splashScene;
    }


//    public Scene levelCompleteScreen(){
//        Text levelCompleteText = new Text("Level Complete!!");
//        levelCompleteText.setFont(new Font(DISPLAY_FONT_SIZE));
//        levelCompleteText.setX(MIDDLE_OF_SCREEN-levelCompleteText.getLayoutBounds().getWidth()/2);
//        levelCompleteText.setY(MIDDLE_OF_SCREEN);
//
//        Text instruction = new Text("click SPACE to go to next level");
//        instruction.setFont(new Font(INSTRUCTION_FONT_SIZE));
//        instruction.setX(MIDDLE_OF_SCREEN-instruction.getLayoutBounds().getWidth()/2);
//        instruction.setY(MIDDLE_OF_SCREEN + levelCompleteText.getLayoutBounds().getHeight());
//
//        Group displayScene = new Group();
//        Scene splashScene = new Scene(displayScene, SIZE, SIZE, BACKGROUND);
//
//        displayScene.getChildren().addAll(levelCompleteText,instruction);
//
//        return splashScene;
//    }

    public Scene startOfGameScreen(){
        Text gameTitle = new Text("BREAKOUT GAME");
        gameTitle.setFont(new Font(DISPLAY_FONT_SIZE));
        gameTitle.setX(MIDDLE_OF_SCREEN - gameTitle.getLayoutBounds().getWidth() / 2);
//        gameTitle.setY(MIDDLE_OF_SCREEN - gameTitle.getLayoutBounds().getHeight());
        gameTitle.setY(Y_OFFSET);


        Text gameInstructions = getText();

        Group displayScene = new Group();
        Scene startScene = new Scene(displayScene, SIZE, SIZE, BACKGROUND);

        displayScene.getChildren().addAll(gameTitle, gameInstructions);

        return startScene;
    }

    private static Text getText() {
        Text gameInstructions = new Text("Press LEFT and RIGHT to move the paddle\nBounce the ball to clear the bricks\n \nPress SPACE to play, pause/start game, and launch the ball \n \nPower-ups you got will be shown as icons on the top of the screen \n\nYou have 5 lives for each level \nPass all 3 levels to win the game!");
        gameInstructions.setFont(new Font(INSTRUCTION_FONT_SIZE));
        gameInstructions.setX(MIDDLE_OF_SCREEN - gameInstructions.getLayoutBounds().getWidth() / 2);
        gameInstructions.setY(MIDDLE_OF_SCREEN);
        return gameInstructions;
    }

}
