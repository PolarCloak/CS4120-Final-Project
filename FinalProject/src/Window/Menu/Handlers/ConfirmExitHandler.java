package Window.Menu.Handlers;

import Window.Menu.Items.MyButton;
import Window.Menu.Panes.BasePane;
import javafx.event.Event;
import javafx.stage.Stage;

public class ConfirmExitHandler extends BaseHandler {


    public ConfirmExitHandler(Stage primaryStage){
        super(primaryStage);
    }

    @Override
    public void handle(Event event) {
        super.handle(event);
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if (source instanceof MyButton) {
            MyButton button = (MyButton) source;
            switch (button.getText()) {
                case "Confirm":
                    if (eventType == "ACTION") {
                        primaryStage.close();
                    }
                    break;
                case "Cancel":
                    if (eventType == "ACTION") {
                        button.getScene().getWindow().hide();
                    }
                    break;

            }
        }
    }
}
