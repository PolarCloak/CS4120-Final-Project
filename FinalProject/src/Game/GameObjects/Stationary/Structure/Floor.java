package Game.GameObjects.Stationary.Structure;

import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.GameObject;
import javafx.scene.image.Image;

public class Floor extends GameObject {

    public Floor(Location loc) {
        super(loc);
        this.id = 2;
        this.size = new Size(100,100);
        this.changeImage(new Image("assets/img/world/structure/floor/rocktile.jpg"));
    }

}
