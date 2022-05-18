package Window.Menu.Panes;

import Window.Menu.Handlers.BaseHandler;
import Window.Menu.Items.MyButton;
import Window.Menu.Items.MyTitle;
import Window.Menu.Stages.MainMenu;
import javafx.scene.layout.VBox;

public abstract class JoinGamePane{

    public static void setJoinGamePane(BasePane bp){
        setJoinGameTitle(bp);
        setJoinGameButtons(bp);
    }

    protected static void setJoinGameTitle(BasePane bp){
        MyTitle title = new MyTitle("Join Game");
        BasePane.centerTitle(title);
        bp.getChildren().add(title);
    }

    protected static void setJoinGameButtons(BasePane bp){
        VBox centerPane = new VBox();
        centerPane.getChildren().addAll(
                new MyButton("Join", bp.handler),
                new MyButton("Cancel", bp.handler)
        );
        centerPane.setTranslateX(MainMenu.WIDTH / 3.5);
        centerPane.setTranslateY(MainMenu.HEIGHT / 2.5);
        centerPane.setSpacing(bp.buttonSpacing);
        bp.getChildren().add(centerPane);
    }
}
