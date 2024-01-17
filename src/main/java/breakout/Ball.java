package breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Circle {
    int velocityX = Main.SPEED;
    int velocityY = Main.SPEED;

    public Ball(double centerX, double CenterY, double radius, Color color) {
        super(centerX,CenterY,radius,color);
    }

    public void setVelocityY(int newYvel) {
        this.velocityY = newYvel;
    }

    public void setVelocityX(int newXvel){
        this.velocityX = newXvel;
    }

    public int getVelocityX(){
        return this.velocityX;
    }

    public int getVelocityY(){
        return this.velocityY;
    }

    public void move(){

    }

    public void resetBall(){

    }


}
