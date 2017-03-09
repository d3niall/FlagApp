package com.doulaize.flagapp.model;

import com.doulaize.flagapp.patterns.FlagPatternBorder;
import com.doulaize.flagapp.patterns.FlagPatternCross;
import com.doulaize.flagapp.patterns.FlagPatternFess;
import com.doulaize.flagapp.patterns.FlagPatternGreekCross;
import com.doulaize.flagapp.patterns.FlagPatternPale;
import com.doulaize.flagapp.patterns.FlagPatternPall;
import com.doulaize.flagapp.patterns.FlagPatternQuadrisection;
import com.doulaize.flagapp.patterns.FlagPatternSaltire;
import com.doulaize.flagapp.patterns.PatternInterface;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 2/16/2017
 */

public class Layer {

    private PatternInterface mPatternInterface;
    private Grid mGrid;
    private Ratio mRatio;
    private boolean active = false;

    public Layer(PatternInterface.patternTypeEnum patternTypeEnum) {

        switch (patternTypeEnum) {
            case BORDER:
                this.mPatternInterface = new FlagPatternBorder();
                break;
            case CROSS:
                this.mPatternInterface = new FlagPatternCross();
                break;
            case FESS:
                this.mPatternInterface = new FlagPatternFess();
                break;
            case GREEKCROSS:
                this.mPatternInterface = new FlagPatternGreekCross();
                break;
            case PALE:
                this.mPatternInterface = new FlagPatternPale();
                break;
            case PALL:
                this.mPatternInterface = new FlagPatternPall();
                break;
            case QUADRISECTION:
                this.mPatternInterface = new FlagPatternQuadrisection();
                break;
            case SALTIRE:
                this.mPatternInterface = new FlagPatternSaltire();
                break;
            default:
                throw new IllegalStateException();

        }

        this.mPatternInterface.setRatio(mRatio);
    }

    public void onDraw(Canvas canvas) {

        if (mPatternInterface != null)
            mPatternInterface.onDraw(canvas);
    }

    public PatternInterface getPatternInterface() {
        return mPatternInterface;
    }

    public void setPatternInterface(PatternInterface patternInterface) {
        mPatternInterface = patternInterface;
    }

    public Grid getGrid() {
        return mGrid;
    }

    public void setGrid(Grid grid) {
        mGrid = grid;
    }

    public Ratio getRatio() {
        return mRatio;
    }

    public void setRatio(Ratio ratio) {
        mRatio = ratio;
        mPatternInterface.setRatio(ratio);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setColor(float x, float y, int color) {
        mPatternInterface.setColor(x, y, color);
    }
}
