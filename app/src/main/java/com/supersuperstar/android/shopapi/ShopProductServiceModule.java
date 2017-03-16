package com.supersuperstar.android.shopapi;

import com.google.gson.Gson;
import com.supersuperstar.android.shopapi.network.ShopProductService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ShopProductServiceModule {

    @Provides
    @ShopApiApplicationScope
    public ShopProductService shopProductService (@ShopRetrofit Retrofit shopApiRetrofit) {
        return shopApiRetrofit.create(ShopProductService.class);
    }

    @Provides
    @ShopRetrofit
    @ShopApiApplicationScope
    public Retrofit shopApiRetrofit (OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.shop.com/AffiliatePublisherNetwork/v1/")
                .build();
    }
}
