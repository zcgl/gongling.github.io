package com.example.gongling.myapplication.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.SurfaceActivity;

public class TextViewBaseLineActivity extends AppCompatActivity {


    public static Intent newIntent(Context context, String videoTitle) {
        Intent intent = new Intent(context, TextViewBaseLineActivity.class);
        intent.putExtra("title", videoTitle);
        return intent;
    }

    public static void intentTo(Context context,String title) {
        context.startActivity(newIntent(context,title));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_base_line);

    }

}
