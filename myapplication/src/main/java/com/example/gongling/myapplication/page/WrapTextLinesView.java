/*
 * @author Jerry
 * @time 2015-12-29 上午10:14:34
 */
package com.example.gongling.myapplication.page;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * 无上下边距的TextView
 *
 * @author Jerry
 * @time 2015-12-29 上午10:14:34
 */
public class WrapTextLinesView extends TextView {

    private float		mWidth;
    private float		mHeight;
    private FontMetrics mFontMetrics;

    public WrapTextLinesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WrapTextLinesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapTextLinesView(Context context) {
        this(context, null);
    }


    /* （非 Javadoc）
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = getPaint();
        mFontMetrics = mPaint.getFontMetrics();
        Log.e("WrapTextLineView",mFontMetrics+"\n"+mFontMetrics.top+"/"+mFontMetrics.ascent+"/"+mFontMetrics.descent+"/"+mFontMetrics.bottom+"/");
        canvas.translate(0, mFontMetrics.top - mFontMetrics.ascent);
        mPaint.setColor(Color.WHITE);

        super.onDraw(canvas);
      /*  *//** 绘制FontMetrics对象的各种线 *//*


        // 计算每一个坐标
        float textWidth = mPaint.measureText(text);
        float baseX = 0;
        float baseY = 700;
        float topY = baseY + fontMetrics.top;
        float ascentY = baseY + fontMetrics.ascent;
        float descentY = baseY + fontMetrics.descent;
        float bottomY = baseY + fontMetrics.bottom;


        // BaseLine描画
        mPaint.setColor(Color.RED);
        canvas.drawLine(baseX, baseY, baseX + textWidth, baseY, mPaint);
        mPaint.setTextSize(20);
        canvas.drawText("base", baseX + textWidth, baseY, mPaint);
        // Base描画
        canvas.drawCircle(baseX, baseY, 5, mPaint);
        // TopLine描画
        mPaint.setColor(Color.LTGRAY);
        canvas.drawLine(baseX, topY, baseX + textWidth, topY, mPaint);
        canvas.drawText("top", baseX + textWidth, topY, mPaint);
        // AscentLine描画
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(baseX, ascentY, baseX + textWidth, ascentY, mPaint);
        canvas.drawText("ascent", baseX + textWidth, ascentY + 10, mPaint);
        // DescentLine描画
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(baseX, descentY, baseX + textWidth, descentY, mPaint);
        canvas.drawText("descent", baseX + textWidth, descentY, mPaint);
        // ButtomLine描画
        mPaint.setColor(Color.MAGENTA);
        canvas.drawLine(baseX, bottomY, baseX + textWidth, bottomY, mPaint);
        canvas.drawText("buttom", baseX + textWidth, bottomY + 10, mPaint);

        // 可以显示全像g这样的字母，但是下边会空出来一部分
		//canvas.drawText(getText().toString(), 0, mHeight, getPaint());

        //super.onDraw(canvas);*/
    }
}
