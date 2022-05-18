package Server.ObjectPackets;


import Game.Game;
import Game.World.World;

import java.io.Serializable;
import java.net.Socket;

public class WorldUpdate extends Packet implements Serializable {

    public World world;

    public WorldUpdate(Socket clientSocket, World world) {
        super(clientSocket);
        this.world = world;
    }
}
