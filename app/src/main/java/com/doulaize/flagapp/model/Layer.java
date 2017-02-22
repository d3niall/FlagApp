package com.doulaize.flagapp.model;

import com.doulaize.flagapp.patterns.Pale;

/**
 * Created by rdeleuze on 2/16/17.
 */

public class Layer {

    public Pale pale;
    public Grid grid;
    private boolean active = false;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
