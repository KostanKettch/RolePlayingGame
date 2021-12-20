import java.util.Random;
public class Dice {
    public static int dice(int d) {
        Random rand = new Random();
        return rand.nextInt(d) + 1;
    }
}
