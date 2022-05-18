package Game.GameObjects.Stationary.Structure.Wall;

import Game.GameObjects.Interfaces.Collision;
import Game.GameObjects.Interfaces.Impassable;
import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class WallCenter extends CollisionGameObject implements Impassable {

    public WallCenter(Location loc) {
        super(loc);
        this.id = 5;
        this.size = new Size();
        this.changeImage(new Image("assets/img/world/structure/wall/wallmid.png"));
    }


    @Override
    public void collision(CollisionGameObject object) {

    }
}
