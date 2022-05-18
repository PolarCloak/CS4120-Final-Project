package Window.Game.Panes;

import Game.Inventory.GUILocation;
import Window.Game.GUI.GUI;
import Window.Menu.Stages.MainMenu;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class RenderingPane extends Pane {

    public static final double WIDTH = MainMenu.WIDTH - GUI.WIDTH;
    public static final double HEIGHT = MainMenu.HEIGHT - TextPane.HEIGHT;

    public RenderingPane(){
        //basic GUI setup
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMaxHeight(HEIGHT);

        this.setRenderAreaBackground();

    }

    private void setRenderAreaBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("assets/img/stage/background-main.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
    }

    public void draw(ImageView image, GUILocation location){
        image.setTranslateX(location.x);
        image.setTranslateY(location.y);
        if(this.getChildren().contains(image)){
            this.getChildren().remove(image);
        }
        this.getChildren().add(image);
    }

    public void clearPane(){
        Platform.runLater(() -> this.getChildren().remove(0,this.getChildren().size()));

    }

    public GUILocation getMiddle(){
        GUILocation loc = new GUILocation(WIDTH/2, HEIGHT/2);
        return loc;
    }

    public void draw(Pane health, GUILocation location){
        health.setTranslateX(location.x);
        health.setTranslateY(location.y);
        if(this.getChildren().contains(health)){
            this.getChildren().remove(health);
        }
        this.getChildren().add(health);
    }


}
