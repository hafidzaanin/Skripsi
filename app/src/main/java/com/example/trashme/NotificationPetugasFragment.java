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
 * {@link NotificationPetugasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationPetugasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationPetugasFragment extends Fragment {

    public NotificationPetugasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_petugas, container, false);
    }


}
