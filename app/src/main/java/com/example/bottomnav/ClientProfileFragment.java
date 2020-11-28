package com.example.bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ClientProfileFragment extends Fragment {

    TextView workout,diet,bmi,profile,membership,name,trainername;
    FirebaseFirestore db;
    FirebaseUser user;
    ImageView profile_image;
    public ClientProfileFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_client_profile,container,false);
 workout = v.findViewById(R.id.workout_title);
 name = v.findViewById(R.id.name);
 membership=v.findViewById(R.id.membership);
 bmi = v.findViewById(R.id.bmi);
 diet = v.findViewById(R.id.diet_title);
 trainername=v.findViewById(R.id.trainer_name);
 profile_image= v.findViewById(R.id.profile_image);
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.getUid().equals("33kcJDaWwdZEjL8XHKMAWjqqVUI3")){
            profile_image.setImageResource(R.drawable.saheel);
        }
        if(user.getUid().equals("ki1iCztzwmOkjfmobsyu2feSNju1")){
            profile_image.setImageResource(R.drawable.jainil);
        }
        if(user.getUid().equals("WQCcZPpktUXYHAo2TKci5ecaJlj2")){
            profile_image.setImageResource(R.drawable.aman);
        }

        db.collection("users").document(user.getUid())
                .collection("profile").document("profile")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.get("name").toString());
                membership.setText(documentSnapshot.get("membership").toString());
                trainername.setText(documentSnapshot.get("trainer_name").toString());
            }
        });
        db.collection("users").document(user.getUid())
                .collection("bmi").document("bmi")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                        bmi.setText(documentSnapshot.get("value").toString());
                    }
                });
        db.collection("users").document(user.getUid())
                .collection("workout").document("workout")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                        workout.setText(documentSnapshot.get("title").toString());
                    }
                });
        db.collection("users").document(user.getUid())
                .collection("diet").document("diet")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                        diet.setText(documentSnapshot.get("title").toString());
                    }
                });



        return v;
    }
}