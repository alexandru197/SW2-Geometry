/**
 * Created by bogdannitescu on 11/12/2017.
 */

public class Pair extends Object{

    double x;
    double y;

    public Pair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isEqualToPair(Pair pair) {
        return (Utility.epsilonEquality(this.x,pair.getX())) &&
                (Utility.epsilonEquality(this.y,pair.getY()));
    }
}
