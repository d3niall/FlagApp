package com.doulaize.flagapp.model;

import com.doulaize.flagapp.patterns.PatternInterface;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rdeleuze on 2/21/2017
 */

public class Flag {

    private List<Layer> layers;
    private Ratio ratio;


    public Flag(int viewWidth, int viewHeight) {
        layers = new ArrayList<>();
        ratio = new Ratio(viewWidth, viewHeight);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void addLayer(PatternInterface.patternTypeEnum patternTypeEnum) {

        Layer l = new Layer(patternTypeEnum);
        if (null == layers)
            throw new IllegalStateException();

        for (int i = 0; i < layers.size(); i++) {
            layers.get(i).setActive(false);
        }

        l.setActive(true);
        layers.add(l);
    }

    public void setActiveLayer(int layerIndex) {

        if (layerIndex == -1)
            layerIndex = layers.size() - 1;

        for (int i = 0; i < layers.size(); i++) {
            if (i != layerIndex)
                layers.get(i).setActive(false);
            else
                layers.get(i).setActive(true);
        }
    }

    public void deleteActiveLayer() {

        ListIterator<Layer> iter = layers.listIterator();
        while (iter.hasNext()) {
            if (iter.next().isActive()) {
                iter.remove();
            }
        }
    }

    public int getLayersNumber() {

        if (layers == null)
            return 0;
        return getLayers().size();
    }

    public void onDraw(Canvas canvas) {
        if (layers == null) {
            return;
        }
        for (int i = 0; i < layers.size(); i++)
            layers.get(i).onDraw(canvas);
    }

    public Ratio getRatio() {
        return ratio;
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }
}
