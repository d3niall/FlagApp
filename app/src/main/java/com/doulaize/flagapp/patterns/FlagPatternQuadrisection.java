package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */
public class FlagPatternQuadrisection extends PatternInterface {

    @Override
    public void onDraw(Canvas canvas, Integer horizontalOffset, Integer verticalOffset, Integer maxWidth, Integer maxHeight) {

    }

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.QUADRISECTION;
    }

}
