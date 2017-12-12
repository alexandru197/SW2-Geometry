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

    public static boolean linesDefinesByFourPointsIntersect(Point line1StartPoint, Point line1EndPoint, Point line2StartPoint, Point line2EndPoint) {
        int test1, test2;
        test1 = Point.counterClockwiseTurn(line1StartPoint,line1EndPoint,line2StartPoint) *
                Point.counterClockwiseTurn(line1StartPoint,line1EndPoint,line2EndPoint);
        test2 = Point.counterClockwiseTurn(line2StartPoint,line2EndPoint,line1StartPoint) *
                Point.counterClockwiseTurn(line2StartPoint,line2EndPoint,line1EndPoint);
        return (test1 <= 0) && (test2 <= 0);
    }

    static boolean onSegment(Point p, Point q, Point r)
    {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }
    public static int orientation(Point p, Point q, Point r)
    {
        // See http://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;  // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    public static boolean pointsDoIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    public static boolean linesIntersect(Line line1, Line line2) {
        return pointsDoIntersect(line1.startPoint,line1.endPoint,line2.startPoint,line2.endPoint);
    }

    public static double determinant (Point a, Point b, Point c) {
        return (a.x * b.y - a.x * c.y + b.x * c.y - b.x * a.y + c.x * a.y - c.x * b.y);
    }

    public boolean isInsideShape (Shape shape) {
        double area = 0;
        boolean passedFirstPoint = false;
        Point lastPoint = null;
        for (Point point : shape.coordinates) {
            if (passedFirstPoint == false) {
                passedFirstPoint = true;
            }
            else {
                area += Math.abs(determinant(point,lastPoint,this))/2;
            }
            lastPoint = point;
        }
        Point point = shape.coordinates.get(0);
        area += Math.abs(determinant(point,lastPoint,this))/2;
        return (Utility.epsilonEquality(area,shape.area()));
    }

//    public boolean isInsideShape(Shape shape) {
//        boolean passedFirstPoint = false;
//        Point lastPoint = null;
//        int crossings = 0;
//        for (Point point : shape.coordinates) {
//            if (passedFirstPoint == false) {
//                passedFirstPoint = true;
//            }
//            else {
//                double slope = (point.y - lastPoint.y) / (point.x - lastPoint.x);
//                boolean cond1 = (lastPoint.x <= this.x) && (this.x < point.x);
//                boolean cond2 = (point.x <= this.x) && (this.x < lastPoint.x);
//                boolean above = (this.y < slope * (this.x - lastPoint.x) + lastPoint.y);
//                if ((cond1 || cond2) && above) crossings++;
//            }
//            lastPoint = point;
//        }
//        Point point = shape.coordinates.get(0);
//        double slope = (point.y - lastPoint.y) / (point.x - lastPoint.x);
//        boolean cond1 = (lastPoint.x <= this.x) && (this.x < point.x);
//        boolean cond2 = (point.x <= this.x) && (this.x < lastPoint.x);
//        boolean above = (this.y < slope * (this.x - lastPoint.x) + lastPoint.y);
//        if ((cond1 || cond2) && above) crossings++;
//        return (crossings % 2 != 0);
//    }
}
