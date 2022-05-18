package Game.World;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum Biome{

    MEADOWS,
    FOREST,
    SWAMP,
    MOUNTAIN,
    PLAINS,
    OCEAN,
    BLANK;

    public ImageView getBiomeImage(Biome biome){
        switch (biome){
            case MEADOWS:
                return new ImageView(new Image("Assets/World/Biomes/Meadows.png"));
            case FOREST:
                return new ImageView(new Image("Assets/World/Biomes/Forest.png"));
            default:
                return new ImageView(new Image("Assets/World/Biomes/Blank.png"));
        }
    }
}


