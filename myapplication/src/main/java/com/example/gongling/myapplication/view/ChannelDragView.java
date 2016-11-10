package com.example.gongling.myapplication.view;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class ChannelDragView extends GridView {
    public static final int TOPGRID = 1;
    public static final int BOTTOMGRID = 2;
    private Rect rect;
    private final Context con;
    private long dragResponseMS = 350;

    public boolean isDragImageExist() {
        return isDragImageExist;
    }

    public void setDragImageExist(boolean dragImageExist) {
        isDragImageExist = dragImageExist;
    }

    private boolean isDragImageExist=false;

    public int getPositon_immovable() {
        return positon_immovable;
    }

    public void setPositon_immovable(int positon_immovable) {
        this.positon_immovable = positon_immovable;
    }

    private int positon_immovable=-1;


    /**
     * 是否可以拖拽，默认不可以
     */
    private boolean isDrag = false;
    private int mDownX;
    private int mDownY;
    private int moveX;
    private int moveY;
    /**
     * 正在拖拽的position
     */
    private int mDragPosition;

    /**
     * 刚开始拖拽的item对应的View
     */
    private View mStartDragItemView = null;

    /**
     * 用于拖拽的镜像，这里直接用一个ImageView
     */
    private ImageView mDragImageView;

    private final WindowManager mWindowManager;
    /**
     * item镜像的布局参数
     */
    private WindowManager.LayoutParams mWindowLayoutParams;

    /**
     * 我们拖拽的item对应的Bitmap
     */
    private Bitmap mDragBitmap;

    /**
     * 按下的点到所在item的上边缘的距离
     */
    private int mPoint2ItemTop;

    /**
     * 按下的点到所在item的左边缘的距离
     */
    private int mPoint2ItemLeft;

    /**
     * DragGridView距离屏幕顶部的偏移量
     */
    private int mOffset2Top;

    /**
     * DragGridView距离屏幕左边的偏移量
     */
    private int mOffset2Left;

    /**
     * 状态栏的高度
     */
    private final int mStatusHeight;

    /**
     * item移动到对方区域回掉
     */
    private OnDragListener onDragListener;

    public ChannelDragView(Context context) {
        this(context, null);
    }

    public ChannelDragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ChannelDragView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mStatusHeight = getStatusHeight(context); // 获取状态栏的高度
        this.con = context;
    }

    private final Handler mHandler = new Handler();

    // 用来处理是否为长按的Runnable
    private final Runnable mLongClickRunnable = new Runnable() {

        @Override
        public void run() {
            isDrag = true; // 设置可以拖拽
            mStartDragItemView.setVisibility(View.INVISIBLE);// 隐藏该item
            // 根据我们按下的点显示item镜像
            createDragImage(mDragBitmap, mDownX, mDownY);
            if (onDragListener != null) {
                onDragListener.onLongClick(mDragPosition);
            }
        }
    };

    /**
     * 设置回调接口
     *
     * @param onChanageListener
     */
    public void setOnDragListener(OnDragListener onChanageListener) {
        this.onDragListener = onChanageListener;
    }

    /**
     * 设置响应拖拽的毫秒数，默认是1000毫秒
     *
     * @param dragResponseMS
     */
    public void setDragResponseMS(long dragResponseMS) {
        this.dragResponseMS = dragResponseMS;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rect = new Rect();
                getHitRect(rect);
                //扩大拖拽应用范围
                if ((int) getTag() == TOPGRID) {
                    rect.top = -300;
                    rect.left = -300;
                    rect.right = rect.right + 300;
                } else {
                    rect.left = -300;
                    rect.right = rect.right + 300;
                    rect.bottom = 10000;
                }
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                // 根据按下的X,Y坐标获取所点击item的position
                mDragPosition = pointToPosition(mDownX, mDownY);

                if (mDragPosition == AdapterView.INVALID_POSITION) {
                    return super.dispatchTouchEvent(ev);
                }

                if (mDragPosition == 0 && (int) getTag() == TOPGRID) {//如果是第一个精选不做任何处理
                    break;
                }
                if(mDragPosition==getChildCount()-1&&(int)getTag()==BOTTOMGRID){
                    break;
                }
                // 使用Handler延迟dragResponseMS执行mLongClickRunnable
                mHandler.postDelayed(mLongClickRunnable, dragResponseMS);

                // 根据position获取该item所对应的View
                mStartDragItemView = getChildAt(mDragPosition - getFirstVisiblePosition());

                // 下面这几个距离大家可以参考我的博客上面的图来理解下
                mPoint2ItemTop = mDownY - mStartDragItemView.getTop();
                mPoint2ItemLeft = mDownX - mStartDragItemView.getLeft();

                mOffset2Top = (int) (ev.getRawY() - mDownY);
                mOffset2Left = (int) (ev.getRawX() - mDownX);

                // 开启mDragItemView绘图缓存
                mStartDragItemView.setDrawingCacheEnabled(true);
                // 获取mDragItemView在缓存中的Bitmap对象
                mDragBitmap = Bitmap.createBitmap(mStartDragItemView.getDrawingCache());
                // 这一步很关键，释放绘图缓存，避免出现重复的镜像
                mStartDragItemView.destroyDrawingCache();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                // 如果我们在按下的item上面移动，只要不超过item的边界我们就不移除mRunnable
                if (!isTouchInItem(mStartDragItemView, moveX, moveY)) {
                    mHandler.removeCallbacks(mLongClickRunnable);
                }
                break;
            case MotionEvent.ACTION_UP:
                mHandler.removeCallbacks(mLongClickRunnable);
                break;
            case MotionEvent.ACTION_CANCEL:
                mHandler.removeCallbacks(mLongClickRunnable);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否点击在GridView的item上面
     *
     * @param
     * @param x
     * @param y
     * @return
     */
    private boolean isTouchInItem(View dragView, int x, int y) {
        if (dragView == null) {
            return false;
        }
        int leftOffset = dragView.getLeft();
        int topOffset = dragView.getTop();
        if (x < leftOffset || x > leftOffset + dragView.getWidth()) {
            return false;
        }
        return !(y < topOffset || y > topOffset + dragView.getHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isDrag && mDragImageView != null) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    moveX = (int) ev.getX();
                    moveY = (int) ev.getY();
                    // 拖动item
                    onDragItem(moveX, moveY);
                    break;
                case MotionEvent.ACTION_UP:
                    if ((int) getTag() == TOPGRID) {
                        onStopDrag(moveX - mPoint2ItemLeft + mOffset2Left, moveY);
                    } else {
                        onStopDrag(moveX - mPoint2ItemLeft + mOffset2Left, moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight);
                    }
                    isDrag = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 创建拖动的镜像
     *
     * @param bitmap
     * @param downX  按下的点相对父控件的X坐标
     * @param downY  按下的点相对父控件的X坐标
     */
    private void createDragImage(Bitmap bitmap, int downX, int downY) {
//        bitmap=Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*1.25f),(int)(bitmap.getHeight()*1.27f),false); 放大效果
        mWindowLayoutParams = new WindowManager.LayoutParams();
        mWindowLayoutParams.format = PixelFormat.TRANSLUCENT; // 图片之外的其他地方透明
        mWindowLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowLayoutParams.x = downX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = downY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowLayoutParams.alpha = 0.55f; // 透明度
        mWindowLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mDragImageView = new ImageView(getContext());
        mDragImageView.setImageBitmap(bitmap);
        mWindowManager.addView(mDragImageView, mWindowLayoutParams);
        isDragImageExist=true;
    }

    /**
     * 从界面上面移动拖动镜像
     */
    public void removeDragImage() {
        if (mDragImageView != null) {
            mWindowManager.removeView(mDragImageView);
            mDragImageView = null;
            isDragImageExist=false;
        }
    }

    /**
     * 拖动item，在里面实现了item镜像的位置更新，item的相互交换以及GridView的自行滚动
     */
    private void onDragItem(int moveX, int moveY) {
        mWindowLayoutParams.x = moveX - mPoint2ItemLeft + mOffset2Left;
        mWindowLayoutParams.y = moveY - mPoint2ItemTop + mOffset2Top - mStatusHeight;
        mWindowManager.updateViewLayout(mDragImageView, mWindowLayoutParams); // 更新镜像的位置
        onSwapItem(moveX, moveY);
    }


    /**
     * 交换item,并且控制item之间的显示与隐藏效果
     */
    private void onSwapItem(int moveX, int moveY) {
        // 获取我们手指移动到的那个item的position
        int tempPosition = pointToPosition(moveX, moveY);
        if (tempPosition == 0 && (int) getTag() == TOPGRID) {
            return;
        }
        if((int)getTag()==BOTTOMGRID&&tempPosition==getChildCount()-1){
            return;
        }
        // 假如tempPosition 改变了并且tempPosition不等于-1,则进行交换
        if(tempPosition != mDragPosition && tempPosition != AdapterView.INVALID_POSITION){
            if(onDragListener!=null){
                onDragListener.onDraging(mDragPosition, tempPosition);
                mDragPosition = tempPosition;
            }
        }
    }


    /**
     * 停止拖拽我们将之前隐藏的item显示出来，并将镜像移除
     */
    private void onStopDrag(int x, int y) {
        View view = getChildAt(mDragPosition - getFirstVisiblePosition());
        if (checkRect(x, y)) {
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
            if (onDragListener != null) {
                onDragListener.onDragInRect();
            }
        } else {
            if (onDragListener != null) {
                onDragListener.onDragtoBeyondRect(mDragPosition - getFirstVisiblePosition());
            }
        }
        removeDragImage();
    }

    /**
     * 停止的时候检查是否在本Rect
     *
     * @return
     */
    private boolean checkRect(int x, int y) {
        if (rect != null) {
            return rect.contains(x, y);
        }
        return true;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * @author xiaanming
     */
    public interface OnDragListener {

        void onDraging(int from, int dest);

        void onDragtoBeyondRect(int postion);

        void onDragInRect();

        void onLongClick(int position);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
