package com.feidian.ek.hzaumooc.View;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class ListDivider extends RecyclerView.ItemDecoration {

    final int height = 16;
    final Drawable drawable = new ColorDrawable(0xffe0e0e0);

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int chlidAdapterPosition = parent.getChildAdapterPosition(view);
        if(chlidAdapterPosition != 0 &&chlidAdapterPosition != 1) outRect.top = height;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView recyclerView, RecyclerView.State state) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (linearLayoutManager != null && adapter != null && linearLayoutManager.getOrientation() == 1) {
            int childCount = recyclerView.getChildCount();
            int paddingleft = recyclerView.getPaddingLeft();
            int width = recyclerView.getWidth() - recyclerView.getPaddingRight();

            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int top = childAt.getTop();
                drawable.setBounds(paddingleft, top - height, width, top);
                drawable.draw(c);
            }


        }

    }
}
