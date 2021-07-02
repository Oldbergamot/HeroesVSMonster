package heroesVersusMonster;

public class Inventory {

    private int gold;
    private int leather;

    Inventory() {
        this.gold = 0;
        this.leather = 0;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addLeather(int leather) {
        this.leather += leather;
    }

    public int getGold() {
        return gold;
    }

    public int getLeather() {
        return leather;
    }
}
