package com.doulaize.flagapp.adapter;

import com.doulaize.flagapp.R;
import com.doulaize.flagapp.model.Layer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by rdeleuze on 2/21/2017
 */

public class ToolbarAdapter extends ArrayAdapter<Layer> {

    public ToolbarAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ToolbarAdapter(Context context, int resource, List<Layer> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.first_toolbar_item, null);
        }

        Layer layer = getItem(position);

        if (null != layer && layer.isActive()) {
            v.setBackgroundResource(R.drawable.ic_tab_light);
        } else {
            v.setBackgroundResource(R.drawable.ic_tab_dark);
        }

        return v;
    }

}
