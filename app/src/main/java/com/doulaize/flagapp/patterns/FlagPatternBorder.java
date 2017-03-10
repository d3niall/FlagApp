package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */
public class FlagPatternBorder extends PatternInterface {

    @Override
    public void onDraw(Canvas canvas, boolean drawEdges) {

    }

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.BORDER;
    }

}
