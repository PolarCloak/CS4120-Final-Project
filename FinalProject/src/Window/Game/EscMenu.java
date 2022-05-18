package Window.Game;

import Game.Game;
import Window.Menu.Handlers.ConfirmExitHandler;
import Window.Menu.Items.MyButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EscMenu extends Stage{

    public static final double HEIGHT = 300;
    public static final double WIDTH = 200;

    Stage primaryStage;
    Scene popupScene;
    Pane basePane;

    EscMenuHandler handler;

    Game game;

    MyButton btnSave;
    MyButton btnExit;
    MyButton btnCancel;

    public EscMenu(Stage primaryStage, Game game, ArrayList<KeyCode> keysPressed){
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);

        this.game = game;

        this.primaryStage = primaryStage;
        this.handler = new EscMenuHandler(primaryStage, game, keysPressed);
        this.setResizable(false);

        //build the scene and pane off of this stage
        basePane = new Pane();
        this.buildPane();
        this.popupScene = new Scene(basePane,WIDTH,HEIGHT);

        popupScene.getStylesheets().add("assets/PopupWindowStyles.css");
        this.setTitle("Menu");
        this.setScene(popupScene);
        this.show();
    }

    private void buildPane(){
        btnSave = new MyButton("Save", handler);
        btnExit = new MyButton("Exit", handler);
        btnCancel = new MyButton("Cancel", handler);

        //vbox
        VBox vBox = new VBox(10);

        Label titleText = new Label("Menu");
        titleText.setId("text");
        vBox.getChildren().add(titleText);


        vBox.getChildren().addAll(btnSave, btnExit, btnCancel);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.setMinWidth(WIDTH);
        vBox.setMinHeight(HEIGHT);
        vBox.setAlignment(Pos.TOP_CENTER);

        basePane.getChildren().add(vBox);
        basePane.setId("popupWindow");
    }




}
