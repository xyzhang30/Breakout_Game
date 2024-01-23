package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class DisplayControl {

    private static final int DISPLAY_FONT_SIZE = 30;
    private static final int INSTRUCTION_FONT_SIZE = 16;
    private static final int SIZE = 540; //size of the screen
    private static final Color BACKGROUND = new Color(0.3,0.3,0.3,1);
    private static final double MIDDLE_OF_SCREEN = (double) SIZE /2;

    public Scene youLostScreen() {
        Text youLostText = new Text("You Lost :(");
        youLostText.setFont(new Font(DISPLAY_FONT_SIZE));
        youLostText.setX(MIDDLE_OF_SCREEN-youLostText.getLayoutBounds().getWidth()/2);
        youLostText.setY(MIDDLE_OF_SCREEN);

        Group displayScene = new Group();
        Scene splashScene = new Scene(displayScene, SIZE, SIZE, BACKGROUND);

        displayScene.getChildren().add(youLostText);

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
        gameTitle.setY(MIDDLE_OF_SCREEN - gameTitle.getLayoutBounds().getHeight());

        Text gameInstructions = new Text("Press LEFT and RIGHT keys to move the paddle\nBounce the ball to clear the bricks\n \n Press SPACE to play");
        gameInstructions.setFont(new Font(INSTRUCTION_FONT_SIZE));
        gameInstructions.setX(MIDDLE_OF_SCREEN - gameInstructions.getLayoutBounds().getWidth() / 2);
        gameInstructions.setY(MIDDLE_OF_SCREEN);


        Group displayScene = new Group();
        Scene startScene = new Scene(displayScene, SIZE, SIZE, BACKGROUND);

        displayScene.getChildren().addAll(gameTitle, gameInstructions);

        return startScene;
    }

}
