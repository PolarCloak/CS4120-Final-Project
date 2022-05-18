package Window.Menu.Handlers;

import Window.Menu.Panes.BasePane;
import Window.Menu.Panes.MainMenuPane;
import Window.Menu.Items.MyButton;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class OptionsHandler extends BaseHandler {


    public OptionsHandler(Stage primaryStage){
        super(primaryStage);
    }


    public void handle(Event event) {
        super.handle(event);
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if(source instanceof MyButton){
            MyButton button = (MyButton) source;
            BasePane rootPane = (BasePane) button.getParent().getParent();
            switch(button.getText()){
                case "Option 1":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Option 2":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Option 3":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Toggle A":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Toggle B":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Back":
                    if(eventType == "ACTION"){
                        rootPane.setMainMenu();
                    }
                    break;

            }

        }
    }
}
