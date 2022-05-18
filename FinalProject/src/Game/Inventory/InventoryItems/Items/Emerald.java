package Game.Inventory.InventoryItems.Items;

import Game.Generator.Chance;
import Game.Inventory.InventoryItems.InventoryItem;
import Game.Inventory.InventoryItems.MaxStackSize;
import Window.Game.GUI.InvItemView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Emerald extends InventoryItem {


    public Emerald() {
        super(2003, "Emerald", 1, MaxStackSize.MSS64);
    }

    @Override
    public InvItemView getImage(int slot) {
        Image image = new Image("assets/img/gui/inventory/items/emerald.png");
        InvItemView iv = new InvItemView(image,slot);
        return iv;
    }
}
