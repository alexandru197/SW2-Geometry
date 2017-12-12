import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by alexandrubondor on 12/12/2017.
 */
public class Visualise {
    ArrayList<FurnitureObject> objectsInRoom = new ArrayList<FurnitureObject>();
    Room room;

    public static final String fileToToWrite = "./visualisation/index.html";

    public Visualise(ArrayList<FurnitureObject> objectsInRoom, Room room) {
        this.objectsInRoom = objectsInRoom;
        this.room = room;
    }

    public static void drawRoom(Room room, PrintWriter writer){
        for (int i = 0; i < room.coordinates.size(); ++i) {
            writer.println("var p" + (i + 1) + " = b1.create('point', [" + room.coordinates.get(i).x + ", " + room.coordinates.get(i).y + "], {name:'',size:0});");
        }
        writer.print("shape = b1.create('polygon',[");
        for (int k = 1; k <= room.coordinates.size(); ++k) {
            if (k < room.coordinates.size()) writer.print("p" + k + ",");
            else writer.print("p" + k);
        }
        writer.print("], {fillColor: \"white\",  fillOpacity: 0.8}); \n\n\n");
    }

    public static void drawFurniture(ArrayList<FurnitureObject> objectsInRoom, PrintWriter writer) {
        for (FurnitureObject furnitureObject : objectsInRoom) {
            int k = 0;
            for (Point point : furnitureObject.coordinates) {
                writer.println("p" + (k + 1) + " = b1.create('point', [" + point.x + ", " + point.y + "], {name:'',size:0});");
                k++;
            }
            writer.print("object = b1.create('polygon',[");
            for (k = 1; k <= furnitureObject.coordinates.size(); ++k) {
                if (k < furnitureObject.coordinates.size()) writer.print("p" + k + ",");
                else writer.print("p" + k);
            }
            writer.println("], {fillColor: \"purple\",  fillOpacity: " + (furnitureObject.getTotalCost() / 100 + 0.3) + "});\n\n");
        }
    }


    public void writeVisualiserCode(){

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileToToWrite, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Parse.indexBegin(fileToToWrite, writer);

        drawRoom(this.room, writer);

        drawFurniture(this.objectsInRoom, writer);

        Parse.indexEnd(fileToToWrite, writer);

    }

}
