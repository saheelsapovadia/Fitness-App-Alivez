package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SelectBMI extends AppCompatActivity {

    TextView tvuser, bmi, fat, age, val, weight, height;
    ImageView userImg;
    ImageView gender;
    FirebaseFirestore db;
    String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bmi);

        tvuser = findViewById(R.id.selected_user_name);
        //bmi = findViewById(R.id.BMI);
        gender = findViewById(R.id.gender_img);
        Intent intent = getIntent();
        fat = findViewById(R.id.fat_per);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        age = findViewById(R.id.age);
        val=findViewById(R.id.BMI);
        if (intent.getExtras() != null) {
            TrainerBMIModel trainerBMIModel = (TrainerBMIModel) intent.getSerializableExtra("data");
            if (trainerBMIModel.getClientName().equals("Saheel Sapovadia")) {
                usr = "33kcJDaWwdZEjL8XHKMAWjqqVUI3";
            }
            if (trainerBMIModel.getClientName().equals("Jainil Shah")) {
                usr = "ki1iCztzwmOkjfmobsyu2feSNju1";
            }
            if (trainerBMIModel.getClientName().equals("Aman Shah")) {
                usr = "WQCcZPpktUXYHAo2TKci5ecaJlj2";
            }
            db = FirebaseFirestore.getInstance();
            //user = FirebaseAuth.getInstance().getCurrentUser();
            db.collection("users").document(usr).collection("bmi")
                    .document("bmi").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    userBmi userBmi = value.toObject(com.example.bottomnav.userBmi.class);
                    /*if (userBmi.getGender().toString().equals("male")) {
                        gender.setImageResource(R.drawable.ic_gender_male);
                    }*/
                    fat.setText(Float.toString(userBmi.getFat_percentage()));
                    weight.setText(Integer.toString(userBmi.getWeight()));
                    height.setText(Integer.toString(userBmi.getHeight()));
                    age.setText(Integer.toString(userBmi.getAge()));
                    val.setText(Integer.toString(userBmi.getValue()));
                }
            });

           // bmi.setText(trainerBMIModel.getBMI());
            tvuser.setText(trainerBMIModel.getClientName());
        }
    }
}