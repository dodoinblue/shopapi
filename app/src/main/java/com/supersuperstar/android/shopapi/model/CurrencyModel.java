package com.supersuperstar.android.shopapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by Charles on 10/03/2017.
 */

public class CurrencyModel {
    @SerializedName("success")
    @Expose
    public boolean success;

    @SerializedName("source")
    @Expose
    public String source;

    @SerializedName("quotes")
    @Expose
    public Object quotes;
}
