import enums.GameState;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private static MainMenu instance;
    GameSession session;
    JFrame frame;
    JTextArea textArea;
    JTextField textField;

    private MainMenu(GameSession session){
        //Sync Game State
        this.session = session;

        // Create the main frame
        frame = new JFrame("Arbers Pokemon Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to fullscreen
        frame.setLayout(new BorderLayout());

        // Create a text area for displaying text
        textArea = new JTextArea(session.name+"Welcome to Arbers Albanian Skills BattleMons!\nPlease select an action:\n1. New Game\n2. Continue\n3. Multiplayer");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create a text field for user input
        textField= new JTextField();
        frame.add(textField, BorderLayout.SOUTH);

        // Add action listener to update the text area when text is entered
        textField.addActionListener(e -> {processText(textField.getText());});

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
                    textArea.setText("Work in progress!");
                    break;
            }
            break;
            case DIALOG:
                DialogEntities.DialogEntity dialog = session.currentDialog;
                dialog.next(text);
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
