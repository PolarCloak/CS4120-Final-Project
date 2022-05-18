package Window.Menu.Panes;

import Window.Menu.Items.*;
import Window.Menu.Stages.MainMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public abstract class OptionsPane{

    public static void setOptionsPane(BasePane bp){
        setOptionsTitle(bp);
        setOptions(bp);
    }

    private static void setOptionsTitle(BasePane bp){
        MyTitle title = new MyTitle("Options");
        BasePane.centerTitle(title);
        bp.getChildren().add(title);
    }

    private static void setOptions(BasePane bp){
        try {
            VBox vbox = new VBox();
            Options options = null;
            File optionsFile = new File("options.dat");
            if(!optionsFile.exists()){
                options = new Options();
                options.save();
            }
            else{
                options = Options.load();
            }
            if(options == null){
                options = new Options();
                options.save();
            }


            HBox option1 = new HBox();
            MyLabel forward = new MyLabel("Forward");
            forward.setId("options");
            option1.getChildren().addAll(
                    forward,
                    getOption1(options)
            );
            HBox option2 = new HBox();
            MyLabel left = new MyLabel("Left");
            left.setId("options");
            option1.getChildren().addAll(
                    left,
                    getOption2(options)
            );
            HBox option3 = new HBox();
            MyLabel down = new MyLabel("Down");
            down.setId("options");
            option1.getChildren().addAll(
                    down,
                    getOption3(options)
            );
            HBox option4 = new HBox();
            MyLabel right = new MyLabel("Right");
            right.setId("options");
            option1.getChildren().addAll(
                    right,
                    getOption4(options)
            );

            vbox.getChildren().addAll(
                    option1,
                    option2,
                    option3,
                    option4,
                    new MyButton("Back", bp.handler)
            );
            vbox.setTranslateX(MainMenu.WIDTH / 3.5);
            vbox.setTranslateY(MainMenu.HEIGHT / 2.5);
            bp.getChildren().add(vbox);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ComboBox getOption1(Options options){
        ComboBox<KeyCode> option = new ComboBox();
        option.getItems().addAll(
                KeyCode.W,KeyCode.UP
        );
        option.setValue(options.getForward());
        option.setId("options");
        return option;
    }
    private static ComboBox getOption2(Options options){
        ComboBox<KeyCode> option = new ComboBox();
        option.getItems().addAll(
                KeyCode.A,KeyCode.LEFT
        );
        option.setValue(options.getLeft());
        option.setId("options");
        return option;
    }
    private static ComboBox getOption3(Options options){
        ComboBox<KeyCode> option = new ComboBox();
        option.getItems().addAll(
                KeyCode.S,KeyCode.DOWN
        );
        option.setValue(options.getDown());
        option.setId("options");
        return option;
    }
    private static ComboBox getOption4(Options options){
        ComboBox<KeyCode> option = new ComboBox();
        option.getItems().addAll(
                KeyCode.D,KeyCode.RIGHT
        );
        option.setValue(options.getRight());
        option.setId("options");
        return option;
    }


}
