package com.example.gongling.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.example.gongling.myapplication.ormsql.ProductMainActivity;
import com.example.gongling.myapplication.page.TextViewBaseLineActivity;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(ScrollingActivity.this, mywebAndProActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_textview) {
            TextViewBaseLineActivity.intentTo(this,"2");
            return true;
        }
        if (id == R.id.action_surfaceview) {
            SurfaceActivity.intentTo(this,"1");
            return true;
        }

        if (id == R.id.action_recycle) {
            RecyclerViewActivity.intentTo(this);
            return true;
        }
        if (id == R.id.action_product) {
            ProductMainActivity.intentTo(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
