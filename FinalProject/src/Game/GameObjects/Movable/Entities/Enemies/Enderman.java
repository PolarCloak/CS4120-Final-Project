package Game.GameObjects.Movable.Entities.Enemies;

import Game.Game;
import Game.GameObjects.Interfaces.Enemies;
import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;

import Game.Player.Player;
import Game.World.World;
import javafx.scene.image.Image;

import java.util.Random;

public class Enderman extends Enemy {


    private static final int sizeX = 50;
    private static final int sizeY = 100;
    private static final double damage = 100;
    private static final double damageCooldown = 5.0;

    private static final double maxEndermanTeleportCooldown = 0.5 * Game.ticksPerSecond;
    private static final int maxTeleportDistance = 50;
    private double endermanTeleportCooldown = maxEndermanTeleportCooldown;


    public Enderman(Location loc, World world) {
        super(loc, world, damage,damageCooldown);
        this.changeImage(new Image("assets/img/world/entity/enemy/enderman/enderman1.png"));
        this.id = 1001;
        this.size = new Size(sizeX,sizeY);
        this.setSpeed(0.7);
    }

    @Override
    public void AIMove(World world) {
        Player player = world.players.get(0);
        this.direction.bi.setMoving();
        if(this.location.x >= player.location.x -(player.size.x/2) && this.location.x <= player.location.x +(player.size.x/2)){
            this.direction.bi.x = 0;
        }
        else if(player.location.x>this.location.x){
            this.direction.bi.x = 1;
        }
        else if(player.location.x<this.location.x){
            this.direction.bi.x = -1;
        }

        if(this.location.y >= player.location.y -(player.size.y/2) && this.location.y <= player.location.y +(player.size.y/2)){
            this.direction.bi.y = 0;
        }
        else if(player.location.y>this.location.y){
            this.direction.bi.y = 1;
        }
        else if(player.location.y<this.location.y){
            this.direction.bi.y = -1;
        }

        decrementTimers();
        if(endermanTeleportCooldown == 0){
            endermanTeleport();
            endermanTeleportCooldown = maxEndermanTeleportCooldown;
        }

    }

    private void endermanTeleport() {
        Random r = new Random();
        int ran1 = r.nextInt()%maxTeleportDistance;
        int neg1 = r.nextInt()%2;
        if(neg1 == 0){
            ran1 = Math.negateExact(ran1);
        }
        int ran2 = r.nextInt()%maxTeleportDistance;
        int neg2 = r.nextInt()%2;
        if(neg2 == 0){
            ran2 = Math.negateExact(ran2);
        }
        this.location.x = this.location.x + ran1;
        this.location.y = this.location.y + ran2;
    }

    private void decrementTimers(){
        this.endermanTeleportCooldown--;
        this.endermanTeleportCooldown = Game.clamp(this.endermanTeleportCooldown, 0, maxEndermanTeleportCooldown);
    }

    @Override
    public void collision(CollisionGameObject object) {

    }


}
