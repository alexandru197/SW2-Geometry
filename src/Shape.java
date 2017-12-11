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

    public double surface() {
        double surface = 0;
        return surface;
    }


    public void displayCoordinates() {
        Iterator iterator = coordinates.iterator();
        while (iterator.hasNext()) {
            Point point = (Point) iterator.next();
            System.out.print("("+ point.x+", "+ point.y+")");
            if (iterator.hasNext()) System.out.print(", "); else System.out.print("; ");
        }
    }

}
