package Game.GameObjects.Stationary;

import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.Inventory.GUILocation;
import Window.Game.Panes.RenderingPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

    public long id;
    public Location location;
    public GUILocation guiLocation;
    public Size size;
    public ImageView image;

    public GameObject(Location loc) {
        this.id = -1; // ID not set is -1
        this.location = loc;
        this.guiLocation = new GUILocation();
        this.size = new Size();
        this.image = new ImageView(new Image("assets/img/empty.png"));

    }

    public void render(RenderingPane pane, Location playerLoc, GUILocation playerGUILoc){
        updateImageSize();
        this.setGUILocation(playerLoc,playerGUILoc);
        pane.draw(image, this.guiLocation);
    }

    private void updateImageSize(){
        this.image.setFitHeight(size.y);
        this.image.setFitWidth(size.x);
    }
    public void changeImage(Image image){
        this.image.setImage(image);
    }

    public void setGUILocation(Location playerLoc, GUILocation playerGUILoc){
        GUILocation guiLoc = new GUILocation();
        if(playerLoc.x>=this.location.x){
            guiLoc.x = playerGUILoc.x - Math.abs(playerLoc.x-this.location.x);
        }
        if(playerLoc.x<this.location.x){
            guiLoc.x = playerGUILoc.x + Math.abs(playerLoc.x-this.location.x);
        }

        if(playerLoc.y>=this.location.y){
            guiLoc.y = playerGUILoc.y + Math.abs(playerLoc.y-this.location.y);
        }
        if(playerLoc.y<this.location.y){
            guiLoc.y = playerGUILoc.y - Math.abs(playerLoc.y-this.location.y);
        }

        this.guiLocation = guiLoc;
    }

}