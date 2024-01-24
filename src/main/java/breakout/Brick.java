package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.Random;

public class Brick extends Rectangle {
    private int hits;
    private int powerupType = 0; //default to 0
    private boolean powerup = false; //default to false
    private boolean explodingBrick = false; //default to false

    public static final int BRICK_WIDTH = 60;
    public static final int BRICK_HEIGHT = 35;
    public static final int BRICK_BORDER_WIDTH = 1;

    public Brick(int Xupperleft, int Yupperleft, int hits){
        super(Xupperleft, Yupperleft, BRICK_WIDTH, BRICK_HEIGHT);
        this.hits = hits;
        if (hits == 1){ //all bricks with 3 hits are powerup bricks
            powerup = true;
            powerupType = generatePowerupType();
        }
        if (hits == 4){
            explodingBrick = true;
        }
        this.setStroke(Color.BLACK); // Border color
        this.setStrokeWidth(BRICK_BORDER_WIDTH); // Border width
        this.setStrokeType(StrokeType.INSIDE); // Border position
//        this.powerupType = powerupType;
    }

    public void gotHit(int level){
        this.hits -= 1;
        if(hits == 0){
            this.setStroke(Color.TRANSPARENT);
            this.setFill(Color.TRANSPARENT);
        } else {
            Color newColor = getBrickColor(hits, level);
            this.setFill(newColor);
        }
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
        else if (level == 2){
            return switch (hits) {
                case 1 -> Color.web("ffc9d7");
                case 2 -> Color.web("b95b7f");
                case 3 -> Color.web("6a3556");
                case 4 -> Color.web("360e22");
                default -> Color.web("000000",0);
            };
        }
        else if (level == 3){
            return switch (hits) {
                case 1 -> Color.web("ffe5d2");
                case 2 -> Color.web("dca7a3");
                case 3 -> Color.web("e69580");
                case 4 -> Color.web("ff7c52");
                default -> Color.web("000000",0);
            };
        }
        return Color.web("000000",0);
    }

    public int getNumHits(){
        return hits;
    }

    public int generatePowerupType(){
//        Random random = new Random();
//        return random.nextInt(3) + 1;
        return 2;
    }

    public boolean isExplodingBrick() {
        return explodingBrick;
    }

    public boolean isPowerupBrick() {
        return powerup;
    }

    public int getPowerupType() {
        return powerupType;
    }
}
