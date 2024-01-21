package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        this.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE); // Border position

//        this.powerupType = powerupType;
    }

    public void gotHit(){
        this.hits -= 1;
    }

//    private Color getBlockColor(int remainingHits){
//        return ''
//    }


}
