package com.doulaize.flagapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdeleuze on 2/21/17.
 */

public class Flag {

    public List<Layer> layers;

    public void addLayer(Layer l) {
        if (null == layers)
            layers = new ArrayList<Layer>();

        layers.add(l);
    }

    public List<Layer> getLayers() {
        return layers;
    }
}
