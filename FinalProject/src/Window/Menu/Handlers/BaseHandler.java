package Window.Menu.Handlers;

import Window.Menu.Items.MyButton;
import Window.Menu.Panes.BasePane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public abstract class BaseHandler implements EventHandler<Event> {

    public Stage primaryStage;

    public BaseHandler(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    private void highlightButton(MyButton myButton){
        Bloom bloom = new Bloom();
        bloom.setThreshold(0);
        Glow glow = new Glow();
        glow.setLevel(100);
        myButton.setEffect(bloom);
        myButton.setEffect(glow);
    }

    private void unhighlightButton(MyButton myButton){
        myButton.setEffect(null);
    }

    private void mouseEnteredSound() {
        Media hoverSound = new Media("File:///C:/Users/david/IdeaProjects/FinalProject/src/assets/aud/click.mp3");
        MediaPlayer hoverSoundPlayer = new MediaPlayer(hoverSound);
        hoverSoundPlayer.setAutoPlay(true);
        hoverSoundPlayer.setCycleCount(1);
    }

    @Override
    public void handle(Event event) {
        String eventType = event.getEventType().toString();
        Object source = event.getSource();
        if(source instanceof MyButton) {
            MyButton button = (MyButton) source;
            if (eventType == "MOUSE_ENTERED") {
                highlightButton(button);
                mouseEnteredSound();
            }
            if (eventType == "MOUSE_EXITED") {
                unhighlightButton(button);
            }
        }
    }
}
