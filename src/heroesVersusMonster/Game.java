package heroesVersusMonster;

import java.util.Scanner;

public class Game {

    private final short SIZE = 15;
    private boolean isOver;
    private Heroes player;
    private Board board;

    Game(){
        this.isOver = false;
        this.board = new Board(SIZE);
        this.player = createPlayer();
    }

    private Heroes createPlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bonjour fier héros!");
        String race;

        do {
            System.out.println("Veuillez choisir votre race (Human/Dwarf)");
            race = sc.nextLine();
        }
        while(!race.equals("Human") && !race.equals("Dwarf"));

        System.out.println("Quel est votre nom?");
        String name = sc.nextLine();
        if (race.equals("Human")) return new Human(new Position((short)0,(short)0), name);
        return new Dwarf(new Position((short)0,(short)0), name);
    }


    public boolean isGameOver() {
        if(this.board.getMonsters().size()==0 || !this.player.getAlive() || isOver ) return true;
        return false;
    }

    public void play() {
        askDecision();
    }

    public void askDecision() {
        Scanner sc = new Scanner(System.in);

        //logique de vérification
        System.out.println("Voulez vous avancer (N,S,E,W), attaquer ou abandonner?");
        String s = sc.nextLine();
        if(s.equals("abandonner")) {
            isOver = true;
            System.out.println("\u001B[91m"+"you are die");
        }
        else if(s.equals("attaquer")) {
            if(checkMonster()) {
                attackOneRound();
            }
        }

        else {
            switch (s){
                case "N" : {
                    if (board.isMovePossible(Direction.NORTH, player.getPosition())){
                        player.setPosition(new Position(player.getPosition().getX(), (short) (player.getPosition().getY()-1)));
                        System.out.println(player.getPosition().getX()+ ", "+player.getPosition().getY());
                        if(checkMonster()) {
                            attackOneRound();
                        }
                    }
                };
                    break;
                case "S" : {
                    if (board.isMovePossible(Direction.SOUTH, player.getPosition())) {
                        player.setPosition(new Position(player.getPosition().getX(), (short) (player.getPosition().getY() +1)));
                        System.out.println(player.getPosition().getX()+ ", "+player.getPosition().getY());
                        if (checkMonster()) {
                            attackOneRound();
                        }
                    }
                }
                    break;
                case "E" :{
                    if (board.isMovePossible(Direction.EAST, player.getPosition())) {
                        player.setPosition(new Position((short) (player.getPosition().getX() + 1), (player.getPosition().getY())));
                        System.out.println(player.getPosition().getX()+ ", "+player.getPosition().getY());
                        if (checkMonster()) {
                            attackOneRound();
                        }
                    }
                }
                    break;
                case "W" : {
                    if (board.isMovePossible(Direction.WEST, player.getPosition())) {
                        player.setPosition(new Position((short) (player.getPosition().getX() - 1), (short) (player.getPosition().getY())));
                        System.out.println(player.getPosition().getX()+ ", "+player.getPosition().getY());
                        if (checkMonster()) {
                            attackOneRound();
                        }
                    }
                }
                    break;
           }
        }
        System.out.println(player.getPosition().getX()+ ", "+player.getPosition().getY());
    }

    public void attackOneRound() {
        Monster monster = (Monster) board.getMonsters()
                .stream()
                .filter(m -> m.getPosition()
                        .equals(player.getPosition()));
        player.attack(monster);
        monster.attack(player);
        if(!monster.isAlive) board.removeMonster(monster);
    }

    public boolean checkMonster() {
        return board.getMonsters().stream()
                .anyMatch(monster -> monster.getPosition().equals(player.getPosition()));
    }

    public void launch() {

        while(!this.isGameOver()) {
            this.displayBoard();
            this.play();        }
    }

    public void displayBoard() {
        board.display(player);
    }

}
