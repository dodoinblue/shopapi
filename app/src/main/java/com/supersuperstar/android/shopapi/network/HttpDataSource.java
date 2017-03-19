package com.supersuperstar.android.shopapi.network;

import android.content.Context;

import com.google.gson.internal.LinkedTreeMap;
import com.supersuperstar.android.shopapi.ShopApiApplication;
import com.supersuperstar.android.shopapi.model.CurrencyModel;
import com.supersuperstar.android.shopapi.model.ProductList;
import com.supersuperstar.android.shopapi.views.ProductListActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

/**
 * Created by Charles on 02/03/2017.
 */

public class HttpDataSource {

    private Context mContext;
    private ShopApiApplication mApplication;

    public HttpDataSource(Context ctx) {
        mContext = ctx;
        mApplication = (ShopApiApplication) mContext.getApplicationContext();
    }

    public void getProductList() {
        Call<ProductList> products = mApplication.getShopProductService().getProducts();
        products.enqueue(new Callback<ProductList>(){

            @Override
            public void onResponse(Call<ProductList> call, retrofit2.Response<ProductList> response) {
                try {
                    ((ProductListActivity) mContext).updateProductList(response.body().products);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ProductList> call, Throwable t) {
                Timber.i("error");
                Timber.i(t);
            }
        });
    }

    public void getCurrencyAndQuotes() {
        Call<CurrencyModel> currency = mApplication.getCurrencyQuotesService().getRates();
        currency.enqueue(new Callback<CurrencyModel>(){

            @Override
            public void onResponse(Call<CurrencyModel> call, retrofit2.Response<CurrencyModel> response) {
                try {
                    LinkedTreeMap rates = (LinkedTreeMap) response.body().quotes;
                    HashMap<String, Double> result = new HashMap<String, Double>();
                    for (Object rate : rates.keySet()) {
                        result.put((String) rate, (Double) rates.get(rate));
                        ((ProductListActivity) mContext).updateConvertRate(result);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CurrencyModel> call, Throwable t) {
                Timber.i("error");
                Timber.i(t);
            }
        });
    }
}
