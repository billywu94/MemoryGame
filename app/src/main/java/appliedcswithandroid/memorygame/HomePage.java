package appliedcswithandroid.memorygame;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by BillyWu on 5/9/17.
 */

public class HomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

    }

    public void signOut(View view){
       FirebaseAuth.getInstance().signOut();  //sign out user
       startActivity(new Intent(this, MainActivity.class)); //redirect to login screen
    }


    public void easyLevel(View view){ startActivity(new Intent(this, easyActivity.class)); }

    public void intermediateLevel(View view){ startActivity(new Intent(this, intermediateLevel.class)); }

    public void difficultLevel(View view){
        startActivity(new Intent(this, difficultLevel.class));
    }
}
