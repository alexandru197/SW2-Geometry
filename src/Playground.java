import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {
        Utility utility = new Utility();
        double a = 0.7071067811865476;
        double b = 0.707106781;
        if (utility.epsilonEquality(a,b)) System.out.println("egale"); else System.out.println("inegale");

        Pair pair1 = new Pair(a,b);
        Pair pair2 = new Pair(b,a);
        ArrayList<Pair> coordinates = new ArrayList<Pair>();
        coordinates.add(pair1);
        coordinates.add(pair2);
        Shape room = new Room(coordinates);
        room.displayCoordinates();
        System.out.println();
        if (pair1.isEqualToPair(pair2)) System.out.println("perechile sunt egale"); else System.out.println("perechile sunt inegale");
    }

}
