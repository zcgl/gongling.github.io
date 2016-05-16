package com.feidian.ek.hzaumooc.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.feidian.ek.hzaumooc.R;

/**
 * Created by EK on 2016/3/24.
 */
public class DefaultSliderView extends BaseSliderView {

    public DefaultSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.render_type_default, null);
        bindEventAndShow(inflate, (ImageView) inflate.findViewById(R.id.daimajia_slider_image));
        return inflate;
    }
}
