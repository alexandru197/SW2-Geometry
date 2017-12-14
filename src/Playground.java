import java.io.IOException;

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

        NewParser parser = new NewParser();
        parser.parse();
        Solver solver = new Solver(parser.room,parser.parsedFurnitureObjects);
        solver.solve();


//        Parse parse = new Parse();
//        try {
//            parse.parseFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Room room = parse.room;
//        ArrayList<FurnitureObject> parsedFurnitureObjects = parse.parsedFurnitureObjects;
//
//        Solver solver = new Solver(room, parsedFurnitureObjects);
//        solver.solve();
        solver.displayPercentage();
        solver.displayRoomValue();
//
        Visualise visualise = new Visualise(solver.furnitureInRoom,solver.room);
        visualise.writeVisualiserCode();

    }

}
