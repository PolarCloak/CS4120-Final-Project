package Window.Menu.Items;

import javafx.scene.input.KeyCode;

import java.io.*;

public class Options implements Serializable {

    private KeyCode forward = KeyCode.W;
    private KeyCode left = KeyCode.A;
    private KeyCode down = KeyCode.S;
    private KeyCode right = KeyCode.D;


    public static Options load() throws IOException, ClassNotFoundException {
        Options options = null;

        File file = new File("options.dat");
        FileInputStream f= new FileInputStream(file);
        ObjectInputStream o = new ObjectInputStream(f);
        options = (Options) o.readObject();

        return options;
    }

    public void save() throws IOException {
        File options = new File("options.dat");

        FileOutputStream f = new FileOutputStream(options);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
    }

    public KeyCode getForward() {
        return forward;
    }
    public void setForward(KeyCode forward) {
        this.forward = forward;
    }

    public KeyCode getLeft() {
        return left;
    }

    public void setLeft(KeyCode left) {
        this.left = left;
    }

    public KeyCode getDown() {
        return down;
    }

    public void setDown(KeyCode down) {
        this.down = down;
    }

    public KeyCode getRight() {
        return right;
    }

    public void setRight(KeyCode right) {
        this.right = right;
    }

}
