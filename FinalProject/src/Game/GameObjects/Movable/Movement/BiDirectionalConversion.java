package Game.GameObjects.Movable.Movement;

public class BiDirectionalConversion {

    public int x = 0;
    public int y = 0;
    private boolean moving = false;

    public void stopMoving(){
        this.x = 0;
        this.y = 0;
    }
    public double getDegree(){
        if(x==1){
            if(y==1){
                return 45;
            }
            if(y==0){
                return 90;
            }
            if(y==-1){
                return 135;
            }
        }
        else if(x==0){
            if(y==1){
                return 0;
            }
            if(y==0){
                return -1;
            }
            if(y==-1){
                return 180;
            }
        }
        else if(x==-1){
            if(y==1){
                return 315;
            }
            if(y==0){
                return 270;
            }
            if(y==-1){
                return 225;
            }
        }
        return -1;
    }

    public String getDirection(){
        if(x==1){
            if(y==1){
                return "NE";
            }
            if(y==0){
                return "E";
            }
            if(y==-1){
                return "SE";
            }
        }
        else if(x==0){
            if(y==1){
                return "N";
            }
            if(y==0){
                return "0";
            }
            if(y==-1){
                return "S";
            }
        }
        else if(x==-1){
            if(y==1){
                return "NW";
            }
            if(y==0){
                return "W";
            }
            if(y==-1){
                return "SW";
            }
        }
        return "0";
    }

    public boolean getMoving(){
        if(x==0 && y==0){
            moving = false;
        }
        return moving;
    }
    public void setMoving(){
        moving = true;
    }



}
