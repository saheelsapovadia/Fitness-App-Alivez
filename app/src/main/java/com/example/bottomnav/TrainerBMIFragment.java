package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class TrainerBMIFragment extends Fragment implements TrainerBMIAdapter.SelectedUser{

    RecyclerView recyclerView;
    View v;
    List<TrainerBMIModel>  mList;
    TrainerBMIAdapter trainerBMIAdapter;
String saheel, aman, jainil;
    Toolbar toolbar;
    FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_trainer_bmi,container,false);
        recyclerView = v.findViewById(R.id.recyclerview4);

        trainerBMIAdapter = new TrainerBMIAdapter(mList,getContext(),this);

        recyclerView.setAdapter(trainerBMIAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        //user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("users").document("33kcJDaWwdZEjL8XHKMAWjqqVUI3").collection("bmi")
                .document("bmi").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userBmi userBmi = value.toObject(com.example.bottomnav.userBmi.class);
                saheel = (String) String.valueOf(userBmi.getValue());

            }
        });
        mList.add(new TrainerBMIModel("Aman Shah",aman,R.drawable.aman));
        mList.add(new TrainerBMIModel("Saheel Sapovadia",saheel,R.drawable.saheel));
        mList.add(new TrainerBMIModel("Jainil Shah",jainil,R.drawable.jainil));

    }

    @Override
    public void selectedUser(TrainerBMIModel trainerBMIModel) {

        startActivity(new Intent(getActivity().getApplication(),SelectBMI.class).putExtra("data",trainerBMIModel));


    }
}


