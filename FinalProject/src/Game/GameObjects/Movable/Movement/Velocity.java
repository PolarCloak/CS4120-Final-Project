package Game.GameObjects.Movable.Movement;

public class Velocity {

    public static final double diagonalModifier = 1.7;
    public double x;
    public double y;

    public Velocity(){
        x=0;
        y=0;
    }

    public Velocity(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setVelocity(double speedWithMod, Direction direction){
        if(!direction.bi.getMoving()){
            //not moving, dont update velocity
            x = 0;
            y = 0;
            return;
        }

        //0 45 90 135 180 225 270 315 360

        double degreeRotation = direction.degree;
        double percentage = 0;


        //top
        if(degreeRotation>=315 || degreeRotation<45){
            if(degreeRotation>=315 && degreeRotation<=360){
                percentage = Math.abs((degreeRotation-270)/90);
                this.x = -1 * (speedWithMod * (1-percentage) * diagonalModifier);
                this.y = 1 * (speedWithMod * (percentage));
            }
            if(degreeRotation>=0 && degreeRotation<45){
                percentage = Math.abs((90-degreeRotation)/90);
                this.x = 1 * (speedWithMod * (1-percentage) * diagonalModifier);
                this.y = 1 * (speedWithMod * (percentage));
            }
        }
        //right
        else if(degreeRotation>=45 && degreeRotation<135){
            //upper right
            if(degreeRotation>=45 && degreeRotation<=90){
                percentage = Math.abs((degreeRotation)/90);
                this.y = 1 * (speedWithMod * (1-percentage) * diagonalModifier);
                this.x = 1 * (speedWithMod * (percentage));
            }
            //lower right
            if(degreeRotation>90 && degreeRotation<135){
                percentage = Math.abs((90-degreeRotation)/90);
                this.y = -1 * (speedWithMod * (percentage) * diagonalModifier);
                this.x = 1 * (speedWithMod * (1-percentage));
            }
        }
        //down
        else if(degreeRotation>=135 && degreeRotation<225){
            if(degreeRotation>=135 && degreeRotation<=180){
                percentage = Math.abs((degreeRotation-90)/90);
                this.x = 1 * (speedWithMod * (1-percentage) * diagonalModifier);
                this.y = -1 * (speedWithMod * (percentage));
            }
            if(degreeRotation>180 && degreeRotation<225){
                percentage = Math.abs((180-degreeRotation)/90);
                this.x = -1 * (speedWithMod * (percentage) * diagonalModifier);
                this.y = -1 * (speedWithMod * (1-percentage));
            }
        }
        //left
        else if(degreeRotation>=225 && degreeRotation<315){
            if(degreeRotation>=225 && degreeRotation<=270){
                percentage = Math.abs((degreeRotation-180)/90);
                this.y = -1 * (speedWithMod * (1-percentage) * diagonalModifier);
                this.x = -1 * (speedWithMod * (percentage));
            }
            if(degreeRotation>270 && degreeRotation<315){
                percentage = Math.abs((270-degreeRotation)/90);
                this.y = 1 * (speedWithMod * (percentage) * diagonalModifier);
                this.x = -1 * (speedWithMod * (1-percentage));
            }
        }
    }


}
