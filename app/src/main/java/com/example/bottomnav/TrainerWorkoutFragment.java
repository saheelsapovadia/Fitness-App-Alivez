package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class TrainerWorkoutFragment extends Fragment implements TrainerWorkoutAdapter.SelectedUser {

    RecyclerView recyclerView;
    View v;
    List<TrainerWorkoutModel> mList;
    TrainerWorkoutAdapter trainerWorkoutAdapter;

    Toolbar toolbar;
    FirebaseFirestore db;
    FirebaseUser user;
    String saheel, jainil, aman;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_trainer_workout, container, false);
        recyclerView = v.findViewById(R.id.recyclerview2);
        trainerWorkoutAdapter = new TrainerWorkoutAdapter(mList, getContext(), this);

        recyclerView.setAdapter(trainerWorkoutAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        //user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("users").document("33kcJDaWwdZEjL8XHKMAWjqqVUI3").collection("workout")
                .document("workout").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userWorkout userWorkout = value.toObject(com.example.bottomnav.userWorkout.class);
                saheel =  String.valueOf(userWorkout.getTitle());

            }
        });
        // Toast.makeText(getActivity(),saheel,Toast.LENGTH_SHORT).show();
        db.collection("trainer").document("luke_Coutino").collection("workout")
                .document("WQCcZPpktUXYHAo2TKci5ecaJlj2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                aman = value.get("title").toString();
            }
        });
        db.collection("trainer").document("luke_Coutino").collection("workout")
                .document("ki1iCztzwmOkjfmobsyu2feSNju1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                jainil = value.get("title").toString();
            }
        });

        String a, s, j;
        a = aman;
        s = saheel;
        j = jainil;
        //Toast.makeText(getActivity(),saheel,Toast.LENGTH_SHORT).show();
        mList.add(new TrainerWorkoutModel("Aman Shah","", R.drawable.aman));
        mList.add(new TrainerWorkoutModel("Saheel Sapovadia", "", R.drawable.saheel));
        mList.add(new TrainerWorkoutModel("Jainil Shah", "", R.drawable.jainil));

    }

    @Override
    public void selectedUser(TrainerWorkoutModel trainerWorkoutModel) {

        startActivity(new Intent(getActivity().getApplication(), SelectWorkout.class).putExtra("data", trainerWorkoutModel));


    }
}


