package Game.Inventory;

import Game.Inventory.InventoryItems.Empty;
import Game.Inventory.InventoryItems.InventoryItem;
import Game.Inventory.InventoryItems.Items.ChestItem;
import Game.Player.Player;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Inventory implements Serializable {

    public static final int SIZE = 28;
    public InventoryItem[] items;

    public Inventory(){
        items = new InventoryItem[SIZE];
        for(int i =0; i<items.length;i++){
            items[i] = new Empty();
        }
    }

    public Inventory(InventoryItem[] items){
        if(items.length == SIZE){
            this.items = items;
        }
        else{
            this.items = new InventoryItem[SIZE];
        }
    }

    public boolean addItem(InventoryItem item){
        for(int i=0; i<SIZE; i++){
            if(items[i] instanceof Empty){
                return addItem(item, i);
            }
        }
        return false;
    }

    public boolean addItem(InventoryItem item, int slotNumber){
        if(items[slotNumber] instanceof Empty){
            this.items[slotNumber] = item;
            return true;
        }
        return false;
    }

    public void switchLocations(int slot1, int slot2){
        InventoryItem item1 = items[slot1];
        items[slot1] = items[slot2];
        items[slot2] = item1;
    }

    public void slotAction(int slotNumber){
        if(items[slotNumber] instanceof Empty){
            return;
        }
        if(items[slotNumber] instanceof ChestItem){
            ChestItem chest = (ChestItem) items[slotNumber];
            InventoryItem[] newItems = chest.generateLoot();
            for(InventoryItem item : newItems){
                this.addItem(item);
            }
            items[slotNumber] = new Empty();
        }
    }

}
