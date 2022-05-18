package Game.World;

import Game.Player.Player;

import java.util.List;

public class Lobby extends World{


    public Lobby() {
        super();
        super.worldMaker.generateLobbyRoom();
    }

    public Lobby(List<Player> players) {
        super();
        for(Player player : players){
            addObject(player);
        }
        super.worldMaker.generateLobbyRoom();
    }

}
