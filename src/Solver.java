import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by bogdannitescu on 12/12/2017.
 */
public class Solver {

    public static final int ROTATION_ANGLE = 72;
    public static final int PRECISE_ROTATION_ANGLE = 5;
    public static final double OFFSET_VALUE = 0.5;
    public static final double PRECISE_OFFSET_VALUE = 0.05;


    Room room;
    ArrayList<FurnitureObject> furniture = new ArrayList<>();
    ArrayList<FurnitureObject> furnitureInRoom = new ArrayList<>();


    public Solver(Room room, ArrayList<FurnitureObject> furniture) {
        this.room = room;
        this.furniture = furniture;
    }

    public void solve() {
        Collections.sort(furniture, new CostComporator());

        double minRoomX = room.minX();
        double maxRoomX = room.maxX();
        double minRoomY = room.minY();
        double maxRoomY = room.maxY();

        for (FurnitureObject furnitureObject : furniture) {

            double minFurnitureX = furnitureObject.minX();
            double minFurnitureY = furnitureObject.minY();
            double maxFurnitureX;
            double maxFurnitureY = furnitureObject.maxY();

            for (double rotation = 0; rotation < 360 ; rotation += ROTATION_ANGLE) {
                furnitureObject.rotate(rotation);
                furnitureObject.translate(minRoomX - minFurnitureX, minRoomY - minFurnitureY);
                while (!furnitureObject.isInsideRoom(room) || furnitureObject.collidesWithFurnitureInRoom(furnitureInRoom)) {
                    furnitureObject.translateX(OFFSET_VALUE);
                    maxFurnitureX = furnitureObject.maxX();
                    if(maxFurnitureX > maxRoomX) {
                        furnitureObject.translateX(minRoomX - minFurnitureX);
                        furnitureObject.translateY(OFFSET_VALUE);
                    }

                    if(maxFurnitureY > maxRoomY) {
                        break;
                    }

                    if (isInValidPosition(furnitureObject)) {
                        break;
                    }
                }

                if (isInValidPosition(furnitureObject)) {
                    boolean ok = false;
                    do {
                        ok = false;
                        while(isInValidPosition(furnitureObject)) {
                            furnitureObject.translateY(-PRECISE_OFFSET_VALUE);
                        }
                        if(isInValidPosition(furnitureObject)) {
                            ok = true;
                        }
                        furnitureObject.translateY(PRECISE_OFFSET_VALUE);

                        while(isInValidPosition(furnitureObject)) {
                            furnitureObject.rotate(PRECISE_ROTATION_ANGLE);
                        }
                        if(isInValidPosition(furnitureObject)) {
                            ok = true;
                        }
                        furnitureObject.rotate(-PRECISE_ROTATION_ANGLE);

                        while(isInValidPosition(furnitureObject)) {
                            furnitureObject.translateX(-PRECISE_OFFSET_VALUE);
                        }
                        if(isInValidPosition(furnitureObject)) {
                            ok = true;
                        }
                        furnitureObject.translateX(PRECISE_OFFSET_VALUE);


                    } while(ok);
                    furnitureInRoom.add(furnitureObject);
                    break;
                }

            }
        }

        for (FurnitureObject furnitureObject : furnitureInRoom) {
            furnitureObject.displayCoordinates();
            System.out.println();
        }

    }

    public void displayPercentage() {
        double coveredArea = 0;
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            coveredArea += furnitureObject.area;
        }
        System.out.println();
        System.out.printf("Percentage: %.9f percents",coveredArea*100/room.area());
        System.out.println();
    }

    public void displayRoomValue() {
        double value = 0;
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            value += furnitureObject.totalCost;
        }
        System.out.println();
        System.out.printf("Value in room: %.9f", value);
        System.out.println();
    }

    private boolean isInValidPosition(FurnitureObject furnitureObject) {
        return furnitureObject.isInsideRoom(room) && !furnitureObject.collidesWithFurnitureInRoom(furnitureInRoom);
    }

    class CostComporator implements Comparator<FurnitureObject> {
        @Override
        public int compare(FurnitureObject a, FurnitureObject b) {
            if (a.totalCost < b.totalCost) return 1;
            if (a.totalCost == b.totalCost && a.area < b.area) return 1;
            return -1;
        }
    }
}
