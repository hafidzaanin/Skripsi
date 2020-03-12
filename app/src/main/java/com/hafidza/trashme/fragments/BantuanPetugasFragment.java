package com.hafidza.trashme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hafidza.trashme.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BantuanPetugasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BantuanPetugasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BantuanPetugasFragment extends Fragment {

    public BantuanPetugasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bantuan_petugas, container, false);
    }


}
