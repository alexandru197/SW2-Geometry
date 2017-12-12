import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public abstract class Shape {

    public ArrayList<Point> coordinates = new ArrayList<Point>();

    public Shape(ArrayList<Point> coordinates) {
        this.coordinates = coordinates;
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


    public void displayCoordinates() {
        Iterator iterator = coordinates.iterator();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            System.out.printf("("+ "%.9f"+", "+"%.9f"+")",point.x,point.y);
            if (iterator.hasNext()) System.out.print(", "); else System.out.print("; ");
        }
    }

}
