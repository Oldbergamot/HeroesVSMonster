package heroesVersusMonster;

public abstract class Character {
    protected int force;
    protected int sta;
    protected int totalPV;
    protected int PV;
    protected Inventory inventory;
    protected boolean isAlive;
    protected Position position;

    Character(Position position) {
        this.sta = generateStat(6);
        this.force = generateStat(6);
        this.PV =  generatePV();
        this.totalPV = PV;
        this.inventory = new Inventory();
        this.isAlive = true;
        this.position = position;
        }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public int getTotalPV() {
        return totalPV;
    }

    public void setTotalPV(int totalPV) {
        this.totalPV = totalPV;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Generate an array of int. Fill the array with the value of a dice
     * return the sum of the 3 biggest throw
     */
    protected int generateStat(int size) {
        int []  array = new int[size];
        int min = 6;
        int sum = 0;

        //filling the array
        for(int i = 0 ; i < array.length ; i++) {
            array[i] = Dice.thow(6);
            if (array[i]<min) min = array[i];
        }
        //compute sum
        for(int i = 0 ; i < array.length ; i++) {
            if (array[i] == min) continue;
            sum += array[i];
        }
        return sum;
    }
    private int generatePV() {
        return this.sta + generateModifier(this.sta);
    }

    protected int generateModifier(int i) {
        if(i<5) return -1;
        else if(i<10) return 0;
        else if(i<15) return 1;
        return 2;
    }

    protected boolean getAlive() {
        return this.isAlive;
    }

    protected void setAlive (boolean b) {
        this.isAlive = b;
    }

    protected void removePV(int PV) {
        this.PV -= PV;
    }

    public void displayPV() {
        System.out.println("PV : "+this.PV);
    }

    public void getLoot(Character other) {
        this.inventory.addGold(other.getInventory().getGold());
        this.inventory.addLeather(other.getInventory().getLeather());
    }

    public Inventory getInventory() {
        return this.inventory;
    }


    public void restoreHealth() {
        this.PV = this.totalPV;
    }

}
