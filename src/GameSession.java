import enums.GameState;
import utils.GameLevel;

import java.util.ArrayList;

public class GameSession {
    private static GameSession instance;

    DialogEntities.DialogEntity currentDialog;
    String name;
    Player player;
    GameState gameState;
    GameMap map;
    Battle currentBattle;


    private GameSession(){
        this.player = new Player();
        this.name="Game: ";
        this.gameState = GameState.MAIN_MENU;
    }

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }

    public GameSession loadFromSave(String name, Player player, GameState gameState, GameLevel currentLevel, ArrayList<GameLevel> levels) {
        if (instance == null) {
            instance = new GameSession();
        }
        this.map = new GameMap();
        map.currentLevel = currentLevel;
        map.levels = levels;
        this.name = name;
        this.player = player;
        this.gameState = gameState;
        return instance;
    }


}
