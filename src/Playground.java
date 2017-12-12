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

        Solver solver = new Solver(room,keptFurtnitureObjects);
        solver.solve();
        solver.displayPercentage();
        solver.displayRoomValue();
    }

}
