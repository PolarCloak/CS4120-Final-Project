package Game.Player;

import Client.Client;
import Game.Game;
import Game.GameObjects.Interfaces.Enemies;
import Game.GameObjects.Interfaces.Impassable;
import Game.GameObjects.Stationary.Chest.Chest;
import Game.GameObjects.Stationary.CollisionGameObject;
import Game.GameObjects.Stationary.Portal.NewDungeonPortal;
import Game.Inventory.GUILocation;
import Game.Inventory.Inventory;
import Game.GameObjects.Location;
import Game.GameObjects.Movable.MovableGameObject;
import Game.GameObjects.Size;
import Server.ObjectPackets.*;
import Window.Game.Panes.RenderingPane;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.Serializable;


public class Player extends MovableGameObject implements Serializable {

    private static final int sizeX = 50;
    private static final int sizeY = 50;

    private double maxOofCooldown = 1 * Game.ticksPerSecond;
    private double oofCooldown = maxOofCooldown;
    private double maxPortalUsageTimer = 2 * Game.ticksPerSecond;
    public double portalUsageTimer = maxPortalUsageTimer;

    public String name;
    public Inventory inventory;
    public Client client;
    public Health health;

    private boolean tookDamage = false;


    public Player(String name) {
        super(new Location());
        this.changeImage(new Image("assets/img/world/player/player1.png"));
        this.id = 1;
        this.size = new Size(sizeX,sizeY);
        this.name = name;
        this.inventory = new Inventory();
        this.health = new Health();
        this.setSpeed(2);
    }

    public Player(String name, Inventory inv, double health) {
        super(new Location());
        this.changeImage(new Image("assets/img/world/player/player1.png"));
        this.id = 1;
        this.size = new Size(sizeX,sizeY);
        this.name = name;
        this.inventory = inv;
        this.health = new Health();
        this.health.health = health;
        this.setSpeed(2);
    }

    @Override
    public void tick() {
        super.tick();
        decrementTimers();
        if(tookDamage && oofCooldown == 0){
            playerDamageSound();
            tookDamage = false;
            oofCooldown = maxOofCooldown;
        }
        if(this.health.health <= 0){
            playerDied();
        }


    }

    private void decrementTimers(){
        this.portalUsageTimer--;
        this.portalUsageTimer = Game.clamp(this.portalUsageTimer, 0, maxPortalUsageTimer);
        this.oofCooldown--;
        this.oofCooldown = Game.clamp(this.oofCooldown, 0, maxOofCooldown);
    }

    private void playerDied() {
        sendTo("death");
    }

    private void sendTo(String reason) {
        this.direction.bi.stopMoving();
        try {
            if(reason.equalsIgnoreCase("death")){
                this.health.fullHeal();
                this.client.sendOutput(new DeathCondition());
            }
            else if(reason.equalsIgnoreCase("win")){
                this.client.sendOutput(new WinCondition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playerWon() {
        sendTo("win");
    }

    public void setClient(Client client){
        this.client = client;
    }

    private void playerFoundTreasure(Chest chest) {
        this.inventory.addItem(chest.toInventoryItem());
        playerWon();
    }

    @Override
    public void render(RenderingPane pane,Location playerLoc, GUILocation playerGUILoc) {
        super.render(pane,playerLoc,playerGUILoc);
        this.guiLocation.set(pane.getWidth()/2, pane.getHeight()/2);
        pane.draw(image, pane.getMiddle());
        pane.draw(health.getHealthBar(), health.getGUILocation(pane));
    }

    @Override
    public void collision(CollisionGameObject go){
        if(go instanceof NewDungeonPortal && portalUsageTimer==0){
            try {
                this.direction.bi.stopMoving();
                this.client.sendOutput(new MoveToDungeon());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(go instanceof Impassable){
            double rightSide = go.location.x + go.size.x;
            double leftSide = go.location.x;
            double topSide = go.location.y;
            double bottomSide = go.location.y - go.size.y;
            if(Math.abs(this.location.x - rightSide)<= 5){
                this.location.x = Game.clamp(this.location.x,rightSide,rightSide+10)+0.1;
                this.velocity.x = 0;
            }
            else if(Math.abs(this.location.x + this.size.x - leftSide) <= 5){
                this.location.x = Game.clamp(this.location.x + this.size.x,leftSide-10,leftSide) - this.size.x- 0.1;
                this.velocity.x = 0;
            }
            if(Math.abs(this.location.y-this.size.y-topSide) <= 5){
                this.location.y = Game.clamp(this.location.y-this.size.y,topSide,topSide)+this.size.y+0.1;
                this.velocity.y = 0;
            }
            else if(Math.abs(this.location.y - bottomSide) <= 5){
                this.location.y = Game.clamp(this.location.y,bottomSide,bottomSide)-0.1;
                this.velocity.y = 0;
            }
        }
        if(go instanceof Enemies){
            Enemies en = (Enemies) go;
            double damage = en.getDamage();
            if(damage>0){
                this.health.damagePlayer(damage);
                this.tookDamage = true;
            }
        }
        if(go instanceof Chest){
            Chest chest = (Chest) go;
            playerFoundTreasure(chest);
//            try {
//                this.client.sendOutput(new MoveChestLocation(chest.toInventoryItem()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
    public void playerDamageSound() {
        Media oofSound = new Media("File:///C:/Users/david/IdeaProjects/FinalProject/src/assets/aud/oof.mp3");
        MediaPlayer soundPlayer = new MediaPlayer(oofSound);
        soundPlayer.setAutoPlay(true);
        soundPlayer.setCycleCount(1);
    }


}
