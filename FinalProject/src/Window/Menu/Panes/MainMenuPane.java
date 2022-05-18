package Window.Menu.Panes;

import Window.Menu.Handlers.MainMenuHandler;
import Window.Menu.Items.MyTitle;
import Window.Menu.Items.MyButton;
import Window.Menu.Stages.MainMenu;
import javafx.scene.layout.VBox;

public abstract class MainMenuPane{

    public static void setMainMenuPane(BasePane bp){
        setMainMenuTitle(bp);
        setMainMenuButtons(bp);
    }

    protected static void setMainMenuTitle(BasePane bp){
        MyTitle title = new MyTitle("Decked Out");
        BasePane.centerTitle(title);
        bp.getChildren().add(title);
    }

    protected static void setMainMenuButtons(BasePane bp){
        VBox centerPane = new VBox();
        centerPane.getChildren().addAll(
                new MyButton("New Game", bp.handler),
                new MyButton("Load Game", bp.handler),
                new MyButton("Join Game", bp.handler),
                new MyButton("Options", bp.handler),
                new MyButton("Exit", bp.handler)
        );
        centerPane.setTranslateX(MainMenu.WIDTH / 3.5);
        centerPane.setTranslateY(MainMenu.HEIGHT / 2.5);
        centerPane.setSpacing(bp.buttonSpacing);
        bp.getChildren().add(centerPane);
    }
}
