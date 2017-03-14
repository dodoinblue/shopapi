package com.supersuperstar.android.shopapi;

import com.google.gson.Gson;
import com.supersuperstar.android.shopapi.network.CurrencyQuotesService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class CurrencyQuotesServiceModule {

    @Provides
    CurrencyQuotesService currencyQuotesService(@CurrencyRetrofit Retrofit currencyQuotesRetrofit) {
        return currencyQuotesRetrofit.create(CurrencyQuotesService.class);
    }

    @Provides
    @CurrencyRetrofit
    public Retrofit currencyQuotesRetrofit (OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("http://www.apilayer.net/api/")
                .build();
    }
}
