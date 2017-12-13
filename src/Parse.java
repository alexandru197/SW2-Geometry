
import java.io.*;
import java.util.ArrayList;

public class Parse {

    public static final String fileToToWrite = "./visualisation/index.html";

    public Room room;
    public ArrayList<FurnitureObject> parsedFurnitureObjects = new ArrayList<FurnitureObject>();


    public static String readFile () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./Problems/problem2.txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        String everything = new String();

        try{while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
            everything = sb.toString();


        }catch(IOException ex){
            System.out.println (ex.toString());
            System.out.println("Could not find file " + br);
        }
        return everything;
    }

    public void parseRoom(String roomString, PrintWriter writer){
        int numberOfCoordinates = 0;
        String numberToConvert;
        ArrayList<Point> coordinates = new ArrayList<>();
        double sumXaxis = 0;
        double max = 0;
        Double x, y = 0.0;
        int j = 0;
        while(j < roomString.length()){
            numberToConvert = "";
            if(Character.isDigit(roomString.charAt(j)) || roomString.charAt(j) == '-') {
                if(roomString.charAt(j) == '-'){
                    numberToConvert = "-";
                    j++;
                }
                while (Character.isDigit(roomString.charAt(j)) || roomString.charAt(j) == '.') {
                    numberToConvert += roomString.charAt(j);
                    j++;
                }
                x = y;
                y = Double.parseDouble(numberToConvert);
                numberOfCoordinates ++;
                if(numberOfCoordinates % 2 == 0) {
                    coordinates.add(new Point(x, y));
                }
            }
            else j++;

        }

        writeShapeCode(numberOfCoordinates, coordinates, "red", 0, 0, 0, writer);

        Room room = new Room(coordinates);
        this.room = room;
    }

    public void parseFurniture(String roomString, String furnitureString, PrintWriter writer){
        int i=0;
        double sumXaxis = 50;
        ArrayList<Point> coordinates = new ArrayList<>();
        roomString = furnitureString.substring(0, 3);
        furnitureString = furnitureString.replace(roomString ,"");
        double max = 0;
        Double x, y = 0.0;
        int j = 0;
        double min = 0;
        int obj = 0;
        String numberToConvert;
        double height = 10;
        for (String val: furnitureString.split(";")){
            coordinates = new ArrayList<Point>();

            String unit = val.substring(0, val.indexOf(':'));
            int unitCost = Integer.parseInt(unit);
            obj++;
            max = 0;
            min = 0;
            y = 0.0;
            x = 0.0;
            j = 2;
            int numberOfCoordinates = 0;
            while(j < val.length()){
                numberToConvert = "";
                if(Character.isDigit(val.charAt(j)) || val.charAt(j) == '-') {
                    if(val.charAt(j) == '-'){
                        numberToConvert = "-";
                        j++;
                    }
                    while (Character.isDigit(val.charAt(j)) || val.charAt(j) == '.') {
                        numberToConvert += val.charAt(j);
                        j++;
                    }
                    x = y;
                    y = Double.parseDouble(numberToConvert);
                    numberOfCoordinates ++;
                    if(numberOfCoordinates % 2 == 0) {
                        coordinates.add(new Point(x, y));
                        if (Double.compare(x, max) > 0) {
                            max = x;
                        }

                        if (Double.compare(x, min) < 0) {
                            min = x;
                        }

                        if(x < 0) {
                            height += 20;
                            sumXaxis = 10;
                        }
                    }
                }
                else j++;


            }
            if(obj % 15 == 0) {
                height += 20;
                sumXaxis = 10;
            }
            sumXaxis += (max + min) + 2;

            double cost = (double) unitCost;

            writeShapeCode(numberOfCoordinates, coordinates, "purple", sumXaxis, height, cost, writer);

            FurnitureObject furniture = new FurnitureObject(coordinates, unitCost);
            this.parsedFurnitureObjects.add(furniture);

        }
    }


    public static void indexBegin(String fileToToWrite, PrintWriter writer){

//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(fileToToWrite, "UTF-8");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        writer.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Visualiser</title>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"jsxgraph.css\" />\n" +
                "<script type=\"text/javascript\" src=\"jsxgraphcore.js\"></script>\n" +
                "</head>\n" +
                "<body onload=\"myFunction()\">\n" +
                "\n" +
                "<jsxgraph box=\"jxgbox\" width=\"1000\" height=\"1000\" ></jsxgraph>\n" +
                "\n" +
                "<script>\n" +
                "        function myFunction() {\n" +
                "            var b1 = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-10, 90, 90, -10], axis:true});\n" +
                "           var shape; \n\n");

        //writer.close();
    }

    public static void indexEnd(String fileToToWrite, PrintWriter writer) {
//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(fileToToWrite, "UTF-8");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        writer.println(" }\n" +
                "       \n" +
                "    </script>\n" +
                "\n" +
                "<div id=\"jxgbox\" class=\"jxgbox\" style=\"height: 98vh; width: 98vh;\"></div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");

        writer.close();
    }

    public static void writeShapeCode(int numberOfCoordinates, ArrayList<Point> coordinates, String color, double sumXaxis, double height, double cost, PrintWriter writer){
//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(fileToToWrite, "UTF-8");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        double max = 0;

        if(color == "red") {
            for (int i = 0; i < numberOfCoordinates / 2; ++i) {
                writer.println("var p" + (i + 1) + " = b1.create('point', [" + coordinates.get(i).x + ", " + coordinates.get(i).y + "], {name:'',size:0});");
            }
            writer.print("shape = b1.create('polygon',[");
            for (int k = 1; k <= numberOfCoordinates / 2; ++k) {
                if (k < numberOfCoordinates / 2) writer.print("p" + k + ",");
                else writer.print("p" + k);
            }
            writer.print("], {fillColor: \"red\",  fillOpacity: 0.8}); \n\n\n");
        }
        else {
            for (int k = 0; k < numberOfCoordinates / 2; k++) {
                writer.println("p" + (k + 1) + " = b1.create('point', [" + (coordinates.get(k).x + sumXaxis) + ", " + (coordinates.get(k).y + height) + "], {name:'',size:0});");
                if (Double.compare(coordinates.get(k).x + sumXaxis, max) > 0)
                    max = coordinates.get(k).x + sumXaxis;
            }
            sumXaxis = max;
            writer.print("object = b1.create('polygon',[");
            for (int k = 1; k <= numberOfCoordinates / 2; ++k) {
                if (k < numberOfCoordinates / 2) writer.print("p" + k + ",");
                else writer.print("p" + k);
            }
            writer.println("], {fillColor: \"" + color + "\",  fillOpacity: " + (cost / 100 + 0.3) + "});\n\n");
        }

    }


    public void parseFile() throws IOException {
        String problem = new String();
        Parse input = new Parse();

        try {
            problem = input.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileToToWrite, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String roomString = problem.substring(3, problem.indexOf('#') + 2);
        String furnitureString = problem.replace(roomString ,"");
        roomString = roomString.substring(0, roomString.length() - 2);

        indexBegin(fileToToWrite, writer);

        parseRoom(roomString, writer);

        parseFurniture(roomString, furnitureString, writer);

        indexEnd(fileToToWrite, writer);


    }



}


