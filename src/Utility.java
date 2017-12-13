/**
 * Created by bogdannitescu on 11/12/2017.
 */
public class Utility {

    static final double EPSILON = 0.0000000001;

    static boolean epsilonEquality (double a, double b) {
        double diff = Math.abs(a-b);
        return diff < EPSILON;
    }
}
