package com.supersuperstar.android.shopapi;

/**
 * Created by Charles on 03/03/2017.
 */

public class Currency {
    public String getmName() {
        return mName;
    }

    private String mName;
    private double mRate;

    public Currency (String name, double rate) {
        mName = name;
        mRate = rate;
    }
}
