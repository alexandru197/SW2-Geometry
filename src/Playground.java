import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {

        ArrayList<FurnitureObject> furniture = new ArrayList<FurnitureObject>();
        ArrayList<Point> coordinates = new ArrayList<Point>();

        Point point1 = new Point(0,0);
        Point point2 = new Point(4,0);
        Point point3 = new Point(4,4);
        Point point4 = new Point(0,4);

        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        Room room = new Room(coordinates);

        coordinates = new ArrayList<Point>();
        point1 = new Point(0,0);
        point2 = new Point(1,0);
        point3 = new Point(1,1);
        point4 = new Point(0,1);


        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        FurnitureObject furnitureObject = new FurnitureObject(coordinates,1);

        furniture.add(furnitureObject);

        coordinates = new ArrayList<Point>();
        point1 = new Point(0,0);
        point2 = new Point(2,0);
        point3 = new Point(2,2);
        point4 = new Point(0,2);


        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        furnitureObject = new FurnitureObject(coordinates,1);

        furniture.add(furnitureObject);

        Solver solver = new Solver(room,furniture);
        solver.solve();
        solver.displayPercentage();
        solver.displayRoomValue();



    }

}
