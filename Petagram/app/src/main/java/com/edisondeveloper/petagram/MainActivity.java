package com.edisondeveloper.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Mascota> listPets;
    private ArrayList<Mascota> listTop;
    public static final String EXTRA_LIST = "list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipRefreshLayout);
        Toolbar toolbar = findViewById(R.id.appBarLayout);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        listPets = new ArrayList<>();
        listPets.add(new Mascota(getString(R.string.mascot01),R.drawable.mascotas_img_01,3));
        listPets.add(new Mascota(getString(R.string.mascot02),R.drawable.mascotas_img_02, 5));
        listPets.add(new Mascota(getString(R.string.mascot03),R.drawable.mascotas_img_03,6));
        listPets.add(new Mascota(getString(R.string.mascot04),R.drawable.mascotas_img_04, 2));
        listPets.add(new Mascota(getString(R.string.mascot05),R.drawable.mascotas_img_05, 1));
        listPets.add(new Mascota(getString(R.string.mascot06),R.drawable.mascotas_img_06, 9));
        listPets.add(new Mascota(getString(R.string.mascot07),R.drawable.mascotas_img_07, 7));
        listPets.add(new Mascota(getString(R.string.mascot08),R.drawable.mascotas_img_08, 10));
        listPets.add(new Mascota(getString(R.string.mascot09),R.drawable.mascotas_img_09, 8));
        listPets.add(new Mascota(getString(R.string.mascot10),R.drawable.mascotas_img_10,5));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MascotaAdapter adapter = new MascotaAdapter(this, listPets);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_option:
                listTop = new ArrayList<>();
                Collections.sort(listPets, new Comparator<Mascota>() {
                    @Override
                    public int compare(Mascota mascota, Mascota t1) {
                        return Integer.valueOf(t1.getPuntuacion()).compareTo(mascota.getPuntuacion());
                    }
                });
                for(int i=0; i<5; i++){
                    Mascota mascotaTop = listPets.get(i);
                    listTop.add(mascotaTop);
                }
                Intent topActivity = new Intent(this, ActivityTopFive.class);
                topActivity.putParcelableArrayListExtra(EXTRA_LIST, listTop);
                startActivity(topActivity);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}