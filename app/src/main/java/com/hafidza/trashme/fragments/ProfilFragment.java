package com.hafidza.trashme.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hafidza.trashme.R;
import com.hafidza.trashme.activities.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    Button login;


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

    View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
    login = (Button) rootView.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    });
        return rootView;
    }


}
