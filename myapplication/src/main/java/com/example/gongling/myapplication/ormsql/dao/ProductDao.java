package com.example.gongling.myapplication.ormsql.dao;

import android.content.Context;
import com.example.gongling.myapplication.ormsql.db.DBHelper;
import com.example.gongling.myapplication.ormsql.db.Product;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by android_dev on 2016/11/9.
 */
public class ProductDao {
    private Context context;
    private Dao<Product, Integer> productDaoOpe;
    private DBHelper dbHelper;

    public ProductDao(Context context) {
        this.context = context;
        dbHelper = DBHelper.getHelper(context);
        try {
            productDaoOpe = dbHelper.getDao(Product.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Product> listProductByCat(int productCatId) {
        try {
            return productDaoOpe.queryBuilder().where().eq("catid", productCatId)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(Product product) {
        try {
            productDaoOpe.create(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Product product) {
        try {
            productDaoOpe.delete(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
