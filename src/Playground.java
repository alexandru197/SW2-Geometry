import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {

        Point point1 = new Point(1,2);
        Point point2 = new Point(4,2);
        Point point3 = new Point(4,3);
        Point point4 = new Point(1,3);

        ArrayList<Point> coordinates = new ArrayList<Point>();
        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);
        coordinates.add(point4);

        FurnitureObject furnitureObject1 = new FurnitureObject(coordinates,1);

        point1 = new Point(0,2);
        point2 = new Point(3,4);
        point3 = new Point(0,4);


        coordinates = new ArrayList<Point>();
        coordinates.add(point1);
        coordinates.add(point2);
        coordinates.add(point3);


        FurnitureObject furnitureObject2 = new FurnitureObject(coordinates,2);

        if (furnitureObject1.collidesShape(furnitureObject2)) System.out.println("They collide"); else
            System.out.println("They don't collide");


    }

}
