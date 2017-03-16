package com.supersuperstar.android.shopapi;

import com.squareup.picasso.Picasso;
import com.supersuperstar.android.shopapi.network.CurrencyQuotesService;
import com.supersuperstar.android.shopapi.network.ShopProductService;

import dagger.Component;

@ShopApiApplicationScope
@Component(modules = {
        ShopProductServiceModule.class,
        CurrencyQuotesServiceModule.class,
        PicassaModule.class
})
public interface ShopApiApplicationComponent {

    Picasso getPicasso();

    ShopProductService getShopProductService();

    CurrencyQuotesService getCurrencyQuotesService();
}
