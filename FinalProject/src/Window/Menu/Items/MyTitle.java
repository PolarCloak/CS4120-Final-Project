package Window.Menu.Items;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MyTitle extends Label {

    public MyTitle(String name){
        this.setText(name);
        this.setId("title");
    }

}
