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


    public boolean isEqualToPair(Point point) {
        return (Utility.epsilonEquality(this.x, point.x)) &&
                (Utility.epsilonEquality(this.y, point.x));
    }

    public double distanceToPoint(Point point) {
        return Math.hypot(this.x - point.x,this.y-point.y);
    }

    public static int counterClockwiseTurn(Point a, Point b, Point c) {
        double area2 = (b.x - a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if (area2 < 0) return -1; // clockwise
        if (area2 > 0) return 1; // counterclockwise
        return 0; // collinear
    }

    public static boolean pointsAreCollinear(Point a, Point b, Point c) {
        return counterClockwiseTurn(a, b, c) == 0;
    }

    public static boolean linesIntersect(Point line1StartPoint, Point line1EndPoint, Point line2StartPoint, Point line2EndPoint) {
        int test1, test2;
        test1 = Point.counterClockwiseTurn(line1StartPoint,line1EndPoint,line2StartPoint) *
                Point.counterClockwiseTurn(line1StartPoint,line1EndPoint,line2EndPoint);
        test2 = Point.counterClockwiseTurn(line2StartPoint,line2EndPoint,line1StartPoint) *
                Point.counterClockwiseTurn(line2StartPoint,line2EndPoint,line1EndPoint);
        return (test1 <= 0) && (test2 <= 0);
    }
}
