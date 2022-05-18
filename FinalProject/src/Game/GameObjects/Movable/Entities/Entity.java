package Game.GameObjects.Movable.Entities;

import Game.GameObjects.Location;
import Game.GameObjects.Movable.MovableGameObject;
import Game.World.World;

public abstract class Entity extends MovableGameObject {

    private World world;

    public Entity(Location loc, World world) {
        super(loc);
        this.world = world;
    }

    public void tick(){
        super.tick();
        this.AIMove(world);
    }

    public abstract void AIMove(World world);

}
