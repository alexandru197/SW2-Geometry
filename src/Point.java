/**
 * Created by bogdannitescu on 11/12/2017.
 */

public class Point extends Object{

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isEqualToPair(Point point) {
        return (Utility.epsilonEquality(this.x, point.getX())) &&
                (Utility.epsilonEquality(this.y, point.getY()));
    }
}
