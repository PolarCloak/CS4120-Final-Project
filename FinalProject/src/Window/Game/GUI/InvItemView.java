package Window.Game.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InvItemView extends ImageView {

    private int slot;

    public InvItemView(Image image, int slot) {
        super(image);
        this.slot = slot;
    }

    public int getSlot(){
        return slot;
    }
}
