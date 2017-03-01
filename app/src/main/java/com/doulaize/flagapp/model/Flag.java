package com.doulaize.flagapp.model;

import com.doulaize.flagapp.patterns.PatternInterface;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

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

    public void addLayer(PatternInterface.patternTypeEnum patternTypeEnum){

        Layer l = new Layer(patternTypeEnum);
        if (null == layers)
            throw new IllegalStateException();

        layers.add(l);

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
