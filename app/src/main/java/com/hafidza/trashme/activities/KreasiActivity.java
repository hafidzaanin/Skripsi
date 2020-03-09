package com.hafidza.trashme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hafidza.trashme.adapter.KreasiAdapter;
import com.hafidza.trashme.models.KreasiViewModel;
import com.hafidza.trashme.R;

import java.util.ArrayList;

public class KreasiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    KreasiAdapter adapter;
    ArrayList<KreasiViewModel> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kreasi);

        items = new ArrayList<>();
        items.add(new KreasiViewModel(R.drawable.gantungan_bunga, "Gantungan Bunga", "selengkapnya"));
        items.add(new KreasiViewModel(R.drawable.kalung, "Kalung", "selengkapnya"));
        items.add(new KreasiViewModel(R.drawable.lampion, "Lampion", "selengkapnya"));
        items.add(new KreasiViewModel(R.drawable.bunga_hias, "Bunga Hias", "selengkapnya"));
        items.add(new KreasiViewModel(R.drawable.tempat_lilin, "Tempat Lilin", "selengkapnya"));

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new KreasiAdapter(KreasiActivity.this, items);
        recyclerView.setAdapter(adapter);
    }
}
