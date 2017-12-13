import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {

        Parse parse = new Parse();
        try {
            parse.parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Room room = parse.room;
        ArrayList<FurnitureObject> parsedFurnitureObjects = parse.parsedFurnitureObjects;
        ArrayList<FurnitureObject> keptFurtnitureObjects = new ArrayList<FurnitureObject>();

        keptFurtnitureObjects.add(parsedFurnitureObjects.get(0));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(1));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(2));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(3));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(4));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(5));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(6));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(7));
        keptFurtnitureObjects.add(parsedFurnitureObjects.get(8));

        Solver solver = new Solver(room, parsedFurnitureObjects);
        solver.solve();
        solver.displayPercentage();
        solver.displayRoomValue();

        Visualise visualise = new Visualise(solver.furnitureInRoom,solver.room);
        visualise.writeVisualiserCode();
//        Point p1 = new Point(0, 0);
//        Point p2 = new Point(4, 0);
//        Point q1 = new Point(2, 1);
//        Point q2 = new Point(1, -1);
//        System.out.println(Point.pointsDoIntersect(p1, p2, q1, q2));
    }

}
