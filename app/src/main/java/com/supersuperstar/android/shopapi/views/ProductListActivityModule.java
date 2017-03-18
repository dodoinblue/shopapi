package com.supersuperstar.android.shopapi.views;

import android.content.Context;

import com.supersuperstar.android.shopapi.model.ProductModel;
import com.supersuperstar.android.shopapi.network.HttpDataSource;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductListActivityModule {

    private final Context mContext;

    public ProductListActivityModule (Context context) {
        this.mContext = context;
    }

    @ProductListActivityScope
    @Provides
    public Context context (){
        return mContext;
    }

    @ProductListActivityScope
    @Provides
    public ProductAdapter productAdapter(ArrayList<ProductModel> list) {
        return new ProductAdapter(list);
    }

    @ProductListActivityScope
    @Provides
    public ArrayList<ProductModel> arrayList() {
        return new ArrayList<ProductModel>();
    }

    @ProductListActivityScope
    @Provides
    public HttpDataSource httpDataSource(Context context) {
        return new HttpDataSource(context);
    }
}
