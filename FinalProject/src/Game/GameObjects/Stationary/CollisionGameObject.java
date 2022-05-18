package Game.GameObjects.Stationary;

import Game.GameObjects.Interfaces.Collision;
import Game.GameObjects.Location;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public abstract class CollisionGameObject extends GameObject implements Collision, Serializable {

    public CollisionGameObject(Location loc) {
        super(loc);
    }

    public Rectangle getBounds(){
        Rectangle bound = new Rectangle(this.guiLocation.x,this.guiLocation.y,size.x,size.y);
        return bound;
    }
}
