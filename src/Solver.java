import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {

    public static final double ROTATION_ANGLE = 30;
    public static final double PRECISE_ROTATION_ANGLE = 0.1;
    public static final double OFFSET_VALUE = 0.5;
    public static final double PRECISE_OFFSET_VALUE = 0.01;
    public static final int PERCENTAGE_BREAKPOINT = 100;
    public static final double SCORE_BREAKPOINT = 100000000;
    int counter = 0;

//    public static final int ROTATION_ANGLE = 90;
//    public static final int PRECISE_ROTATION_ANGLE = 45;
//    public static final double OFFSET_VALUE = 1;
//    public static final double PRECISE_OFFSET_VALUE = 0.5;

    Room room;
    ArrayList<FurnitureObject> furniture = new ArrayList<>();
    ArrayList<FurnitureObject> furnitureInRoom = new ArrayList<>();


    public Solver(Room room, ArrayList<FurnitureObject> furniture) {
        this.room = room;
        this.furniture = furniture;
    }

    public void solve() {
        Collections.sort(furniture, new CostComporator());
//        Collections.shuffle(furniture);

        double minRoomX = room.minX();
        double maxRoomX = room.maxX();
        double minRoomY = room.minY();
        double maxRoomY = room.maxY();
        double coveredArea = 0;
        double roomValue = 0;

        for (FurnitureObject furnitureObject : furniture) {
            if (coveredArea*100/room.area() > PERCENTAGE_BREAKPOINT) {
                System.out.println();
                System.out.println("Covered "+ PERCENTAGE_BREAKPOINT + "% ... BREAKING ... ");
                System.out.println();
                break;
            }

            counter++;
            System.out.println("Testing object " + counter + " out of " + furniture.size() + " objects ..." + counter * 100 / furniture.size()+ "% ... " +
                    + coveredArea*100/room.area() + "% covered ....");
            System.out.println("Room value is... : "+roomValue);
            double minFurnitureX = furnitureObject.minX();
            double minFurnitureY = furnitureObject.minY();
            double maxFurnitureX = furnitureObject.maxX();
            double maxFurnitureY = furnitureObject.maxY();

            for (double rotation = 0; rotation < 360 ; rotation += ROTATION_ANGLE) {
                furnitureObject.translate(minRoomX - minFurnitureX, minRoomY - minFurnitureY);
                while (!isInValidPosition(furnitureObject)) {

                    maxFurnitureX = furnitureObject.maxX();
                    maxFurnitureY = furnitureObject.maxY();
                    minFurnitureX = furnitureObject.minX();
                    minFurnitureY = furnitureObject.minY();
                    if(maxFurnitureX > maxRoomX) {
                        furnitureObject.translateX(minRoomX - minFurnitureX);
                        furnitureObject.translateY(OFFSET_VALUE);
                        maxFurnitureX = furnitureObject.maxX();
                        maxFurnitureY = furnitureObject.maxY();
                        minFurnitureX = furnitureObject.minX();
                        minFurnitureY = furnitureObject.minY();
                    }

                    if(maxFurnitureY > maxRoomY) {
                        break;
                    }

                    if (isInValidPosition(furnitureObject)) {
                        break;
                    }
                    furnitureObject.translateX(OFFSET_VALUE);
                    furnitureObject.rotate(ROTATION_ANGLE);
                }
                if (isInValidPosition(furnitureObject)) {
                    boolean ok = false;

                    do {
                        int countLoops = 0;
                        ok = false;

                        while(isInValidPosition(furnitureObject)) {
                            countLoops++;
                            furnitureObject.translateY(-PRECISE_OFFSET_VALUE);
                        }
                        if(countLoops > 1) {
                            ok = true;
                        }
                        if(countLoops >= 1) {
                            furnitureObject.translateY(PRECISE_OFFSET_VALUE);
                        }

                        countLoops = 0;
                        while(isInValidPosition(furnitureObject)) {
                            countLoops++;
                            furnitureObject.rotate(PRECISE_ROTATION_ANGLE);
                        }
                        if(countLoops > 1) {
                            ok = true;
                        }
                        if(countLoops >= 1) {
                            furnitureObject.rotate(-PRECISE_ROTATION_ANGLE);
                        }

                        countLoops = 0;
                        while(isInValidPosition(furnitureObject)) {
                            countLoops++;
                            furnitureObject.translateX(-PRECISE_OFFSET_VALUE);
                        }
                        if(countLoops > 1) {
                            ok = true;
                        }
                        if(countLoops >= 1) {
                            furnitureObject.translateX(PRECISE_OFFSET_VALUE);
                        }

                    } while(ok);

                    furnitureInRoom.add(furnitureObject);
                    coveredArea += furnitureObject.area;
                    System.out.println("Adding furniture object... :");
                    furnitureObject.displayCoordinates();
                    System.out.println();
                    roomValue += furnitureObject.totalCost;
                    break;
                }
                if(roomValue >= SCORE_BREAKPOINT) {
                    break;
                }

            }

        }
        System.out.println();
        System.out.println("Printing solution ...");
        System.out.println();
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            furnitureObject.displayCoordinates();
        }

    }

    public double getRoomValue() {
        double value = 0;
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            value += furnitureObject.totalCost;
        }
        return value;
    }

    public void displayPercentage() {
        double coveredArea = 0;
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            coveredArea += furnitureObject.area;
        }
        System.out.println();
        System.out.printf("Percentage: %.10f percents",coveredArea*100/room.area());
        System.out.println();
    }

    public void displayRoomValue() {
        double value = 0;
        for (FurnitureObject furnitureObject : furnitureInRoom) {
            value += furnitureObject.totalCost;
        }
        System.out.println();
        System.out.printf("Value in room: %.10f", value);
        System.out.println();
    }

    private boolean isInValidPosition(FurnitureObject furnitureObject) {
        //return !furnitureObject.collidesWithFurnitureInRoom(furnitureInRoom);
        return furnitureObject.isInsideRoom(room) && !furnitureObject.collidesWithFurnitureInRoom(furnitureInRoom);
    }

    class CostComporator implements Comparator<FurnitureObject> {
        @Override
        public int compare(FurnitureObject a, FurnitureObject b) {
            if (a.totalCost < b.totalCost) return 1;
            if (a.totalCost == b.totalCost && a.area < b.area) return 1;
            if (a.totalCost == b.totalCost && a.area == b.area) return 0;
            return -1;
        }
    }

    class AreaComporator implements Comparator<FurnitureObject> {
        @Override
        public int compare(FurnitureObject a, FurnitureObject b) {
            if (a.area > b.area) return 1;
            if (a.area == b. area) return 0;
            return -1;
        }
    }
}
