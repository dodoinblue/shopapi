package com.supersuperstar.android.shopapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ListView mProductListView;
    private ArrayList<Product> mProductList;
    ProductArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProductListView = (ListView) findViewById(R.id.product_list);

        mProductList = new ArrayList<Product>();
        mAdapter = new ProductArrayAdapter(this, mProductList);

        mProductListView.setAdapter(mAdapter);

        new LongOperation().execute();
    }

    private class LongOperation extends AsyncTask<String, Void, ArrayList<Product>> {

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            ArrayList<Product> result = new ArrayList<Product>();
            for (int i = 1; i <= 30; i++) {
                result.add(new Product("Title " + i, "blablabla " + i, "", i * 13, ""));
            }
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Product> result) {
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
            mProductList = result;
            mAdapter = new ProductArrayAdapter(ProductListActivity.this, mProductList);
            mProductListView.setAdapter(mAdapter);
            Log.i("Charles_TAG", "result updated " + result.toString());
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
