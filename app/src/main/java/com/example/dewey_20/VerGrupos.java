package com.example.dewey_20;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class VerGrupos extends Fragment {

    ListView lvGrupos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_grupos, container, false);
        lvGrupos = view.findViewById(R.id.lvGrupos);

        String[] nombres = getResources().getStringArray(R.array.Grupos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, nombres);
        lvGrupos.setAdapter(adapter);

        return view;
    }
}