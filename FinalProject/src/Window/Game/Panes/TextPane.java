package Window.Game.Panes;

import Window.Game.GUI.GUI;
import Window.Menu.Stages.MainMenu;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class TextPane extends Pane{

    public static final double WIDTH = MainMenu.WIDTH - GUI.WIDTH;
    public static final double HEIGHT = 300;
    public TextArea textArea;

    public TextPane(){
        //basic GUI setup
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setMinWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMaxHeight(HEIGHT);
        this.setTranslateY(MainMenu.HEIGHT-this.HEIGHT);
        this.setTextAreaBackground();

        this.textArea = new TextArea();
        textArea.setEditable(false);
        setTextArea();
        this.getChildren().add(textArea);

        textArea.appendText("Game Started.\n");
    }

    private void setTextAreaBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("assets/img/stage/background1.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        GamePane.addSideBorder(this);
        GamePane.addTopBorder(this);
    }

    private void setTextArea(){
        textArea.setMinWidth(WIDTH);
        textArea.setMaxWidth(WIDTH);
        textArea.setPrefWidth(WIDTH);
        textArea.setMinHeight(HEIGHT - GamePane.topBorderThickness);
        textArea.setMaxHeight(HEIGHT - GamePane.topBorderThickness);
        textArea.setPrefHeight(HEIGHT - GamePane.topBorderThickness);
        textArea.setTranslateY(GamePane.topBorderThickness);
        textArea.setStyle(
                "-fx-opacity: 0.2;" +
                "-fx-font-size: 20;" +
                "-fx-font-family: 'Nova Flat';" +
                "-fx-text-fill: rgb(7,5,5);"
        );
        textArea.setBackground(Background.EMPTY);

    }


}
