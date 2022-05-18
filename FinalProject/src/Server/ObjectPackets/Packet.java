package Server.ObjectPackets;

import java.net.Socket;
import java.util.Date;

public abstract class Packet {

    public Date date = new Date();
    public Socket clientSocket;

    public Packet(Socket clientSocket){
        this.clientSocket = clientSocket;
    }


}
