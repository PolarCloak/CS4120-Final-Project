package Game.Player;

import Game.Inventory.GUILocation;
import Window.Game.Panes.RenderingPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Health implements Serializable {

    private GUILocation location;
    private static final double healthBarMaxWidth = 350;
    private static final double healthBarMaxHeight = 30;
    private Pane healthBarPane;
    private Rectangle healthBar;
    private Rectangle healthBarBorder;
    private Rectangle healthBarBack;
    private Label titleText;
    private Label healthValues;


    private double maxHealth;
    public double health;
    public double damageCooldown;


    public Health(){
        this.location = new GUILocation();
        this.maxHealth = 1000;
        this.health = maxHealth;
        healthBar = new Rectangle(healthBarMaxWidth, healthBarMaxHeight, Color.RED);
        healthBarBorder = new Rectangle(healthBarMaxWidth+6, healthBarMaxHeight+6,Color.GREY);
        healthBarBack = new Rectangle(healthBarMaxWidth,healthBarMaxHeight,Color.BLACK);
        this.titleText = new Label("Health");
        this.healthValues = new Label(health + " / " + maxHealth);
        this.initializeHealthBar();
    }

    public Pane getHealthBar(){
        this.updateHealthBar();
        this.updateHealthValues();

        healthBarPane.getChildren().remove(3,5);

        healthBarPane.getChildren().add(3,this.healthBar);
        healthBarPane.getChildren().add(4,this.healthValues);

        return healthBarPane;
    }

    public Pane initializeHealthBar(){
        healthBarPane = new Pane();
        healthBarBorder.setTranslateX(-3);
        healthBarBorder.setTranslateY(-3);
        healthBarPane.getChildren().add(0,healthBarBorder);
        healthBarPane.getChildren().add(1,healthBarBack);
        this.updateHealthBar();

        titleText.setTranslateX(healthBarMaxWidth/2 - titleText.getWidth()/2);
        titleText.setTranslateY(-healthBarMaxHeight);
        healthBarPane.getChildren().add(2,titleText);



        healthBarPane.getChildren().add(3,healthBar);

        this.updateHealthValues();
        healthValues.setTranslateX(4);
        healthBarPane.getChildren().add(4,healthValues);

        return healthBarPane;
    }

    private void updateHealthValues() {
        this.healthValues = new Label(health + " / " + maxHealth);
    }

    private void setHealth(double value){
        this.health = Game.Game.clamp(value,0,maxHealth);
    }

    public GUILocation getGUILocation(RenderingPane pane){
        this.location.x = pane.getWidth()/2 - this.healthBarBorder.getWidth()/2 + 30;
        this.location.y = 40;
        return this.location;
    }

    private void updateHealthBar(){
        double width = this.healthBarMaxWidth * getHealthPercent();
        healthBar.setWidth(width);
    }

    public void damagePlayer(double damage){
        this.setHealth(this.health - damage);
    }

    private double getHealthPercent(){
        return (health / maxHealth);
    }

    public void fullHeal(){
        this.health = maxHealth;
    }


}
