import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Room extends Shape {
    private ArrayList<Point> coordinates = new ArrayList<Point>();
    public Room(ArrayList<Point> coordinates) {
        super(coordinates);
    }



}
