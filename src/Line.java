/**
 * Created by bogdannitescu on 12/12/2017.
 */
public class Line {

    public Point startPoint;
    public Point endPoint;

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public void displayLine() {
        System.out.printf("("+ "%.9f"+", "+"%.9f"+")",startPoint.x,startPoint.y);
        System.out.print(" -- ");
        System.out.printf("("+ "%.9f"+", "+"%.9f"+")",endPoint.x,endPoint.y);
        System.out.println();
    }

}
