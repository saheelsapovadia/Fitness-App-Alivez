package com.example.bottomnav;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TrainerBottomNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private long backPressedTime;
    private Toast backToast;
    BottomNavigationView navView;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    LinearLayout linearLayout;

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to Exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime =  System.currentTimeMillis();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                setFragment(new TrainerProfileFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new ClientProfileFragment()).commit();
                break;

            case R.id.nav_profile:
                setFragment(new trainerprofile());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new profile()).commit();
                break;

            case R.id.nav_manageClient:
                setFragment(new manageClientsFragment());
                //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment1,new profile()).commit();
                break;



            case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(TrainerBottomNav.this, "Logged Out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrainerBottomNav.this, loginStartup.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_bottom_nav);
        //getSupportActionBar().hide();
        navView = findViewById(R.id.trainer_nav_view2);
        frameLayout = findViewById(R.id.nav_host_fragment1);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view3);

        toolbar = findViewById(R.id.trainer_toolbar);
        linearLayout = findViewById(R.id.linear_layout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ALIVEZ");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        setFragment(new TrainerProfileFragment());

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ((item.getItemId())){
                    case R.id.navigation_trainer_profile:
                        setFragment(new TrainerProfileFragment());
                        return true;

                    case R.id.navigation_trainer_workout:
                        setFragment(new TrainerWorkoutFragment());
                        return true;

                    case R.id.navigation_trainer_diet:
                        setFragment(new TrainerDietFragment());
                        return true;

                    case R.id.navigation_trainer_bmi:
                        setFragment(new TrainerBMIFragment());
                        return true;

                    default:
                        return false;
                }


            }
        });
    }



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment1,fragment);
        fragmentTransaction.commit();
    }

}