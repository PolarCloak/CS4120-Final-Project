package Game;

import Game.GameObjects.Location;
import Game.GameObjects.Movable.Entities.Enemies.Enderman;
import Game.GameObjects.Movable.Entities.Enemies.Zombie;
import Game.Handling.UserInputHandler;
import Game.Player.Player;
import Game.World.EmptyWorld;
import Game.World.Lobby;
import Game.World.RandomDungeon;

import Game.World.World;
import Server.Server;
import Window.Game.Panes.GamePane;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Game implements Runnable{

    private String gameName;
    public static final double ticksPerSecond = 60.0;
    public static final double secondsPerGameEvent = 6.0;
    public static final double secondsPerInventoryUpdate = 0.25;
    public double inventoryTimer = Game.secondsPerInventoryUpdate * Game.ticksPerSecond;
    public double eventTimer = Game.secondsPerGameEvent * Game.ticksPerSecond;

    public Stage primaryStage;
    public Scene gameScene;
    public GamePane gamePane;

    public boolean running = false;
    public UserInputHandler userHandler;

    public World lobby;
    public World world;
    private boolean isInDungeon;

    //the server that this game is running on
    public Server server;

    public Game(String name, Stage primaryStage, Scene gameScene, GamePane gamePane) {
        this.isInDungeon = false;
        this.gameName = name;
        this.gameScene = gameScene;
        this.gameScene.getStylesheets().add("assets/GameWindowStyles.css");
        this.primaryStage = primaryStage;
        this.gamePane = gamePane;
        this.lobby = new Lobby();
        userHandler = new UserInputHandler(primaryStage,this);
        primaryStage.addEventFilter(EventType.ROOT,userHandler);
    }

    @Override
    public void run(){
        running = true;
        long lastTime = System.nanoTime();
        double amountOfFrames = 100.0;
        double ns = 1000000000 / ticksPerSecond;
        double nsR = 1000000000 / amountOfFrames;
        double delta = 0;
        double deltaRender = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            deltaRender += (now - lastTime) / nsR;
            lastTime = now;
            while (delta >= 1) {
                try {
                    tick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                delta--;
            }
            while(deltaRender >= 1){
                render();
                frames++;
                deltaRender--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    public synchronized void stop() {
        try {
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double clamp(double var, double min, double max) {
        if(var>=max) return max;
        else if(var<=min) return min;
        else return var;
    }

    private void tick() throws IOException {
        if(isInDungeon){
            world.objectHandler.tick();
            this.gameProgression(world);
        }
        else{
            lobby.objectHandler.tick();
        }


//        server.updateAllClients();
    }

    private void render() {
        if(isInDungeon){
            Platform.runLater(() -> world.objectHandler.render(gamePane.renderingPane));
        }
        else{
            Platform.runLater(() -> lobby.objectHandler.render(gamePane.renderingPane));
        }
        Platform.runLater(() -> inventoryTick());

    }

    public void moveToDungeon(List<Player> players){
        System.out.println("Moving to a new Dungeon");
        this.gamePane.renderingPane.clearPane();
        this.world = new EmptyWorld();
        this.isInDungeon = true;
        this.gamePane.renderingPane.clearPane();
        this.world = new RandomDungeon(players);
        for(Player player : players){
            player.location = new Location();
        }


    }
    public void moveToLobby(List<Player> players){
        System.out.println("Moving to Lobby");
        this.gamePane.renderingPane.clearPane();
        this.lobby = new EmptyWorld();
        this.isInDungeon = false;
        this.gamePane.renderingPane.clearPane();
        this.lobby = new Lobby(players);
        for(Player player : players){
            player.location = new Location();
        }

    }
    private void inventoryTick(){
        inventoryTimer--;
        inventoryTimer = Game.clamp(inventoryTimer,0,Game.secondsPerInventoryUpdate * Game.ticksPerSecond);
        if(inventoryTimer == 0){
            if(isInDungeon){
                this.gamePane.gui.updateInventory(this.world.players.get(0).inventory);
            }
            else{
                this.gamePane.gui.updateInventory(this.lobby.players.get(0).inventory);
            }
            inventoryTimer = Game.secondsPerInventoryUpdate * Game.ticksPerSecond;
        }
    }

    public void setServer(Server server){
        this.server = server;
    }

    public void gameProgression(World world) {
        if(eventTimer > 0){
            eventTimer--;
            return;
        }
        for(Player player : world.players){
            Location playerLoc = player.location;
            Random r = new Random();
            int direction = r.nextInt()%4;
            Location enemyLoc = new Location();
            if(direction == 0){
                enemyLoc.x = playerLoc.x + 200;
            }
            else if(direction == 1){
                enemyLoc.y = playerLoc.y + 200;
            }
            else if(direction == 2){
                enemyLoc.x = playerLoc.x - 200;
            }
            else if(direction == 3){
                enemyLoc.y = playerLoc.y - 200;
            }

            int rand = r.nextInt()%2;
            if(rand == 0){
                world.addObject(new Zombie(enemyLoc,world));
            }
            else if(rand == 1){
                world.addObject(new Enderman(enemyLoc,world));
            }

        }
        eventTimer = Game.secondsPerGameEvent * Game.ticksPerSecond;
    }

    public boolean isInDungeon(){
        return isInDungeon;
    }

    public String getGameName(){
        return this.gameName;
    }

    public Player getPlayer(){
        if(isInDungeon()){
            return this.world.players.get(0);
        }
        else{
            return this.lobby.players.get(0);
        }
    }
}