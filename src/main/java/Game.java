import java.io.IOException;
import java.nio.file.Paths;

import java.util.List;
import java.util.Random;
import java.nio.file.*;

class Game {

    private List<String> data;

    Game(){
        data = getWordList();
    }

    private List<String> getWordList(){
        {
            try {
                return Files.readAllLines(Paths.get("words.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    String getRandomWord() {
        Random rand = new Random();
        return data.get(rand.nextInt(data.size()));
    }
}
