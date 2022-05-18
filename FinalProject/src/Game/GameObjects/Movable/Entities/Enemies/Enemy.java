package Game.GameObjects.Movable.Entities.Enemies;

import Game.Game;
import Game.GameObjects.Interfaces.Enemies;
import Game.GameObjects.Location;
import Game.GameObjects.Movable.Entities.Entity;
import Game.Player.Health;
import Game.World.World;

public abstract class Enemy extends Entity implements Enemies {

    private double damage;
    private double damageCooldown;
    private double maxDamageCooldown;

    public Enemy(Location loc, World world, double damage, double damageCooldown) {
        super(loc, world);
        this.damage = damage;
        this.maxDamageCooldown = damageCooldown * Game.ticksPerSecond;
        this.damageCooldown = maxDamageCooldown;
    }

    @Override
    public double getDamage() {
        if(damageCooldown == 0){
            this.damageCooldown = maxDamageCooldown;
            return damage;
        }
        else{
            return 0;
        }
    }

    public void tick(){
        super.tick();
        damageCooldown--;
        damageCooldown = Game.clamp(damageCooldown,0,maxDamageCooldown);
    }
}
