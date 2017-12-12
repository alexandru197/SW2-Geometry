import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {

        Point point3 = new Point(1,2);
        Point point4 = new Point(4,2);
        Point point5 = new Point(4,3);
        Point point6 = new Point(1,3);

        Point point7 = new Point(1,2);
        Point point8 = new Point(4,2);
        Point point9 = new Point(4,3);
        Point point10 = new Point(1,3);
        ArrayList<Point> roomCoordinates= new ArrayList<Point>();
        roomCoordinates.add(point7);
        roomCoordinates.add(point8);
        roomCoordinates.add(point9);
        roomCoordinates.add(point10);
        Room room = new Room(roomCoordinates);

        ArrayList<Point> coordinates = new ArrayList<Point>();
        coordinates.add(point3);
        coordinates.add(point4);
        coordinates.add(point5);
        coordinates.add(point6);
        FurnitureObject furnitureObject = new FurnitureObject(coordinates,1);
        System.out.println();
        Parse parse = new Parse();
        parse.parseFile();
        furnitureObject.displayCoordinates();
        System.out.println();
        System.out.printf("Area = %.9f",furnitureObject.area());

        System.out.println();
        furnitureObject.displayCoordinates();
        System.out.println();
        System.out.printf("Area = %.9f",furnitureObject.area());

        System.out.println();
        Point point1 = new Point(2,3);
        if (point1.isInsideShape(furnitureObject)) System.out.println("Is inside"); else System.out.println("not inside");

        if (furnitureObject.isInsideRoom(room)) System.out.println("Furniture is inside the room"); else
            System.out.println("Furniture is outside the room");

    }

}
