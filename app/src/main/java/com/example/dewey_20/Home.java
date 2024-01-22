package com.example.dewey_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class Home extends Fragment {

    ImageButton imtVerTareas, imtVerGrupos;
    VerTareas verTareas = new VerTareas();
    VerGrupos verGrupos = new VerGrupos();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imtVerTareas = view.findViewById(R.id.imtVerTareas);
        imtVerGrupos = view.findViewById(R.id.imtVerGrupos);


        imtVerTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.frameContainer, verTareas);

                // Commit the transaction
                transaction.commit();
            }

        });
        imtVerGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

                // Replace whatever is in the fragment_container view with this fragment
                transaction.replace(R.id.frameContainer, verGrupos);

                // Commit the transaction
                transaction.commit();
            }

        });

        return view;
    }
}