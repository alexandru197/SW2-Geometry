import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by bogdannitescu on 14/12/2017.
 */
public class NewParser {


    public Room room;
    public ArrayList<FurnitureObject> parsedFurnitureObjects = new ArrayList<FurnitureObject>();

    public void parse() {
        try(BufferedReader br = new BufferedReader(new FileReader("./Problems/problem2.txt"))) {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
            String everything = sb.toString();

            String[] splitString = everything.split(": ");
            everything = splitString[1];
            everything = everything.replaceAll(" ","");
            everything = everything.replaceAll("\n","");
            String[] roomAndFurniture = everything.split(Pattern.quote("#"));

            String room = roomAndFurniture[0];
            String furniture = roomAndFurniture[1];

            room = room.substring(1,room.length()-1);

            ArrayList<Point> points = new ArrayList<>();

            String[] pointsInRoom = room.split(Pattern.quote("),("));
            for (String point : pointsInRoom) {
                point = point.replaceAll(Pattern.quote(")"),"");
                String[] pointCoordinates = point.split(",");
                Point point1 = new Point(Double.parseDouble(pointCoordinates[0]), Double.parseDouble(pointCoordinates[1]));

                points.add(point1);
            }

            this.room = new Room(points);



            String[] furnitureUnitsInFurniture = furniture.split(";");
            for (String furnitureUnit : furnitureUnitsInFurniture) {
                points = new ArrayList<Point>();
                FurnitureObject furnitureObject;
                String[] costAndPoints = furnitureUnit.split(":");
                int cost = Integer.parseInt(costAndPoints[0]);
                String furnitureString = costAndPoints[1].substring(1, costAndPoints[1].length()-1);
                String[] pointsInFurniture = furnitureString.split(Pattern.quote("),("));
                for (String point : pointsInFurniture) {
                    point = point.replaceAll(Pattern.quote(")"),"");
                    String[] pointCoordinates = point.split(",");
                    Point point1 = new Point(Double.parseDouble(pointCoordinates[0]), Double.parseDouble(pointCoordinates[1]));
                    points.add(point1);
                }
                furnitureObject = new FurnitureObject(points,cost);
                parsedFurnitureObjects.add(furnitureObject);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
