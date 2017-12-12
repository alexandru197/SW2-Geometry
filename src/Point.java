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

    public static double determinant (Point a, Point b, Point c) {
        return (a.x * b.y - a.x * c.y + b.x * c.y - b.x * a.y + c.x * a.y - c.x * b.y);
    }

    public boolean isInsideShape(Shape shape) {
        boolean passedFirstPoint = false;
        Point lastPoint = null;
        int crossings = 0;
        for (Point point : shape.coordinates) {
            if (passedFirstPoint == false) {
                passedFirstPoint = true;
            }
            else {
                double slope = (point.y - lastPoint.y) / (point.x - lastPoint.x);
                boolean cond1 = (lastPoint.x <= this.x) && (this.x < point.x);
                boolean cond2 = (point.x <= this.x) && (this.x < lastPoint.x);
                boolean above = (this.y < slope * (this.x - lastPoint.x) + lastPoint.y);
                if ((cond1 || cond2) && above) crossings++;
            }
            lastPoint = point;
        }
        return (crossings % 2 != 0);
    }
}
