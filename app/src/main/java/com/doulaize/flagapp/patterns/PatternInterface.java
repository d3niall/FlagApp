package com.doulaize.flagapp.patterns;

import com.doulaize.flagapp.model.Ratio;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */

public abstract class PatternInterface {

    Ratio mRatio;

    abstract public patternTypeEnum getPatternType();

    abstract public void onDraw(Canvas canvas, boolean drawEdges);

    public boolean isButtonAddAllowed() {
        return false;
    }

    public boolean isButtonRemoveAllowed() {
        return false;
    }

    public void setRatio(Ratio ratio) {
        mRatio = ratio;
    }

    public void buttonAddPressed() {
    }


    public void buttonRemovePressed() {
    }

    public void setColor(float x, float y, int color) {
    }


    public enum patternTypeEnum {
        BORDER, CROSS, FESS, GREEKCROSS, PALE, PALL, QUADRISECTION, SALTIRE
    }
}
