package com.example.kuku;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HousingAndEquipmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout mDrawer;
    private final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing_and_equipment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.bringToFront();

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        mAppBarConfiguration = new AppBarConfiguration.Builder().setOpenableLayout(mDrawer).build();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG," Housing and equipment Navigation drawer opened");

        if (item.getItemId() == R.id.nav_breeds) {
            Toast.makeText(this,"BREEDS",Toast.LENGTH_SHORT).show();
            Intent breeds = new Intent(this, BreedsActivity.class);
            startActivity(breeds);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId() == R.id.nav_brooding) {
            Toast.makeText(this,"BROODING",Toast.LENGTH_SHORT).show();
            Intent brooding = new Intent(this, BroodingActivity.class);
            startActivity(brooding);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_housing_and_equipment){
            Toast.makeText(this,"HOUSING AND EQUIPMENT",Toast.LENGTH_SHORT).show();
            Intent housing = new Intent(this, HousingAndEquipmentActivity.class);
            startActivity(housing);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_poultry_management){
            Toast.makeText(this,"POULTRY MANAGEMENT",Toast.LENGTH_SHORT).show();
            Intent management = new Intent(this, PoultryManagementActivity.class);
            startActivity(management);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_common_diseases){
            Toast.makeText(this,"COMMON DISEASES", Toast.LENGTH_SHORT).show();
            Intent commonDiseases = new Intent(this, PoultryHealthManagementActivity.class);
            startActivity(commonDiseases);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_best_practice){
            Toast.makeText(this,"BEST PRACTICE", Toast.LENGTH_SHORT).show();
            Intent bestPractice = new Intent(this, BestPracticeActivity.class);
            startActivity(bestPractice);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_bad_habits){
            Toast.makeText(this,"BAD HABITS", Toast.LENGTH_SHORT).show();
            Intent badHabits = new Intent(this, BadHabitsActivity.class);
            startActivity(badHabits);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
            finish();
            return true;
        }
        return false;
    }


}