package Window.Menu.Handlers;

import Client.Client;
import Game.Game;
import Game.Inventory.Inventory;
import Game.Player.Health;
import Game.Player.Player;
import Server.Server;
import Server.ServerApp;


import Window.Game.Panes.GamePane;
import Window.Menu.Items.MyButton;
import Window.Menu.Panes.BasePane;
import Window.Menu.Stages.MainMenu;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class LoadGameHandler extends BaseHandler{
    public LoadGameHandler(Stage primaryStage) {
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
                case "Load":
                    if(eventType == "ACTION"){
                        try {
                            String gameName = this.getGameName(rootPane);
                            if(gameName == null){
                                break;
                            }
                            File direct = new File("savefiles/" + gameName);
                            if(!direct.isDirectory()){
                                break;
                            }
                            Player player = loadPlayer(gameName);
                            GamePane gamePane = new GamePane();
                            Scene gameScene = new Scene(gamePane, MainMenu.WIDTH,MainMenu.HEIGHT);
                            Game newGame = new Game(gameName,primaryStage,gameScene,gamePane);
                            newGame.lobby.addObject(player);
                            Server server = new Server(9090, newGame);
                            newGame.setServer(server);
                            ServerApp app = new ServerApp();
                            app.setServer(server);
                            Client client = new Client("localhost", 9090, player);
                            player.setClient(client);
                            rootPane.backgroundMedia.dispose();
                            primaryStage.setScene(gameScene);


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
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

    private String getGameName(BasePane rootPane) {
        VBox centerPane = null;
        ComboBox mcb = null;
        for(int i = 0; i<rootPane.getChildren().size(); i++){
            if(rootPane.getChildren().get(i) instanceof VBox){
                centerPane = (VBox) rootPane.getChildren().get(i);
            }
        }
        if(centerPane == null){
            return null;
        }
        for(int i = 0; i<centerPane.getChildren().size(); i++){
            if(centerPane.getChildren().get(i) instanceof ComboBox){
                mcb = (ComboBox) centerPane.getChildren().get(i);
            }
        }
        if(mcb == null){
            return null;
        }
        if(mcb.getValue() != null){
            return (String) mcb.getValue();
        }
        else{
            return null;
        }
    }

    private Player loadPlayer(String gameName) throws IOException, ClassNotFoundException {
        String name = null;
        Inventory inv = null;
        double health = 0;

        File file = new File("savefiles/" + gameName + "/name.dat");
        FileInputStream f= new FileInputStream(file);
        ObjectInputStream o = new ObjectInputStream(f);
        name = (String) o.readObject();

        file = new File("savefiles/" + gameName + "/inventory.dat");
        f= new FileInputStream(file);
        o = new ObjectInputStream(f);
        inv = (Inventory) o.readObject();

        file = new File("savefiles/" + gameName + "/health.dat");
        f= new FileInputStream(file);
        o = new ObjectInputStream(f);
        health = (double) o.readObject();

        Player player = new Player(name, inv, health);

        return player;
    }
}
