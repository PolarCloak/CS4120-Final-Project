package Server.ObjectPackets;

import java.io.Serializable;
import java.net.Socket;

public class MoveToDungeon implements Serializable, TextOutput {

    @Override
    public String getTextOutput() {
        return "Generating dungeon.\n";
    }
}
