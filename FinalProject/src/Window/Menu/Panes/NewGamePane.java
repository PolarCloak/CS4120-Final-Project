package Window.Menu.Panes;

import Window.Menu.Handlers.BaseHandler;
import Window.Menu.Items.MyButton;
import Window.Menu.Items.MyTextField;
import Window.Menu.Items.MyTitle;
import Window.Menu.Stages.MainMenu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class NewGamePane{

    public static void setNewGamePane(BasePane bp){
        setNewGameTitle(bp);
        setNewGameButtons(bp);
    }

    protected static void setNewGameTitle(BasePane bp){
        MyTitle title = new MyTitle("New Game");
        BasePane.centerTitle(title);
        bp.getChildren().add(title);
    }

    protected static void setNewGameButtons(BasePane bp){
        VBox centerPane = new VBox();
        centerPane.getChildren().add(new MyTextField("Player Name"));
        centerPane.getChildren().addAll(
                new MyButton("Start", bp.handler),
                new MyButton("Cancel", bp.handler)
        );

        centerPane.setTranslateX(MainMenu.WIDTH / 3.5);
        centerPane.setTranslateY(MainMenu.HEIGHT / 2.5);
        centerPane.setSpacing(BasePane.buttonSpacing);
        bp.getChildren().add(centerPane);
    }

}
