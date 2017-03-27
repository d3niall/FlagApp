package com.doulaize.flagapp.adapter;

import com.doulaize.flagapp.R;
import com.doulaize.flagapp.model.Layer;
import com.terlici.dragndroplist.DragNDropAdapter;
import com.terlici.dragndroplist.DragNDropListView;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by rdeleuze on 2/21/2017
 */

public class FlagLayersAdapter extends ArrayAdapter<Layer> implements DragNDropAdapter {
    int mPosition[];
    private int mHandler;
    private List<Layer> dataList;

    public FlagLayersAdapter(Context context, int resource, List<Layer> items) {
        super(context, resource, items);
        mHandler = R.id.image_layer;
        dataList = items;
        setup(items.size());
    }

    private void setup(int size) {
        mPosition = new int[size];

        for (int i = 0; i < size; ++i)
            mPosition[i] = i;
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


    @Override
    public Layer getItem(int position) {
        return super.getItem(mPosition[position]);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(mPosition[position]);
    }

    @Override
    public void onItemDrag(DragNDropListView parent, View view, int position, long id) {

    }

    @Override
    public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id) {
        int position = mPosition[startPosition];

        if (startPosition < endPosition)
            for(int i = startPosition; i < endPosition; ++i)
                mPosition[i] = mPosition[i + 1];
        else if (endPosition < startPosition)
            for(int i = startPosition; i > endPosition; --i)
                mPosition[i] = mPosition[i - 1];

        mPosition[endPosition] = position;

        List<Layer> dataListTmp = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++){
            dataListTmp.add(dataList.get(mPosition[i]));
        }

        for (int i = 0; i < dataListTmp.size(); i++){
            dataList.set(i, dataListTmp.get(i));
        }

        setup(dataList.size());

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public void notifyDataSetChanged() {
        setup(dataList.size());
        super.notifyDataSetChanged();
    }

    @Override
    public int getDragHandler() {
        return mHandler;
    }
    
}
