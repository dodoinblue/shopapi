package com.supersuperstar.android.shopapi.views;

import com.supersuperstar.android.shopapi.ShopApiApplicationComponent;

import dagger.Component;

@ProductListActivityScope
@Component(modules = {ProductListActivityModule.class}, dependencies = {ShopApiApplicationComponent.class})
public interface ProductListAcitivityComponent {
    ProductAdapter productAdapter();
}
