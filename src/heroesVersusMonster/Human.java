package heroesVersusMonster;

public class Human extends Heroes{

    Human (Position position, String name) {
        super(position, name);
        this.force += 1;
        this.sta += 1;
    }
}
