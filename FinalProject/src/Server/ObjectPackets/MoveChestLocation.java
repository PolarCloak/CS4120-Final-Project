package Server.ObjectPackets;

import Game.Inventory.InventoryItems.Items.ChestItem;

import java.io.Serializable;

public class MoveChestLocation implements TextOutput, Serializable {

    public ChestItem chest;

    public MoveChestLocation(ChestItem chest){
        this.chest = chest;
    }

    @Override
    public String getTextOutput() {
        return "The chest has been found, and has now been moved.";
    }
}
