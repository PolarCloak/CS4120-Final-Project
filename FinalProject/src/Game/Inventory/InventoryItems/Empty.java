package Game.Inventory.InventoryItems;

import Window.Game.GUI.InvItemView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Empty extends InventoryItem{


    public Empty() {
        super(0, "Empty", 1, MaxStackSize.MSS1);
    }

    @Override
    public InvItemView getImage(int slot) {
        Image image = new Image("assets/img/gui/inventory/items/empty.png");
        return new InvItemView(image,slot);
    }
}
