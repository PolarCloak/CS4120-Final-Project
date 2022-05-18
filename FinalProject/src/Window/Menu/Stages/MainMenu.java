package Window.Menu.Stages;

import Client.Client;
import Window.Menu.Handlers.MainMenuHandler;
import Window.Menu.Panes.BasePane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenu {

    //given this
    private static final String gameTitle = "Decked Out";
    public static final double HEIGHT = 1000;
    public static final double WIDTH = 1280;

    Client client;
    Stage primaryStage;
    Scene mainMenuScene;
    BasePane rootPane;


    public MainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        rootPane = new BasePane(new MainMenuHandler(primaryStage));
        mainMenuScene = new Scene(rootPane, WIDTH, HEIGHT);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);

        importStylesheets(mainMenuScene);

        primaryStage.setScene(mainMenuScene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(gameTitle);
        primaryStage.getIcons().add(new Image("assets/img/stage/icon2.png"));
        primaryStage.show();

    }


    public static void importStylesheets(Scene scene) {
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Nova+Flat");
        scene.getStylesheets().add("assets/MainMenuStyles.css");
    }
}
