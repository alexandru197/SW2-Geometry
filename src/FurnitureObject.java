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
        double radians = (degrees * Math.PI) / (double) 180;
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        for (Point point : coordinates) {
            double x = point.x;
            double y = point.y;
            point.x = (x * cos) + (y * sin);
            point.y = -(x * sin) + (y * cos);
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
