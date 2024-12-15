import enums.ItemType;
import enums.PromptType;
import interfaces.GameStateAction;
import interfaces.InventoryAction;
import levels.Prompt;

import java.util.ArrayList;

public class Inventory {
    ArrayList<ItemEntities.Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public String useItem(int index){
        index=index-1;
        if(index>=items.size() || index<0){
            return "No such item exists!";
        }

        ItemEntities.Item item = items.get(index);

        if(item.type== ItemType.KEY){
            return "This item cannot be used!";
        }

        if(item.amount==1){
            items.remove(item);
        }

        item.use();
        return item.description;
    }

    public void addItem(ItemEntities.Item newItem){
        for(ItemEntities.Item item : items){
            if(item.name.equals(newItem.name)){
                item.amount+=newItem.amount;
                return;
            }
        }
        items.add(newItem);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Current Inventory:\n");
        for(int i=0;i<items.size();i++){
            builder.append(items.get(i).toString());
            if(i!=items.size()-1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
