import java.io.IOException;

/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Playground {
    public static void main (String[] args) {

        Parse parse = new Parse();
        try {
            parse.parseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
