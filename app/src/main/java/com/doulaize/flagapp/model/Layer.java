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
 * Created by rdeleuze on 2/16/17.
 */

public class Layer {

    private PatternInterface patternInterface;
    private Grid grid;
    private boolean active = false;

    public Layer(PatternInterface.patternTypeEnum patternTypeEnum) {

        switch (patternTypeEnum) {
            case BORDER:
                this.patternInterface = new FlagPatternBorder();
                break;
            case CROSS:
                this.patternInterface = new FlagPatternCross();
                break;
            case FESS:
                this.patternInterface = new FlagPatternFess();
                break;
            case GREEK_CROSS:
                this.patternInterface = new FlagPatternGreekCross();
                break;
            case PALE:
                this.patternInterface = new FlagPatternPale();
                break;
            case PALL:
                this.patternInterface = new FlagPatternPall();
                break;
            case QUADRISECTION:
                this.patternInterface = new FlagPatternQuadrisection();
                break;
            case SALTIRE:
                this.patternInterface = new FlagPatternSaltire();
                break;
            default:
                throw new IllegalStateException();

        }
    }

    public void onDraw(Canvas canvas) {

        if (patternInterface != null)
            patternInterface.onDraw(canvas);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
