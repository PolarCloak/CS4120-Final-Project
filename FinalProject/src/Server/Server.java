package Server;

import Client.Client;
import Game.Game;
import Game.GameObjects.Location;
import Game.GameObjects.Movable.Entities.Enemies.Zombie;
import Game.GameObjects.Movable.Entities.Entity;
import Game.GameObjects.Stationary.Chest.Chest;
import Game.Player.Player;
import Game.World.World;
import Server.ObjectPackets.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Random;

public class Server{
    private static double ignoreInput = 40;

    public int port;
    public boolean running = true;

    public ServerSocket ss;
    public ArrayList<Socket> clientSockets = new ArrayList<>();

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Game game;
    Thread mainGameThread;

    public Server(int port, Game game){
        try{
            this.game = game;
            game.server = this;
            ss=new ServerSocket(port);
            this.port=port;
            mainGameThread = new Thread(game);
            mainGameThread.start();
            new Thread(() -> {
                while(running){
                    try{
                        System.out.println("waiting for clients");

                        Socket s = ss.accept();

                        System.out.println("Client connected");
                        Thread clientThread = new Thread(() -> {
                            try{
                                clientSockets.add(s);
                                in = new ObjectInputStream(s.getInputStream());
                                out = new ObjectOutputStream(s.getOutputStream());
                                while(running){
                                    try{
                                        //this server will wait on input from the client, and act accordingly
                                        ignoreInput--;
                                        ignoreInput = Game.clamp(ignoreInput, 0, 40);
                                        if(ignoreInput<=0){
                                            this.receiveInput(in.readObject());
                                        }
                                    }catch(Exception e){
                                        this.dispose();
                                    }
                                }
                            }catch(Exception exception){ exception.printStackTrace(); }
                        });
                        clientThread.setDaemon(true);
                        clientThread.setName("Client "+s.getInetAddress().toString());
                        clientThread.start();
                    }catch(SocketException e){  //Do nothing
                    }catch(IOException e){ e.printStackTrace(); }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispose(){
        this.game.running = false;
        running=false;
        if(clientSockets != null) {
            for (Socket s : clientSockets) {
                try {
                    s.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        clientSockets=null;
        if(ss != null) {
            try {
                ss.close();
            } catch (IOException e) {
            }
        }
        ss=null;
    }


    private void receiveInput(Object o) throws IOException {
        if(o == null){
            return;
        }
        if(ignoreInput>0){
            return;
        }
        if(o instanceof TextOutput){
            TextOutput text = (TextOutput) o;
            this.game.gamePane.textPane.textArea.appendText(text.getTextOutput());
        }
        if(o instanceof MoveToDungeon){
            this.ignoreInput = 40;
            this.game.gamePane.renderingPane.clearPane();
            this.game.moveToDungeon(this.game.lobby.players);
        }
        if(o instanceof MoveChestLocation){
            this.game.world.removeAllObjectsOfType(Chest.class);
            Random r = new Random();
            int x = r.nextInt()%900;
            int y = r.nextInt()%900;
            game.world.addObject((new Chest(new Location(x,y))));

        }
        if(o instanceof CloseMyConnection){
            CloseMyConnection c = (CloseMyConnection) o;
        }
        if(o instanceof WorldUpdate){
            WorldUpdate w = (WorldUpdate) o;

        }
        if(o instanceof WinCondition){
            this.ignoreInput = 40;
            this.game.gamePane.renderingPane.clearPane();
            this.game.moveToLobby(this.game.world.players);
        }
        if(o instanceof DeathCondition){
            this.ignoreInput = 40;
            this.game.gamePane.renderingPane.clearPane();
            this.game.moveToLobby(this.game.lobby.players);
        }
        if(o instanceof Player){
            Player p = (Player) o;
            game.world.verifyPlayer(p);
        }
        if(o instanceof Command){
            Command c = (Command) o;
            runCommand(c);
        }
    }

    public void updateAllClients() throws IOException {
        for(Socket s :clientSockets){
            WorldUpdate w = new WorldUpdate(s, game.world);
            out.writeObject(w);
        }

    }

    private String runCommand(Command c){
        if(c.command.equalsIgnoreCase("clear")){
            if(c.args.length == 0){
                game.world.removeAllObjectsOfType(Entity.class);
                return "Removed all entities";
            }
            else if(c.args.length == 1){
                if(c.args[0].equalsIgnoreCase("zombie")){
                    game.world.removeAllObjectsOfType(Zombie.class);
                    return "Removed all Zombies";
                }
            }

        }
        if(c.command.equalsIgnoreCase("heal")){
            game.world.players.get(0).health.fullHeal();
            return "You have been healed";
        }
        return "Failed to run command";
    }
}