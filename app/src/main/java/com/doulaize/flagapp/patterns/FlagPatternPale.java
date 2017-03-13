package com.doulaize.flagapp.patterns;

import com.doulaize.flagapp.common.Constants;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.doulaize.flagapp.common.Constants.COLOR_EDGE;

/**
 * Created by rdeleuze on 2/16/2017
 */
public class FlagPatternPale extends PatternInterface {

    private List<Float> mTopCoordinates;
    private List<Float> mBottomCoordinates;
    private List<Integer> mColors;

    private Paint paint = new Paint();

    public FlagPatternPale() {
        mTopCoordinates = new ArrayList<>();
        mBottomCoordinates = new ArrayList<>();
        mColors = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            mTopCoordinates.add((float) (i * 100. / 3D));
            mBottomCoordinates.add((float) (i * 100. / 3D));
        }

        mColors.add(Color.TRANSPARENT);
        mColors.add(Constants.DARK_YELLOW);
        mColors.add(Color.TRANSPARENT);
    }

    @Override
    public patternTypeEnum getPatternType() {
        return patternTypeEnum.PALE;
    }

    @Override
    public void onDraw(Canvas canvas, boolean drawEdges) {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size() || mTopCoordinates.size() + 1 != mColors.size())
            throw new IllegalStateException();


        float yTop = mRatio.getVerticalOffset();
        float yBottom = mRatio.getVerticalOffset() + mRatio.getViewHeight();

        float xTop = mRatio.getHorizontalOffset();
        float xBottom = mRatio.getHorizontalOffset();

        for (int i = 0; i < mTopCoordinates.size(); ++i) {

            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(mColors.get(i));
            Path path = new Path();
            path.moveTo(xTop, yTop);
            path.lineTo(mRatio.getHorizontalOffset() + mTopCoordinates.get(i) * mRatio.getViewWidth() / 100, yTop);
            path.lineTo(mRatio.getHorizontalOffset() + mBottomCoordinates.get(i) * mRatio.getViewWidth() / 100, yBottom);
            path.lineTo(xBottom, yBottom);
            path.lineTo(xTop, yTop);
            canvas.drawPath(path, paint);

            // TODO : When drawing edges, choose color according to the two side colors, and only draw he division (no need to draw the whole rect
            if (drawEdges) {
                Path pathEdge = new Path();
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(COLOR_EDGE);
                pathEdge.moveTo(xTop, yTop);
                pathEdge.lineTo(mRatio.getHorizontalOffset() + mTopCoordinates.get(i) * mRatio.getViewWidth() / 100, yTop);
                pathEdge.lineTo(mRatio.getHorizontalOffset() + mBottomCoordinates.get(i) * mRatio.getViewWidth() / 100, yBottom);
                pathEdge.lineTo(xBottom, yBottom);
                pathEdge.lineTo(xTop, yTop);
                canvas.drawPath(pathEdge, paint);
            }

            xTop = mRatio.getHorizontalOffset() + mTopCoordinates.get(i) * mRatio.getViewWidth() / 100;
            xBottom = mRatio.getHorizontalOffset() + mBottomCoordinates.get(i) * mRatio.getViewWidth() / 100;
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mColors.get(mColors.size() - 1));
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
    public boolean isButtonReverseHorizontalAllowed() {
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
        mColors.add(Color.TRANSPARENT);
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
            mColors.remove(mColors.size() - 1);
        }
    }

    @Override
    public void buttonReverseHorizontalPressed() {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        Collections.reverse(mTopCoordinates);
        Collections.reverse(mBottomCoordinates);
        Collections.reverse(mColors);

        for (int i = 0; i < mTopCoordinates.size(); i++) {
            mTopCoordinates.set(i, 100 - mTopCoordinates.get(i));
            mBottomCoordinates.set(i, 100 - mBottomCoordinates.get(i));
        }
    }

    @Override
    public void setColor(float x, float y, int color) {

        if (null == mTopCoordinates || null == mBottomCoordinates || mTopCoordinates.size() != mBottomCoordinates.size())
            throw new IllegalStateException();

        boolean found = false;
        for (int i = 0; i < mTopCoordinates.size(); i++) {

            if (((mBottomCoordinates.get(i) - mTopCoordinates.get(i)) * y) / 100 + mTopCoordinates.get(i) > x) {
                found = true;
                mColors.set(i, color);
                break;
            }
        }

        if (!found) {
            mColors.set(mColors.size() - 1, color);
        }
    }
}
