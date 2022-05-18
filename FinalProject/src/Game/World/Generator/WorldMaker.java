package Game.World.Generator;

import Game.GameObjects.Location;
import Game.GameObjects.Movable.Entities.Enemies.Zombie;
import Game.GameObjects.Stationary.Chest.Chest;
import Game.GameObjects.Stationary.Portal.NewDungeonPortal;
import Game.GameObjects.Stationary.Structure.Floor;
import Game.GameObjects.Stationary.Structure.Wall.WallCenter;
import Game.GameObjects.Stationary.Structure.Wall.WallHoriz;
import Game.GameObjects.Stationary.Structure.Wall.WallVert;
import Game.World.World;

import java.util.Random;

public class WorldMaker {

    public World world;

    public WorldMaker(World world){
        this.world = world;
    }

    public void generateLobbyRoom(){
        for(int x = -3; x<=3; x++){
            for(int y = -2; y<=2; y++){
                Floor floor = new Floor(new Location(x*100,y*100));
                floor.size.x = 100;
                floor.size.y = 100;
                world.addObject(floor);
                if(x==-3 || x==3){
                    if(y==-2 || y==2){

                    }
                    else{
                        WallVert wallVert = new WallVert(new Location(x*100,y*100));
                        wallVert.size.x = 100;
                        wallVert.size.y = 100;
                        world.addObject(wallVert);
                    }

                }
                if(y==-2 || y==2){
                    if(y==2 && x==0){
                        NewDungeonPortal newDung = new NewDungeonPortal(new Location(x*100,y*100));
                        world.addObject(newDung);
                    }
                    else{
                        WallHoriz wallHoriz = new WallHoriz(new Location(x*100,y*100));
                        wallHoriz.size.x = 100;
                        wallHoriz.size.y = 100;
                        world.addObject(wallHoriz);
                    }
                    if(x==-3 || x==3){
                        WallCenter wallCenter = new WallCenter(new Location(x*100,y*100));
                        wallCenter.size.x = 100;
                        wallCenter.size.y = 100;
                        world.addObject(wallCenter);
                    }

                }

            }
        }


    }
    public void generateDungeon(){
        for(int x = -10; x<=10; x++){
            for(int y = -10; y<=10; y++){
                Floor floor = new Floor(new Location(x*100,y*100));
                floor.size.x = 100;
                floor.size.y = 100;
                world.addObject(floor);
                if(x==-10 || x==10){
                    if(y==-10 || y==10){

                    }
                    else{
                        WallVert wallVert = new WallVert(new Location(x*100,y*100));
                        wallVert.size.x = 100;
                        wallVert.size.y = 100;
                        world.addObject(wallVert);
                    }

                }
                if(y==-10 || y==10) {
                    WallHoriz wallHoriz = new WallHoriz(new Location(x * 100, y * 100));
                    wallHoriz.size.x = 100;
                    wallHoriz.size.y = 100;
                    world.addObject(wallHoriz);

                    if (x == -10 || x == 10) {
                        WallCenter wallCenter = new WallCenter(new Location(x * 100, y * 100));
                        wallCenter.size.x = 100;
                        wallCenter.size.y = 100;
                        world.addObject(wallCenter);
                    }
                }
            }

        }
        world.addObject(new Zombie(new Location(400,400),world));
        Random r = new Random();
        int x = r.nextInt()%900;
        int y = r.nextInt()%900;
        world.addObject((new Chest(new Location(x,y))));
    }
}
