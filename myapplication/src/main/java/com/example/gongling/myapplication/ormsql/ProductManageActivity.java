package com.example.gongling.myapplication.ormsql;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.gongling.myapplication.*;

public class ProductManageActivity extends AppCompatActivity {

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, ProductManageActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage);
        setTitle("分类管理");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_product_manager, menu);
        super.onCreateOptionsMenu(menu);
        // menu.add(Menu.NONE, Menu.FIRST+1, 0, "设置");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
