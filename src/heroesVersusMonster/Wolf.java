package heroesVersusMonster;

import java.util.Random;

public class Wolf extends Monster{
    Wolf(Position position) {
        super(position);
        generateLoot();
        this.type = "Loup";
    }

    private void generateLoot() {
        Random r = new Random();
        this.inventory.addLeather(r.nextInt(6));
    }
}
