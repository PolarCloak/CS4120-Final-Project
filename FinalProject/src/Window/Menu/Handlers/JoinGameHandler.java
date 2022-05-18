package Window.Menu.Handlers;

import Window.Menu.Items.MyButton;
import Window.Menu.Panes.BasePane;
import Window.Menu.Panes.MainMenuPane;
import Window.Menu.Panes.NewGamePane;
import Window.Menu.Panes.OptionsPane;
import Window.Menu.Stages.ConfirmExit;
import javafx.event.Event;
import javafx.stage.Stage;

public class JoinGameHandler extends BaseHandler{
    public JoinGameHandler(Stage primaryStage) {
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
                case "Join":
                    if(eventType == "ACTION"){

                    }
                    break;
                case "Cancel":
                    if(eventType == "ACTION"){
                        rootPane.setMainMenu();
                    }
                    break;

            }

        }
    }
}
