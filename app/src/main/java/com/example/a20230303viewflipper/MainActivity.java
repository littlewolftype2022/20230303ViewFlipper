package com.example.a20230303viewflipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ViewFlipper mViewFlipper;
    private float mTouchDownX;
    private float mTouchUpX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        mViewFlipper.setOnTouchListener(this);

        //mViewFlipper.setAutoStart(true);
        //mViewFlipper.setFlipInterval(2000);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //当手指按下时，

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mTouchDownX = event.getX();
            // 取得左右滑动时手指按下的X坐标
            return true;
            //当手指在屏幕上松开
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //取得左右滑动时手指松开的坐标
            mTouchUpX = event.getX();

            if(mTouchUpX - mTouchDownX > 100){
                //设置切换时的动画
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_in));
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_right_out));
                mViewFlipper.showPrevious();
                //从左往右切换到上一个view
            } else if (mTouchDownX - mTouchUpX > 100) {
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
                mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));
                //从右往左切换到下一个view
                mViewFlipper.showNext();
            }
            return true;
        }

        return false;
    }
}