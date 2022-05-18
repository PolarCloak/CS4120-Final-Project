package Window.Menu.Panes;

import Client.Client;
import Window.Menu.Handlers.*;
import Window.Menu.Items.MyTitle;
import Window.Menu.Stages.MainMenu;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class BasePane extends Pane {

    private static final String backgroundImageURL = "File:///C:/Users/david/IdeaProjects/FinalProject/src/assets/vid/menu_background.mp4";
    protected static final int buttonSpacing = 6;
    protected static final int buttonFontSize = 30;
    public BaseHandler handler;
    public MediaPlayer backgroundMedia;

    public BasePane(BaseHandler handler){
        this.handler = handler;
        setBackground();
        MainMenuPane.setMainMenuPane(this);
    }

    public void clearPane(){
//        for(int i=0;i<this.getChildren().size();i++){
//            System.out.println("Child#"+i+": "+this.getChildren().get(i));
//        }
        this.getChildren().remove(1,this.getChildren().size());
    }

    public void clearPaneBackground(){
        this.getChildren().remove(0);
    }

    private void setBackground(){
        Media media = new Media(backgroundImageURL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(MainMenu.HEIGHT);

//        mediaView.setFitWidth(MainMenu.WIDTH);
        this.getChildren().add(0,mediaView);
        this.backgroundMedia = mediaPlayer;
    }

    public static void centerTitle(MyTitle title){
        title.setTranslateX(MainMenu.WIDTH / 2 - 270);
        title.setTranslateY(MainMenu.HEIGHT / 6);
    }

    public void setMainMenu(){
        clearPane();
        this.handler = new MainMenuHandler(handler.primaryStage);
        MainMenuPane.setMainMenuPane(this);
    }
    public void setOptions(){
        clearPane();
        this.handler = new OptionsHandler(handler.primaryStage);
        OptionsPane.setOptionsPane(this);
    }
    public void setJoinGame() {
        clearPane();
        this.handler = new JoinGameHandler(handler.primaryStage);
        JoinGamePane.setJoinGamePane(this);
    }
    public void setLoadGame() {
        clearPane();
        this.handler = new LoadGameHandler(handler.primaryStage);
        LoadGamePane.setLoadGamePane(this);
    }
    public void setNewGame() {
        clearPane();
        this.handler = new NewGameHandler(handler.primaryStage);
        NewGamePane.setNewGamePane(this);
    }
}
