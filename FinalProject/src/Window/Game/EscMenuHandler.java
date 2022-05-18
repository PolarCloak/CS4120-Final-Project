package Window.Game;

import Game.Game;
import Window.Menu.Handlers.BaseHandler;
import Window.Menu.Items.MyButton;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class EscMenuHandler extends BaseHandler {

    Game game;
    ArrayList<KeyCode> keysPressed;

    public EscMenuHandler(Stage primaryStage, Game game, ArrayList<KeyCode> keysPressed){
        super(primaryStage);
        this.game = game;
        this.keysPressed = keysPressed;
    }

    @Override
    public void handle(Event event) {
        super.handle(event);
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if (source instanceof MyButton) {
            MyButton button = (MyButton) source;
            switch (button.getText()) {
                case "Save":
                    if (eventType == "ACTION") {
                        this.save();
                    }
                    break;
                case "Exit":
                    if (eventType == "ACTION") {
                        this.save();
                        game.server.dispose();
                        primaryStage.close();
                    }
                    break;
                case "Cancel":
                    if (eventType == "ACTION") {
                        while(keysPressed.contains(KeyCode.ESCAPE)){
                            keysPressed.remove(KeyCode.ESCAPE);
                        }
                        button.getScene().getWindow().hide();
                    }
                    break;

            }
        }
    }

    private void save(){
        try {
            File direct = new File("savefiles/" + game.getGameName());
            direct.mkdir();

            File file = new File("savefiles/" + game.getGameName() + "/name.dat");
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(game.getPlayer().name);

            f = new FileOutputStream(new File("savefiles/" + game.getGameName() + "/inventory.dat"));
            o = new ObjectOutputStream(f);
            o.writeObject(game.getPlayer().inventory);

            f = new FileOutputStream(new File("savefiles/" + game.getGameName() + "/health.dat"));
            o = new ObjectOutputStream(f);
            o.writeObject(game.getPlayer().health.health);

            game.gamePane.textPane.textArea.appendText("Game has been saved.\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}