package com.example.gongling.myapplication.ormsql;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.ormsql.dao.ProductCategoryDao;
import com.example.gongling.myapplication.ormsql.dao.ProductDao;
import com.example.gongling.myapplication.ormsql.db.Product;
import com.example.gongling.myapplication.ormsql.db.ProductCategory;
import com.example.gongling.myapplication.view.CommonPagerAdapter;
import com.example.gongling.myapplication.view.VerticalViewPager;
import com.example.gongling.myapplication.wxapi.WXApi;

import java.util.List;

public class ProductMainActivity extends AppCompatActivity implements CatAdapter.MyItemClickListener {

    Button catManagerBtn;
    Button listProBtn;
    View tabLine;
    RecyclerView catListView;
    CatAdapter adpter;
    VerticalViewPager viewPager;
    List<ProductCategory> categories;

    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    int curItem = 0;

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, ProductMainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        initData();
        catManagerBtn = (Button) findViewById(R.id.pro_top_btn1);
        listProBtn = (Button) findViewById(R.id.pro_top_btn2);

        catManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductManageActivity.intentTo(ProductMainActivity.this);
            }
        });

        listProBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductMainActivity.this,"wx start",Toast.LENGTH_SHORT).show();
                WXApi.getIWXAPI(ProductMainActivity.this);
                WXApi.getLoginAPIParams();

            }
        });


        catListView = (RecyclerView) findViewById(R.id.cat_tab_trip);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        catListView.setLayoutManager(linearLayoutManager);
        catListView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }
        });
        categories = productCategoryDao.listProductCat();
        adpter = new CatAdapter(this, categories);
        adpter.setOnItemClickListener(this);
        catListView.setAdapter(adpter);
        tabLine = findViewById(R.id.cat_tab_line);
        viewPager = (VerticalViewPager) findViewById(R.id.pro_vierpager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        catListView.post(new Runnable() {
            @Override
            public void run() {
                catListView.getChildAt(curItem).setBackgroundColor(Color.parseColor("#e3f948"));
            }
        });
        viewPager.setOnPageChangeListener(new VerticalViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("test", position + "/" + positionOffset + "/" + positionOffsetPixels);
                if(positionOffset<=0) return;
                if (position == curItem && position + 1 < categories.size()) {//翻下一页
                    catListView.getChildAt(position + 1).getBackground().setAlpha((int) (255 * positionOffset));
                    catListView.getChildAt(curItem).getBackground().setAlpha((int) (255 * (1-positionOffset)));
                } else {//翻上页
                    catListView.getChildAt(position).getBackground().setAlpha((int) (255 * (1-positionOffset)));
                    catListView.getChildAt(curItem).getBackground().setAlpha((int) (255 * positionOffset));
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("test", position + "/onPageSelected");
                adpter.setSelected(position);
                catListView.getChildAt(position).setBackgroundColor(Color.parseColor("#e3f948"));
                curItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onItemClick(View view, int postion) {
        viewPager.setCurrentItem(postion);
        Toast.makeText(this, "pos" + postion, Toast.LENGTH_SHORT).show();
    }

    public void initData() {
        productCategoryDao = new ProductCategoryDao(this);
        productDao = new ProductDao(this);
        if (productCategoryDao.listProductCat().isEmpty()) {
            for (int i = 0; i < 5; i++) {
                ProductCategory category = new ProductCategory(i, "洗衣粉类" + i, "各种");
                productCategoryDao.addProductCat(category);
                for (int j = 0; j < 10; j++) {
                    Product product = new Product(111l, "abc" + j, 12.5, "");
                    product.setCatid(category.getId());
                    productDao.addProduct(product);
                }
            }
        }

    }


    private class MyAdapter extends CommonPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new ProductFragment();
            Bundle b = new Bundle();
            b.putInt("cid", categories.get(position).getId());
            b.putInt("position", position);
            f.setArguments(b);
            return f;
        }

        @Override
        public int getCount() {
            return categories.size();
        }

        // tab标题
        @Override
        public CharSequence getPageTitle(int position) {
            return position + "";
        }

    }

}

