package Window.Menu.Stages;

import Window.Menu.Handlers.ConfirmExitHandler;
import Window.Menu.Items.MyButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmExit extends Stage{

    public static final double HEIGHT = 180;
    public static final double WIDTH = 400;

    Stage primaryStage;
    Scene popupScene;
    Pane basePane;

    ConfirmExitHandler handler;

    MyButton btnYes;
    MyButton btnNo;

    public ConfirmExit(Stage primaryStage){
        //add this popup onto the first stage
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        this.primaryStage = primaryStage;
        this.handler = new ConfirmExitHandler(primaryStage);
        this.setResizable(false);

        //build the scene and pane off of this stage
        basePane = new Pane();
        this.buildPane();
        this.popupScene = new Scene(basePane,WIDTH,HEIGHT);


        popupScene.getStylesheets().add("assets/PopupWindowStyles.css");
        this.setTitle("Confirm Exit");
        this.setScene(popupScene);
        this.show();
    }

    private void buildPane(){
        btnYes = new MyButton("Confirm", handler);
        btnNo = new MyButton("Cancel", handler);

        //vbox
        VBox vBox = new VBox(40);

        Label confirmText = new Label("Are you sure you would like to exit?");
        confirmText.setId("text");
        vBox.getChildren().add(confirmText);

        HBox buttons = new HBox(40);
        buttons.getChildren().addAll(btnYes, btnNo);
        buttons.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().add(buttons);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(7,0,0,0));

        basePane.getChildren().add(vBox);
        basePane.setId("popupWindow");
    }




}
