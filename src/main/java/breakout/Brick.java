package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Brick extends Rectangle {
    int hits;
    int powerupType;
    public static final int BRICK_WIDTH = 60;
    public static final int BRICK_HEIGHT = 35;
    public static final int BRICK_BORDER_WIDTH = 1;

//    public Brick(int Xupperleft, int Yupperleft, int hits, int powerupType){
    public Brick(int Xupperleft, int Yupperleft, int hits){
        super(Xupperleft, Yupperleft, BRICK_WIDTH, BRICK_HEIGHT);
        this.hits = hits;
        this.setFill(Color.GREY);
        this.setStroke(Color.BLACK); // Border color
        this.setStrokeWidth(BRICK_BORDER_WIDTH); // Border width
        this.setStrokeType(StrokeType.OUTSIDE); // Border position

//        this.powerupType = powerupType;
    }

    public void gotHit(int level){
        this.hits -= 1;
        Color newColor = getBrickColor(this.hits-1, level);
        this.setFill(newColor);
    }

    public Color getBrickColor(int hits, int level){
        //returns the color the brick should be according to its remaining hits
        if (level == 1) { //blueish green
            return switch (hits) {
                case 1 -> Color.web("b0dbf1");
                case 2 -> Color.web("63a69f");
                case 3 -> Color.web("1f7a8c");
                case 4 -> Color.web("03254c");
                default -> Color.web("000000",0);
            };
        }
        if (level == 2){
            return switch (hits) {
                case 1 -> Color.web("ffc9d7");
                case 2 -> Color.web("b95b7f");
                case 3 -> Color.web("6a3556");
                case 4 -> Color.web("360e22");
                default -> Color.web("000000",0);
            };
        }
        if (level == 3){
            return switch (hits) {
                case 1 -> Color.web("ffe5d2");
                case 2 -> Color.web("dca7a3");
                case 3 -> Color.web("e69580");
                case 4 -> Color.web("ff7c52");
                default -> Color.web("000000",0);
            };
        }
        return Color.GREY;
    }

}
