package breakout;

import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    int hits;
    int powerupType;

    public Brick(int Xupperleft, int Yupperleft, int hits, int powerupType){
        super(Xupperleft, Yupperleft, Main.BLOCK_WIDTH, Main.BLOCK_HEIGHT);
        this.hits = hits;
        this.powerupType = powerupType;
    }

    public void gotHit(){
        this.hits -= 1;
    }

}
