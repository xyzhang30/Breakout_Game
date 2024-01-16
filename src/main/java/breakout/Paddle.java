package breakout;

import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Paddle extends Rectangle {
    private double initialX; // Initial X-coordinate of the paddle

    public Paddle(int Xupperleft, int Yupperleft, int paddleWidth, int paddleHeight){
        super(Xupperleft, Yupperleft, paddleWidth, paddleHeight);
        this.initialX = Xupperleft;
    }

    public void move(double mouseX) {
        // Ensure the paddle stays within the scene bounds
        double newX = mouseX - getWidth() / 2;
        if (newX >= 0 && newX + getWidth() <= Main.SIZE) {
            setX(newX);
        }
    }

}
