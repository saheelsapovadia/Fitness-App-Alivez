package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class manageClientsFragment extends Fragment implements manageClientsAdapter.SelectedUser{

    RecyclerView recyclerView;
    View v;
    List<manageClientsModel> mList;
    manageClientsAdapter manageClientsAdapter;

    Toolbar toolbar;
    FirebaseFirestore db;
    FirebaseUser user;
    String saheel, jainil, aman;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_manage_clients, container, false);
        recyclerView = v.findViewById(R.id.manageClientRecyclerview);
        manageClientsAdapter = new manageClientsAdapter(mList, getContext(),this);

        recyclerView.setAdapter(manageClientsAdapter);
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
        mList.add(new manageClientsModel("Aman Shah","", R.drawable.aman));
        mList.add(new manageClientsModel("Saheel Sapovadia","", R.drawable.saheel));
        mList.add(new manageClientsModel("Jainil Shah","", R.drawable.jainil));

    }

    @Override
    public void selectedUser(manageClientsModel manageClientsModel) {

        startActivity(new Intent(getActivity().getApplication(),selectManageClients.class).putExtra("data", manageClientsModel));


    }


}


