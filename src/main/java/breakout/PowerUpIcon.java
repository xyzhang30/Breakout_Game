package breakout;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class PowerUpIcon {

    private Rectangle paddleIcon;
    private Polygon speedUpIcon;
    private Circle addBallIcon;

    public PowerUpIcon(){
        setPaddleIcon();
        setSpeedUpIcon();
        setAddBallIcon();
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

    public Circle getAddBallIcon() {
        return addBallIcon;
    }

    public void setAddBallIcon() {
        addBallIcon = new Circle();
        addBallIcon.setCenterX(230);
        addBallIcon.setCenterY(15);
        addBallIcon.setRadius(5);
    }
}
