package Game.Handling;

import Game.Game;
import Game.GameObjects.Location;
import Game.GameObjects.Movable.Entities.Enemies.Zombie;
import Game.GameObjects.Movable.Movement.Direction;
import Game.Player.Player;
import Game.World.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

import java.util.Random;

public class GameEventHandler implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        EventType type = event.getEventType();
        System.out.println(type);
    }


}
