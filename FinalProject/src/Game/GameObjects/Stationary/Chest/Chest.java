package Game.GameObjects.Stationary.Chest;


import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.Generator.Chance;
import Game.Inventory.InventoryItems.InventoryItem;
import Game.Inventory.InventoryItems.Items.ChestItem;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Chest extends CollisionGameObject implements Serializable {



    public Chest(Location loc) {
        super(loc);
        this.id = 7;
        this.size = new Size(50,50);
        this.changeImage(new Image("assets/img/world/structure/chest/chest2.png"));
    }


    @Override
    public void collision(CollisionGameObject object) {

    }

    public ChestItem toInventoryItem(){
        return new ChestItem();
    }


}
