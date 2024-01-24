package breakout;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class PowerUpIcon {

    private Rectangle paddleIcon;
    private Polygon speedUpIcon;

    public PowerUpIcon(){
        setPaddleIcon();
        setSpeedUpIcon();
    }

    public void setPaddleIcon(){
        paddleIcon = new Rectangle();
        paddleIcon.setX(200);
        paddleIcon.setY(13);
        paddleIcon.setWidth(10);
        paddleIcon.setHeight(4);
    }

    public Rectangle getPaddleIcon(){
        return paddleIcon;
    }

    public void setSpeedUpIcon(){
        speedUpIcon = new Polygon();
        speedUpIcon.getPoints().setAll(
                175.0, 10.0,
                175.0, 20.0,
                185.0, 15.0
        );
    }

    public Polygon getSpeedUpIcon(){
        return speedUpIcon;
    }

}
