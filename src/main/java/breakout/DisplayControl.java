package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.*;

public class DisplayControl {


    public Scene youLostScreen() {
        Text youLostText = new Text("You Lost!");
        youLostText.setStyle("-fx-font-size: 24;");

        Group layout = new Group();
        layout.getChildren().add(youLostText);

        Scene splashScene = new Scene(layout, 400, 300); // Adjust width and height as needed

        return splashScene;
    }

}
