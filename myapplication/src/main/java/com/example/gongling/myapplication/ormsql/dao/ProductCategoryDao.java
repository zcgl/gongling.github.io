package com.example.gongling.myapplication.ormsql.dao;

import android.content.Context;
import com.example.gongling.myapplication.ormsql.db.DBHelper;
import com.example.gongling.myapplication.ormsql.db.ProductCategory;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by android_dev on 2016/11/9.
 */
public class ProductCategoryDao {
    private Context context;
    private Dao<ProductCategory, Integer> productCatDaoOpe;
    private DBHelper dbHelper;

    public ProductCategoryDao(Context context) {
        this.context = context;
        dbHelper = DBHelper.getHelper(context);
        try {
            productCatDaoOpe = dbHelper.getDao(ProductCategory.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取所有的商品分类
     * @return
     */
    public List<ProductCategory> listProductCat()
    {
        try
        {
            return productCatDaoOpe.queryForAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void addProductCat(ProductCategory productCat) {
        try {
            productCatDaoOpe.create(productCat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductCat(ProductCategory productCat) {
        try {
            productCatDaoOpe.delete(productCat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
