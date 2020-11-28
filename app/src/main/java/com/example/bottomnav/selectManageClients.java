package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class selectManageClients extends AppCompatActivity {

    TextView tvuser,status;
    ImageView userImg;

    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_manage_clients);

        userImg = findViewById(R.id.selected_manage_clients_image);
        tvuser = findViewById(R.id.selected_manage_clients_name);
        status = findViewById(R.id.Status);

        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            manageClientsModel manageClientsModel = (manageClientsModel) intent.getSerializableExtra("data");

            tvuser.setText(manageClientsModel.getClientName());
            userImg.setImageResource(manageClientsModel.getClientImg());
            status.setText(manageClientsModel.getStatus());

        }


    }


}