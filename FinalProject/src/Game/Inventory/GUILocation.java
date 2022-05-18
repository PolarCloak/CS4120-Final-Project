package Game.Inventory;

import java.io.Serializable;

public class GUILocation implements Serializable {

    public double x;
    public double y;


    public GUILocation() {
        this.x = 10000;
        this.y = 10000;
    }

    public GUILocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }



}
