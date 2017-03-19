package com.supersuperstar.android.shopapi;

import android.app.Application;
import android.widget.Toast;

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


    private ShopApiApplicationComponent mComponent;
    private Picasso mPicasso;
    private ShopProductService mShopProductService;
    private CurrencyQuotesService mCurrencyQuotesService;

    public Picasso getPicasso() {
        return mPicasso;
    }

    public ShopProductService getShopProductService() {
        return mShopProductService;
    }

    public CurrencyQuotesService getCurrencyQuotesService() {
        return mCurrencyQuotesService;
    }

    public ShopApiApplicationComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());
        }

        mComponent = DaggerShopApiApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        mShopProductService = mComponent.getShopProductService();
        mCurrencyQuotesService = mComponent.getCurrencyQuotesService();
        mPicasso = mComponent.getPicasso();

        Toast.makeText(this, "ABCDEFg", Toast.LENGTH_SHORT).show();
    }
}
