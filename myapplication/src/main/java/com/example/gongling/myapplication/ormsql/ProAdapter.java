package com.example.gongling.myapplication.ormsql;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.ormsql.db.Product;

import java.util.List;

/**
 * Created by android_dev on 2016/11/9.
 */
public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ProHolder> {

    Context context;
    List<Product> products;
    private MyItemClickListener mItemClickListener;
    public ProAdapter(Activity context,List<Product> products) {
        this.context = context;
        this.products=products;
    }

    @Override
    public ProAdapter.ProHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProHolder proHolder = new ProHolder(LayoutInflater.from(
                context).inflate(R.layout.product_item, parent, false),mItemClickListener);
        return proHolder;
    }

    @Override
    public void onBindViewHolder(ProHolder holder, int position) {

            holder.tv.setText(products.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return products==null?0:products.size();
    }

    class ProHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MyItemClickListener mListener;
        TextView tv;

        public ProHolder(View view,MyItemClickListener mListener) {
            super(view);
            this.mListener=mListener;
            tv = (TextView) view.findViewById(R.id.pro_name);
            tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface MyItemClickListener {
         void onItemClick(View view,int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }
}


