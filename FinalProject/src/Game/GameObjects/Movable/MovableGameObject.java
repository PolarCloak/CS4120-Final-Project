package Game.GameObjects.Movable;

import Game.GameObjects.Location;
import Game.GameObjects.Movable.Movement.Direction;
import Game.GameObjects.Movable.Movement.Velocity;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.Inventory.GUILocation;
import Window.Game.Panes.RenderingPane;

import java.io.Serializable;

public abstract class MovableGameObject extends CollisionGameObject implements Serializable{

    public Velocity velocity;
    public Direction direction;

    private double speed;
    public double speedMod;

    public MovableGameObject(Location loc) {
        super(loc);
        this.velocity = new Velocity();
        this.direction = new Direction();
        this.speed = 0.5; //default speed of object
        this.speedMod = 1.0; // 100% speed
    }

    public void tick(){
        this.updateRotation();
        velocity.setVelocity(getSpeedWithMod(), direction);
        location.updateLocation(velocity);
    }

    public void render(RenderingPane pane, Location playerLoc, GUILocation playerGUILoc){
        super.render(pane,playerLoc,playerGUILoc);
        this.rotateImage();
    }

    public double getSpeedWithMod(){
        return this.speedMod * speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public void updateRotation() {
        direction.tryRotating();
    }

    public void rotateImage(){
        this.image.setRotate(this.direction.degree);
    }

}
