package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */

public interface PatternInterface {

    public patternTypeEnum getPatternType();

    public void onDraw(Canvas canvas);

    public enum patternTypeEnum {
        BORDER, CROSS, FESS, GREEK_CROSS, PALE, PALL, QUADRISECTION, SALTIRE
    }
}
