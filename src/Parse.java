import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parse {


    public static String readFile () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
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

        try {
            myString = p.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s1 = myString.substring(3, myString.indexOf('#') + 2);

        String myString1 = myString.replace(s1 ,"");

        s1 = s1.substring(0, s1.length() - 2);
//        System.out.println("Camera este : " +s1);

//      STRUCTURE   var p1 = b1.create('point', [10, 10], {name:'',size:0});

        int points = 1;
        int number = 0;
        int j = 0;
        String s2;
        ArrayList<Point> coordinates = new ArrayList<>();
        double sumX = 0;
        double max = 0;
        Double y = 0.0;
        Double x;
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
                    System.out.println("var p" + number/2 + " = b1.create('point', [" + x + ", " + y + "], {name:'',size:0});");
                }
            }
            else j++;

        }
        System.out.print("var camera = b1.create('polygon',[");
        for(int k = 1; k <= number/2; ++k){
            if(k < number/2) System.out.print("p" + k + ",");
            else System.out.print("p" + k);
        }
        System.out.print("], {fillColor: \"red\"});");
        System.out.println("");

        Room room = new Room(coordinates);


        int i=0;
        sumX += max + 1;
        s1 = myString1.substring(0, 3);
        myString1 = myString1.replace(s1 ,"");
        for (String val: myString1.split(";")){
            System.out.println("");
            System.out.println("");
            System.out.println("");
            max = 0;
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
                             System.out.println("p" + number/2 + " = b1.create('point', [" + (x+2*sumX) + ", " + y + "], {name:'',size:0});");
                    }
                }
                else j++;

            }
            sumX += max + 1;
            System.out.print("object = b1.create('polygon',[");
            for(int k = 1; k <= number/2; ++k){
                if(k < number/2) System.out.print("p" + k + ",");
                else System.out.print("p" + k);
            }
            System.out.print("], {fillColor: \"green\"});");
            System.out.println("");

            //FurnitureObject furniture = new FurnitureObject(coordinates);



            // System.out.println(val);
        }


        System.out.println("");

    }
}


