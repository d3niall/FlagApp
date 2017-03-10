package com.doulaize.flagapp.adapter;

import com.doulaize.flagapp.R;
import com.doulaize.flagapp.model.Layer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by rdeleuze on 2/21/2017
 */

public class FlagLayersAdapter extends ArrayAdapter<Layer> {

    public FlagLayersAdapter(Context context, int resource, List<Layer> items) {
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

        String imageName = "pattern_" + layer.getPatternInterface().getPatternType().name().toLowerCase();

        ImageView imageView = (ImageView) v.findViewById(R.id.image_layer);
        ImageView iconVisibilityView = (ImageView) v.findViewById(R.id.icons_layer_hidden);

        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier(imageName, "drawable", getContext().getPackageName());

        imageView.setImageResource(resourceId);

        if (null != layer && layer.isActive()) {
            v.setBackgroundResource(R.drawable.ic_tab_light);
        } else {
            v.setBackgroundResource(R.drawable.ic_tab_dark);
        }

        if (layer.isVisible())
            iconVisibilityView.setVisibility(View.INVISIBLE);
        else
            iconVisibilityView.setVisibility(View.VISIBLE);

        return v;
    }


}
