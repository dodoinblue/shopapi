package com.supersuperstar.android.shopapi;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    public final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ShopApiApplicationScope
    public Context context() {
        return context;
    }

}
