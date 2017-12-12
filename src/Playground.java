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
        ArrayList<Point> coordinates = new ArrayList<Point>();
        coordinates.add(point3);
        coordinates.add(point4);
        coordinates.add(point5);
        coordinates.add(point6);
        FurnitureObject furnitureObject = new FurnitureObject(coordinates,1);
        furnitureObject.displayCoordinates();
        System.out.println();
        System.out.printf("Area = %.9f",furnitureObject.area());
        furnitureObject.rotate(36);
        System.out.println();
        furnitureObject.displayCoordinates();
        System.out.println();
        System.out.printf("Area = %.9f",furnitureObject.area());

        System.out.println();
        Point point1 = new Point(2,2.7);
        if (point1.isInsideShape(furnitureObject)) System.out.println("Is inside"); else System.out.println("not inside");

    }

}
