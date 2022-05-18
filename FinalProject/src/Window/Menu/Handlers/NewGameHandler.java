package Window.Menu.Handlers;

import Client.Client;
import Game.GameObjects.Location;
import Game.Player.Player;
import Server.ServerApp;
import Game.Game;
import Server.Server;
import Window.Game.Panes.GamePane;
import Window.Menu.Items.MyTextField;
import Window.Menu.Panes.BasePane;
import Window.Menu.Items.MyButton;
import Window.Menu.Stages.MainMenu;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewGameHandler extends BaseHandler {

    public NewGameHandler(Stage primaryStage){
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
                case "Start":
                    if(eventType == "ACTION"){
                        //get the text from the field first
                        String input = getFieldText(rootPane);

                        GamePane gamePane = new GamePane();
                        Scene gameScene = new Scene(gamePane,MainMenu.WIDTH,MainMenu.HEIGHT);
                        Game newGame = new Game(input,primaryStage,gameScene,gamePane);
                        Player newPlayer = new Player(input);
                        newGame.lobby.addObject(newPlayer);
                        Server server = new Server(9090, newGame);
                        newGame.setServer(server);
                        ServerApp app = new ServerApp();
                        app.setServer(server);
                        Client client = new Client("localhost", 9090, newPlayer);
                        newPlayer.setClient(client);
                        rootPane.backgroundMedia.dispose();
                        primaryStage.setScene(gameScene);
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

    private String getFieldText(BasePane rootPane) {
        VBox centerPane = null;
        MyTextField mtf = null;
        for(int i = 0; i<rootPane.getChildren().size(); i++){
            if(rootPane.getChildren().get(i) instanceof VBox){
                centerPane = (VBox) rootPane.getChildren().get(i);
            }
        }
        if(centerPane == null){
            return "Default";
        }
        for(int i = 0; i<centerPane.getChildren().size(); i++){
            if(centerPane.getChildren().get(i) instanceof MyTextField){
                mtf = (MyTextField) centerPane.getChildren().get(i);
            }
        }
        if(mtf == null){
            return "Default";
        }
        if(mtf.getText() != null){
            return mtf.getText();
        }
        else{
            return "Default";
        }
    }
}
