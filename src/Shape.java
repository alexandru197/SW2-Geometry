import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public abstract class Shape {

    public ArrayList<Point> coordinates = new ArrayList<Point>();
    public ArrayList<Line> lines = new ArrayList<Line>();

    public Shape(ArrayList<Point> coordinates) {

        this.coordinates = coordinates;
        Iterator iterator = coordinates.iterator();
        Point lastPoint = (Point) iterator.next();
        Point currentPoint;
        Line line;
        while (iterator.hasNext()) {
            currentPoint = (Point) iterator.next();
            line = new Line(lastPoint,currentPoint);
            this.lines.add(line);
            lastPoint = currentPoint;
        }
        currentPoint = this.coordinates.get(0);
        line = new Line(lastPoint,currentPoint);
        this.lines.add(line);
    }

    public double originDeterminant(Point a, Point b) {
        return (a.x * b.y - b.x * a.y);
    }

    public double area() {
        double area = 0;
        boolean passedFirstPoint = false;
        Point lastPoint = null;
        for (Point point : coordinates) {
            if (passedFirstPoint == false) {
                passedFirstPoint = true;
            }
            else {
                area += originDeterminant(point,lastPoint)/2;
            }
            lastPoint = point;
        }
        Point point = coordinates.get(0);
        area += originDeterminant(point,lastPoint)/2;
        return Math.abs(area);
    }

    public boolean collidesShape(Shape shape) {

        for (Point point : shape.coordinates) {
            if (point.isInsideShape(this)) {
                return true;
            }
        }

        for (Point point : this.coordinates) {
            if (point.isInsideShape(shape)) {
                return true;
            }
        }

       for (Line thisLine : this.lines) {
            for (Line shapeLine : shape.lines) {
                if (Point.linesIntersect(thisLine,shapeLine)) {
                    return true;
                }
            }
       }
        for (Line thisLine : shape.lines) {
            for (Line shapeLine : this.lines) {
                if (Point.linesIntersect(thisLine,shapeLine)) {
                    return true;
                }
            }
        }
        return false;
    }

    public double minX() {
        Point minPoint = null;
        for (Point point : coordinates) {
            if (minPoint == null) {
                minPoint = point;
            }
            else if (point.x < minPoint.x) {
                minPoint = point;
            }
        }
        return minPoint.x;
    }

    public double maxX() {
        Point minPoint = null;
        for (Point point : coordinates) {
            if (minPoint == null) {
                minPoint = point;
            }
            else if (point.x > minPoint.x) {
                minPoint = point;
            }
        }
        return minPoint.x;
    }

    public double minY() {
        Point minPoint = null;
        for (Point point : coordinates) {
            if (minPoint == null) {
                minPoint = point;
            }
            else if (point.y < minPoint.y) {
                minPoint = point;
            }
        }
        return minPoint.y;
    }


    public double maxY() {
        Point minPoint = null;
        for (Point point : coordinates) {
            if (minPoint == null) {
                minPoint = point;
            }
            else if (point.y > minPoint.y) {
                minPoint = point;
            }
        }
        return minPoint.y;
    }



    public void displayCoordinates() {
        Iterator iterator = coordinates.iterator();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            System.out.printf("("+ "%.10f"+", "+"%.10f"+")",point.x,point.y);
            if (iterator.hasNext()) System.out.print(", "); else System.out.print("; ");
        }
    }

}
