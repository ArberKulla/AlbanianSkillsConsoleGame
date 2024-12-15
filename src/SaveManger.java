import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import enums.GameState;
import utils.ColorTypeAdapter;
import utils.GameLevel;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SaveManger {
    MainMenu menu = MainMenu.getInstance();
    GameSession session = GameSession.getInstance();

    public void saveGame() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Color.class, new ColorTypeAdapter())  // Handle Color serialization
                .create();
        try (FileWriter writer = new FileWriter("save.json")) {
            JsonObject jsonObject = new JsonObject();

// Add each object to the JsonObject with appropriate keys
            jsonObject.add("player", gson.toJsonTree(session.player));
            jsonObject.add("name", gson.toJsonTree(session.name));
            jsonObject.add("gameState", gson.toJsonTree(session.gameState));
            jsonObject.add("currentLevel", gson.toJsonTree(session.map.currentLevel));
            jsonObject.add("levels", gson.toJsonTree(session.map.levels));
            // Write the JsonObject to the writer (file or output stream)
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameSession loadGame() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Color.class, new ColorTypeAdapter())  // Register the ColorTypeAdapter for deserialization
                .create();

        try (FileReader reader = new FileReader("save.json")) {
// Read the JSON and deserialize it into a Map to extract each component
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

// Extract each object from the JSON
            Player player = gson.fromJson(jsonObject.get("player"), Player.class);
            String name = gson.fromJson(jsonObject.get("name"), String.class);
            GameState gameState = gson.fromJson(jsonObject.get("gameState"), GameState.class);
            GameLevel currentLevel = gson.fromJson(jsonObject.get("currentLevel"), GameLevel.class);
            Type gameLevelListType = new TypeToken<ArrayList<GameLevel>>() {
            }.getType();
            ArrayList<GameLevel> levels = gson.fromJson(jsonObject.get("levels"), gameLevelListType);

            session.loadFromSave(name, player, gameState, currentLevel, levels);
            menu.processText("");
        } catch (IOException e) {
            menu.textArea.setText("No Save found, making new game!\nLets begin this epic journey!");
            menu.startDialog(DialogEntities.NEW_GAME);
        }
        return null;
    }

}
