package appliedcswithandroid.memorygame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void easyLevel(View view){
        startActivity(new Intent(this, easyActivity.class));
    }

    public void intermediateLevel(View view){ startActivity(new Intent(this, intermediateLevel.class)); }

    public void difficultLevel(View view){
        startActivity(new Intent(this, difficultLevel.class));
    }


}
