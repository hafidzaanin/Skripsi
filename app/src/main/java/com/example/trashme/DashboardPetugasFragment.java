package com.example.trashme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardPetugasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardPetugasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardPetugasFragment extends Fragment {

    public DashboardPetugasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_petugas, container, false);
    }
    
}
