import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {
        Utility utility = new Utility();
        double a = 0.7071067811865476;
        double b = 0.707106781;
//        if (utility.epsilonEquality(a,b)) System.out.println("egale"); else System.out.println("inegale");

        Point point1 = new Point(a,b);
        Point point2 = new Point(b,a);
        ArrayList<Point> coordinates = new ArrayList<Point>();
        coordinates.add(point1);
        coordinates.add(point2);
        Shape room = new Room(coordinates);
//        room.displayCoordinates();
        System.out.println();
//        if (point1.isEqualToPair(point2)) System.out.println("perechile sunt egale"); else System.out.println("perechile sunt inegale");
        FurnitureObject square = new FurnitureObject(coordinates);

        Parse output = new Parse();
        output.parseFile();


    }

}
