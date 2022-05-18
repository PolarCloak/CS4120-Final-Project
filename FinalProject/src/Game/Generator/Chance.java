package Game.Generator;

public class Chance {

    private int n;
    private int d;

    public Chance(int numerator, int denominator){
        this.setN(numerator);
        this.setD(denominator);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public double getChance(){
        return ((double)this.n/(double)this.d);
    }

    public String toString(){
        return(n+"/"+d);
    }
}
