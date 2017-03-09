package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

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

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.PALE;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        paint.setStyle(Paint.Style.STROKE);

//        //TODO : Move this into Ratio or PAtternInterface
//        if ((maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW() < (maxHeight - 2 * verticalOffset)) {
//            newWidth = maxWidth - 2 * horizontalOffset;
//            newHeight = (maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW();
//        } else {
//            newWidth = (maxHeight - 2 * verticalOffset) * mRatio.getEW() / mRatio.getNS();
//            newHeight = maxHeight - 2 * verticalOffset;
//        }
//        float newHorizontalOffset = (maxWidth - newWidth) / 2;
//        float newVerticalOffset = (maxHeight - newHeight) / 2;

        paint.setColor(Color.BLACK);

        float yTop = mRatio.getVerticalOffset();
        float yBottom = mRatio.getVerticalOffset() + mRatio.getViewHeight();

        float xTop = mRatio.getHorizontalOffset();
        float xBottom = mRatio.getHorizontalOffset();

        for (int i = 0; i < mTopCoordinates.size(); ++i) {

            Path path = new Path();
            path.moveTo(xTop, yTop);
            path.lineTo(mRatio.getHorizontalOffset() + mTopCoordinates.get(i) * mRatio.getViewWidth() / 100, yTop);
            path.lineTo(mRatio.getHorizontalOffset() + mBottomCoordinates.get(i) * mRatio.getViewWidth() / 100, yBottom);
            path.lineTo(xBottom, yBottom);
            path.lineTo(xTop, yTop);

            canvas.drawPath(path, paint);

            xTop = mRatio.getHorizontalOffset() + mTopCoordinates.get(i) * mRatio.getViewWidth() / 100;
            xBottom = mRatio.getHorizontalOffset() + mBottomCoordinates.get(i) * mRatio.getViewWidth() / 100;
        }

        Path path = new Path();
        path.moveTo(xTop, yTop);
        path.lineTo(mRatio.getHorizontalOffset() + mRatio.getViewWidth(), yTop);
        path.lineTo(mRatio.getHorizontalOffset() + mRatio.getViewWidth(), yBottom);
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

        if (mTopCoordinates.size() > 0) {
            for (int i = 0; i < mTopCoordinates.size() - 1; i++) {

                mTopCoordinates.set(i, 100 * mTopCoordinates.get(i) / mTopCoordinates.get(mTopCoordinates.size() - 1));
                mBottomCoordinates.set(i, 100 * mBottomCoordinates.get(i) / mBottomCoordinates.get(mBottomCoordinates.size() - 1));
            }
            mTopCoordinates.remove(mTopCoordinates.size() - 1);
            mBottomCoordinates.remove(mBottomCoordinates.size() - 1);
        }
    }

    @Override
    public void setColor(float x, float y, int color) {

        Point p = mRatio.getOrthoCoord(x, y);
        // TODO : Trouver Couleur en fonction des coord et update mColors
    }
}
