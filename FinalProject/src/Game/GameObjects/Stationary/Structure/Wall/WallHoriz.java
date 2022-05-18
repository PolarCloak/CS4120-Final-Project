package Game.GameObjects.Stationary.Structure.Wall;

import Game.GameObjects.Interfaces.Collision;
import Game.GameObjects.Interfaces.Impassable;
import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class WallHoriz extends CollisionGameObject implements Impassable {

    public WallHoriz(Location loc) {
        super(loc);
        this.id = 4;
        this.size = new Size();
        this.changeImage(new Image("assets/img/world/structure/wall/wallhoriz.png"));
    }


    @Override
    public void collision(CollisionGameObject object) {
        //do nothing when collided with
        //unless we want to break this wall....
    }
}
