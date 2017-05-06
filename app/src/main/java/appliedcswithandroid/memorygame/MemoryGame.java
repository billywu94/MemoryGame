package appliedcswithandroid.memorygame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by BillyWu on 5/4/17.
 */

public class MemoryGame {

    ArrayList<String> wordArray = new ArrayList<>();

    public MemoryGame(Reader reader) throws IOException{
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null){
            String word = line.trim();
            wordArray.add(word);
        }
    }
}
