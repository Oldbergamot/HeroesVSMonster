package heroesVersusMonster;

import java.util.Random;

public class Whelp extends Monster{
    Whelp(Position position) {
        super(position);
        this.sta += 1;
        this.type = "dragonnet";
    }

    private void generateLoot() {
        Random r = new Random();
        this.inventory.addLeather(r.nextInt(10));
        this.inventory.addGold(r.nextInt(15));
    }
}
