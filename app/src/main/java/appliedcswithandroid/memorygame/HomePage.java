package appliedcswithandroid.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by BillyWu on 5/9/17.
 */

public class HomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }


    public void easyLevel(View view){ startActivity(new Intent(this, easyActivity.class)); }

    public void intermediateLevel(View view){ startActivity(new Intent(this, intermediateLevel.class)); }

    public void difficultLevel(View view){
        startActivity(new Intent(this, difficultLevel.class));
    }
}
