package com.example.gongling.myapplication.ormsql.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by android_dev on 2016/11/9.
 */
@DatabaseTable(tableName = "tb_category")
public class ProductCategory {

    @DatabaseField(columnName = "cid",unique = true)
    private int cat_id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "desc")
    private String desc;

    public ProductCategory() {

    }

    public ProductCategory(int id, String name, String desc) {
        this.cat_id = id;
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return cat_id;
    }

    public void setId(int id) {
        this.cat_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
