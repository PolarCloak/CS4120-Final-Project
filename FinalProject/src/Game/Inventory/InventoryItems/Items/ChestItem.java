package Game.Inventory.InventoryItems.Items;

import Game.Generator.Chance;
import Game.Generator.RandomChance;
import Game.Inventory.Inventory;
import Game.Inventory.InventoryItems.InventoryItem;
import Game.Inventory.InventoryItems.MaxStackSize;
import Window.Game.GUI.InvItemView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class ChestItem extends InventoryItem implements Serializable {

    public ChestItem() {
        super(2002, "Chest", 1, MaxStackSize.MSS1);
    }

    @Override
    public InvItemView getImage(int slot) {
        Image image = new Image("assets/img/world/structure/chest/chest2.png");
        InvItemView iv = new InvItemView(image,slot);
        return iv;
    }

    public InventoryItem[] generateLoot(){
        Chance[] chances = new Chance[]{new Chance(4,5), new Chance(15,100), new Chance(5,100)};
        RandomChance numOfItems = new RandomChance(chances);
        InventoryItem[] items = new InventoryItem[numOfItems.chooseRandom()+1];
        for(int i = 0; i< items.length; i++){
            items[i] = new Emerald();
        }
        return items;
    }
}
