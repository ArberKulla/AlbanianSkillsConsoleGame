import enums.PromptType;
import interfaces.PromptAction;

public class Prompt {
    PromptType type;
    String text;
    PromptAction action;

    public Prompt(String text, PromptType type, PromptAction action){
        this.action=action;
        this.text=text;
        this.type=type;
    }
    public Prompt(String text, PromptType type){
        this.text=text;
        this.type=type;
    }

    public void onResponse(String text){
        action.execute(text);
    }
}
