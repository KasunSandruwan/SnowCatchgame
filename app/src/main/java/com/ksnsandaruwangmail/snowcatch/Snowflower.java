package com.ksnsandaruwangmail.snowcatch;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.ksnsandaruwangmail.snowcatch.utils.PixelHelper;

/**
 * Created by welcome on 1/4/2017.
 */

public class Snowflower extends ImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator mAnimator;
    private SnowListner mListener;
    private boolean mPopped;

    public Snowflower(Context context) {
        super(context);
    }

    public Snowflower(Context context ,int rawHeight) {
        super(context);

        mListener = (SnowListner) context;
        this.setImageResource(R.drawable.snow);

        int rawWidth =rawHeight/2;

        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);

    }

    public void rainSnow(int screenHeight, int duration) {


        mAnimator = new ValueAnimator();
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(0f,screenHeight);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        if(!mPopped){
            mListener.popSnow(this ,false);
        }

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        setY((Float) animation.getAnimatedValue());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!mPopped && event.getAction() ==MotionEvent.ACTION_DOWN){
            mListener.popSnow(this, true);
            mPopped =true;
            mAnimator.cancel();
        }
        return super.onTouchEvent(event);
    }

    public void setPopped(boolean popped) {

        mPopped =popped;
        if (popped){
           mAnimator.cancel();
        }

    }

    public interface SnowListner{
        void popSnow(Snowflower snowflower,boolean userTuch);
    }
}
