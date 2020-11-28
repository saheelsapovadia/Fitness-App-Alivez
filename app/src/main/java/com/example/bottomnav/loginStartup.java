package com.example.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginStartup extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_startup);
        //getSupportActionBar().hide();
        textView= findViewById(R.id.trial);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        /*if (user != null) {
            // User is signed in
            textView.setText(user.getUid());
        } else {
            // No user is signed in
        }*/


    }

    public void callLoginScreen(View view){


        Intent intent = new Intent(getApplicationContext(),login.class);

        Pair[] pairs = new Pair[1];

        pairs[0] = new Pair<View,String>(findViewById(R.id.login_btn),"transition_login");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(loginStartup.this, pairs);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }
    }
}