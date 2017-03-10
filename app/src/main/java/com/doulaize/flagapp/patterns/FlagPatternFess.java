package com.doulaize.flagapp.patterns;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdeleuze on 3/1/2017
 */
public class FlagPatternFess extends PatternInterface {

    private List<Float> mLeftCoordinates;
    private List<Float> mRightCoordinates;
    private Paint paint = new Paint();

    public FlagPatternFess() {
        mLeftCoordinates = new ArrayList<>();
        mRightCoordinates = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            mLeftCoordinates.add((float) (i * 100. / 3D));
            mRightCoordinates.add((float) (i * 100. / 3D));
        }
    }

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.FESS;
    }

    @Override
    public void onDraw(Canvas canvas, boolean drawEdges) {

        if (null == mLeftCoordinates || null == mRightCoordinates || mLeftCoordinates.size() != mRightCoordinates.size())
            throw new IllegalStateException();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        float xLeft = mRatio.getHorizontalOffset();
        float xRight = mRatio.getHorizontalOffset() + mRatio.getViewWidth();

        float yLeft = mRatio.getVerticalOffset();
        float yRight = mRatio.getVerticalOffset();

        for (int i = 0; i < mLeftCoordinates.size(); ++i) {

            Path path = new Path();
            path.moveTo(xLeft, yLeft);
            path.lineTo(xLeft, mRatio.getVerticalOffset() + mLeftCoordinates.get(i) * mRatio.getViewHeight() / 100);
            path.lineTo(xRight, mRatio.getVerticalOffset() + mRightCoordinates.get(i) * mRatio.getViewHeight() / 100);
            path.lineTo(xRight, yRight);
            path.lineTo(xLeft, yLeft);

            canvas.drawPath(path, paint);

            yLeft = mRatio.getVerticalOffset() + mLeftCoordinates.get(i) * mRatio.getViewHeight() / 100;
            yRight = mRatio.getVerticalOffset() + mRightCoordinates.get(i) * mRatio.getViewHeight() / 100;
        }

        Path path = new Path();
        path.moveTo(xLeft, yLeft);
        path.lineTo(xLeft, mRatio.getVerticalOffset() + mRatio.getViewHeight());
        path.lineTo(xRight, mRatio.getVerticalOffset() + mRatio.getViewHeight());
        path.lineTo(xRight, yRight);
        path.lineTo(xLeft, yLeft);

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

        if (null == mLeftCoordinates || null == mRightCoordinates || mLeftCoordinates.size() != mRightCoordinates.size())
            throw new IllegalStateException();

        float r = (mLeftCoordinates.size() + 1.0f) * 1.0f / (mLeftCoordinates.size() + 2.0f);

        for (int i = 0; i < mLeftCoordinates.size(); i++) {

            mLeftCoordinates.set(i, mLeftCoordinates.get(i) * r);
            mRightCoordinates.set(i, mRightCoordinates.get(i) * r);
        }

        mLeftCoordinates.add(100 * r);
        mRightCoordinates.add(100 * r);
    }

    @Override
    public void buttonRemovePressed() {

        if (null == mLeftCoordinates || null == mRightCoordinates || mLeftCoordinates.size() != mRightCoordinates.size())
            throw new IllegalStateException();

        if (mLeftCoordinates.size() > 0) {
            for (int i = 0; i < mLeftCoordinates.size() - 1; i++) {

                mLeftCoordinates.set(i, 100 * mLeftCoordinates.get(i) / mLeftCoordinates.get(mLeftCoordinates.size() - 1));
                mRightCoordinates.set(i, 100 * mRightCoordinates.get(i) / mRightCoordinates.get(mRightCoordinates.size() - 1));
            }

            mLeftCoordinates.remove(mLeftCoordinates.size() - 1);
            mRightCoordinates.remove(mRightCoordinates.size() - 1);
        }
    }
}
