package com.example.bottomnav;

import android.app.AppComponentFactory;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnav.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TrainerProfileFragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    List<TrainerProfileModel>  mList;
    MyAdapterTrainerProfile myAdapterTrainerProfile;
    FirebaseFirestore db;
    FirebaseUser user;
    String name,value;
    TextView trial;
    String name1[];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_trainer_profile,container,false);
        recyclerView = v.findViewById(R.id.recyclerView1);

        myAdapterTrainerProfile = new MyAdapterTrainerProfile(mList,getContext());
        recyclerView.setAdapter(myAdapterTrainerProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
trial = v.findViewById(R.id.trial);
        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        name1 =new String[3];
      /* db.collection("trainer")
                .document("luke_Coutino").collection("users").document("users").addSnapshotListener(new EventListener<DocumentSnapshot>() {
           @Override
           public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               name1 = value.get("name");
           }
       });*/
               /*.whereEqualTo("payment", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                name = document.get("name").toString();
                                value=value+name;
                                trial.setText(value);
                                mList.add(new TrainerProfileModel("name","No",R.drawable.vk));
                                Log.d(TAG, name+ " => ");
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });*/
    /*.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    name =documentSnapshot.get("name").toString();
                    trial.append(name);
                    mList.add(new TrainerProfileModel("d","No",R.drawable.vk));
                }
            }
        });*/



       mList.add(new TrainerProfileModel("Saheel Sapovadia","No",R.drawable.saheel));
        mList.add(new TrainerProfileModel("Jainil Shah","No",R.drawable.jainil));
        mList.add(new TrainerProfileModel("Aman Shah","No",R.drawable.aman));
    }



}