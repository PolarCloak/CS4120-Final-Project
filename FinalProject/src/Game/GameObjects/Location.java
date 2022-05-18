package Game.GameObjects;

import Game.GameObjects.Movable.Movement.Velocity;

import java.io.Serializable;

public class Location implements Serializable {

    public double x;
    public double y;

    public Location(){
        x=0;
        y=0;
    }

    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void updateLocation(Velocity velocity) {
        this.x += velocity.x;
        this.y += velocity.y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x= " + Math.round(x) +
                ", y= " + Math.round(y) +
                '}';
    }
}
