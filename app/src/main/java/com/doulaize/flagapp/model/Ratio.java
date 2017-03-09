package com.doulaize.flagapp.model;

import android.graphics.Point;

/**
 * Created by rdeleuze on 2/24/2017
 */

public class Ratio {

    public static int DEFAULT_RATIO_HORIZONTAL_VALUE = 4;
    public static int DEFAULT_RATIO_VERTICAL_VALUE = 3;

    private Integer EW;
    private Integer NS;

    private float mMinimalHorizontalOffset = 0f;
    private float mMinimalVerticalOffset = 0f;
    private float mMaximalViewWidth = 1.0f;
    private float mMaximalViewHeight = 1.0f;


    private float mHorizontalOffset;
    private float mVerticalOffset;
    private float mViewWidth;
    private float mViewHeight;

    public Ratio() {

        EW = DEFAULT_RATIO_HORIZONTAL_VALUE;
        NS = DEFAULT_RATIO_VERTICAL_VALUE;
    }

    public Ratio(Integer EW, Integer NS) {

        this.EW = EW;
        this.NS = NS;
    }

    public Integer getEW() {
        return EW;
    }

    public void setEW(Integer EW) {
        this.EW = EW;
        calculateOffsets();
    }

    public Integer getNS() {
        return NS;
    }

    public void setNS(Integer NS) {
        this.NS = NS;
        calculateOffsets();
    }


    public void setMinimalHorizontalOffset(float horizontalOffset) {
        mMinimalHorizontalOffset = horizontalOffset;
        calculateOffsets();
    }


    public void setMinimalVerticalOffset(float verticalOffset) {
        mMinimalVerticalOffset = verticalOffset;
        calculateOffsets();
    }

    public float getViewHeight() {
        return mViewHeight;
    }

    private void setViewHeight(float viewHeight) {
    }

    public void setMaximumViewHeight(float viewHeight) {
        mMaximalViewHeight = viewHeight;
        calculateOffsets();
    }

    public float getViewWidth() {
        return mViewWidth;
    }

    private void setViewWidth(float viewWidth) {
        mViewWidth = viewWidth;
    }

    public void setMaximumViewWidth(float viewWidth) {
        mMaximalViewWidth = viewWidth;
        calculateOffsets();
    }

    private void calculateOffsets() {

        if ((mMaximalViewWidth - 2 * mMinimalHorizontalOffset) * getNS() / getEW() < (mMaximalViewHeight - 2 * mMinimalVerticalOffset)) {
            mViewWidth = mMaximalViewWidth - 2 * mMinimalHorizontalOffset;
            mViewHeight = (mMaximalViewWidth - 2 * mMinimalHorizontalOffset) * getNS() / getEW();
        } else {
            mViewWidth = (mMaximalViewHeight - 2 * mMinimalVerticalOffset) * getEW() / getNS();
            mViewHeight = mMaximalViewHeight - 2 * mMinimalVerticalOffset;
        }
        mHorizontalOffset = (mMaximalViewWidth - mViewWidth) / 2;
        mVerticalOffset = (mMaximalViewHeight - mViewHeight) / 2;

    }

    public float getHorizontalOffset() {
        return mHorizontalOffset;
    }

    private void setHorizontalOffset(float minimalHorizontalOffset) {
    }

    public float getVerticalOffset() {
        return mVerticalOffset;
    }

    private void setVerticalOffset(float minimalVerticalOffset) {
    }

    public void setMaximalViewWidth(float maximalViewWidth) {
        mMaximalViewWidth = maximalViewWidth;
    }

    public void setMaximalViewHeight(float maximalViewHeight) {
        mMaximalViewHeight = maximalViewHeight;
    }

    public Point getOrthoCoord (float aX, float aY){

        Point p = new Point();

        p.x = (int)( (100./mViewWidth) *(aX - mHorizontalOffset));
        p.y = (int)( (100./mViewHeight) *(aX - mVerticalOffset));

        return p;
    }
}
