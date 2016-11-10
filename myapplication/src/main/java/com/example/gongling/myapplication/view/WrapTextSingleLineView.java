/*
 * @author Jerry
 * @time 2015-12-29 上午10:14:34
 */
package com.example.gongling.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 无上下边距的TextView
 *
 * @author gongling
 * @time 2016-10-14 上午14:14:34
 */
public class WrapTextSingleLineView extends TextView {

    private float mWidth;

    private float mHeight;

    private FontMetrics mFontMetrics;

    public WrapTextSingleLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    public WrapTextSingleLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapTextSingleLineView(Context context) {
        this(context, null);
    }

    /**
     * 初始化
     *
     * @return void
     * @author Jerry
     * @time 2015-12-29 上午10:16:09
     */
    private void init() {
        TextPaint paint = getPaint();
        mWidth = paint.measureText(getText().toString());
        mFontMetrics = paint.getFontMetrics();

        // 上边距为负数 相加求文字高度
        mHeight = mFontMetrics.descent-mFontMetrics.ascent;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int desWidth = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int desHeight = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:

                mWidth = desWidth;

                break;

            case MeasureSpec.AT_MOST:

                mWidth = mWidth > desWidth ? desWidth : mWidth;

                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:

                mHeight = desHeight;

                break;

            case MeasureSpec.AT_MOST:
                mHeight = mHeight > desHeight ? desHeight : mHeight;

                break;
        }
        setMeasuredDimension((int) Math.ceil(mWidth), (int) Math.ceil(mHeight));
    }

    /* （非 Javadoc）
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
		canvas.drawText(getText().toString(), 0, -mFontMetrics.ascent, getPaint());
    }
}
