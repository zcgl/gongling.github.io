/*
 * @author Jerry
 * @time 2015-12-29 上午10:14:34
 */
package com.example.gongling.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 无上下边距的TextView
 *
 * @author Jerry
 * @time 2015-12-29 上午10:14:34
 */
public class WrapTextMutiLinesView extends TextView {

    private float mWidth;

    private float mHeight;

    private FontMetrics mFontMetrics;
    private final Paint paint = new Paint();
    private float maxWidth;
    private int maxLine=3;
    private float dotWidth = 0;
    public WrapTextMutiLinesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    public WrapTextMutiLinesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapTextMutiLinesView(Context context) {
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

        Log.e("metrics",mFontMetrics.toString()+"/"+getTextSize()+"\n"+mFontMetrics.top+"/"+mFontMetrics.ascent+"/"+mFontMetrics.descent+"/"+mFontMetrics.bottom+"/"+mFontMetrics.leading);
        // 上边距为负数 相加求文字高度
        mHeight = -(mFontMetrics.ascent - mFontMetrics.descent);
        dotWidth = paint.measureText(".");

    }

    /* （非 Javadoc）
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /* （非 Javadoc）
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        maxWidth = getMeasuredWidth();
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        // 不能显示全像g这样的字母
        //canvas.drawText(getText().toString(), 0, mHeight - 2, paint);

        // 可以显示全像g这样的字母，但是下边会空出来一部分
        char[] textCharArray = getText().toString().toCharArray();
        float drawedWidth = 0;
        int lineCount=0;
        float lineheight=mFontMetrics.descent-mFontMetrics.ascent;
        for (int i = 0; i < textCharArray.length; i++) {
            float  charWidth = paint.measureText(textCharArray, i, 1);
            if (textCharArray[i] == '\n') {
                lineCount++;
                drawedWidth = 0;
                continue;
            }
            if (lineCount == 0) {
                canvas.drawText(textCharArray, i, 1, drawedWidth, -mFontMetrics.ascent, getPaint());
            }else{
                canvas.drawText(textCharArray, i, 1, drawedWidth, lineheight*lineCount-mFontMetrics.ascent, getPaint());
            }
            drawedWidth += charWidth;
        }

       /* lineCount=0;
        drawedWidth=0;
        paint.setColor(Color.parseColor("#33ff00"));
        for (int i = 0; i < textCharArray.length; i++) {
            float charWidth = paint.measureText(textCharArray, i, 1);
            if (textCharArray[i] == '\n') {
                lineCount++;
                drawedWidth = 0;
                continue;
            }
            if (maxWidth - drawedWidth < charWidth) {
                lineCount++;
                drawedWidth = 0;
            }

           *//* if (endIndex != 0 && i >= endIndex) {
//                canvas.drawText(textCharArray, i, 1, drawedWidth, (lineCount + 1) * textSize * LineSpacing, paint);
                canvas.drawText("...".toCharArray(), 0, 3, drawedWidth, (lineCount + 1) * textSize * LineSpacing, paint);
                canvas.save();
                break draw;
            }*//*
            // 第一行不要上间距，第二、三...行要上间距
            if (lineCount == 0) {
                canvas.drawText(textCharArray, i, 1, drawedWidth, (lineCount + 1) * getTextSize() * 1, paint);
            } else {
                canvas.drawText(textCharArray, i, 1, drawedWidth, (lineCount + 1) * getTextSize() * 1.1f, paint);
            }
            drawedWidth += charWidth;
        }*/
    }

    private int measureTotalWidth(char[] arr) {
        int lineCount = 0;
        float drawedWidth = 0;
        float charWidth;

        for (int i = 0; i < arr.length; i++) {
            charWidth = paint.measureText(arr, i, 1);
            if (arr[i] == '\n') {
                lineCount++;
                drawedWidth = 0;
                continue;
            }

            if (maxWidth - drawedWidth < charWidth) {
                lineCount++;
                drawedWidth = 0;
                if (lineCount >= maxLine) {
                    // if (i != arr.length - 1) {
                    float restWidth = (maxWidth - drawedWidth);
                    float needWidth = dotWidth * 3;
                    float singleWidth = paint.measureText(arr, i - 1, 1);
                    restWidth += singleWidth;
                    if (restWidth > needWidth) {
                        return i - 1;
                    } else {
                        float nextTWidth = paint.measureText(arr, i - 2, 1);
                        restWidth += nextTWidth;
                        if (restWidth > needWidth) {
                            return i - 2;
                        } else {
                            return i - 3;
                        }
                    }
                    // }
                }
            }
            drawedWidth += charWidth;
        }
        return 0;
    }
}
