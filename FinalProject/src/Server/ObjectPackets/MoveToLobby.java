package Server.ObjectPackets;

import java.io.Serializable;
import java.net.Socket;

public class MoveToLobby implements TextOutput, Serializable {

    @Override
    public String getTextOutput() {
        return "Returning to the lobby.\n";
    }
}
