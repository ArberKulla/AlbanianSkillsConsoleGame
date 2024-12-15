import enums.GameState;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private static MainMenu instance;
    GameSession session;
    JFrame frame;
    JTextArea textArea;
    JTextField textField;
    JPanel panel;


    private MainMenu(GameSession session) {
        //Sync Game State
        this.session = session;

        // Create the main frame
        frame = new JFrame("Tajran Saga");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.BLACK);
        frame.add(panel, BorderLayout.NORTH);
        // Create a text area for displaying text
        textArea = new JTextArea(session.name + "Welcome to the fantasitcal World of Tajran!\nPlease select an action:\n1. New Game\n2. Continue\n3. Multiplayer");
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(780, 200));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create a text field for user input
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(780, 20));
        frame.add(textField, BorderLayout.SOUTH);

        // Add action listener to update the text area when text is entered
        textField.addActionListener(e -> {
            processText(textField.getText());
        });

        // Make the frame visible
        frame.setVisible(true);

    }


    public void processText(String text) {
        //Main Menu Selection
        switch (session.gameState) {
            case MAIN_MENU:
                switch (text.toUpperCase()) {
                    case ("NEW"):
                    case ("1"):
                        textArea.setText("Lets begin this epic journey!");
                        startDialog(DialogEntities.NEW_GAME);
                        break;
                    case ("CONTINUE"):
                    case ("2"):
                        //Load Save Logic
                        textArea.setText("Last time, on Battlemons!");
                        SaveManger saveManger = new SaveManger();
                        GameSession session2 = saveManger.loadGame();
                        if (session2 != null) {
                            processText("");
                        }
                        break;
                    case ("MULTIPLAYER"):
                    case ("3"):
                        //Load Save Logic
                        textArea.append("\nYou must buy the DLC to access this mode!");
                        break;
                    default:
                        textArea.setText("Welcome to the fantasitcal World of Tajran!\nPlease select an action:\n1. New Game\n2. Continue\n3. Multiplayer");
                }
                break;


            case DIALOG:
                DialogEntities.DialogEntity dialog = session.currentDialog;
                dialog.next(text);
                break;


            case OPEN_WORLD:
                switch (text.toUpperCase()) {
                    case ("87"):
                    case ("W"):
                        session.map.movePlayer(session.player.x - 1, session.player.y);
                        break;
                    case ("65"):
                    case ("A"):
                        session.map.movePlayer(session.player.x, session.player.y - 1);
                        break;
                    case ("83"):
                    case ("S"):
                        session.map.movePlayer(session.player.x + 1, session.player.y);
                        break;
                    case ("68"):
                    case ("D"):
                        session.map.movePlayer(session.player.x, session.player.y + 1);
                        break;
                    case ("HELP"):
                        textArea.append("\nGame: You should walk around, collect loot, and kill monsters!\n" +
                                "Helpful Prompts:\n" +
                                "Stats -> Shows your current stats!\n" +
                                "Inventory -> Shows your current inventory!\n" +
                                "Inventory use (number) -> Uses the item in that current inventory slot!");
                        break;
                    case ("STATS"):
                        textArea.append("\n" + session.player.showStats());
                        break;
                    case ("SAVE"):
                        SaveManger saveManger = new SaveManger();
                        saveManger.saveGame();
                        textArea.append("\nGame: Game successfully saved!");
                        break;
                    default:
                        session.map.printMap();
                }

                if (text.toUpperCase().contains("INVENTORY")) {
                    String[] args = text.split(" ");
                    if (args.length == 1) {
                        textArea.append("\n" + session.player.inventory.toString());
                    } else if (args.length == 3) {
                        String indexString = args[2];
                        int index;
                        try {
                            index = Integer.valueOf(indexString);
                        } catch (NumberFormatException exception) {
                            return;
                        }

                        textArea.append("\n" + session.player.inventory.useItem(index));
                    }
                }
                break;
            case BATTLE:
                Player player = session.player;
                player.isBlocking=false;
                switch (text.toUpperCase()) {
                    case ("W"):
                    case ("A"):
                    case ("S"):
                    case ("D"):
                        break;
                    case ("1"):
                    case ("ATTACK"):
                        textArea.append("\nYou attack with all your might!");
                        session.currentBattle.playerAttackEnemy(player.stats.getStrength());
                        break;
                    case ("2"):
                    case ("BLOCK"):
                        textArea.append("\nYou put up your guard! +20 Mana!");
                        session.player.isBlocking = true;
                        session.currentBattle.enemyTurn();
                        break;
                    case ("3"):
                    case ("SPELL"):
                        if (player.stats.getMana() >= 20) {
                            textArea.append("\nYou cast a mighty spell! -20 Mana!");
                            session.currentBattle.playerAttackEnemy(player.stats.getStrength() + 10);
                            session.player.stats.setMana(session.player.stats.getMana() - 20);
                        } else {
                            textArea.append("\nGame: You do not have enough mana for a spell");
                        }
                        break;
                    case ("STATS"):
                        textArea.append("\n" + session.player.showStats());
                        break;
                    case ("STATS ENEMY"):
                        textArea.append("\n" + session.currentBattle.enemy.showStats());
                        break;
                    default:
                        textArea.append("\nGame: Here is what your curent actions are!\n" +
                                "1. Attack: Attack the enemy normally\n" +
                                "2. Block: Makes you take half damage next turn and restores mana!!\n" +
                                "3. Spell: Uses mana for a powerful spell!\n" +
                                "4. Inventory: Uses the inventory feature!");

                        break;
                }

                if (text.toUpperCase().contains("INVENTORY") || text.toUpperCase().contains("4")) {
                    String[] args = text.split(" ");
                    if (args.length == 1) {
                        textArea.append("\n" + session.player.inventory.toString());
                    } else if (args.length == 3) {
                        String indexString = args[2];
                        int index;
                        try {
                            index = Integer.valueOf(indexString);
                        } catch (NumberFormatException exception) {
                            return;
                        }

                        textArea.append("\n" + session.player.inventory.useItem(index));
                        session.currentBattle.enemyTurn();
                    }
                }

                break;
        }


        textField.setText("");
    }


    public void startBattle() {
        session.gameState = GameState.BATTLE;
        session.currentBattle = new Battle();
        processText("");
    }


    public void startDialog(DialogEntities.DialogEntity dialog) {
        session.gameState = GameState.DIALOG;
        session.currentDialog = dialog;
        session.currentDialog.currentIndex = 0;
        processText("");
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu(GameSession.getInstance());
        }
        return instance;
    }

    public void gameOver() {
        session.gameState = GameState.MAIN_MENU;
        panel.removeAll();
        panel.setBackground(Color.black);
        processText("");
    }

}
