package com.example.gongling.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class SurfaceActivity extends AppCompatActivity {

    public static Intent newIntent(Context context, String videoTitle) {
        Intent intent = new Intent(context, SurfaceActivity.class);
        intent.putExtra("title", videoTitle);
        return intent;
    }

    public static void intentTo(Context context,String title) {
        context.startActivity(newIntent(context,title));
    }

    Button btnSimpleDraw, btnTimerDraw;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    private Timer mTimer;
    private MyTimerTask mTimerTask;
    int Y_axis[],//保存正弦波的Y轴上的点
            centerY,//中心线
            oldX,oldY,//上一个XY点
            currentX;//当前绘制到的X轴上的点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSimpleDraw = (Button) this.findViewById(R.id.Button01);
        btnTimerDraw = (Button) this.findViewById(R.id.Button02);
        btnSimpleDraw.setOnClickListener(new ClickEvent());
        btnTimerDraw.setOnClickListener(new ClickEvent());
        surfaceView = (SurfaceView) this.findViewById(R.id.SurfaceView01);
        surfaceHolder = surfaceView.getHolder();

        // 初始化y轴数据
        centerY = (getWindowManager().getDefaultDisplay().getHeight() - surfaceView
                .getTop()) / 2;
        Y_axis = new int[getWindowManager().getDefaultDisplay().getWidth()];
        for (int i = 1; i < Y_axis.length; i++) {// 计算正弦波
            Y_axis[i - 1] = centerY
                    - (int) (100 * Math.sin(i * 2 * Math.PI / 180));
        }

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
                Log.e("surfaceChanged:",
                        String.valueOf(width) + "," + String.valueOf(oldX + height));
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e("surfaceCreated:", "surfaceCreated");
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.e("surfaceDestroyed:","surfaceDestroyed");
                cleanSendTimerTask();
            }
        });
    }

    class ClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            cleanSendTimerTask();

            if (v == btnSimpleDraw) {

                SimpleDraw(Y_axis.length-1);//直接绘制正弦波

            } else if (v == btnTimerDraw) {

                //动态绘制正弦波的定时器
                if (mTimer == null) {
                    mTimer = new Timer();
                }
                if (mTimerTask == null) {
                    mTimerTask = new MyTimerTask();
                }
                ClearDraw();
                currentX = 0;
                oldY = centerY;
                oldX = 0;
                mTimer.schedule(mTimerTask, 0, 5);//动态绘制正弦波
            }

        }

    }
    /**
     * 清空监听的每条消息的发送时间处理消息超时
     * 这里关闭掉Timer 与 TimerTask
     */
    private void cleanSendTimerTask() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
    }
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {

            SimpleCurDraw(currentX);
            currentX++;//往前进
            if (currentX == Y_axis.length - 1) {//如果到了终点，则清屏重来
                ClearDraw();
                currentX = 0;
                oldY = centerY;
            }
        }

    }

    /**
     * 绘制指定区域
     */
    void SimpleDraw(int length) {
        ClearDraw();
        oldX = 0;
        oldY = centerY;
        Canvas canvas = surfaceHolder.lockCanvas(new Rect(oldX, 0, oldX + length,
                getWindowManager().getDefaultDisplay().getHeight()));// 关键:获取画布
        Log.i("Canvas:",
                String.valueOf(oldX) + "," + String.valueOf(oldX + length));

        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);// 画笔为绿色
        mPaint.setStrokeWidth(2);// 设置画笔粗细

        int y;
        for (int i = oldX + 1; i < length; i++) {// 绘画正弦波
            y = Y_axis[i - 1];
            canvas.drawLine(oldX, oldY, i, y, mPaint);
            oldX = i;
            oldY = y;
        }
        surfaceHolder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
    }

    /**
     * 绘制指定区域
     */
    void SimpleCurDraw(int cur) {
        if (cur == 0) {
            oldX = 0;
            oldY = centerY;
        }

        Canvas canvas = surfaceHolder.lockCanvas(new Rect(oldX, 0, oldX + cur,
                getWindowManager().getDefaultDisplay().getHeight()));// 关键:获取画布
        Log.i("Canvas:",
                String.valueOf(oldX) + "," + String.valueOf(oldX + cur));

        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);// 画笔为绿色
        mPaint.setStrokeWidth(4);// 设置画笔粗细

        int y;
        // 绘画正弦波
        y = Y_axis[cur];
        canvas.drawLine(oldX, oldY, cur, y, mPaint);
        oldX = cur;
        oldY = y;
        surfaceHolder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
    }

    void ClearDraw() {
        Canvas canvas = surfaceHolder.lockCanvas(null);
        canvas.drawColor(Color.BLACK);// 清除画布
        surfaceHolder.unlockCanvasAndPost(canvas);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause:","onPause");
        cleanSendTimerTask();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop:","onStop");
    }
}
