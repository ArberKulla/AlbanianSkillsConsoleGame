import enums.GameState;

import javax.swing.*;
import java.awt.*;

public class GameSession {
    private static GameSession instance;

    DialogEntities.DialogEntity currentDialog;
    String name;
    Player player;
    GameState gameState;


    private GameSession(){
        this.player = new Player();
        this.name="Game :";
        this.gameState = GameState.MAIN_MENU;
    }

    public static GameSession getInstance() {
        if (instance == null) {
            instance = new GameSession();
        }
        return instance;
    }


}