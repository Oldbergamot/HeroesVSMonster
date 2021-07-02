package heroesVersusMonster;

public class Monster extends Character{

    protected short x;
    protected short y;
    String type;

    Monster(Position position) {
        super(position);
    }

    protected void attack(Heroes other) {
        int dmg = other.getPV();
        other.removePV(Dice.thow(4)+generateModifier(this.force));
        dmg -= other.getPV();
        System.out.println(this.type+" attaque "
                +other.getName()
                +" et lui fait "
                +dmg
                +" point de domage");

        if (other.getPV() <= 0) {
            other.setAlive(false);
            this.getLoot(other);
            System.out.println(other.getName()+" succombe à ses blessures"+
                    this.type+ " obient : "+other.getInventory().getGold()+ "or et "+other.getInventory().getLeather()+" cuirs");
        }

    }
    public String getType() {
        return type;
    }
}
