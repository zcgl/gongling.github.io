package com.feidian.ek.hzaumooc.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.feidian.ek.hzaumooc.Bean.GoodClass;
import com.feidian.ek.hzaumooc.Bean.MainViewTitle;
import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.DefaultSliderView;
import com.feidian.ek.hzaumooc.View.NoScrollGridView;
import com.feidian.ek.hzaumooc.View.NoScrollListView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int SLIDER = 5;
    public static final int GIRD = 6;
    public static final int LIST = 7;
    public static final int SLIDERCLASS=0;//图片轮播
    public static final int RecommendCLASS=1;//推荐课程
    public static final int ClaaRANK=4;//排名课程
    public static final int GoodClass=2;//精品课程
    public static final int YunClass=3;

    Activity activity;
    LayoutInflater layoutInflater;

    class GirdViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_girdview_title) TextView title;
        @Bind(R.id.item_girdview)
        NoScrollGridView gridView;
        @Bind(R.id.item_girdview_more)
        TextView more;
        @Bind(R.id.item_girdview_image)
        ImageView image;
        private MoreOnClickListener moreOnClickListener;
        private ItemOnClickListener itemOnClickListener;

        public GirdViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public void setMoreOnClickListener(int position)//position表明其kind 传入的为在MainViewTitle定义的值
        {
            if(moreOnClickListener==null)
            {
                moreOnClickListener=new MoreOnClickListener(position,activity);
            }
            else
            {
                moreOnClickListener.setPosition(position);
            }
            if(itemOnClickListener == null)
            {
                itemOnClickListener=new ItemOnClickListener(GIRD,activity,position);
            }
            else
            {
                itemOnClickListener.setPosition(position);
            }
            more.setOnClickListener(moreOnClickListener);
            gridView.setOnItemClickListener(itemOnClickListener);
        }
    }

    public MainAdapter(Activity activity) {
        this.activity = activity;
        layoutInflater = LayoutInflater.from(activity);
    }

    /**
     * 滚动条
     */
    class ViewHolderSlider extends RecyclerView.ViewHolder {

        @Bind(R.id.item_slider_banner)
        public SliderLayout sliderLayout;
        @Bind(R.id.item_slider_indicator)
        public PagerIndicator indicator;

        public ViewHolderSlider(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            Context context = itemView.getContext();

            ViewGroup.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.sliderLayout.getLayoutParams();
            if (layoutParams == null || layoutParams.width <= 0) {
                //可用高度小于100dp
                Point point = new Point();
                ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
                int width = point.x;
                layoutParams = new FrameLayout.LayoutParams(width, (int) (((float) width) / 2.333333f));

            } else {
                layoutParams.height = (int) (((float) layoutParams.width) / 2.333333f);
            }
            sliderLayout.setLayoutParams(layoutParams);
            sliderLayout.setCustomIndicator(indicator);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case SLIDER:
                return new ViewHolderSlider(layoutInflater.inflate(R.layout.item_slider_banner, parent, false));
            case GIRD:
                return new GirdViewHolder(layoutInflater.inflate(R.layout.item_girdview, parent, false));
            case LIST:
                return new ListViewHolder(layoutInflater.inflate(R.layout.item_listview, parent, false));
            default:
                return new GirdViewHolder(layoutInflater.inflate(R.layout.item_girdview, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderSlider) {
            ViewHolderSliderOperate(holder);
        } else if (holder instanceof GirdViewHolder) {
            GirdViewHolderOperate(holder,position);
        } else  if (holder instanceof ListViewHolder) {
            ListHolderOperate(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == SLIDERCLASS) return SLIDER;
        if (position == RecommendCLASS) return GIRD;
        if (position == ClaaRANK) return LIST;
        if (position == GoodClass) return GIRD;
        if (position == YunClass)  return GIRD;
        return super.getItemViewType(position);
    }
    private void  ViewHolderSliderOperate(RecyclerView.ViewHolder holder)
    {
       /* ((ViewHolderSlider) holder).sliderLayout.removeAllSliders();
        ((ViewHolderSlider) holder).sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        ((ViewHolderSlider) holder).sliderLayout.startAutoCycle();
        ((ViewHolderSlider) holder).sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        BaseSliderView sliderView = new DefaultSliderView(activity);
        sliderView.image("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);
        for (int i = 0; i < 3; i++) {
            ((ViewHolderSlider) holder).sliderLayout.addSlider(sliderView);
        }*/
        ((ViewHolderSlider) holder).sliderLayout.removeAllSliders();
        ((ViewHolderSlider) holder).sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        ((ViewHolderSlider) holder).sliderLayout.startAutoCycle();
        ((ViewHolderSlider) holder).sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        BaseSliderView[] sliderView=new com.daimajia.slider.library.SliderTypes.DefaultSliderView[5];
        sliderView[0] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
        sliderView[0].image("http://course.hzau.edu.cn/G2S/ShowSystem/css/ad1.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderView[1] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
        sliderView[1].image("http://course.hzau.edu.cn/G2S/ShowSystem/css/ad2.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderView[2] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
        sliderView[2].image("http://course.hzau.edu.cn/G2S/ShowSystem/css/ad3.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderView[3] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
        sliderView[3].image("http://course.hzau.edu.cn/G2S/ShowSystem/css/ad4.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderView[4] = new com.daimajia.slider.library.SliderTypes.DefaultSliderView(activity);
        sliderView[4].image(R.mipmap.biaoti).setScaleType(BaseSliderView.ScaleType.CenterCrop);
        for (int i = 0; i < 5; i++) {
            ((ViewHolderSlider) holder).sliderLayout.addSlider(sliderView[i]);
        }
    }
    private void GirdViewHolderOperate(RecyclerView.ViewHolder holder,int position)
    {
        if(position == RecommendCLASS)
        {
            ((GirdViewHolder) holder).title.setText("官方推荐");
            ((GirdViewHolder) holder).gridView.setAdapter(new GridAdapter(activity, MainViewTitle.RECOMMEND));
            ((GirdViewHolder) holder).image.setBackground(activity.getResources().getDrawable(MainViewTitle.IMAGE[0]));
            ((GirdViewHolder) holder).setMoreOnClickListener(MainViewTitle.RECOMMEND);
        }
        else if(position == GoodClass)
        {
            ((GirdViewHolder) holder).title.setText("精品课程");
            ((GirdViewHolder) holder).gridView.setAdapter(new GridAdapter(activity, MainViewTitle.GOODCLASS));
            ((GirdViewHolder) holder).image.setBackground(activity.getResources().getDrawable(MainViewTitle.IMAGE[1]));
            ((GirdViewHolder) holder).setMoreOnClickListener(MainViewTitle.GOODCLASS);
        }
        else if(position == YunClass)
        {
            ((GirdViewHolder) holder).title.setText("云课程");
            ((GirdViewHolder) holder).gridView.setAdapter(new GridAdapter(activity, MainViewTitle.YUNCLASS));
            ((GirdViewHolder) holder).image.setBackground(activity.getResources().getDrawable(MainViewTitle.IMAGE[2]));
            ((GirdViewHolder) holder).setMoreOnClickListener(MainViewTitle.YUNCLASS);
        }


    }
    private void ListHolderOperate(RecyclerView.ViewHolder holder,int position)
    {
        if(position==ClaaRANK)
        {
            ((ListViewHolder) holder).title.setText("热门点击");
            ((ListViewHolder) holder).listView.setAdapter(new ListAdapter(activity, 4));
            ((ListViewHolder) holder).setItemOnClickListener(MainViewTitle.RANK,activity);
        }
    }
}
