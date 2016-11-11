package com.example.gongling.myapplication.ormsql;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.ormsql.db.ProductCategory;

import java.util.List;

/**
 * Created by android_dev on 2016/11/9.
 */
public class ProMagAdapter extends RecyclerView.Adapter<ProMagAdapter.CatHolder> {
    private MyItemClickListener mItemClickListener;
    Context context;
    List<ProductCategory> productCategoryList;
    int selectedpos=0;
    public ProMagAdapter(Activity context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList=productCategoryList;
    }


    @Override
    public ProMagAdapter.CatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CatHolder catHolder=new CatHolder(LayoutInflater.from(
                context).inflate(R.layout.product_catmag_item, parent,
                false),mItemClickListener);
        return catHolder;
    }

    @Override
    public void onBindViewHolder(ProMagAdapter.CatHolder holder, int position) {
        if(position==productCategoryList.size()){
            holder.catName.setText("+");
        }else {
            holder.catName.setText(productCategoryList.get(position).getName());
        }
        holder.catLine.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return productCategoryList.size()+1;
    }

    class CatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyItemClickListener mListener;
        View catLyout;
        TextView catName;
        View catLine;

        public CatHolder(View view,MyItemClickListener mListener) {
            super(view);
            this.mListener=mListener;
            catLyout=view.findViewById(R.id.cat_layout);
            catName = (TextView) view.findViewById(R.id.cat_name);
            catLine = view.findViewById(R.id.cat_line);
            catLyout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public void setSelected(int pos){
        selectedpos=pos;
        notifyDataSetChanged();
    }
}

