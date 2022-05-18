package Game.World;

import Game.GameObjects.Interfaces.Collision;
import Game.GameObjects.Movable.Entities.Enemies.Zombie;
import Game.GameObjects.Movable.MovableGameObject;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.GameObject;
import Game.Handling.GameEventHandler;
import Game.Handling.ObjectHandler;
import Game.Player.Player;
import Game.World.Generator.WorldMaker;

import java.util.ArrayList;

public abstract class World {

    private static final double size = 10000;

    public ObjectHandler objectHandler;
    public GameEventHandler gameHandler;
    public ArrayList<Player> players = new ArrayList<>();

    public WorldMaker worldMaker;

    public World(){
        this.objectHandler = new ObjectHandler();
        this.worldMaker = new WorldMaker(this);
    }

    public void addObject(GameObject object){
        if(object instanceof MovableGameObject){
            objectHandler.addMovableObject((MovableGameObject) object);
        }
        if(object instanceof CollisionGameObject){
            objectHandler.addCollisionObject((CollisionGameObject) object);
        }
        if(object instanceof Player){
            players.add((Player) object);
        }
        objectHandler.addGameObject(object);
    }
    public void removeObject(GameObject object){
        if(object instanceof MovableGameObject){
            objectHandler.removeMovableObject((MovableGameObject) object);
        }
        if(object instanceof CollisionGameObject){
            objectHandler.removeCollisionObject((CollisionGameObject) object);
        }
        if(object instanceof Player){
            players.add((Player) object);
        }
        objectHandler.removeGameObject(object);
    }
    public void removeAllObjectsOfType(Class cl){
        for(GameObject obj : objectHandler.renderedObjects){
            if(obj.getClass() == cl){
                removeObject(obj);
            }
        }
    }

    public void verifyPlayer(Player player){
        if(players.contains(player)){
            return;
        }
        else{
            players.add(player);
            return;
        }
    }

}
