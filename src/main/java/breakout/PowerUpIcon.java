package breakout;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class PowerUpIcon {

    private Rectangle paddleIcon;
    private Polygon speedUpIcon;
    private Circle addBallIcon;

    private static final int PADDLE_ICON_X = 200;
    private static final int PADDLE_ICON_Y = 13;
    private static final int PADDLE_ICON_WIDTH = 10;
    private static final int PADDLE_ICON_HEIGHT = 4;
    private static final int BALL_ICON_CENTER_X = 230;
    private static final int BALL_ICON_CENTER_Y = 15;
    private static final int BALL_ICON_RADIUS = 5;


    public PowerUpIcon(){
        setPaddleIcon();
        setSpeedUpIcon();
        setAddBallIcon();
    }

    public void setPaddleIcon(){
        paddleIcon = new Rectangle();
        paddleIcon.setX(PADDLE_ICON_X);
        paddleIcon.setY(PADDLE_ICON_Y);
        paddleIcon.setWidth(PADDLE_ICON_WIDTH);
        paddleIcon.setHeight(PADDLE_ICON_HEIGHT);
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
        addBallIcon.setCenterX(BALL_ICON_CENTER_X);
        addBallIcon.setCenterY(BALL_ICON_CENTER_Y);
        addBallIcon.setRadius(BALL_ICON_RADIUS);
    }
}
