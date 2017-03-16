package com.supersuperstar.android.shopapi;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class PicassaModule {

    @Provides
    @ShopApiApplicationScope
    Picasso picasso(Context context) {
        return new Picasso.Builder(context)
                // This just makes image download very slow, why?
//                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
    }

}
