package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Circle {
    private double velocityX;
    private double velocityY;

    private static final int SIZE = 540; //size of the screen
    private static final int BALL_RADIUS = 10;
    private static final int BALL_INITIAL_X = SIZE / 2;
    private static final int BALL_INITIAL_Y = SIZE - 20 - BALL_RADIUS;


    public Ball(double centerX, double CenterY, double radius, Color color, double velocityX, double velocityY) {
        super(centerX,CenterY,radius,color);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setVelocityY(double newYvel) {
        this.velocityY = newYvel;
    }

    public void setVelocityX(double newXvel){
        this.velocityX = newXvel;
    }

    public double getVelocityX(){
        return this.velocityX;
    }

    public double getVelocityY(){
        return this.velocityY;
    }

    public void move(double secondDelay) {
        setCenterX(getCenterX() + velocityX * secondDelay);
        setCenterY(getCenterY() + velocityY * secondDelay);

        if (getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= SIZE) {
            bounceX();
        }
        if (getCenterY() - getRadius() <= 0) {
            bounceY();
        }
    }

    public void resetBall(){
        this.setCenterX(BALL_INITIAL_X);
        this.setCenterY(BALL_INITIAL_Y);
    }

    public void bounceX(){
        velocityX = -velocityX;
    }

    public void bounceY(){
        velocityY = -velocityY;
    }

}
