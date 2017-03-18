package com.supersuperstar.android.shopapi.views;

import com.supersuperstar.android.shopapi.model.ProductModel;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductListActivityModule {

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
}
