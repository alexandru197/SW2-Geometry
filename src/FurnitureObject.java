import java.util.ArrayList;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class FurnitureObject extends Shape{
    public int unitCost;
    public double area;
    public double totalCost;
    public FurnitureObject(ArrayList<Point> coordinates, int unitCost) {
        super(coordinates);
        this.unitCost = unitCost;
        this.area = area();
        this.totalCost = getTotalCost();
    }

    public void rotate(double degrees) {
        double radians = (degrees * Math.PI) / (double) 180;
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double xTranslate = coordinates.get(0).x;
        double yTranslate = coordinates.get(0).y;
        translate(-xTranslate,-yTranslate);
        for (Point point : coordinates) {
            double x = point.x;
            double y = point.y;
            point.x = (x * cos) + (y * sin);
            point.y = -(x * sin) + (y * cos);
        }
        translate(xTranslate,yTranslate);
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

    public boolean isInsideRoom (Room room) {
        for (Point point : this.coordinates) {
            if (!point.isInsideShape(room)) {
                return false;
            }
        }

//        for (Line thisLine : this.lines) {
//            for (Line shapeLine : room.lines) {
//                if (Point.linesIntersect(thisLine, shapeLine)) {
//                    return false;
//                }
//            }
//        }
        return true;
    }

    public double getTotalCost(){
        double totalCost = this.unitCost * this.area;
        return totalCost;
    }

    public boolean collidesWithFurnitureInRoom(ArrayList<FurnitureObject> furnitureInRoom) {
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            if (this.collidesShape(furnitureObject)) return true;
        }
        return false;
    }
}
