package Window.Menu.Panes;

import Window.Menu.Items.MyButton;
import Window.Menu.Items.MyTitle;
import Window.Menu.Stages.MainMenu;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.io.File;

public abstract class LoadGamePane{

    public static void setLoadGamePane(BasePane bp){
        setLoadGameTitle(bp);
        setLoadGameButtons(bp);
    }

    protected static void setLoadGameTitle(BasePane bp){
        MyTitle title = new MyTitle("Load Game");
        BasePane.centerTitle(title);
        bp.getChildren().add(title);
    }

    protected static void setLoadGameButtons(BasePane bp){
        VBox centerPane = new VBox();
        centerPane.getChildren().addAll(
                getFiles(),
                new MyButton("Load", bp.handler),
                new MyButton("Cancel", bp.handler)
        );
        centerPane.setTranslateX(MainMenu.WIDTH / 3.5);
        centerPane.setTranslateY(MainMenu.HEIGHT / 2.5);
        centerPane.setSpacing(bp.buttonSpacing);
        bp.getChildren().add(centerPane);
    }

    protected static ComboBox getFiles(){
        ComboBox<String> loadGames = new ComboBox<>();

        File direct = new File("savefiles");
        for(String path : direct.list()){
            loadGames.getItems().add(path);
        }
        if(loadGames.getItems().size() != 0){
            loadGames.setValue(loadGames.getItems().get(0));
        }
        return loadGames;
    }
}
