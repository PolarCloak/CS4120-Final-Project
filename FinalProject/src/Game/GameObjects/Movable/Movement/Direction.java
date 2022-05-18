package Game.GameObjects.Movable.Movement;

public class Direction {

    //higher speed means slower turns
    private static final double turnSpeed = 5;

    public BiDirectionalConversion bi = new BiDirectionalConversion();
    public double degree;

    public Direction(){
        degree = 0;
    }
    public void rotateTowardN(){
        if(degree>=180){
            double destination = 360;
            rotateClockwise(destination);
        }
        if(degree<180){
            double destination = 0;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardNE(){
        if(degree>=225){
            double destination = 405;
            rotateClockwise(destination);
        }
        if(degree<225){
            double destination = 45;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardE(){
        if(degree>=270){
            double destination = 450;
            rotateClockwise(destination);
        }
        if(degree<270){
            double destination = 90;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardSE(){
        if(degree>=315){
            double destination = 495;
            rotateClockwise(destination);
        }
        if(degree<315){
            double destination = 135;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardS(){
        if(degree>=0){
            double destination = 180;
            rotateClockwise(destination);
        }
        if(degree<360){
            double destination = 180;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardSW(){
        if(degree>=45){
            double destination = 225;
            rotateClockwise(destination);
        }
        if(degree<45){
            double destination = -135;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardW(){
        if(degree>=90){
            double destination = 270;
            rotateClockwise(destination);
        }
        if(degree<90){
            double destination = -90;
            rotateCounterclockwise(destination);
        }
    }
    public void rotateTowardNW(){
        if(degree>=135){
            double destination = 315;
            rotateClockwise(destination);
        }
        if(degree<135){
            double destination = -45;
            rotateCounterclockwise(destination);
        }
    }


    public double lockDegree(double degree){
        if(degree>=360){
            return lockDegree(degree-360);
        }
        if(degree<0){
            return lockDegree(degree+359.99);
        }
        else{
            return degree;
        }
    }


    public void rotateCounterclockwise(double destination){
        double newDegree = degree - (degree-destination)/turnSpeed;
        this.setDegree(newDegree);
    }

    public void rotateClockwise(double destination){
        double newDegree = (destination-degree)/turnSpeed + degree;
        this.setDegree(newDegree);
    }

    public void setDegree(double degree) {
        this.degree = lockDegree(degree);
    }

    //0 45 90 135 180 225 270 315 360

    public void tryRotating() {
        String desired = bi.getDirection();
        if(desired == "0"){
            return;
        }
        else if(desired == "N"){
            rotateTowardN();
        }
        else if(desired == "NE"){
            rotateTowardNE();
        }
        else if(desired == "E"){
            rotateTowardE();
        }
        else if(desired == "SE"){
            rotateTowardSE();
        }
        else if(desired == "S"){
            rotateTowardS();
        }
        else if(desired == "SW"){
            rotateTowardSW();
        }
        else if(desired == "W"){
            rotateTowardW();
        }
        else if(desired == "NW"){
            rotateTowardNW();
        }
    }
}
