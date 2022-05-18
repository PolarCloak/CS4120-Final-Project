package Client;

import Window.Menu.Stages.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientApp extends Application {

    Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainMenu menu = new MainMenu(primaryStage);
        primaryStage.setOnCloseRequest(event -> primaryStage.close());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setClient(Client client){
        this.client = client;
    }
}
