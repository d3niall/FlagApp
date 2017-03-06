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
    public void onDraw(Canvas canvas, Integer horizontalOffset, Integer verticalOffset, Integer maxWidth, Integer maxHeight) {

        if (null == mLeftCoordinates || null == mRightCoordinates || mLeftCoordinates.size() != mRightCoordinates.size())
            throw new IllegalStateException();

        float newWidth = 0;
        float newHeight = 0;
        // TODO : move these lines into Ratio Class
        if ((maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW() < (maxHeight - 2 * verticalOffset)) {
            newWidth = maxWidth - 2 * horizontalOffset;
            newHeight = (maxWidth - 2 * horizontalOffset) * mRatio.getNS() / mRatio.getEW();
        } else {
            newWidth = (maxHeight - 2 * verticalOffset) * mRatio.getEW() / mRatio.getNS();
            newHeight = maxHeight - 2 * verticalOffset;
        }
        float newHorizontalOffset = (maxWidth - newWidth) / 2;
        float newVerticalOffset = (maxHeight - newHeight) / 2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        float xLeft = newHorizontalOffset;
        float xRight = newHorizontalOffset + newWidth;

        float yLeft = newVerticalOffset;
        float yRight = newVerticalOffset;

        for (int i = 0; i < mLeftCoordinates.size(); ++i) {

            Path path = new Path();
            path.moveTo(xLeft, yLeft);
            path.lineTo(xLeft, newVerticalOffset + mLeftCoordinates.get(i) * newHeight / 100);
            path.lineTo(xRight, newVerticalOffset + mRightCoordinates.get(i) * newHeight / 100);
            path.lineTo(xRight, yRight);
            path.lineTo(xLeft, yLeft);

            canvas.drawPath(path, paint);

            yLeft = newVerticalOffset + mLeftCoordinates.get(i) * newHeight / 100;
            yRight = newVerticalOffset + mRightCoordinates.get(i) * newHeight / 100;
        }

        Path path = new Path();
        path.moveTo(xLeft, yLeft);
        path.lineTo(xLeft, newVerticalOffset + newHeight);
        path.lineTo(xRight, newVerticalOffset + newHeight);
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

        float r = (mLeftCoordinates.size() + 2.0f) / (mLeftCoordinates.size() + 1.0f);

        for (int i = 0; i < mLeftCoordinates.size() - 1; i++) {

            mLeftCoordinates.set(i, mLeftCoordinates.get(i) * r);
            mRightCoordinates.set(i, mRightCoordinates.get(i) * r);
        }

        if (mLeftCoordinates.size() > 0) {
            mLeftCoordinates.remove(mLeftCoordinates.size() - 1);
            mRightCoordinates.remove(mRightCoordinates.size() - 1);
        }
    }
}
