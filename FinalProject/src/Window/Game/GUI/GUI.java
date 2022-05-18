package Window.Game.GUI;

import Game.Inventory.GUILocation;
import Game.Inventory.Inventory;
import Game.Inventory.InventoryItems.Empty;
import Game.Inventory.InventoryItems.InventoryItem;
import Window.Game.Panes.GamePane;
import Window.Menu.Stages.MainMenu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GUI extends Pane {

    public static final double WIDTH = 400;
    public static final double HEIGHT = MainMenu.HEIGHT;
    public static final double inventoryPanelBufferLeft = 20;
    public static final double inventoryPanelBufferRight = 25;
    public static final double inventoryPanelBufferTop = 20;
    public static final double inventoryPanelBufferBottom = 40;
    public static final double internalInvBufferTop = 60;
    public static final double internalInvBufferLeft = 68;
    public static final double slotSize = 59;
    public static final double slotSeperation = 5;
    public static final double slotThickness = 4;
    public static final double itemSlotSize = slotSize - (slotThickness*2);


    public GUI() {
        //basic GUI setup
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMaxHeight(HEIGHT);
        this.setTranslateX(MainMenu.WIDTH-this.WIDTH);
        this.setGUIBackground();

        //set up the inventory area
        this.placeInventoryBackground();
        this.fillWithEmptyItems();

    }

    private void setGUIBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("assets/img/stage/background-main.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        GamePane.addSideBorder(this);
    }

    private void placeInventoryBackground(){
        Image invImage = new Image("assets/img/gui/inventory/inventory-background.png");
        ImageView imageView = new ImageView(invImage);
        imageView.setFitWidth(this.WIDTH - inventoryPanelBufferLeft - inventoryPanelBufferRight);
        imageView.setFitHeight((this.HEIGHT/3 * 2) - inventoryPanelBufferTop - inventoryPanelBufferBottom);
        imageView.setTranslateX(this.WIDTH - imageView.getFitWidth() -inventoryPanelBufferRight);
        imageView.setTranslateY(this.HEIGHT - imageView.getFitHeight() -inventoryPanelBufferBottom);
        this.getChildren().add(0,imageView);

        //prep for making the inv layout
        HBox rows = new HBox();
        invSlot slot = new invSlot(0);
        for(int x=0; x<Inventory.SIZE/7; x++){
            VBox col = new VBox();

            for(int y=0; y<Inventory.SIZE/4; y++){
                slot = new invSlot( (4*y) + x);
                col.getChildren().add(slot);
                col.setSpacing(slotSeperation);
            }
            col.setTranslateX(internalInvBufferLeft + (x*slotSeperation));
            col.setTranslateY(imageView.getTranslateY() + internalInvBufferTop);
            rows.getChildren().add(col);
        }
        this.getChildren().add(1,rows);

    }

    public void updateInventory(Inventory inv){
        for(int i = 0; i<this.getChildren().size(); i++){
            if(this.getChildren().get(i) instanceof InvItemView){
                this.getChildren().remove(i);
            }
        }
        for(int i = 0; i<inv.items.length; i++){
            placeInventoryItem(inv.items[i],i);
        }
    }


    private void fillWithEmptyItems(){
        for(int i =0; i<Inventory.SIZE; i++){
            placeInventoryItem(new Empty(), i);
        }
    }

    public void placeInventoryItem(InventoryItem item, int slot){
        GUILocation slotLocation = this.getSlotLocation(slot);
        InvItemView itemView = item.getImage(slot);
        itemView.setFitWidth(itemSlotSize);
        itemView.setFitHeight(itemSlotSize);
        itemView.setTranslateX(slotLocation.x);
        itemView.setTranslateY(slotLocation.y);
        this.getChildren().add(itemView);
    }

    public int getSlotNumber(InvItemView item){
        return item.getSlot();
    }

    public GUILocation getSlotLocation(int slotNumber){
        if(slotNumber >= Inventory.SIZE || slotNumber<0){
            return new GUILocation(-1,-1);
        }
        GUILocation loc = new GUILocation(0,0);
        ImageView imageView = (ImageView) this.getChildren().get(0);
        double baseX = imageView.getTranslateX() + internalInvBufferLeft + slotThickness - (slotSize/3);
        double baseY = imageView.getTranslateY() + internalInvBufferTop + slotThickness;
        double spacing = slotSeperation + slotSize;
        switch(slotNumber){
            case 0:
                loc.set(baseX, baseY);
                break;
            case 1:
                loc.set(baseX + spacing*1, baseY);
                break;
            case 2:
                loc.set(baseX + spacing*2, baseY);
                break;
            case 3:
                loc.set(baseX + spacing*3, baseY);
                break;
            case 4:
                loc.set(baseX, baseY+spacing*1);
                break;
            case 5:
                loc.set(baseX + spacing*1, baseY+spacing*1);
                break;
            case 6:
                loc.set(baseX + spacing*2, baseY+spacing*1);
                break;
            case 7:
                loc.set(baseX + spacing*3, baseY+spacing*1);
                break;
            case 8:
                loc.set(baseX, baseY+spacing*2);
                break;
            case 9:
                loc.set(baseX + spacing*1, baseY+spacing*2);
                break;
            case 10:
                loc.set(baseX + spacing*2, baseY+spacing*2);
                break;
            case 11:
                loc.set(baseX + spacing*3, baseY+spacing*2);
                break;
            case 12:
                loc.set(baseX, baseY+spacing*3);
                break;
            case 13:
                loc.set(baseX + spacing*1, baseY+spacing*3);
                break;
            case 14:
                loc.set(baseX + spacing*2, baseY+spacing*3);
                break;
            case 15:
                loc.set(baseX + spacing*3, baseY+spacing*3);
                break;
            case 16:
                loc.set(baseX, baseY+spacing*4);
                break;
            case 17:
                loc.set(baseX + spacing*1, baseY+spacing*4);
                break;
            case 18:
                loc.set(baseX + spacing*2, baseY+spacing*4);
                break;
            case 19:
                loc.set(baseX + spacing*3, baseY+spacing*4);
                break;
            case 20:
                loc.set(baseX, baseY+spacing*5);
                break;
            case 21:
                loc.set(baseX + spacing*1, baseY+spacing*5);
                break;
            case 22:
                loc.set(baseX + spacing*2, baseY+spacing*5);
                break;
            case 23:
                loc.set(baseX + spacing*3, baseY+spacing*5);
                break;
            case 24:
                loc.set(baseX, baseY+spacing*6);
                break;
            case 25:
                loc.set(baseX + spacing*1, baseY+spacing*6);
                break;
            case 26:
                loc.set(baseX + spacing*2, baseY+spacing*6);
                break;
            case 27:
                loc.set(baseX + spacing*3, baseY+spacing*6);
                break;
        }
        return loc;
    }
}
