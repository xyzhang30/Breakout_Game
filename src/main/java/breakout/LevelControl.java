package breakout;

public class LevelControl {
    int level;
    int lives;

    public void setLevel(int level){
        this.level = level;
    }

    public void reset(){

    }

    public void loseLive(){
        this.lives -= 1;
    }

}
