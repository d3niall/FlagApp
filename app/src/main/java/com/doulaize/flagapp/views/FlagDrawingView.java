package com.doulaize.flagapp.views;

import com.doulaize.flagapp.listener.NewRatioListener;
import com.doulaize.flagapp.model.Flag;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by rdeleuze on 2/22/2017
 */
public class FlagDrawingView extends View {

    Flag mFlag;

    NewRatioListener newRatioListener;

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

        mFlag.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        final int action = event.getAction();

        if (action == MotionEvent.ACTION_UP)
            mFlag.onClickDrawingArea(pointX, pointY);

        // Checks for the event that occurs
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
        // Force a view to draw again
        postInvalidate();
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (null != newRatioListener)
            newRatioListener.onNewFlagViewSize(getWidth(), getHeight()); //height is ready
    }

    public void setNewRatioListener(NewRatioListener newRatioListener) {
        this.newRatioListener = newRatioListener;
    }
}