package com.doulaize.flagapp.views;

import com.doulaize.flagapp.common.Constants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rdeleuze on 3/8/2017
 */
public class RatioDialogFlagDrawingView extends View {


    Integer mFlagWidth = 1;
    Integer mFlagHeight = 1;

    Paint mPaint;

    public RatioDialogFlagDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null == mPaint)
            mPaint = new Paint();

        mPaint.setStyle(Paint.Style.STROKE);

        if (mFlagWidth * 1.0 / mFlagHeight > Constants.RATIO_MAX_VALUE || mFlagWidth * 1.0 / mFlagHeight < Constants.RATIO_MIN_VALUE)
            mPaint.setColor(android.graphics.Color.RED);
        else
            mPaint.setColor(android.graphics.Color.BLACK);


        Integer offset = 10;

        float newWidth = 0;
        float newHeight = 0;

        if ((getWidth() - 2 * offset) * mFlagHeight / mFlagWidth < (getHeight() - 2 * offset)) {
            newWidth = getWidth() - 2 * offset;
            newHeight = (getWidth() - 2 * offset) * mFlagHeight / mFlagWidth;
        } else {
            newWidth = (getHeight() - 2 * offset) * mFlagWidth / mFlagHeight;
            newHeight = getHeight() - 2 * offset;
        }

        float newHorizontalOffset = (getWidth() - newWidth) / 2;
        float newVerticalOffset = (getHeight() - newHeight) / 2;

        float yTop = newVerticalOffset;
        float yBottom = newVerticalOffset + newHeight;

        float xLeft = newHorizontalOffset;
        float xRight = newHorizontalOffset + newWidth;

        canvas.drawRect(xLeft, yTop, xRight, yBottom, mPaint);

    }

    public void setFlagWidth(Integer flagWidth) {
        mFlagWidth = flagWidth;
    }

    public void setFlagHeight(Integer flagHeight) {
        mFlagHeight = flagHeight;
    }

}