package Game.Inventory.InventoryItems;

import Window.Game.GUI.InvItemView;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class InventoryItem implements Serializable {

    public long id;
    public String name;
    public int stackSize;
    public MaxStackSize maxStackSize;

    public InventoryItem(long id, String name, int stackSize, MaxStackSize maxStackSize){
        this.id = id;
        this.name = name;
        this.stackSize = stackSize;
        this.maxStackSize = maxStackSize;
    }

    public abstract InvItemView getImage(int slot);




}
