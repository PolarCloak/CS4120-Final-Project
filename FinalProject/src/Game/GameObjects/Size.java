package Game.GameObjects;

import java.io.Serializable;

public class Size implements Serializable{

    public double x;
    public double y;

    public Size(){
        x=100;
        y=100;
    }

    public Size(double x, double y){
        this.x = x;
        this.y = y;
    }
}
