package Game.GameObjects.Movable.Entities.Enemies;

import Game.GameObjects.Interfaces.Enemies;
import Game.GameObjects.Location;
import Game.GameObjects.Size;
import Game.GameObjects.Stationary.CollisionGameObject;

import Game.Player.Player;
import Game.World.World;
import javafx.scene.image.Image;

public class Zombie extends Enemy {


    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private static final double damage = 20;
    private static final double damageCooldown = 2.0;


    public Zombie(Location loc, World world) {
        super(loc, world, damage,damageCooldown);
        this.changeImage(new Image("assets/img/world/entity/enemy/zombie1.png"));
        this.id = 1000;
        this.size = new Size(sizeX,sizeY);
        this.setSpeed(1.4);
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

    }

    @Override
    public void collision(CollisionGameObject object) {

    }


}
