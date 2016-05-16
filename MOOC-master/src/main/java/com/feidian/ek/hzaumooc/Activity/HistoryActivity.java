package com.feidian.ek.hzaumooc.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.feidian.ek.hzaumooc.R;
import com.feidian.ek.hzaumooc.View.TimeLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    String [] s={"魅力汉语","精品数学","Java课程设计"};
    String[] data={"2016-04-22","2016-04-19","2016-04-08"};
    @Bind(R.id.history_toolbar)
    Toolbar toolbar;
    @Bind(R.id.timeline_layout)
    TimeLinearLayout timeLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        toolbar.setTitle("浏览历史");
        for (int i=0;i<s.length;i++)
        {
            addItem(i);
        }
    }
    private void addItem(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.item_test, timeLinearLayout, false);
        ((TextView) v.findViewById(R.id.tx_action)).setText(s[position]);
        ((TextView) v.findViewById(R.id.tx_action_time)).setText(data[position]);
        //((TextView) v.findViewById(R.id.tx_action_status)).setText("finish");
        timeLinearLayout.addView(v);
    }

    private void subItem() {
        if (timeLinearLayout.getChildCount() > 0) {
            timeLinearLayout.removeViews(timeLinearLayout.getChildCount() - 1, 1);
        }
    }
}
