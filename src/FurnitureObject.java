import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class FurnitureObject extends Shape{
    public ArrayList<Pair> coordinates = new ArrayList<Pair>();
    public int unitCost;
    public FurnitureObject(ArrayList<Point> coordinates, int unitCost) {
        super(coordinates);
        this.unitCost = unitCost;
    }

    public void rotate(double degrees) {
        System.out.println("Rotated with "+degrees+" degrees");
    }

}
