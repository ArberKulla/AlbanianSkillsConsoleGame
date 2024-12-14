import enums.GameClass;

public class Player {
    String name= "Red";
    GameClass gameClass;
    Stats stats;
    Inventory inventory;
    int x;
    int y;

    public Player(){
        this.name = "Arber";
        this.gameClass = GameClass.WARRIOR;
    }

    public void setGameClass(GameClass gameClass) {
        switch (gameClass) {
            case WARRIOR:
            this.gameClass = GameClass.WARRIOR;
            this.stats = new Stats(100, 50, 10, 15, 5);
            break;
            case MAGE:
                this.gameClass = GameClass.MAGE;
                this.stats = new Stats(100, 50, 10, 15, 5);
                break;
            case RANGE:
                this.gameClass = GameClass.RANGE;
                this.stats = new Stats(100, 50, 10, 15, 5);
                break;
        }
    }


}
