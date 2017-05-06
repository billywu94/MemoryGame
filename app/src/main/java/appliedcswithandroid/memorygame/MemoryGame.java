package appliedcswithandroid.memorygame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by BillyWu on 5/4/17.
 */

public class MemoryGame {

    private Random random = new Random();

    ArrayList<String> easyLength = new ArrayList<>();
    ArrayList<String> intermediateLength = new ArrayList<>();
    ArrayList<String> difficultLength = new ArrayList<>();


    public MemoryGame(Reader reader) throws IOException{
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null){
            String word = line.trim();
            if(word.length() < 6){
                easyLength.add(word);
            }else if(word.length() > 6 && word.length() < 10){
                intermediateLength.add(word);
            }else{
                difficultLength.add(word);
            }
        }
    }

    public String chooseRandomEasy(){
        String randomWord = "";
        int rand = random.nextInt(easyLength.size());
        return randomWord = easyLength.get(rand);
    }

    public String chooseRandomIntermediate(){
        String randomWord = "";
        int rand = random.nextInt(intermediateLength.size());
        return randomWord = intermediateLength.get(rand);
    }

    public String chooseRandomDifficult(){
        String randomWord = "";
        int rand = random.nextInt(difficultLength.size());
        return randomWord = difficultLength.get(rand);
    }

}
