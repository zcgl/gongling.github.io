package com.example.gongling.myapplication.ormsql.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by android_dev on 2016/11/9.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "my_product.db";

    private DBHelper(Context context)
    {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, ProductCategory.class);
            TableUtils.createTable(connectionSource, Product.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try
        {
            TableUtils.dropTable(connectionSource, ProductCategory.class, true);
            TableUtils.dropTable(connectionSource, Product.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static DBHelper dbinstance;

    public static synchronized DBHelper getHelper(Context context){
        context=context.getApplicationContext();
        if(dbinstance==null){
            synchronized (DBHelper.class){
                if(dbinstance==null){
                    dbinstance=new DBHelper(context);
                }
            }
        }
        return dbinstance;
    }


    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public synchronized Dao getDao(Class clazz) throws SQLException{

        Dao dao=null;
        String clazzName=clazz.getName();

        if(daos.containsKey(clazzName)){
            dao=daos.get(clazzName);
        }

        if(dao==null){
            dao=super.getDao(clazz);
            daos.put(clazzName,dao);
        }
        return dao;
    }
}
