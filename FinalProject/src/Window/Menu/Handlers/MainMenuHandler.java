package Window.Menu.Handlers;

import Window.Menu.Panes.*;
import Window.Menu.Stages.ConfirmExit;
import Window.Menu.Items.MyButton;
import javafx.event.Event;
import javafx.stage.Stage;

public class MainMenuHandler extends BaseHandler {


    public MainMenuHandler(Stage primaryStage){
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
                case "New Game":
                    if(eventType == "ACTION"){
                        rootPane.setNewGame();
                    }
                    break;
                case "Load Game":
                    if(eventType == "ACTION"){
                        rootPane.setLoadGame();
                    }
                    break;
                case "Join Game":
                    if(eventType == "ACTION"){
                        rootPane.setJoinGame();
                    }
                    break;
                case "Options":
                    if(eventType == "ACTION"){
                        rootPane.setOptions();
                    }
                    break;
                case "Exit":
                    if(eventType == "ACTION"){
                        new ConfirmExit(super.primaryStage);
                    }
                    break;

            }

        }
    }
}
