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

public class SelectDiet extends AppCompatActivity {

    TextView tvuser,slot,chart;
    ImageView userImg;
    String usr,slot_time,slot_date;
    Button selected;
    RadioGroup radioGroup;
    RadioButton fat,muscle,endurance,radioButton;
    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_diet);

        userImg = findViewById(R.id.selected_user_img);
        tvuser = findViewById(R.id.selected_user_name);
        chart = findViewById(R.id.diet_data);
        slot = findViewById(R.id.slot_data);
        selected = findViewById(R.id.assign_btn);
        fat= findViewById(R.id.fat_loss);
        muscle= findViewById(R.id.muscle_gain);
        endurance=findViewById(R.id.endurance);
        radioGroup=findViewById(R.id.radiogroup);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            TrainerDietModel trainerDietModel = (TrainerDietModel) intent.getSerializableExtra("data");
            if (trainerDietModel.getClientName().equals("Saheel Sapovadia")) {
                usr = "33kcJDaWwdZEjL8XHKMAWjqqVUI3";
            }
            if (trainerDietModel.getClientName().equals("Jainil Shah")) {
                usr = "ki1iCztzwmOkjfmobsyu2feSNju1";
            }
            if (trainerDietModel.getClientName().equals("Aman Shah")) {
                usr = "WQCcZPpktUXYHAo2TKci5ecaJlj2";
            }
            tvuser.setText(trainerDietModel.getClientName());
            userImg.setImageResource(trainerDietModel.getClientImg());
        }
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        db.collection("users").document(usr).collection("diet")
                .document("diet").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userDiet userDiet = value.toObject(com.example.bottomnav.userDiet.class);
                slot_time = (String) String.valueOf(userDiet.getSlot_time());
                slot_date = (String) String.valueOf(userDiet.getSlot_date());
                slot.setText(slot_time + " " + slot_date);
                chart.setText(String.valueOf(userDiet.getTitle()));

            }
        });
        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String set;

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton= findViewById(radioId);

                if(radioId == R.id.maintenance){
                    set="Maintenance";
                    Toast.makeText(SelectDiet.this,radioButton.getText()+" Assigned",Toast.LENGTH_SHORT).show();
                    db.collection("users").document(usr).collection("diet")
                            .document("diet").update("title",set);
                    db.collection("trainer").document("luke_Coutino")
                            .collection("diet").document(usr).update("title",set);

                }
                if(radioId == R.id.gain){
                    set="Weight Gain";
                    Toast.makeText(SelectDiet.this,radioButton.getText()+" Assigned",Toast.LENGTH_SHORT).show();
                    db.collection("users").document(usr).collection("diet")
                            .document("diet").update("title",set);
                    db.collection("trainer").document("luke_Coutino")
                            .collection("diet").document(usr).update("title",set);

                }
                if(radioId == R.id.loss){
                    set="Weight Loss";
                    Toast.makeText(SelectDiet.this,radioButton.getText()+" Assigned",Toast.LENGTH_SHORT).show();
                    db.collection("users").document(usr).collection("diet")
                            .document("diet").update("title",set);
                    db.collection("trainer").document("luke_Coutino")
                            .collection("diet").document(usr).update("title",set);

                }
            }
        });
    }
    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton= findViewById(radioId);
       // Toast.makeText(this,radioButton.getText()+" Selected",Toast.LENGTH_SHORT).show();

    }
}