import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public abstract class Shape {

    private ArrayList<Pair> coordinates = new ArrayList<Pair>();

    public Shape(ArrayList<Pair> coordinates) {
        this.coordinates = coordinates;
    }

    public double surface() {
        double surface = 0;
        return surface;
    }

    public ArrayList<Pair> getCoordinates() {
        return coordinates;
    }

    public void displayCoordinates() {
        Iterator iterator = coordinates.iterator();
        while (iterator.hasNext()) {
            Pair pair = (Pair) iterator.next();
            System.out.print("("+pair.getX()+", "+pair.getY()+")");
            if (iterator.hasNext()) System.out.print(", "); else System.out.print("; ");
        }
    }

}
