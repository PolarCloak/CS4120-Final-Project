package Game.World;

import Game.Player.Player;

import java.util.List;
import java.util.Random;

public class RandomDungeon extends World{

    public long seed;

    public RandomDungeon(List<Player> players) {
        super();
        Random r = new Random();
        this.seed = r.nextLong();

        for(Player player : players){
            this.addObject(player);
        }

        this.generateDungeon();

    }

    private void generateDungeon(){
        super.worldMaker.generateDungeon();
    }



}
