import enums.GameClass;
import enums.GameState;
import enums.PromptType;
import interfaces.GameStateAction;
import levels.Prompt;

public class DialogEntities {
     static MainMenu menu = MainMenu.getInstance();
     static GameSession session = GameSession.getInstance();

    public static DialogEntity[] randomDialog = {
            new DialogEntity(
                    "Blacksmith",
                    new Prompt[]{
                            new Prompt(
                                    "Hello traveler, do you want me to upgrade you wears?\nY/N",
                                    PromptType.REACTION,
                                    (text) -> {
                                        switch (text.toUpperCase()){
                                            case("Y"):
                                            case("YES"):
                                                menu.textArea.append("\nBlacksmith: Excellent! Your wears are now stronger!\n+5 to Strength!");
                                                session.player.stats.setStrength(session.player.stats.getStrength()+5);
                                                break;
                                            case("N"):
                                            case("NO"):
                                                menu.textArea.append("\nBlacksmith: Suit yourself traveler....");
                                                break;
                                            default:
                                                session.currentDialog.currentIndex--;
                                        }
                                    }

                            ),
                    },
                    ()->{
                        goToWorld();
                    }
            ),
            new DialogEntity(
                    "Medic",
                    new Prompt[]{
                            new Prompt(
                                    "Hello traveler, care for a checkup?\nY/N",
                                    PromptType.REACTION,
                                    (text) -> {
                                        switch (text.toUpperCase()){
                                            case("Y"):
                                            case("YES"):
                                                menu.textArea.append("\nMedic: There, this medicine should get you up to speed!\n+10 to Max Health!");
                                                session.player.stats.setMaxHp(session.player.stats.getMaxHp()+10);
                                                session.player.stats.addHealth(10);
                                                break;
                                            case("N"):
                                            case("NO"):
                                                menu.textArea.append("\nMedic: You're not an apple are you?");
                                                break;
                                            default:
                                                session.currentDialog.currentIndex--;
                                        }
                                    }

                            ),
                    },
                    ()->{
                        goToWorld();
                    }
            ),
            new DialogEntity(
                    "Historian",
                    new Prompt[]{
                            new Prompt(
                                    "I can tell you about this land, would you like to hear?\nY/N",
                                    PromptType.REACTION,
                                    (text) -> {
                                        switch (text.toUpperCase()){
                                            case("Y"):
                                            case("YES"):
                                                break;
                                            case("N"):
                                            case("NO"):
                                                menu.textArea.append("\nHistorian: Your loss kid!");
                                                session.currentDialog.currentIndex=-1;
                                                break;
                                            default:
                                                session.currentDialog.currentIndex--;
                                        }
                                    }

                            ),
                            new Prompt(
                                    "Ah, so you wish to know about Tajran, do you? Sit close, and I’ll tell you what the old tales say.",
                                    PromptType.SKIPPABLE
                            ),
                            new Prompt(
                                    "Long ago, before the twin suns began their dance across the sky, Tajran was but a land of chaos. The skies roared with elder storms, wild and untamed, striking the ground with such fury that no life could take root. It was the Ancients—beings of wisdom and power—who bound the storms to the highest peaks, chaining them with songs that still echo in the winds.",
                                    PromptType.SKIPPABLE
                            ),
                            new Prompt(
                                    "They say the deserts of Tajran weren’t always golden, no. Once, they were teeming jungles, with rivers so clear you could see the fish as if they were jewels floating on air. But greed, my friend, greed and ambition, led to the great Blooming Sands War. The mages of the East sought to harness the Worldheart—a great crystal buried deep below Tajran—to make their kingdoms eternal. They shattered it instead. Its shards scattered across the land, and the magic that spilled forth turned the jungles into dunes and the rivers into veins of glass.",
                                    PromptType.SKIPPABLE
                            ),
                            new Prompt(
                                    "And the twin suns? Ah, now that is a tale as old as time itself. The elders claim they were once two celestial siblings who loved Tajran dearly, but they quarreled over how to care for it. One wanted endless light to nourish the land, the other wanted darkness so it could rest. Their battle still rages above, though the gods cursed them to only touch for a fleeting moment at dawn and dusk.",
                                    PromptType.SKIPPABLE
                            ),
                            new Prompt(
                                    "Tajran, my young friend, is not just a land—it is a story written in the stars, the sands, and the storms. And we are but scribes, adding our own lines to its eternal tale. Now... what story will you write in its sands?",
                                    PromptType.SKIPPABLE
                            )
                    },
                    ()->{
                        goToWorld();
                    }
            )
    };


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
                session.map.enterMap();
            }
    );

    //Game END
    public static DialogEntity GAME_OVER = new DialogEntity(
            "Game",
            new Prompt[]{
                    new Prompt(
                            "You Lost!",
                            PromptType.SKIPPABLE
                    )},
                    ()->{
                        menu.gameOver();
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

        public DialogEntity(String name, Prompt[] prompts) {
            this.name = name;
            this.prompts = prompts;
        }

        public void next(String text){
            if(currentIndex>0) {
                if (prompts[currentIndex-1].type == PromptType.REACTION) {
                    prompts[currentIndex-1].action.execute(text);
                }
            }

            if(currentIndex==-1){
                finished();
                goToWorld();
                return;
            }

                if(currentIndex<prompts.length) {
                    menu.textArea.append("\n"+name +": "+ prompts[currentIndex].text);
                }

                if(currentIndex>=prompts.length) {
                    finished();
                }

                currentIndex++;
        }

        public void finished(){
            if(action!=null){
            action.execute();
            }
        }


    }

    public static void goToWorld(){
        session.gameState= GameState.OPEN_WORLD;
        session.currentDialog.currentIndex=0;
        menu.processText("");
    }
}