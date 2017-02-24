package com.doulaize.flagapp.model;

import com.doulaize.flagapp.patterns.Pale;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 2/16/17.
 */

public class Layer {

    private Pale pale;
    private Grid grid;
    private Integer index;
    private boolean active = false;

    public Layer(Integer i) {

        this.index = i;
        this.pale = new Pale();
    }

    public void onDraw(Canvas canvas) {

        if (pale != null)
            pale.onDraw(canvas);

    }

    public Pale getPale() {
        return pale;
    }

    public void setPale(Pale pale) {
        this.pale = pale;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
