package com.example.kuku.MainActivity;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kuku.BadHabits.BadHabitsActivity;
import com.example.kuku.BestPractice.BestPracticeActivity;
import com.example.kuku.Breeds.BreedsActivity;
import com.example.kuku.Breeds.BreedsExamplesActivity;
import com.example.kuku.Brooding.BroodingActivity;
import com.example.kuku.Database.DataBaseOpenHelper;
import com.example.kuku.Database.DataManager;
import com.example.kuku.HousingAndEquipment.HousingAndEquipmentActivity;
import com.example.kuku.PoultryHealthManagement.PoultryHealthManagementActivity;
import com.example.kuku.PoultryManagement.PoultryManagementActivity;
import com.example.kuku.R;
import com.google.android.material.navigation.NavigationView;

import static com.example.kuku.Database.DataBaseContract.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        ,LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_MAIN = 0;
    private DrawerLayout mDrawer;
    private final String TAG = getClass().getSimpleName();
    private MainActivityRecyclerAdapter mMainActivityRecyclerAdapter;
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mMainLayoutManager;
    private DataBaseOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Main inflated");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG,"Main toolbar");


        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.bringToFront();
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDbOpenHelper = new DataBaseOpenHelper(this);
        initializeDisplayContent();
    }

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainActivityRecyclerAdapter.notifyDataSetChanged();
        getLoaderManager().restartLoader(LOADER_MAIN, null, this);

        mDrawer.closeDrawer(GravityCompat.START);
        updateNavHeader();
    }

    private void initializeDisplayContent() {

        DataManager.loadFromDatabase(mDbOpenHelper);

        mRecyclerItems = findViewById(R.id.list_main);
        mMainLayoutManager = new LinearLayoutManager(this);

        mMainActivityRecyclerAdapter = new MainActivityRecyclerAdapter(this,null);


        displayMain();
    }
    private void displayMain() {
        mRecyclerItems.setLayoutManager(mMainLayoutManager);
        mRecyclerItems.setAdapter(mMainActivityRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void updateNavHeader() {

    }
    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader loader = null;
        if(i == LOADER_MAIN) {
            loader = new CursorLoader(this) {
                @Override
                public Cursor loadInBackground() {
                    SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
                    final String[] Columns = {
                            MainEntry.getQName(MainEntry._ID),
                            MainEntry.COLUMN_TITLE,
                            MainEntry.COLUMN_DESCRIPTION};

                    final String mainOrderBy = MainEntry.COLUMN_TITLE;

                    return db.query( MainEntry.TABLE_NAME,Columns,
                            null, null, null, null,mainOrderBy);
                }
            };
        }
        return loader;
    }
    @Override
    public void onLoadFinished(Loader loader, Cursor data) {
        if(loader.getId() == LOADER_MAIN)  {
            mMainActivityRecyclerAdapter.changeCursor(data);
        }
    }
    @Override
    public void onLoaderReset(Loader loader) {
        if(loader.getId() == LOADER_MAIN)  {
            mMainActivityRecyclerAdapter.changeCursor(null);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG,"Main activity Navigation drawer opened");

        if (item.getItemId() == R.id.nav_breeds) {
            Toast.makeText(this,"BREEDS",Toast.LENGTH_SHORT).show();
            Intent breeds = new Intent(this, BreedsExamplesActivity.class);
            startActivity(breeds);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId() == R.id.nav_brooding) {
            Toast.makeText(this,"BROODING",Toast.LENGTH_SHORT).show();
            Intent brooding = new Intent(this, BroodingActivity.class);
            startActivity(brooding);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_housing_and_equipment){
            Toast.makeText(this,"HOUSING AND EQUIPMENT",Toast.LENGTH_SHORT).show();
            Intent housing = new Intent(this, HousingAndEquipmentActivity.class);
            startActivity(housing);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_poultry_management){
            Toast.makeText(this,"POULTRY MANAGEMENT",Toast.LENGTH_SHORT).show();
            Intent management = new Intent(this, PoultryManagementActivity.class);
            startActivity(management);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_common_diseases){
            Toast.makeText(this,"COMMON DISEASES", Toast.LENGTH_SHORT).show();
            Intent commonDiseases = new Intent(this, PoultryHealthManagementActivity.class);
            startActivity(commonDiseases);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_best_practice){
            Toast.makeText(this,"BEST PRACTICE", Toast.LENGTH_SHORT).show();
            Intent bestPractice = new Intent(this, BestPracticeActivity.class);
            startActivity(bestPractice);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }else if (item.getItemId()==R.id.nav_bad_habits){
            Toast.makeText(this,"BAD HABITS", Toast.LENGTH_SHORT).show();
            Intent badHabits = new Intent(this, BadHabitsActivity.class);
            startActivity(badHabits);
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            mDrawer.closeDrawer(GravityCompat.START);
//            finish();
            return true;
        }
        return false;
    }


}