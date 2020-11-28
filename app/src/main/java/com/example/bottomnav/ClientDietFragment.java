package com.example.bottomnav;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

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

import java.util.Calendar;

public class ClientDietFragment extends Fragment implements View.OnClickListener {

    EditText date_edit, time_edit;
    Button date_btn, time_btn, submit_btn;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String value, date, time, curr_slot;
    private ImageView imageView;
    TextView diet_slot,heading;
    FirebaseFirestore db;
    FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_client_diet, container, false);

        //date_btn = v.findViewById(R.id.date_btn);
        //time_btn = v.findViewById(R.id.time_btn);
        submit_btn = v.findViewById(R.id.select_meeting_slot);
        date_edit = v.findViewById(R.id.editDate);
        time_edit = v.findViewById(R.id.editTime);
        imageView = v.findViewById(R.id.img_diet);
        diet_slot = v.findViewById(R.id.diet_slot);
heading=v.findViewById(R.id.heading1);
        // firebase

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
      /*  db.collection("users").document(user.getUid()).collection("diet")
                .document("diet").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userDiet userDiet = documentSnapshot.toObject(com.example.bottomnav.userDiet.class);
                value = (String) userDiet.getTitle();
                curr_slot = userDiet.getSlot_date() + "\n" + userDiet.getSlot_time();
                diet_slot.setText(curr_slot);
                if (value.equals("fatloss")) {
                    imageView.setImageResource(R.drawable.fat_loss);
                }
                if (value.equals("musclegain")) {
                    imageView.setImageResource(R.drawable.muscle_gain);
                }
                if (value.equals("endurance")) {
                    imageView.setImageResource(R.drawable.endurance);
                }

            }


        });*/

        db.collection("users").document(user.getUid()).collection("diet")
                .document("diet").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot doc, @Nullable FirebaseFirestoreException error) {
                userDiet userDiet = doc.toObject(com.example.bottomnav.userDiet.class);
                value = (String) userDiet.getTitle();
                heading.setText(userDiet.getTitle());
                curr_slot = userDiet.getSlot_date() + "\n" + userDiet.getSlot_time();
                diet_slot.setText(curr_slot);
                if (value.equals("Weight Gain")) {
                    imageView.setImageResource(R.drawable.weightgain);
                }
                if (value.equals("Weight Loss")) {
                    imageView.setImageResource(R.drawable.weightloss);
                }
                if (value.equals("Maintenance")) {
                    imageView.setImageResource(R.drawable.maintenance);
                }
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(user.getUid()).collection("diet")
                        .document("diet").update("slot_time", time);
                db.collection("users").document(user.getUid()).collection("diet")
                        .document("diet").update("slot_date", date);
            }
        });

        //date_btn.setOnClickListener(this);
        //time_btn.setOnClickListener(this);
        date_edit.setFocusable(false);
        date_edit.setKeyListener(null);
        time_edit.setFocusable(false);
        time_edit.setKeyListener(null);

        date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        date_edit.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        if(minute<10) time = hourOfDay + ":0" + minute;
                        else time = hourOfDay + ":" + minute;
                        time_edit.setText(time);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        return v;
    }

    @Override
    public void onClick(View v) {
        if (v == date_btn) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    date = dayOfMonth + "-" + (month + 1) + "-" + year;
                    date_edit.setText(date);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        }
        if (v == time_btn) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    if(minute<10) time = hourOfDay + ":0" + minute;
                    else time = hourOfDay + ":" + minute;
                    time_edit.setText(time);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}