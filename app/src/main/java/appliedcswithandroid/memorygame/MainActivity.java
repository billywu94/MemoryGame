package appliedcswithandroid.memorygame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
    Firebase references : https://firebase.google.com/docs/database/android/start/
    https://firebase.google.com/docs/database/security/quickstart#sample-rules
    https://firebase.google.com/docs/auth/android/password-auth
 */

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText emailField;
    private EditText passwordField;
    private DatabaseReference database;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference("Score");
        //database.child("message").child(firebaseUser.getUid()).setValue("Inserting into db");
        database.setValue("inserting into database");

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.getCurrentUser();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //Register to MemoryGame App
    private void createAccount(final String email, final String password){
        System.out.println("Entering createAccount method");
        if(email.isEmpty() || password.isEmpty()){ //if field is not completed notify user
            Toast.makeText(MainActivity.this, "All fields are required to be completed",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the homepage
                            Log.d("EmailPassword", "createUserWithEmail:success");
                            mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("EmailPassword", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    //Sign in existing user to MemoryGame App
    private void signIn(String email, String password){
        if(email.isEmpty() || password.isEmpty()){ //if field is not completed toast a message
            Toast.makeText(MainActivity.this, "All fields are required to be completed",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the homepage
                            Log.d("EmailPassword", "signInWithEmail:success");
                            mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("EmailPassword", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mStatusTextView.setText(R.string.auth_failed);
                        }
                       // hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    //Register Button, calls createAccountMethod
    public void createButton(View view){
        Button createButton = (Button) findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(emailField.getText().toString(), passwordField.getText().toString()); //createAccount method takes in user email and password
                System.out.println("User entered: " + emailField.getText().toString() + " " + passwordField.getText().toString());
            }
        });
    }

    //Sign in button, calls signIn method
    public void signInButton(View view){
        Button signInButton = (Button) findViewById(R.id.signIn);
        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(emailField.getText().toString(),passwordField.getText().toString()); //signIn method takes in user email and password
            }
        });
    }

}
