package Server.ObjectPackets;

import java.io.Serializable;

public class DeathCondition extends MoveToLobby implements Serializable {

    @Override
    public String getTextOutput() {
        String out = super.getTextOutput();
        return ("Oh dear, you died!\n" + out);
    }
}
