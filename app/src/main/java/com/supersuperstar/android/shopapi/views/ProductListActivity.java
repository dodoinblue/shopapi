package com.supersuperstar.android.shopapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.supersuperstar.android.shopapi.R;
import com.supersuperstar.android.shopapi.ShopApiApplication;
import com.supersuperstar.android.shopapi.model.ProductModel;
import com.supersuperstar.android.shopapi.network.HttpDataSource;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class ProductListActivity extends AppCompatActivity {

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.currency_selector)
    Spinner mCurrencyPicker;
    @BindView(R.id.tip_message)
    TextView mTipMessage;

    @Inject
    ProductAdapter mProductAdapter;
    @Inject
    HttpDataSource mDataSource;
    @Inject
    ArrayList<ProductModel> mProducts;

    private ProductListAcitivityComponent mComponent;
    private RecyclerView.LayoutManager mLayoutManager;
    private HashMap<String, Double> mConvertRates = new HashMap<String, Double>();
    private ArrayAdapter<String> mCurrencyAdapter;

    public void updateProductList(ArrayList<ProductModel> products) {
        mTipMessage.setVisibility(View.GONE);
        mProducts.addAll(products);
        mProductAdapter.notifyDataSetChanged();
    }

    public void updateConvertRate(HashMap<String, Double> rates) {
        mConvertRates = rates;
        setupCurrencySelector();
    }

    public void handleProductListLoadingError() {
        if (mProducts.size() == 0) {
            mTipMessage.setText(getText(R.string.network_error_retry));
        }
        mTipMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTipMessage.setText(getText(R.string.loading));
                mDataSource.getProductList();
                mTipMessage.setOnClickListener(null);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        ButterKnife.bind(this);

        mComponent = DaggerProductListAcitivityComponent.builder()
                .productListActivityModule(new ProductListActivityModule(this))
                .shopApiApplicationComponent(((ShopApiApplication) this.getApplication()).getComponent())
                .build();
        mComponent.injectProductListActivity(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        setupCurrencySelector();

        setupProductAdaptor();

//        mDataSource = mComponent.getHttpDataSource();


        // Fetch data
        mDataSource.getProductList();
        mDataSource.getCurrencyAndQuotes();
    }

    private void setupProductAdaptor() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mProductAdapter = mComponent.productAdapter();
        mRecyclerView.setAdapter(mProductAdapter);
        mRecyclerView.setOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Timber.i("onLoadMore");
                mDataSource.getProductList();
            }
        });
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
                if (currencyName.equals("USD")) {
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
