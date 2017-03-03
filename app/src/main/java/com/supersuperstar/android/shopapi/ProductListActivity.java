package com.supersuperstar.android.shopapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Product> mProducts = new ArrayList<Product>();
    private HashMap<String, Double> mConvertRates = new HashMap<String, Double>();
    private Spinner mCurrencyPicker;
    private ArrayAdapter<String> mCurrencyAdapter;

    protected void updateDataSet(ArrayList<Product> products) {
        mProducts.addAll(products);
        mProductAdapter = new ProductAdapter(mProducts);
        mRecyclerView.setAdapter(mProductAdapter);
    }

    protected void updateConvertRate(HashMap<String, Double> rates) {
        mConvertRates = rates;
        setupCurrencySelector();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mCurrencyPicker = (Spinner) findViewById(R.id.currency_selector);
        setupCurrencySelector();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        HttpDataSource dataSource = new HttpDataSource(this);
        dataSource.getProductList();
        dataSource.getCurrencyAndQuotes();
    }

    private void setupCurrencySelector() {
        ArrayList<String> list = new ArrayList<String>();
        // Add default currency to the list
        list.add("USD");
        for (String key : mConvertRates.keySet()) {
            String currency = key.replace("USD", "");
            if (currency.length() != 0) {
                list.add(key.replace("USD", ""));
            }
        }

        mCurrencyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        mCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mCurrencyPicker.setAdapter(mCurrencyAdapter);
        mCurrencyPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String currencyName = mCurrencyAdapter.getItem(position);
                double rate;
                if (currencyName == "USD") {
                    rate = 1d;
                } else {
                    rate = mConvertRates.get("USD" + currencyName);
                }

                // Just in case currency adapter got setup first
                if (mProductAdapter != null) {
                    mProductAdapter.setRate(rate);
                    mProductAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
