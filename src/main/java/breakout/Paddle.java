package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Paddle extends Rectangle {
    private double paddleX;
    private double paddleSpeed;


    public static final int SIZE = 540; //size of the screen
    public static final int PADDLE_INITIAL_X = SIZE/2-50;
    public static final int PADDLE_INITIAL_Y = SIZE - 20;


    public Paddle(double Xupperleft, double Yupperleft, int paddleWidth, int paddleHeight, double paddleSpeed){
        super(Xupperleft, Yupperleft, paddleWidth, paddleHeight);
        this.paddleX = Xupperleft;
        this.paddleSpeed = paddleSpeed;
    }

    public void resetPaddle(){
        this.setX(PADDLE_INITIAL_X);
        this.setY(PADDLE_INITIAL_Y);
    }

    public double getInitialX(){
        return paddleX;
    }

//    public void stop() {
//
//    }

    public void moveLeft() {
        if (getX() - paddleSpeed >= 0) {
            setX(getX() - paddleSpeed);
        }
    }

    public void moveRight() {
        if (getX() + getWidth() + paddleSpeed <= SIZE) {
            setX(getX() + paddleSpeed);
        }
    }
}
