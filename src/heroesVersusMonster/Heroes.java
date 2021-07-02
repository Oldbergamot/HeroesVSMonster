package heroesVersusMonster;

public class Heroes extends Character{

    String name;

    Heroes(Position position, String name) {
        super(position);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    protected void attack(Monster other) {
        int dmg = other.getPV();
        other.removePV(Dice.thow(4)+generateModifier(this.force));
        dmg -= other.getPV();
        System.out.println(this.name+" attaque "
                +other.getType()
                +" et lui fait "
                +dmg
                +" point de domage");
        System.out.println(other.getType()+" n'a plus que "+other.getPV()+ " PV sur "+other.getTotalPV());

        if (other.getPV() <= 0) {
            other.setAlive(false);
            this.getLoot(other);
            System.out.println(other.getType()+" succombe à ses blessures "+
                    this.name+ " obient : "+other.getInventory().getGold()+ " or et "+other.getInventory().getLeather()+" cuirs");

        }
    }
}
