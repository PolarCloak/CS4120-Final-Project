package Game.Handling;

import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.GameObject;
import Game.GameObjects.Movable.MovableGameObject;
import Game.Player.Player;
import Window.Game.Panes.RenderingPane;

import java.util.LinkedList;

public class ObjectHandler{

    public LinkedList<GameObject> renderedObjects = new LinkedList<GameObject>();
    public LinkedList<MovableGameObject> tickedObjects = new LinkedList<MovableGameObject>();
    public LinkedList<CollisionGameObject> collisionObjects = new LinkedList<>();

    public void tick() {
        for(MovableGameObject mgo : tickedObjects) {
            mgo.tick();
        }
        for(CollisionGameObject object : collisionObjects){
            for(CollisionGameObject collidedInto : collisionObjects){
                if(object.getBounds().intersects(collidedInto.getBounds().getLayoutBounds())){
                    object.collision(collidedInto);
                }
            }
        }
    }

    public void render(RenderingPane pane) {
        Player player = null;
        for(GameObject go : renderedObjects) {
            if(go instanceof Player){
                player = (Player) go;
            }
        }
        for(GameObject go : renderedObjects) {
            if(go instanceof Player){
                player = (Player) go;
            }
            go.render(pane, player.location, player.guiLocation);
        }
        player.render(pane, player.location, player.guiLocation);
    }

    public void addGameObject(GameObject object) {
        this.renderedObjects.add(object);
    }
    public void removeGameObject(GameObject object) {
        this.renderedObjects.remove(object);
    }

    public void addMovableObject(MovableGameObject object) {
        this.tickedObjects.add(object);
    }
    public void removeMovableObject(MovableGameObject object) {
        this.tickedObjects.remove(object);
    }

    public void addCollisionObject(CollisionGameObject object) {
        this.collisionObjects.add(object);
    }
    public void removeCollisionObject(CollisionGameObject object) {
        this.collisionObjects.remove(object);
    }

}