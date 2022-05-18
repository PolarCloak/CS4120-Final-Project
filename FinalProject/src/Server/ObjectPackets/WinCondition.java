package Server.ObjectPackets;

import java.io.Serializable;

public class WinCondition extends MoveToLobby implements Serializable {

    @Override
    public String getTextOutput() {
        String out = super.getTextOutput();
        return ("You got the chest!\n" + out);
    }
}
