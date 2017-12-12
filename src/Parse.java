
import java.io.*;
import java.util.ArrayList;

public class Parse {


    public static String readFile () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Problems/problem26.txt"));
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




    public static void parseFile(){
        String myString = new String();
        Parse p = new Parse();


        PrintWriter writer = null;
        try {
            writer = new PrintWriter("./visualisation/index.html", "UTF-8");
           // writer = new PrintWriter("output.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            myString = p.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s1 = myString.substring(3, myString.indexOf('#') + 2);

        String myString1 = myString.replace(s1 ,"");

        s1 = s1.substring(0, s1.length() - 2);

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
                "            // var b1 = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-4, 2, 6, -4]});\n" +
                "            var b1 = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-10, 90, 90, -10], axis:true});\n" +
                "            ");
        int points = 1;
        int number = 0;
        int j = 0;
        String s2;
        ArrayList<Point> coordinates = new ArrayList<>();
        double sumX = 0;
        double max = 0;
        Double y = 0.0;
        Double x;
        double roomHeight = 0;
        double height = 0;
        while(j < s1.length()){
            s2 = "";
            if(Character.isDigit(s1.charAt(j)) || s1.charAt(j) == '-') {
                if(s1.charAt(j) == '-'){
                    s2 = "-";
                    j++;
                }
                while (Character.isDigit(s1.charAt(j)) || s1.charAt(j) == '.') {
                    s2 += s1.charAt(j);
                    j++;
                }
                x = y;
                y = Double.parseDouble(s2);
                number ++;
                if(number % 2 == 0) {
                    coordinates.add(new Point(x, y));
                    if (Double.compare(x, max) > 0) {
                        max = x;
                    }
                    if (Double.compare(y, roomHeight) > 0) {
                        roomHeight = y;
                    }
                    //System.out.println("var p" + number/2 + " = b1.create('point', [" + x + ", " + y + "], {name:'',size:0});");
                    writer.println("var p" + number/2 + " = b1.create('point', [" + x + ", " + y + "], {name:'',size:0});");
                }
            }
            else j++;

        }
        writer.print("var camera = b1.create('polygon',[");
        for(int k = 1; k <= number/2; ++k){
            if(k < number/2) writer.print("p" + k + ",");
            else writer.print("p" + k);
        }
        writer.print("], {fillColor: \"red\",  fillOpacity: 0.8});");
        writer.println("");
        writer.println("var object;");

        Room room = new Room(coordinates);
        ///////////////


        int i=0;
        sumX += max + 1;
        s1 = myString1.substring(0, 3);
        myString1 = myString1.replace(s1 ,"");
        double min = 0;
        int obj = 0;
        for (String val: myString1.split(";")){
            coordinates = new ArrayList<Point>();

            String unit = val.substring(0, val.indexOf(':'));
            System.out.println(unit);
            int unitCost = Integer.parseInt(unit);
            obj++;
            writer.println("");
            writer.println("");
            writer.println("");
            max = 0;
            min = 0;
            y = 0.0;
            x = 0.0;
            j = 2;
            number = 0;
            while(j < val.length()){
                s2 = "";
                if(Character.isDigit(val.charAt(j)) || val.charAt(j) == '-') {
                    if(val.charAt(j) == '-'){
                        s2 = "-";
                        j++;
                    }
                    while (Character.isDigit(val.charAt(j)) || val.charAt(j) == '.') {
                        s2 += val.charAt(j);
                        j++;
                    }
                    x = y;
                    y = Double.parseDouble(s2);
                    number ++;
                    if(number % 2 == 0) {
                        coordinates.add(new Point(x, y));
                        if (Double.compare(x, max) > 0) {
                            max = x;
                        }

                        if (Double.compare(x, min) < 0) {
                            min = x;
                        }

                        if(x < 0) {
                            height += 10 + 2;
                            sumX = 10;
                        }
                    }
                }
                else j++;


            }
            if(obj % 15 == 0) {
                height += 10 + 2;
                sumX = 10;
            }
            sumX += (max + min) + 2;

            for(int k = 0; k < number/2; k++){
                writer.println("p" + (k+1) + " = b1.create('point', [" + (coordinates.get(k).x+sumX) + ", " + (coordinates.get(k).y + height)  + "], {name:'',size:0});");
                if(Double.compare(coordinates.get(k).x+sumX, max) > 0)
                    max = coordinates.get(k).x+sumX;
            }

            sumX = max;
            writer.print("object = b1.create('polygon',[");
            for(int k = 1; k <= number/2; ++k){
                if(k < number/2) writer.print("p" + k + ",");
                else writer.print("p" + k);
            }
            double cost = (double) unitCost;
            writer.print("], {fillColor: \"purple\",  fillOpacity: " + (cost / 100 + 0.3) + "});");
            System.out.println(cost);

            FurnitureObject furniture = new FurnitureObject(coordinates, unitCost);



            // writer.println(val);
        }


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



}


