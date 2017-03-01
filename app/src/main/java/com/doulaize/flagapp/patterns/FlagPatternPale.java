package com.doulaize.flagapp.patterns;

import com.doulaize.flagapp.exception.CoordinateException;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdeleuze on 2/16/2017
 */
public class FlagPatternPale implements PatternInterface {


    // setup initial color
    private final int paintColor = Color.RED;
    // defines paint and canvas
    private Paint drawPaint;

    private Integer mMinCoordinate;
    private Integer mMaxCoordinate;
    private List<Integer> mCoordinates;
    private List<Integer> mSecondaryCoordinates;
    private Boolean isRegular;


    public FlagPatternPale() {

    }

    public FlagPatternPale(Integer aMin, Integer aMax) {

        mMinCoordinate = aMin;
        mMaxCoordinate = aMax;
        mCoordinates = new ArrayList<Integer>();
    }

    void addPoint(Integer mNewCoordinate) {

        if (mNewCoordinate > mMaxCoordinate || mNewCoordinate < mMinCoordinate) {
            throw new CoordinateException(mNewCoordinate, mMinCoordinate, mMaxCoordinate);
        }

        int position;
        for (position = 0; position < mCoordinates.size(); position++) {
            if (mCoordinates.get(position) > mNewCoordinate)
                break;
        }

        mCoordinates.add(position, mNewCoordinate);

    }

    void addPoint(boolean evenOutResult) {

        Integer newCoord = (Integer) (((mCoordinates.size() > 0 ? mCoordinates.get(mCoordinates.size() - 1) : mMinCoordinate) + mMaxCoordinate) / 2);
        mCoordinates.add(newCoord);

        if (evenOutResult)
            makeRegular();

    }

    void makeRegular() {
        Integer step = (Integer) ((mMaxCoordinate - mMinCoordinate) / (1 + mCoordinates.size()));
        for (int i = 0; i < mCoordinates.size(); i++) {
            mCoordinates.set(i, (i + 1) * step);
        }
    }

    private void setupPaint() {
        // Setup paint with color and stroke styles
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.PALE;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (drawPaint == null)
            setupPaint();

        canvas.drawCircle(20, 20, 15, drawPaint);
    }

}