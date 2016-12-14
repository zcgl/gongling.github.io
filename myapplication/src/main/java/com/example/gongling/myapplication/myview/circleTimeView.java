package com.example.gongling.myapplication.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.gongling.myapplication.R;

/**
 * Created by Administrator on 2016-08-01.
 */
public class circleTimeView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mCircleColor = Color.BLACK;

    public circleTimeView(Context context) {
        super(context);

    }

    public circleTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public circleTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circleTimeView);
        mCircleColor = a.getColor(R.styleable.circleTimeView_circle_color, Color.BLUE);
        a.recycle();
        init();
    }

    private void init() {
        mPaint.setColor(mCircleColor);

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;

        int circleCenterX = width / 2;
        int circleCenterY = height / 2;
        canvas.drawCircle(circleCenterX, circleCenterY, radius, mPaint);

        int hourLength = 15;
        //drawLine (float startX, float startY, float stopX, float stopY, Paint paint)
        for (int i = 0; i < 12; i++) {
            float stopX = (float) (circleCenterX + radius * Math.cos(15 * i));
            float stopY = (float) (circleCenterY - radius * Math.sin(15 * i));
            float startX = (float) (circleCenterX + (radius - hourLength) * Math.cos(15 * i));
            float startY = (float) (circleCenterY - (radius - hourLength) * Math.sin(15 * i));
            canvas.drawLine(startX, startY, stopX,stopY,mPaint);
        }
    }
}
