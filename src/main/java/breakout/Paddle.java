package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Paddle extends Rectangle {
    private double paddleX; // Initial X-coordinate of the paddle

    public static final int SIZE = 540; //size of the screen
    public static final int PADDLE_INITIAL_X = SIZE/2-50;
    public static final int PADDLE_INITIAL_Y = SIZE - 20;
//    private static final int PADDLE_WIDTH = 100;
//    private static final int PADDLE_HEIGHT = 10;


    public Paddle(double Xupperleft, double Yupperleft, int paddleWidth, int paddleHeight){
        super(Xupperleft, Yupperleft, paddleWidth, paddleHeight);
        this.paddleX = Xupperleft;
    }

//    public void move(double mouseX) { //needs to move with key code
//        // set new X
//        double newX = mouseX - getWidth() / 2;
//        if (newX >= 0 && newX + getWidth() <= Main.SIZE) {
//            setX(newX);
//        }
//    }

    public void resetPaddle(){
        this.setX(PADDLE_INITIAL_X);
        this.setY(PADDLE_INITIAL_Y);
    }

    public double getInitialX(){
        return paddleX;
    }

}
