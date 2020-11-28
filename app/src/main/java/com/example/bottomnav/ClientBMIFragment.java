package com.example.bottomnav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottomnav.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ClientBMIFragment extends Fragment {

    public ClientBMIFragment() {

    }


    TextView weight;
    TextView height;
    TextView bmi;
    TextView age;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_client_bmi, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        bmi = view.findViewById(R.id.BMI);
        age = view.findViewById(R.id.age);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        /*db.collection("users").document(user.getUid()).collection("bmi")
                .document("bmi").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userBmi userBmi = documentSnapshot.toObject(com.example.bottomnav.userBmi.class);
                weight.setText(Integer.toString(userBmi.getWeight()));
                height.setText(Integer.toString(userBmi.getHeight()));
                bmi.setText(Integer.toString(userBmi.getValue()));
                age.setText(Integer.toString(userBmi.getAge()));
            }
        });*/
        db.collection("users").document(user.getUid()).collection("bmi")
                .document("bmi").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userBmi userBmi = value.toObject(com.example.bottomnav.userBmi.class);
                weight.setText(Integer.toString(userBmi.getWeight()));
                height.setText(Integer.toString(userBmi.getHeight()));
                bmi.setText(Integer.toString(userBmi.getValue()));
                age.setText(Integer.toString(userBmi.getAge()));
            }
        });

        return view;
    }
}