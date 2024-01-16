package breakout;

import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    int hits;
    int type;

    public Block(int Xupperleft, int Yupperleft, int blockWidth, int blockHeight){
        super(Xupperleft, Yupperleft, blockWidth, blockHeight);
    }

}
