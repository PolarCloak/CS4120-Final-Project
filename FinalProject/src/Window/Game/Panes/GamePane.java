package Window.Game.Panes;

import Window.Game.GUI.GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GamePane extends Pane{

    public static final double topBorderThickness = 13;

    public GUI gui;
    public RenderingPane renderingPane;
    public TextPane textPane;

    public GamePane(){
        gui = new GUI();
        textPane = new TextPane();
        renderingPane = new RenderingPane();
        this.getChildren().add(renderingPane);
        this.getChildren().add(textPane);
        this.getChildren().add(gui);
        startGameBackgroundSound();
    }

    public static void addSideBorder(Pane pane){
        Image leftImg = new Image("assets/img/stage/border-image-left.png");
        ImageView leftView = new ImageView(leftImg);
        leftView.setFitHeight(pane.getHeight());
        leftView.setFitWidth(leftImg.getWidth());
        pane.getChildren().add(leftView);

        Image rightImg = new Image("assets/img/stage/border-image-right.png");
        ImageView rightView = new ImageView(rightImg);
        rightView.setFitHeight(pane.getHeight());
        rightView.setFitWidth(rightImg.getWidth());
        rightView.setX(pane.getWidth()-rightView.getFitWidth());
        pane.getChildren().add(rightView);
    }

    public static void addTopBorder(Pane pane){
        Image topImg = new Image("assets/img/stage/large_break.jpg");
        ImageView topView = new ImageView(topImg);
        topView.setFitHeight(topBorderThickness);
        topView.setFitWidth(pane.getWidth());
        pane.getChildren().add(topView);


    }

    private void startGameBackgroundSound(){
//        Media backgroundSound = new Media("File:///C:/Users/david/IdeaProjects/FinalProject/src/assets/aud/oof.mp3");
//        MediaPlayer soundPlayer = new MediaPlayer(backgroundSound);
//        soundPlayer.setAutoPlay(true);
//        soundPlayer.setCycleCount(1);
    }


}
