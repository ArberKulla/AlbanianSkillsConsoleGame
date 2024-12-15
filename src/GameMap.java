import utils.GameLevel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameMap {
    GameLevel currentLevel;
    GameSession session = GameSession.getInstance();
    MainMenu menu = MainMenu.getInstance();

    public GameMap(){
        currentLevel = new GameLevel();
    }

    public void enterMap(){
        menu.textArea.append("\n"+currentLevel.flavor);
    }

    public void printMap() {
        currentLevel.placePlayer(session.player.x, session.player.y);
        menu.panel.setBackground(currentLevel.color);
        menu.panel.removeAll();
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
        menu.frame.revalidate();
        menu.frame.repaint();
    }

    public void movePlayer(int newX, int newY){
        Player player = session.player;
        int x = player.x;
        int y = player.y;
        if(newX<0){changeLevel('W'); return;}
        else if(newX==currentLevel.grid.length){changeLevel('S'); return;}
        else if (newY<0){changeLevel('A'); return;}
        else if(newY==currentLevel.grid.length){changeLevel('D'); return;}


        char nextTile = currentLevel.grid[newX][newY];
        if(nextTile=='=') {
            menu.textArea.append("\nYou hit a wall!");
            return;
        }
        else if(nextTile=='\u2728'){
            foundTressure();
        }
        else if(nextTile=='\u263A'){
            foundNPC();
        }
        else{
            randomEncounter();
        }
        session.player.steps++;


        player.x = newX;
        player.y = newY;
        currentLevel.updateMap(x, y, newX, newY);
        printMap();
    }

    public void randomEncounter(){
        int steps = session.player.steps;
        double encounterChance = 1.0+0.01*steps;
        if(encounterChance>=Math.random()*2+1){
            session.player.steps=0;
            menu.startBattle();
        }
    }


    public void changeLevel(char key){
        currentLevel.grid[session.player.x][session.player.y]=' ';
        GameLevel newLevel = new GameLevel();
        switch (key) {
            case 'W':
                if (currentLevel.upLevel != null) {
                    currentLevel = currentLevel.upLevel;
                } else {
                    currentLevel.upLevel = newLevel;
                    newLevel.downLevel = currentLevel;
                    currentLevel = newLevel;
                }
                session.player.x = currentLevel.grid.length-1;
                session.player.y = currentLevel.grid.length/2;
                break;
            case 'A':
                if(currentLevel.leftLevel!=null){
                    currentLevel = currentLevel.leftLevel;
                }
                else {
                currentLevel.leftLevel = newLevel;
                newLevel.rightLevel = currentLevel;
                currentLevel = newLevel;
                }
                session.player.x = currentLevel.grid.length/2;
                session.player.y = currentLevel.grid.length-1;
                break;
            case 'S':
                if(currentLevel.downLevel!=null){
                    currentLevel = currentLevel.downLevel;
                }
                else {
                    currentLevel.downLevel = newLevel;
                    newLevel.upLevel = currentLevel;
                    currentLevel = newLevel;
                }
                session.player.x = 0;
                session.player.y = currentLevel.grid.length/2;
                break;
            case 'D':
                if(currentLevel.rightLevel!=null){
                    currentLevel = currentLevel.rightLevel;
                }
                else {
                    currentLevel.rightLevel = newLevel;
                    newLevel.leftLevel = currentLevel;
                    currentLevel = newLevel;
                }
                session.player.x = currentLevel.grid.length/2;
                session.player.y = 0;
                break;
        }

        printMap();
        menu.textArea.append("\n"+newLevel.flavor);
    }

    public void foundTressure(){
        Random random = new Random();
        int randomIndex = random.nextInt(ItemEntities.itemList.length);
        ItemEntities.Item randomItem = ItemEntities.itemList[randomIndex];
        menu.textArea.append("\nGame: You found x"+randomItem.amount+" "+randomItem.name);
        session.player.inventory.addItem(randomItem);
    }

    public void foundNPC(){
        Random random = new Random();
        int randomIndex = random.nextInt(DialogEntities.randomDialog.length);
        DialogEntities.DialogEntity randomDialog = DialogEntities.randomDialog[randomIndex];
        menu.startDialog(randomDialog);
    }
}
