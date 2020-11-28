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

import java.util.ArrayList;
import java.util.List;

public class TrainerDietFragment extends Fragment implements TrainerDietAdapter.SelectedUser{

    RecyclerView recyclerView;
    View v;
    List<TrainerDietModel>  mList;
    TrainerDietAdapter trainerDietAdapter;

    Toolbar toolbar;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        v =  inflater.inflate(R.layout.fragment_trainer_diet,container,false);
        recyclerView = v.findViewById(R.id.recyclerview3);


        trainerDietAdapter = new TrainerDietAdapter(mList,getContext(),this);

        recyclerView.setAdapter(trainerDietAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();

        mList.add(new TrainerDietModel("Aman Shah", "", R.drawable.aman));
        mList.add(new TrainerDietModel("Saheel Sapovadia", "", R.drawable.saheel));
        mList.add(new TrainerDietModel("Jainil Shah", "", R.drawable.jainil));
    }

    @Override
    public void selectedUser(TrainerDietModel trainerDietModel) {

        startActivity(new Intent(getActivity().getApplication(),SelectDiet.class).putExtra("data",trainerDietModel));


    }
}


