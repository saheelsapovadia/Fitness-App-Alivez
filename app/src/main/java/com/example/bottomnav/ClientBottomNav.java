package com.example.bottomnav;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class ClientBottomNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private long backPressedTime;
    private Toast backToast;
    BottomNavigationView navView;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_bottom_nav);
        //getSupportActionBar().hide();

        /*---------------HOOKS--------------*/
        navView = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.nav_host_fragment1);

        drawerLayout = findViewById(R.id.client_drawer_layout);


        navigationView = findViewById(R.id.client_nav_view2);
        linearLayout = findViewById(R.id.linear_layout);

        toolbar = findViewById(R.id.client_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ALIVEZ");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        setFragment(new ClientProfileFragment());

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ((item.getItemId())) {
                    case R.id.navigation_client_profile:
                        setFragment(new ClientProfileFragment());
                        return true;

                    case R.id.navigation_client_workout:
                        setFragment(new ClientWorkoutFragment());
                        return true;

                    case R.id.navigation_client_diet:
                        setFragment(new ClientDietFragment());
                        return true;

                    case R.id.navigation_client_bmi:
                        setFragment(new ClientBMIFragment());
                        return true;

                    default:
                        return false;
                }


            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                setFragment(new ClientProfileFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new ClientProfileFragment()).commit();
                break;

            case R.id.nav_profile:
                setFragment(new clientprofile());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new profile()).commit();
                break;

            case R.id.nav_trainerInfo:
                setFragment(new trainerprofile());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new profile()).commit();
                break;

            case R.id.payment:
                setFragment(new payment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new profile()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ClientBottomNav.this, "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ClientBottomNav.this, loginStartup.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_rate:
                Toast.makeText(this,"Rate",Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment1, fragment);
        fragmentTransaction.commit();
    }


}
