package levels;

import enums.PromptType;
import interfaces.PromptAction;

public class Prompt {
    public PromptType type;
    public String text;
    public PromptAction action;

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
