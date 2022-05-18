package Client;

import Game.Player.Player;
import Server.ObjectPackets.CloseMyConnection;
import Server.ObjectPackets.WorldUpdate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{

    //client variables
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

    public boolean running = true;

    public Player player;

    public Client(String ip, int port, Player player){
        this.player = player;
        try{
            this.socket=new Socket(ip, port);
            out=new ObjectOutputStream(socket.getOutputStream());
            in=new ObjectInputStream(socket.getInputStream());

            Thread clientThread = new Thread(() -> {
                while(running){
                    try{
                        //this client will wait for input from the server and act accordingly
                        this.receivedInput(in.readObject());
                    }catch(Exception exception) {
                        this.dispose();
                    }
                }
            });
            clientThread.setName("Client Connection");
            clientThread.start();
        }catch(Exception exception){
            running=false;
        }
    }

    private void receivedInput(Object o) throws IOException {
        if(o instanceof CloseMyConnection){
            this.dispose();
        }
        else if(o instanceof WorldUpdate){
            WorldUpdate w = (WorldUpdate) o;

        }
        else if(o instanceof Player){

        }
    }

    public void sendOutput(Object o) throws IOException {
        out.writeObject(o);
    }

    public void dispose(){
        try{
            if(running){
                running=false;
                socket.close();
                in.close();
                out.close();
            }
            socket=null;
            in=null;
            out=null;
        }catch(Exception e){ e.printStackTrace();}
    }

    public boolean compareSocket(Socket socket){
        return this.socket.equals(socket);
    }

}