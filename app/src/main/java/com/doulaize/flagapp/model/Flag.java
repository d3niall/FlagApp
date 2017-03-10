package com.doulaize.flagapp.model;

import com.doulaize.flagapp.common.Constants;
import com.doulaize.flagapp.patterns.PatternInterface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.doulaize.flagapp.model.Flag.APP_STATE.FLAG_FSM_STATE_COLOR_SELECTED;

/**
 * Created by rdeleuze on 2/21/2017
 */

public class Flag {

    private List<Layer> mLayers;

    private Ratio mRatio;
    private APP_STATE fmsState = APP_STATE.FLAG_FSM_STATE_IDLE;
    private int mColorSelected = Color.RED;

    private Paint paint = new Paint();

    public Flag() {
        mLayers = new ArrayList<>();
        mRatio = new Ratio();
    }

    public List<Layer> getLayers() {
        return mLayers;
    }

    public void addLayer(PatternInterface.patternTypeEnum patternTypeEnum) {

        Layer l = new Layer(patternTypeEnum);
        if (null == mLayers)
            throw new IllegalStateException();

        for (int i = 0; i < mLayers.size(); i++) {
            mLayers.get(i).setActive(false);
        }

        l.setActive(true);
        l.setRatio(mRatio);
        mLayers.add(l);
    }

    public void hideOrShowActiveLayer() {

        for (int i = 0; i < mLayers.size(); i++) {
            if (mLayers.get(i).isActive())
                mLayers.get(i).hideOrShowLayer();
        }
    }

    public void deleteActiveLayer() {

        ListIterator<Layer> iter = mLayers.listIterator();
        while (iter.hasNext()) {
            if (iter.next().isActive()) {
                iter.remove();
            }
        }
    }

    public Layer getActiveLayer() {

        for (int i = 0; i < mLayers.size(); i++) {

            if (mLayers.get(i).isActive()) {
                return mLayers.get(i);
            }
        }
        return null;
    }

    public void setActiveLayer(int layerIndex) {

        if (layerIndex == -1)
            layerIndex = mLayers.size() - 1;

        for (int i = 0; i < mLayers.size(); i++) {
            if (i != layerIndex)
                mLayers.get(i).setActive(false);
            else
                mLayers.get(i).setActive(true);
        }
    }

    public int getLayersNumber() {

        if (mLayers == null)
            return 0;
        return getLayers().size();
    }

    public void onDraw(Canvas canvas) {

        if (mLayers == null) {
            return;
        }

        for (int i = 0; i < mLayers.size(); i++) {
            mLayers.get(i).onDraw(canvas);
        }

        // Draw the edge
        float yTop = mRatio.getVerticalOffset();
        float yBottom = mRatio.getVerticalOffset() + mRatio.getViewHeight();
        float xLeft = mRatio.getHorizontalOffset();
        float xRight = mRatio.getHorizontalOffset() + mRatio.getViewWidth();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Constants.COLOR_EDGE);
        Path path = new Path();
        path.moveTo(xLeft, yTop);
        path.lineTo(xLeft, yBottom);
        path.lineTo(xRight, yBottom);
        path.lineTo(xRight, yTop);
        path.lineTo(xLeft, yTop);
        canvas.drawPath(path, paint);
    }

    public Ratio getRatio() {
        return mRatio;
    }

    public void setNewRatio(Integer ew, Integer ns) {
        if (mLayers == null || mRatio == null)
            throw new IllegalStateException();

        mRatio.setNS(ns);
        mRatio.setEW(ew);

        for (int i = 0; i < mLayers.size(); i++)
            mLayers.get(i).setRatio(mRatio);
    }

    public void setNewDimensions(float width, float height) {
        if (mLayers == null || mRatio == null)
            throw new IllegalStateException();

        mRatio.setMaximalViewWidth(width);
        mRatio.setMaximalViewHeight(height);

        for (int i = 0; i < mLayers.size(); i++)
            mLayers.get(i).setRatio(mRatio);
    }

    public APP_STATE getFmsState() {
        return fmsState;
    }

    public void setFmsState(APP_STATE fmsState) {
        this.fmsState = fmsState;
    }

    public int getColorSelected() {
        return mColorSelected;
    }

    public void setColorSelected(int colorSelected) {
        this.mColorSelected = colorSelected;
    }

    public void onClickDrawingArea(float x, float y) {

        if (fmsState == FLAG_FSM_STATE_COLOR_SELECTED) {
            Point p = mRatio.getOrthoCoord(x, y);

            if (0 < p.x && p.x < 100 && 0 < p.y && p.y < 100)
                getActiveLayer().setColor(p.x, p.y, getColorSelected());
        }

    }

    public void setOffset(Integer offsetH, Integer offsetV) {
        mRatio.setMinimalHorizontalOffset(offsetH);
        mRatio.setMinimalVerticalOffset(offsetV);
    }

    static public enum APP_STATE {FLAG_FSM_STATE_IDLE, FLAG_FSM_STATE_COLOR_SELECTED}
}
