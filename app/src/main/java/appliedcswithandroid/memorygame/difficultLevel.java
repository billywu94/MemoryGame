package appliedcswithandroid.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by BillyWu on 5/6/17.
 */

public class difficultLevel extends Activity {
    private MemoryGame wordContainer;
    String wordToMemorize = "";
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //set view for difficult level
        setContentView(R.layout.difficult_activity);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt"); //load words.txt file
            wordContainer = new MemoryGame(new InputStreamReader(inputStream));
        } catch (IOException e){
            Toast toast = Toast.makeText(this, "Could not load file", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        randomWordToMemorize(); //randomly selects 3 words from the arrayList displays for 5 seconds
    }

    public void randomWordToMemorize(){
        String secondWord = wordContainer.chooseRandomDifficult();
        String thirdWord = wordContainer.chooseRandomDifficult();
        wordToMemorize = wordContainer.chooseRandomDifficult();
        wordToMemorize = wordToMemorize + " " + secondWord + " " + thirdWord;
        System.out.println("This are the words: " + wordToMemorize);
        final TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(wordToMemorize);
        //source: http://stackoverflow.com/questions/22194761/hide-textview-after-some-time-in-android
        //http://stackoverflow.com/questions/8177830/hide-a-layout-after-10-seconds-in-android
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.INVISIBLE);
            }
        }, 5000); //set visibility for 5 seconds

    }

    //setting background color
    //source: http://stackoverflow.com/questions/8961071/android-changing-background-color-of-the-activity-main-view
    public void setActivityBackgroundColor(int color){
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    //check if the words the user enters matches to the one shown on the screen
    public void checkWord(View view){
        Button button = (Button) findViewById(R.id.submitDifficult);
        EditText editText = (EditText) findViewById(R.id.editText3);
        editText.setFocusable(false); //source: http://stackoverflow.com/questions/4297763/disabling-of-edittext-in-android
        String userInput = editText.getText().toString();
        System.out.println("This is the user input: " + userInput);
        if(wordToMemorize.equals(userInput)){
            button.setEnabled(false);
            setActivityBackgroundColor(Color.GREEN);
            score += 3;
            Toast.makeText(this,"Well Done!!", Toast.LENGTH_SHORT).show();
        }else{
            button.setEnabled(false);
            setActivityBackgroundColor(Color.RED);
            Toast.makeText(this,"Sorry, the word(s) you entered was incorrect.", Toast.LENGTH_SHORT).show();
        }

    }


    //method for onClick
    //resets background color, chooses new random words, enables any disabled buttons
    public void nextWord(View view){
        setActivityBackgroundColor(Color.parseColor("#EEEEEE"));
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setVisibility(View.VISIBLE);
        randomWordToMemorize(); //selects new random words here
        EditText editText = (EditText) findViewById(R.id.editText3);
        editText.setText("");
        editText.setFocusableInTouchMode(true);
        Button button = (Button) findViewById(R.id.submitDifficult);
        button.setEnabled(true);

    }

    public int getScore(){
        return score;
    }

    //back to home
    public void home(View view){
        startActivity(new Intent(this, HomePage.class));
    }
}
