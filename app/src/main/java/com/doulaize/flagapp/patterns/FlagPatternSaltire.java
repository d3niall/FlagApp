package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/2017
 */
public class FlagPatternSaltire extends PatternInterface {

    @Override
    public void onDraw(Canvas canvas, Integer horizontalOffset, Integer verticalOffset, Integer maxWidth, Integer maxHeight) {

    }

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.SALTIRE;
    }

    @Override
    public void buttonAddPressed() {
        // TODO
    }

    @Override
    public void buttonRemovePressed() {
        // TODO
    }
}
