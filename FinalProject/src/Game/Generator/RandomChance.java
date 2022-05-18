package Game.Generator;

import java.util.Random;

public class RandomChance {

    private Chance[] chances;
    private int mostRecentRandom;

    public RandomChance(Chance[] listOfChances){
        this.chances = listOfChances;
    }
    public Chance[] getChances() {
        return chances;
    }
    public int getMostRecentRandom() {
        return this.mostRecentRandom;
    }

    //returns the index of chosen item, or -1 if the chance rolled onto an empty chance.
    //returns -2 if the total was more than 100%, and cannot be generated.
    public int chooseRandom(){
        if(chances == null){
            return -1;
        }
        double percent = this.getTotalPercentage() * 100;
        if(percent>100){
            System.out.format("RandomChance: total percentage is too high: %.2f\n", percent);
            return -2;
        }
        Chance[] chanceLCD = getChancesWithLCD();
        Random r = new Random();
        int ran = r.nextInt(chanceLCD[0].getD()+1);
        for(int i=0;i<chances.length;i++){
            int n = chanceLCD[i].getN();
            //we are subtracting the chance of this option from the random gen, if the value was inside the random gen
            //then we have found where that random gen lied and thus found the randomly chosen index.
            if(ran-n <=0){
                mostRecentRandom = i;
                return i;
            }
            else{ran -= n;}
        }
        return -1;
    }

    //returns the total percentage of all of the chances added up. Used to check for less than 100%
    private double getTotalPercentage(){
        double total = 0;
        for(int i = 0; i<chances.length; i++){
            total+= chances[i].getChance();
        }
        return total;
    }

    private Chance[] getChancesWithLCD(){
        return(LCD.convertAllWithLCD(this.chances));
    }
}

class LCD {
    private static int gcd (int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
    private static int lcm (int a, int b) {
        return (a * b) / gcd(a, b);
    }
    private static int getLCM(int[] values) {
        int a = values[0], b = 0;
        for(int i = 1; i<values.length; i++){
            b = values[i];
            a = lcm(a,b);
        }
        return a;
    }
    static Chance[] convertAllWithLCD(Chance[] chances){
        Chance[] lcdChances = new Chance[chances.length];
        int[] d = new int[chances.length];
        for(int i = 0; i<chances.length; i++){
            d[i] = chances[i].getD();
        }
        int lcd = getLCM(d);
        for(int i=0;i<chances.length;i++){
            int mult = lcd / chances[i].getD();
            lcdChances[i] = new Chance(chances[i].getN() * mult, lcd);
        }
        return lcdChances;
    }
}
class main{
    public static void main(String[] args){
        Chance[] c = new Chance[]{new Chance(1,54), new Chance(2,32), new Chance(5,20)};
        RandomChance rc = new RandomChance(c);
        int ran = rc.chooseRandom();
        System.out.println("index chosen: " + ran);

    }
}

