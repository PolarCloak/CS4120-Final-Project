package Game.Handling;


import Game.Game;
import Game.GameObjects.Stationary.GameObject;
import Game.Inventory.InventoryItems.InventoryItem;
import Game.Player.Player;
import Game.World.World;
import Window.Game.EscMenu;
import Window.Game.GUI.InvItemView;
import Window.Game.GUI.invSlot;
import Window.Menu.Handlers.ConfirmExitHandler;
import Window.Menu.Stages.ConfirmExit;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserInputHandler implements EventHandler<Event>{

    Stage primaryStage;
    public Game game;

    protected ArrayList<KeyCode> keysPressed = new ArrayList<>();


    public UserInputHandler(Stage primaryStage, Game game){
        this.primaryStage = primaryStage;
        this.game = game;
    }

    public void handleKeyPressedDown(KeyEvent event) {
        KeyCode code = event.getCode();
        Player player = getPlayer();

        if(code.equals(KeyCode.ESCAPE) && !keysPressed.contains(KeyCode.ESCAPE)){
            new EscMenu(primaryStage,game,this.keysPressed);
        }

        keysPressed.add(code);

        if(code.equals(KeyCode.W)){
            player.direction.bi.y = 1;
            player.direction.bi.setMoving();
        }
        else if(code.equals(KeyCode.D)){
            player.direction.bi.x = 1;
            player.direction.bi.setMoving();
        }
        else if(code.equals(KeyCode.S)){
            player.direction.bi.y = -1;
            player.direction.bi.setMoving();
        }
        else if(code.equals(KeyCode.A)){
            player.direction.bi.x = -1;
            player.direction.bi.setMoving();
        }

    }

    public void handleKeyLiftedUp(KeyEvent event) {
        KeyCode code = event.getCode();
        Player player = getPlayer();

        while(keysPressed.contains(code)){
            keysPressed.remove(code);
        }

        if(code.equals(KeyCode.W)){
            player.direction.bi.y = 0;
        }
        if(code.equals(KeyCode.D)){
            player.direction.bi.x = 0;
        }
        if(code.equals(KeyCode.S)){
            player.direction.bi.y = 0;
        }
        if(code.equals(KeyCode.A)){
            player.direction.bi.x = 0;
        }

    }

    public void handleMouseEnteredTarget(MouseEvent event) {

    }

    private void handleMouseClicked(MouseEvent mouseEvent) {
        Player player = getPlayer();
        if(mouseEvent.getTarget() instanceof invSlot){
            invSlot slot = (invSlot) mouseEvent.getTarget();
            System.out.println("Trying to open slot number: " + slot.getSlotNumber());
            player.inventory.slotAction(slot.getSlotNumber());
            game.gamePane.gui.updateInventory(player.inventory);
        }
        if(mouseEvent.getTarget() instanceof InvItemView){
            InvItemView slot = (InvItemView) mouseEvent.getTarget();
            System.out.println("Trying to open slot number: " + slot.getSlot());
            player.inventory.slotAction(slot.getSlot());
            game.gamePane.gui.updateInventory(player.inventory);
        }
    }

    @Override
    public void handle(Event event) {
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            MouseEvent mouseEvent = (MouseEvent) event;
            handleMouseClicked(mouseEvent);
        }
        if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED_TARGET)) {
            MouseEvent mouseEvent = (MouseEvent) event;
            handleMouseEnteredTarget(mouseEvent);
        }
        if(event.getEventType().equals(KeyEvent.KEY_PRESSED)){
            KeyEvent keyEvent = (KeyEvent) event;
            handleKeyPressedDown(keyEvent);
        }
        if(event.getEventType().equals(KeyEvent.KEY_RELEASED)){
            KeyEvent keyEvent = (KeyEvent) event;
            handleKeyLiftedUp(keyEvent);
        }
    }

    private Player getPlayer(){
        if(game.isInDungeon()){
            return game.world.players.get(0);
        }
        else if(!game.isInDungeon()){
            return game.lobby.players.get(0);
        }
        else{
            return null;
        }
    }

}
