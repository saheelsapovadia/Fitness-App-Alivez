package com.example.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private Button loginbtn;
    private EditText email,password;
    private TextView info;
    private int counterr=5;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();

        email = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.lgn_btn);

        //firebase authentication

        mAuth = FirebaseAuth.getInstance();


        //info = (TextView)findViewById(R.id.textView);
        //info.setText("No of Attempts Remaining: 5");
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void validate(String email, final String password){
        /*if((username.equals("trainer")) && (password.equals("trainer"))){
            Toast.makeText(getBaseContext(), "Welcome Trainer!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,TrainerBottomNav.class);
            startActivity(intent);
        }else if((username.equals("user")) && (password.equals("user"))){
            Toast.makeText(getBaseContext(), "Welcome User!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ClientBottomNav.class);
            startActivity(intent);
        }else{
            counterr--;
           // info.setText("No of Attempts Remaining: "+ String.valueOf(counterr));
            if(counterr==0){
                loginbtn.setEnabled(false);
            }
        }*/

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            if(password.equals("trainer")){
                                Intent intent = new Intent(login.this, TrainerBottomNav.class);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(login.this, ClientBottomNav.class);
                                startActivity(intent);
                                //updateUI(user);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            // [START_EXCLUDE]
                            // checkForMultiFactorFailure(task.getException());
                            // [END_EXCLUDE]
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mBinding.status.setText(R.string.auth_failed);
                        }
                        //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
}
