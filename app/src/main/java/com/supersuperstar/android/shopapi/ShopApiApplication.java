package com.supersuperstar.android.shopapi;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import com.supersuperstar.android.shopapi.network.CurrencyQuotesService;
import com.supersuperstar.android.shopapi.network.ShopProductService;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Charles on 04/03/2017.
 */

public class ShopApiApplication extends Application {


    public Picasso getPicasso() {
        return mPicasso;
    }

    public ShopProductService getShopProductService() {
        return mShopProductService;
    }

    public CurrencyQuotesService getCurrencyQuotesService() {
        return mCurrencyQuotesService;
    }

    private Picasso mPicasso;
    private ShopProductService mShopProductService;
    private CurrencyQuotesService mCurrencyQuotesService;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        // Gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        // Http Logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        File cacheFile = new File(getCacheDir(), "okhttp_cache");
        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();


        // Picasso
        mPicasso = new Picasso.Builder(this)
                // This just makes image download very slow, why?
//                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();


        // Retrofit
        Retrofit shopApiRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.shop.com/AffiliatePublisherNetwork/v1/")
                .build();

        Retrofit currencyRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("http://www.apilayer.net/api/")
                .build();

        mShopProductService = shopApiRetrofit.create(ShopProductService.class);
        mCurrencyQuotesService = currencyRetrofit.create(CurrencyQuotesService.class);
    }
}
