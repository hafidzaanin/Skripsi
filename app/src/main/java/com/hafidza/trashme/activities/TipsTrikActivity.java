package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hafidza.trashme.R;
import com.hafidza.trashme.adapter.TipsAdapter;
import com.hafidza.trashme.models.TipsViewModel;

import java.util.ArrayList;

public class TipsTrikActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TipsAdapter adapter;
    ArrayList<TipsViewModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_trik);

        items = new ArrayList<>();
        items.add(new TipsViewModel(R.drawable.pilah_sampah, "Cara Memilah Sampah", "selengkapnya"));
        items.add(new TipsViewModel(R.drawable.sampah_organik, "Cara Mengolah Sampah Organik", "selengkapnya"));
        items.add(new TipsViewModel(R.drawable.sampah_rumah, "Cara Mengolah Sampah Rumah Tangga", "selengkapnya"));
        items.add(new TipsViewModel(R.drawable.pot_plastik, "Tips Me-Reuse Botol Bekas", "selengkapnya"));
        items.add(new TipsViewModel(R.drawable.reuse_reduce_recycle, "Tips dan Trik Mengolah Sampah", "selengkapnya"));

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new TipsAdapter(TipsTrikActivity.this, items);
        recyclerView.setAdapter(adapter);
    }
}
