package com.supersuperstar.android.shopapi.views;

import com.supersuperstar.android.shopapi.ShopApiApplicationComponent;
import com.supersuperstar.android.shopapi.network.HttpDataSource;

import dagger.Component;

@ProductListActivityScope
@Component(modules = {ProductListActivityModule.class}, dependencies = {ShopApiApplicationComponent.class})
public interface ProductListAcitivityComponent {
//    ProductAdapter productAdapter();
//
//    HttpDataSource getHttpDataSource();

    void injectProductListActivity(ProductListActivity productListActivity);
}
