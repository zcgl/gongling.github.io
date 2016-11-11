package com.example.gongling.myapplication.ormsql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.ormsql.dao.ProductCategoryDao;
import com.example.gongling.myapplication.ormsql.dao.ProductDao;
import com.example.gongling.myapplication.ormsql.db.Product;
import com.example.gongling.myapplication.ormsql.db.ProductCategory;

import java.util.List;

public class ProductManageActivity extends AppCompatActivity implements ProMagAdapter.MyItemClickListener {

    RecyclerView catGridList;
    ProductCategoryDao productCategoryDao;
    ProductDao productDao;
    List<ProductCategory> categories;
    ProMagAdapter adpter;
    boolean isShowFragment = false;

    public static void intentTo(Context context) {
        context.startActivity(new Intent(context, ProductManageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage);
        catGridList = (RecyclerView) findViewById(R.id.cat_list);
        catGridList.setLayoutManager(new GridLayoutManager(this, 3));
        productCategoryDao = new ProductCategoryDao(this);
        productDao = new ProductDao(this);
        categories = productCategoryDao.listProductCat();
        adpter = new ProMagAdapter(this, categories);
        adpter.setOnItemClickListener(this);
        catGridList.setAdapter(adpter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShowFragment = false;
                getFragmentManager().popBackStack();
            }
        });
    }


    @Override
    public void onItemClick(View view, int postion) {
        if (postion == categories.size()) {
            Toast.makeText(this, "more", Toast.LENGTH_SHORT).show();
            ProductCategory category = new ProductCategory(postion, "洗衣粉类" + postion, "各种");
            productCategoryDao.addProductCat(category);
            for (int j = 0; j < 10; j++) {
                Product product = new Product(111l, "abc" + j, 12.5, "");
                product.setCatid(category.getId());
                productDao.addProduct(product);
            }
            categories = productCategoryDao.listProductCat();
            adpter = new ProMagAdapter(this, categories);
            catGridList.setAdapter(adpter);
        } else {
            Toast.makeText(this, postion + "", Toast.LENGTH_SHORT).show();
            ProductManageFragment f = new ProductManageFragment();
            Bundle b = new Bundle();
            b.putInt("cid", categories.get(postion).getId());
            b.putInt("position", postion);
            f.setArguments(b);
            getFragmentManager().beginTransaction()
                    .replace(R.id.manager_container, f)
                    .addToBackStack(null)
                    .commit();
            isShowFragment = true;
        }
    }

    @Override
    public void onBackPressed() {
        if (isShowFragment) {
            isShowFragment = false;
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
