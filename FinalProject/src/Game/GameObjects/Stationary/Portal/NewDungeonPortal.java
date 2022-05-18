package Game.GameObjects.Stationary.Portal;

import Game.GameObjects.Interfaces.Collision;
import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.GameObject;
import Game.Player.Player;
import javafx.scene.image.Image;

public class NewDungeonPortal extends CollisionGameObject{

    public NewDungeonPortal(Location loc) {
        super(loc);
        this.id = 6;
        this.size = new Size(100,100);
        this.changeImage(new Image("assets/img/world/structure/door/door4.png"));
    }

    @Override
    public void collision(CollisionGameObject object) {

    }
}
