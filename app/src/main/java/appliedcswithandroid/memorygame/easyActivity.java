package appliedcswithandroid.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by BillyWu on 5/6/17.
 */

public class easyActivity extends Activity{
    private MemoryGame wordContainer;
    String wordToMemorize = "";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //set view for easy level
        setContentView(R.layout.easy_activity);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            wordContainer = new MemoryGame(new InputStreamReader(inputStream));
        } catch (IOException e){
            Toast toast = Toast.makeText(this, "Could not load file", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        wordToMemorize = wordContainer.chooseRandomEasy();
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(wordToMemorize);
        //source: http://stackoverflow.com/questions/22194761/hide-textview-after-some-time-in-android
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.INVISIBLE);
            }
        }, 3000); //set visibility for 3 seconds


    }
    //setting background color
    //source: http://stackoverflow.com/questions/8961071/android-changing-background-color-of-the-activity-main-view
    public void setActivityBackgroundColor(int color){
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    public void checkWord(View view){
        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setFocusable(false); //source: http://stackoverflow.com/questions/4297763/disabling-of-edittext-in-android
        String userInput = editText.getText().toString();
        System.out.println("This is the user input: " + userInput);
        if(wordToMemorize.equals(userInput)){
            setActivityBackgroundColor(Color.GREEN);
            Toast.makeText(this,"Congratulations, you got the word correct!!!", Toast.LENGTH_SHORT).show();
        }else{
            setActivityBackgroundColor(Color.RED);
            Toast.makeText(this,"Sorry, the word you entered was incorrect.", Toast.LENGTH_SHORT).show();
        }

    }

    //back to homepage
    public void home(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
