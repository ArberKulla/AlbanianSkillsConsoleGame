import enums.GameClass;
import enums.ItemType;
import utils.Stats;

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
        this.inventory = new Inventory();
    }

    public void setGameClass(GameClass gameClass) {
        switch (gameClass) {
            case WARRIOR:
            this.gameClass = GameClass.WARRIOR;
            this.stats = new Stats(100, 50, 10, 15, 5);
            inventory.addItem(new ItemEntities.Item("Sword",1, ItemType.KEY));
            inventory.addItem(new ItemEntities.Item("Armor",1, ItemType.KEY));
                break;
            case MAGE:
                this.gameClass = GameClass.MAGE;
                this.stats = new Stats(100, 50, 10, 15, 5);
                inventory.addItem(new ItemEntities.Item("Staff",1, ItemType.KEY));
                inventory.addItem(new ItemEntities.Item("Robe",1, ItemType.KEY));
                break;
            case RANGE:
                this.gameClass = GameClass.RANGE;
                this.stats = new Stats(100, 50, 10, 15, 5);
                inventory.addItem(new ItemEntities.Item("Bow",1, ItemType.KEY));
                inventory.addItem(new ItemEntities.Item("Quiver",1, ItemType.KEY));
                break;
        }
    }

    public String showStats(){
        return name+" Stats:"+stats.toString();
    }


}
