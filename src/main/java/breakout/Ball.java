package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Circle {
    private double velocityX;
    private double velocityY;

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

        if (getCenterX() - getRadius() <= 0 || getCenterX() + getRadius() >= LevelControl.SIZE) {
            bounceX();
        }
        if (getCenterY() - getRadius() <= 0) {
            bounceY();
        }
    }

    public void resetBall(){

    }

    public void bounceX(){
        velocityX = -velocityX;
    }

    public void bounceY(){
        velocityY = -velocityY;
    }

}
