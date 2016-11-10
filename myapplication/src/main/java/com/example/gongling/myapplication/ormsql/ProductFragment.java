package com.example.gongling.myapplication.ormsql;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.gongling.myapplication.R;
import com.example.gongling.myapplication.ormsql.dao.ProductDao;
import com.example.gongling.myapplication.ormsql.db.Product;

import java.util.List;

/**
 * Created by android_dev on 2016/11/10.
 */
public class ProductFragment extends Fragment implements ProAdapter.MyItemClickListener{
    private static final String TAG = "TestFragment";

    int position;
    int curCatId;
    RecyclerView prolistView;
    List<Product> products;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        position = b.getInt("position");
        curCatId = b.getInt("cid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        prolistView = (RecyclerView) view.findViewById(R.id.pro_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        prolistView.setLayoutManager(gridLayoutManager);
        ProductDao productDao = new ProductDao(getActivity());
        products = productDao.listProductByCat(curCatId);
        ProAdapter adpter=new ProAdapter(getActivity(), products);
        adpter.setOnItemClickListener(this);
        prolistView.setAdapter(adpter);

    }

    public void onItemClick(View view, int postion) {

            Toast.makeText(getActivity(), "postion:"+postion, Toast.LENGTH_SHORT).show();

    }
}