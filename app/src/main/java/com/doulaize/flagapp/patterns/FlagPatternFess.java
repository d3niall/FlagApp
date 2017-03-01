package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;

/**
 * Created by rdeleuze on 3/1/17.
 */
public class FlagPatternFess implements PatternInterface {

    @Override
    public void onDraw(Canvas canvas){

    }

    @Override
    public patternTypeEnum getPatternType(){
        return patternTypeEnum.FESS;
    }
}
