package com.feidian.ek.hzaumooc.Suggestion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.feidian.ek.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

public class SuggestActivity extends Activity {
    private List<Msg> msgList=new ArrayList<Msg>();
    private ListView list_view;
    private EditText input_text;
    private Button send;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest);

        initData();
        list_view = (ListView) findViewById(R.id.list_view);
        input_text = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        adapter = new MsgAdapter(this,R.layout.msg_item, msgList);
        list_view.setAdapter(adapter);

        //发送消息
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = input_text.getText().toString();
                if(!"".equals(text)){
                    Msg msg = new Msg(text, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，更新ListView
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    list_view.setSelection(msgList.size());
                    input_text.setText("");//情况输入框的内容
                }
            }
        });
    }
    private void initData() {
        // TODO Auto-generated method stub
        Msg msg1 = new Msg("您好，请问有什么可以帮您的么？", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
    }
}
