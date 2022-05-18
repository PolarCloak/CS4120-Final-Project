package Window.Game.GUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class invSlot extends Pane {

    public static final double slotSize = 59;
    private int slotNumber;

    public invSlot(int slotNumber){
        this.slotNumber = slotNumber;
        this.setId("invSlot");

        this.setHeight(slotSize);
        this.setMaxHeight(slotSize);
        this.setMinHeight(slotSize);

        this.setWidth(slotSize);
        this.setMaxWidth(slotSize);
        this.setMinWidth(slotSize);

        setBorderColor("grey");

    }

    public void setBorderColor(String color){
        this.setStyle(
                "-fx-border-width: 1.5;" +
                "-fx-border-color: " + color + ";"
        );
    }

    public int getSlotNumber(){
        return this.slotNumber;
    }

}
