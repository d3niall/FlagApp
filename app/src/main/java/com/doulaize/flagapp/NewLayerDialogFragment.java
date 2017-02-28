package com.doulaize.flagapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by rdeleuze on 2/27/2017
 */

public class NewLayerDialogFragment extends DialogFragment {

    static NewLayerDialogFragment newInstance() {
        return new NewLayerDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.text_select_new_layer_pattern));
        View v = inflater.inflate(R.layout.add_layer_fragment, container, false);

        GridLayout g = (GridLayout) v.findViewById(R.id.patterns_grid_view);
        int childCount = g.getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) g.getChildAt(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // your click code here
                }
            });
        }
        return v;
    }

}
