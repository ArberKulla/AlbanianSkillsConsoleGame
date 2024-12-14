import enums.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));
        panel.setPreferredSize(new Dimension(300,300));
        panel.setBackground(Color.BLACK);
        frame.add(panel, BorderLayout.NORTH);
        // Create a text area for displaying text
        textArea = new JTextArea(session.name + "Welcome to the fantasitcal World of Tajran!\nPlease select an action:\n1. New Game\n2. Continue\n3. Multiplayer");
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(780,200));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create a text field for user input
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(780,20));
        frame.add(textField, BorderLayout.SOUTH);

        // Add action listener to update the text area when text is entered
        textField.addActionListener(e -> {
            processText(textField.getText());
        });

            // Make the frame visible
            frame.setVisible(true);

    }


    public void processText(String text){
        //Main Menu Selection
        switch(session.gameState){
            case MAIN_MENU:
            switch(text.toUpperCase()){
                case("NEW"):
                case("1"):
                    textArea.setText("Lets begin this epic journey!");
                    startDialog(DialogEntities.NEW_GAME);
                    break;
                case("CONTINUE"):
                case("2"):
                    //Load Save Logic
                    textArea.setText("Last time, on Battlemons!");
                    break;
                case("MULTIPLAYER"):
                case("3"):
                    //Load Save Logic
                    textArea.append("\nWork in progress!");
                    break;
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
                        session.map.movePlayer(session.player.x-1,session.player.y);
                        break;
                    case ("65"):
                    case ("A"):
                        session.map.movePlayer(session.player.x,session.player.y-1);
                        break;
                    case ("83"):
                    case ("S"):
                        session.map.movePlayer(session.player.x+1,session.player.y);
                        break;
                    case ("68"):
                    case ("D"):
                        session.map.movePlayer(session.player.x,session.player.y+1);
                        break;
                    case ("HELP"):
                        textArea.append("\nGame: You should walk around, collect loot, and kill monsters!\n" +
                                "Helpful Prompts:\n" +
                                "Stats -> Shows your current stats!\n" +
                                "Inventory -> Shows your current inventory!\n" +
                                "Inventory use (number) -> Uses the item in that current inventory slot!");
                        break;
                    case ("STATS"):
                        textArea.append("\n"+session.player.showStats());
                        break;
                    default:
                        session.map.printMap();
                }
                break;
            case BATTLE:
                break;
        }



        textField.setText("");
    }

    public void startDialog(DialogEntities.DialogEntity dialog){
        session.gameState = GameState.DIALOG;
        session.currentDialog = dialog;
        processText("");
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu(GameSession.getInstance());
        }
        return instance;
    }

}
