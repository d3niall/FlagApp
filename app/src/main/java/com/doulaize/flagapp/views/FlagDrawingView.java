package com.doulaize.flagapp.views;

import com.doulaize.flagapp.model.Flag;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by rdeleuze on 2/22/2017
 */
public class FlagDrawingView extends View {

    Flag mFlag;

    Integer mHorizontalOffset = 0;
    Integer mVerticalOffset = 0;

    public FlagDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public void setFlag(Flag flag) {
        mFlag = flag;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mFlag == null)
            throw new IllegalStateException();

        mFlag.onDraw(canvas, mHorizontalOffset, mVerticalOffset, getWidth(), getHeight());
    }

    public void setHorizontalOffset(Integer horizontalOffset) {
        mHorizontalOffset = horizontalOffset;
    }

    public void setVerticalOffset(Integer verticalOffset) {
        mVerticalOffset = verticalOffset;
    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float pointX = event.getX();
//        float pointY = event.getY();
//        // Checks for the event that occurs
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                path.moveTo(pointX, pointY);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                path.lineTo(pointX, pointY);
//                break;
//            default:
//                return false;
//        }
//        // Force a view to draw again
//        postInvalidate();
//        return true;
//    }
}