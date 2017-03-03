package com.supersuperstar.android.shopapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ListView mProductListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProductListView = (ListView) findViewById(R.id.product_list);

        ArrayList<Product> productList = new ArrayList<Product>();
        ProductArrayAdapter adapter = new ProductArrayAdapter(this, productList);

        mProductListView.setAdapter(adapter);

        for (int i = 1; i <= 30; i++) {
            productList.add(new Product("Title " + i, "blablabla " + i, "", i * 13, ""));
        }

    }
}
