import enums.ItemType;
import interfaces.InventoryAction;

public class ItemEntities {
    static GameSession session = GameSession.getInstance();

    public static Item[] itemList = {
            new Item(
                    "Health Potion S",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Health Potion S",
                    2,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Health Potion S",
                    3,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Health Potion M",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 50 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Health Potion M",
                    2,
                    ItemType.CONSUMABLE,
                    "You recovered 50 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Health Potion L",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 100 Health!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion S",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion S",
                    2,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion S",
                    3,
                    ItemType.CONSUMABLE,
                    "You recovered 20 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion M",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 50 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion M",
                    2,
                    ItemType.CONSUMABLE,
                    "You recovered 50 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Mana Potion L",
                    1,
                    ItemType.CONSUMABLE,
                    "You recovered 100 Mana!",
                    () -> {
                        session.player.stats.addHealth(20);
                    }
            ),
            new Item(
                    "Repel",
                    1,
                    ItemType.CONSUMABLE,
                    "You are now less likely to find monsters!",
                    () -> {
                        session.player.steps = -50;
                    }
            ),
    };

    public static class Item {
        String name;
        ItemType type;
        int amount;
        InventoryAction action;
        String description;

        public Item(String name, int amount, ItemType type, String description, InventoryAction action) {
            this.name = name;
            this.amount = amount;
            this.description = description;
            this.type = type;
            this.action = action;
        }

        public Item(String name, int amount, ItemType type) {
            this.name = name;
            this.amount = amount;
            this.type = type;
        }

        public void use() {
            amount--;
            action.execute();
        }

        @Override
        public String toString() {
            return name + " x" + amount;
        }
    }
}
