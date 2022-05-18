package Window.Menu.Items;


import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

public class MyTextField extends TextField {

    public MyTextField(String initialText){
        this.setText(initialText);
        this.setBackground(Background.EMPTY);
        this.setId("textField");
    }
}
