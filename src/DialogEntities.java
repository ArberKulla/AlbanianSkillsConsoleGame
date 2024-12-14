import enums.GameClass;
import enums.GameState;
import enums.PromptType;
import interfaces.GameStateAction;

public class DialogEntities {
     static MainMenu menu = MainMenu.getInstance();
     static GameSession session = GameSession.getInstance();

    //START GAME
    public static DialogEntity GAME_START = new DialogEntity(
            "Game",
            new Prompt[]{
            new Prompt(
                    "...",
                    PromptType.SKIPPABLE
            ),
            new Prompt(
                    "You wake up in your bed...",
                    PromptType.SKIPPABLE
            ),
            new Prompt(
                    "You get out of the house....",
                    PromptType.SKIPPABLE
            ),
            new Prompt(
                    "Its time to explore!",
                    PromptType.SKIPPABLE
            ),
    },
            ()->{
                session.map = new GameMap();
                session.player.x= session.map.currentLevel.grid.length/2;
                session.player.y= session.map.currentLevel.grid.length/2;
                session.gameState= GameState.OPEN_WORLD;
                menu.processText("");
            }
    );

    // NEW GAME
    public static DialogEntity NEW_GAME = new DialogEntity(
            "Nugua"
            ,new Prompt[]{
            new Prompt(
                    "My child, what shall be your name?",
                    PromptType.REACTION,
                    (text) -> {
                        session.player.name=text;
                        menu.textArea.append("\nNagua: "+session.player.name+"... a wonderful name.... ");
                    }
            ),
            new Prompt(
                    "What path will you choose my child?\n1. Warrior\n2. Mage\n3. Range",
                    PromptType.REACTION,
                    (text) -> {
                        switch(text.toUpperCase()){
                            case ("WARRIOR"):
                            case ("1"):
                                session.player.setGameClass(GameClass.WARRIOR);
                                break;
                            case ("MAGE"):
                            case ("2"):
                                session.player.setGameClass(GameClass.MAGE);
                                break;
                            case ("RANGE"):
                            case ("3"):
                                session.player.setGameClass(GameClass.RANGE);
                                break;
                            default:
                                session.currentDialog.currentIndex--;
                        }
                    }
            ),
            new Prompt(
                    "A wonderful choice.... welcome to the world of Tajran!",
                    PromptType.SKIPPABLE
            ),
    },
            ()->{
        menu.startDialog(GAME_START);
            }
    );



    // Inner class representing more complex dialog objects
    public static class DialogEntity {
        String name;
        int currentIndex=0;
        Prompt[] prompts;
        GameStateAction action;

        public DialogEntity(String name, Prompt[] prompts, GameStateAction action) {
            this.name = name;
            this.prompts = prompts;
            this.action = action;
        }

        public void next(String text){
            if(currentIndex>0) {
                if (prompts[currentIndex-1].type == PromptType.REACTION) {
                    prompts[currentIndex-1].action.execute(text);
                }
            }

                if(currentIndex<prompts.length) {
                    menu.textArea.append("\n"+name +": "+ prompts[currentIndex].text);
                }

                if(currentIndex==prompts.length) {
                    finished();
                }

                currentIndex++;
        }

        public void finished(){
            action.execute();
        }


    }
}