package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdeleuze on 2/16/2017
 */
public class FlagPatternPale extends PatternInterface {

    private List<Float> mTopCoordinates;
    private List<Float> mBottomCoordinates;
    private List<Color> mColors;

    private Paint paint = new Paint();

    public FlagPatternPale() {
        mTopCoordinates = new ArrayList<>();
        mBottomCoordinates = new ArrayList<>();
        mColors = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            mTopCoordinates.add((float) (i * 100. / 3D));
            mBottomCoordinates.add((float) (i * 100. / 3D));
        }
    }

    private void setupPaint() {
//        // Setup paint with color and stroke styles
//        drawPaint = new Paint();
//        drawPaint.setColor(paintColor);
//        drawPaint.setAntiAlias(true);
//        drawPaint.setStrokeWidth(5);
//        drawPaint.setStyle(Paint.Style.STROKE);
//        drawPaint.setStrokeJoin(Paint.Join.ROUND);
//        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }


    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.PALE;
    }

    @Override
    public void onDraw(Canvas canvas, Integer horizontalOffset, Integer verticalOffset, Integer maxWidth, Integer maxHeight) {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.MAGENTA);
        float newWidth = 0;
        float newHeight = 0;

        if ((maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW() < (maxHeight - 2 * verticalOffset)) {
            newWidth = maxWidth - 2 * horizontalOffset;
            newHeight = (maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW();
        } else {
            newWidth = (maxHeight - 2 * verticalOffset) * mRatio.getEW() / mRatio.getNS();
            newHeight = maxHeight - 2 * verticalOffset;
        }
        float newHorizontalOffset = (maxWidth - newWidth) / 2;
        float newVerticalOffset = (maxHeight - newHeight) / 2;

        paint.setColor(Color.BLACK);

        float yTop = newVerticalOffset;
        float yBottom = newVerticalOffset + newHeight;

        float xTop = newHorizontalOffset;
        float xBottom = newHorizontalOffset;

        for (int i = 0; i < mTopCoordinates.size(); ++i) {

            Path path = new Path();
            path.moveTo(xTop, yTop);
            path.lineTo(newHorizontalOffset + mTopCoordinates.get(i) * newWidth / 100, yTop);
            path.lineTo(newHorizontalOffset + mBottomCoordinates.get(i) * newWidth / 100, yBottom);
            path.lineTo(xBottom, yBottom);
            path.lineTo(xTop, yTop);

            canvas.drawPath(path, paint);

            xTop = newHorizontalOffset + mTopCoordinates.get(i) * newWidth / 100;
            xBottom = newHorizontalOffset + mBottomCoordinates.get(i) * newWidth / 100;
        }

        Path path = new Path();
        path.moveTo(xTop, yTop);
        path.lineTo(newHorizontalOffset + newWidth, yTop);
        path.lineTo(newHorizontalOffset + newWidth, yBottom);
        path.lineTo(xBottom, yBottom);
        path.lineTo(xTop, yTop);

        canvas.drawPath(path, paint);
    }

    @Override
    public boolean isButtonAddAllowed() {
        return true;
    }

    @Override
    public boolean isButtonRemoveAllowed() {
        return true;
    }

    @Override
    public void buttonAddPressed() {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        float r = (mTopCoordinates.size() + 1.0f) * 1.0f / (mTopCoordinates.size() + 2.0f);

        for (int i = 0; i < mTopCoordinates.size(); i++) {

            mTopCoordinates.set(i, mTopCoordinates.get(i) * r);
            mBottomCoordinates.set(i, mBottomCoordinates.get(i) * r);
        }

        mTopCoordinates.add(100 * r);
        mBottomCoordinates.add(100 * r);
    }

    @Override
    public void buttonRemovePressed() {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        float r = (mTopCoordinates.size() + 2.0f) / (mTopCoordinates.size() + 1.0f);

        for (int i = 0; i < mTopCoordinates.size() - 1; i++) {

            mTopCoordinates.set(i, mTopCoordinates.get(i) * r);
            mBottomCoordinates.set(i, mBottomCoordinates.get(i) * r);
        }

        if (mTopCoordinates.size() > 0) {
            mTopCoordinates.remove(mTopCoordinates.size() - 1);
            mBottomCoordinates.remove(mBottomCoordinates.size() - 1);
        }
    }
}
