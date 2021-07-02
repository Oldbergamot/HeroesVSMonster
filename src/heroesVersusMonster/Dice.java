package heroesVersusMonster;

import java.util.Random;

public class Dice {
    public static int thow(int side) {
        Random r = new Random();
        return r.nextInt(side)+1;
    }
}
