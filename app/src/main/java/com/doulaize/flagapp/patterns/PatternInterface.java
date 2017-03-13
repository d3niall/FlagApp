package com.doulaize.flagapp.patterns;

import com.doulaize.flagapp.model.Ratio;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */

public abstract class PatternInterface {

    Ratio mRatio;

    abstract public patternTypeEnum getPatternType();

    public void setRatio(Ratio ratio) {
        mRatio = ratio;
    }

    abstract public void onDraw(Canvas canvas, boolean drawEdges);

    public boolean isButtonOrthoAllowed() {
        return false;
    }

    public boolean isButtonHorizontalEqAllowed() {
        return false;
    }

    public boolean isButtonVerticalEqAllowed() {
        return false;
    }

    public boolean isButtonAddAllowed() {
        return false;
    }

    public boolean isButtonRemoveAllowed() {
        return false;
    }

    public boolean isButtonReverseHorizontalAllowed() {
        return false;
    }

    public boolean isButtonReverseVerticalAllowed() {
        return false;
    }


    public void buttonOrthoPressed() {
    }

    public void buttonHorizontalEqPressed() {
    }

    public void buttonVerticalEqPressed() {
    }

    public void buttonAddPressed() {
    }

    public void buttonRemovePressed() {
    }

    public void buttonReverseHorizontalPressed() {
    }

    public void buttonReverseVerticalPressed() {
    }

    public void setColor(float x, float y, int color) {
    }

    public enum patternTypeEnum {
        BORDER, CROSS, FESS, GREEKCROSS, PALE, PALL, QUADRISECTION, SALTIRE
    }
}
