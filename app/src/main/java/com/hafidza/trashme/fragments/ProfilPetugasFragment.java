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
 * {@link ProfilPetugasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilPetugasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilPetugasFragment extends Fragment {

    public ProfilPetugasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_petugas, container, false);
    }

}
