import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class FurnitureObject extends Shape{
    public int unitCost;
    public FurnitureObject(ArrayList<Point> coordinates, int unitCost) {
        super(coordinates);
        this.unitCost = unitCost;
    }

    public void rotate(double degrees) {
        //double radians = (degrees * Math.PI) / 180.0;
        double cos = Math.cos(degrees);
        double sin = Math.sin(degrees);
        for (Point point : coordinates) {
            point.x = (point.x * cos) - (point.y * sin);
            point.y = (point.x * sin) + (point.y * cos);
        }
    }

    public void translateX(double xAmount) {
        for (Point point : coordinates) {
            point.x += xAmount;
        }
    }

    public void translateY(double yAmount) {
        for (Point point : coordinates) {
            point.y += yAmount;
        }
    }

    public void translate(double xAmount, double yAmount) {
        translateX(xAmount);
        translateY(yAmount);
    }
}
