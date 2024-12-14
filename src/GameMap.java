import javax.swing.*;
import java.awt.*;

public class GameMap {
    GameLevel currentLevel;
    GameSession session = GameSession.getInstance();
    MainMenu menu = MainMenu.getInstance();

    public GameMap(){
        currentLevel = new GameLevel();
    }

    public void printMap() {
        currentLevel.placePlayer(session.player.x, session.player.y);
        menu.panel.setLayout(new GridLayout(currentLevel.grid.length, currentLevel.grid.length));
        // Set GridLayout
        // Add JLabel for each character in the array
        for (int i = 0; i < currentLevel.grid.length; i++) {
            for (int j = 0; j < currentLevel.grid[i].length; j++) {
                JLabel label = new JLabel(String.valueOf(currentLevel.grid[i][j]), SwingConstants.CENTER);
                label.setFont(new Font("Monospaced", Font.PLAIN, 20));  // Monospaced font for consistent character width
                menu.panel.add(label);
            }
        }

        menu.textArea.setText("");
        menu.frame.revalidate();
        menu.frame.repaint();


    }
}
