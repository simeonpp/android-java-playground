package com.example.simeon.firstapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.simeon.firstapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    public SimpleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_simple, container, false);

        TextView tvFragmentText = (TextView) root.findViewById(R.id.tv_fragment_text);
        tvFragmentText.setText("Set from fragment logic");

        return root;
    }
}
