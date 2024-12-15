import enums.GameClass;
import enums.ItemType;
import utils.Stats;

public class Player {
    String name;
    GameClass gameClass;
    Stats stats;
    Inventory inventory;
    boolean isBlocking;
    int steps;
    int fightsWon;
    int x;
    int y;

    public Player() {
        this.name = "Arber";
        this.gameClass = GameClass.WARRIOR;
        this.inventory = new Inventory();
        this.steps = 0;
        this.fightsWon = 0;
    }

    public void setGameClass(GameClass gameClass) {
        switch (gameClass) {
            case WARRIOR:
                this.gameClass = GameClass.WARRIOR;
                this.stats = new Stats(100, 50, 10, 15, 5);
                inventory.addItem(new ItemEntities.Item("Sword", 1, ItemType.KEY));
                inventory.addItem(new ItemEntities.Item("Armor", 1, ItemType.KEY));
                break;
            case MAGE:
                this.gameClass = GameClass.MAGE;
                this.stats = new Stats(50, 150, 20, 5, 10);
                inventory.addItem(new ItemEntities.Item("Staff", 1, ItemType.KEY));
                inventory.addItem(new ItemEntities.Item("Robe", 1, ItemType.KEY));
                break;
            case RANGE:
                this.gameClass = GameClass.RANGE;
                this.stats = new Stats(75, 100, 15, 1, 15);
                inventory.addItem(new ItemEntities.Item("Bow", 1, ItemType.KEY));
                inventory.addItem(new ItemEntities.Item("Quiver", 1, ItemType.KEY));
                break;
        }
    }

    public String addExp(int ammount) {
        if (stats.addExp(ammount)) {
            int[] addedStats;
            switch (gameClass) {
                case WARRIOR:
                    addedStats = new int[]{10, 5, 5, 7, 2};
                    stats.addToAll(addedStats);
                    break;
                case MAGE:
                    addedStats = new int[]{5, 10, 10, 2, 2};
                    stats.addToAll(addedStats);
                    break;
                case RANGE:
                    addedStats = new int[]{10, 10, 7, 5, 5};
                    stats.addToAll(addedStats);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + gameClass);
            }

            return "\nYou leveled up to level " + stats.getLevel() + "!\n"
                    + "Added Stats: [" + addedStats[0] + " HP," + addedStats[1] + " Mana," + addedStats[2] + " Strength," + addedStats[3] + " Defense," + addedStats[4] + " Speed]";
        }
        return "";
    }

    public String showStats() {
        return name + " Stats:" + stats.toString();
    }

}
