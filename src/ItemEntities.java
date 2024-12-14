import enums.ItemType;
import interfaces.InventoryAction;

public class ItemEntities {
    static MainMenu menu = MainMenu.getInstance();
    static GameSession session = GameSession.getInstance();


    public static class Item {
        String name;
        ItemType type;
        int amount=0;
        InventoryAction action;
        String description;

        public Item(String name,int amount, ItemType type, String description, InventoryAction action) {
            this.name = name;
            this.amount = amount;
            this.description = description;
            this.type = type;
            this.action = action;
        }

        public Item(String name,int amount, ItemType type) {
            this.name = name;
            this.amount = amount;
            this.type = type;
        }

        public void use(){
            action.execute();
        }

        @Override
        public String toString(){
            return name+"x "+amount;
        }
    }
}
