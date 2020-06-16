package com.edisondeveloper.petagram;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ActivityTopFive extends AppCompatActivity {

    private ArrayList<Mascota> topList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_five);
        Toolbar appBar = findViewById(R.id.appBar);
        setSupportActionBar(appBar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.button_back);
        }
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            topList = extras.getParcelableArrayList(MainActivity.EXTRA_LIST);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        MascotaAdapter adapter = new MascotaAdapter(this, topList);
        recyclerView.setAdapter(adapter);
    }
}