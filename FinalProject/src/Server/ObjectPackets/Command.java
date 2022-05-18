package Server.ObjectPackets;


import java.io.Serializable;
import java.net.Socket;

public class Command extends Packet implements TextOutput, Serializable {

    public String command;
    public String[] args;

    public Command(Socket clientSocket, String command, String[] args){
        super(clientSocket);
        this.command = command;
        this.args = args;
    }


    @Override
    public String getTextOutput() {
        String ret = "/" + command;
        for(String arg : args){
            ret += "/" + arg;
        }
        return ret;
    }
}
