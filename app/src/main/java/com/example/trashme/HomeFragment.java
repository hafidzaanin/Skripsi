package com.example.trashme;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageButton menu;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        menu = (ImageButton) rootView.findViewById(R.id.button_Pelanggaran);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PelanggaranActivity.class);
                startActivity(intent);
            }
        });

        menu = (ImageButton) rootView.findViewById(R.id.button_Tips);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipsTrikActivity.class);
                startActivity(intent);
            }
        });

        menu = (ImageButton) rootView.findViewById(R.id.button_Kreasi);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KreasiActivity.class);
                startActivity(intent);
            }
        });

        menu = (ImageButton) rootView.findViewById(R.id.button_Tagihan);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TagihanActivity.class);
                startActivity(intent);
            }
        });

        menu = (ImageButton) rootView.findViewById(R.id.button_Jadwal);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JadwalActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
