package Server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerApp extends Application {

    public Server server;

    public Stage primaryStage;
    public Scene serverScene;
    public Pane basePane;
    public TextArea log;


    @Override
    public void start(Stage primaryStage) throws Exception {
        buildServerPanel(primaryStage);
    }

    private void buildServerPanel(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Pane pane = new Pane();
        this.basePane = pane;
        TextArea log = new TextArea();
        this.log = log;
        ScrollPane scrollPane = new ScrollPane(log);
        basePane.getChildren().add(0,scrollPane);
        Scene scene = new Scene(basePane, 450, 200);
        this.serverScene = scene;
        primaryStage.setTitle("Game Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setServer(Server server){
        this.server = server;
    }


}
