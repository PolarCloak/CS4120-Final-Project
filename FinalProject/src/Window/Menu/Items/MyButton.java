package Window.Menu.Items;

import Window.Menu.Handlers.MainMenuHandler;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MyButton extends Button{

    public MyButton(String name, EventHandler handler){
        this.setText(name);
        this.setId("menuButton");
        this.setBackground(Background.EMPTY);
        this.setEventHandler(EventType.ROOT, handler);
    }

}
