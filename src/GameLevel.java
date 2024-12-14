import java.util.HashSet;
import java.util.Random;


public class GameLevel {
    String[] locations = {"You have entered the Forest; it seems rather calm!", "You have entered the Cave; the darkness feels oppressive.", "You have entered the Desert; the heat is almost unbearable.", "You have entered the Mountain; the air is thin and crisp.", "You have entered the Swamp; the smell is nauseating and the ground squelches beneath your feet.", "You have entered the Ruins; ancient walls whisper tales of a forgotten past.", "You have entered the Volcano; molten lava bubbles menacingly around you.", "You have entered the Tavern; the smell of ale and laughter fills the air.", "You have entered the Enchanted Grove; the trees seem to hum with a magical melody.", "You have entered the Frozen Wastes; icy winds bite at your skin."};

    String flavor;
    char[][] grid;
    GameLevel upLevel;
    GameLevel downLevel;
    GameLevel leftLevel;
    GameLevel rightLevel;

    public GameLevel(){
        generateMap();
    }


    public void generateMap(){
        Random rand = new Random();
        flavor = locations[rand.nextInt(locations.length)];
        int randSize = (int) (Math.random() * 5);  // Generates a random number between 0 and 4
        int size = (randSize * 2) + 7;
        int npcAmmount = (int) (Math.random() * randSize);
        int tressureAmmount = (int) (Math.random() * randSize);
        grid = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }

        for(int i=0;i<size/3;i++){
            grid[0][i] = '=';
            grid[0][size-i-1] = '=';
            grid[size-1][i] = '=';
            grid[size-1][size-i-1] = '=';
            if(i>0){
                grid[i][0] = '=';
                grid[i][size-1] = '=';
                grid[size-i-1][0] = '=';
                grid[size-i-1][size-1] = '=';
            }
        }


        HashSet<String> pickedPositions = new HashSet<>();
        while (tressureAmmount > 0) {
            rand = new Random();
            // Generate random row and column indices within the inner area (1 to size-2)
            int x = rand.nextInt(size - 2) + 1;
            int y = rand.nextInt(size - 2) + 1;

            // Create a unique position (row, col) string
            String position = x + "," + y;

            // Ensure the position is not repeated
            if (!pickedPositions.contains(position)) {
                pickedPositions.add(position);
                grid[x][y] = '\u2728';
                tressureAmmount--;
            }
        }

        while (npcAmmount > 0) {
            rand = new Random();
            // Generate random row and column indices within the inner area (1 to size-2)
            int x = rand.nextInt(size - 2) + 1;
            int y = rand.nextInt(size - 2) + 1;

            // Create a unique position (row, col) string
            String position = x + "," + y;

            // Ensure the position is not repeated
            if (!pickedPositions.contains(position)) {
                pickedPositions.add(position);
                grid[x][y] = '\u263A';
                npcAmmount--;
            }
        }

    }

    public void placePlayer(int new_x, int new_y){
        grid[new_x][new_y]='★';
    }

    public char updateMap(int old_x,int old_y, int new_x, int new_y){
        char oldChar = grid[new_x][new_y];
        grid[old_x][old_y]=' ';
        grid[new_x][new_y]='★';
        return oldChar;
    }

}
