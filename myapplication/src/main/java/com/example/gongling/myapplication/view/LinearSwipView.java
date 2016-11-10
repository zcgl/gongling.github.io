package com.example.gongling.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by android_dev on 2016/11/7.
 */
public class LinearSwipView extends LinearLayout {

    Context context;
    GestureDetector gestureDetector;

    public LinearSwipView(Context context) {
        super(context);
        this.context=context;
        initGensture();
    }
    public LinearSwipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initGensture();
    }

    public LinearSwipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        this.context=context;
        initGensture();
    }

    private void moveRightReLayout(float x){
         this.layout((int)x,0,getMeasuredWidth(), getMeasuredHeight());
    }

    private void initGensture(){
        setBackgroundColor(Color.parseColor("#f3f3f3"));
        gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                Log.e("eee",motionEvent1.getX()+" /"+motionEvent.getX()+" "+v+"/"+v1);
                if(motionEvent1.getX()-motionEvent.getX()>200){
                    Toast s= Toast.makeText(context,",eeee",Toast.LENGTH_LONG);
                    s.show();
                    moveRightReLayout(motionEvent1.getX()-motionEvent.getX()-200);
                    return true;
                }
               return false;
            }

        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
           /* case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                gestureDetector.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
                break;*/
        }
        return true;
    }
}
