package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Paddle extends Rectangle {
    private double paddleX; // Initial X-coordinate of the paddle

    public Paddle(int Xupperleft, int Yupperleft, int paddleWidth, int paddleHeight){
        super(Xupperleft, Yupperleft, paddleWidth, paddleHeight);
        this.paddleX = Xupperleft;
    }

    public void move(double mouseX) { //needs to move with key code
        // set new X
        double newX = mouseX - getWidth() / 2;
        if (newX >= 0 && newX + getWidth() <= Main.SIZE) {
            setX(newX);
        }
    }

    public double getInitialX(){
        return paddleX;
    }

}
