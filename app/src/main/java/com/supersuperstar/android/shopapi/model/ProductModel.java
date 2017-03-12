package com.supersuperstar.android.shopapi.model;

import android.text.Html;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

/**
 * Created by Charles on 09/03/2017.
 */

public class ProductModel {
    private static final String REGEX_NON_DIGIT_NON_DOT = "[^\\d.]+";

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mTitle;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("minimumPrice")
    @Expose
    private String mPrice;

    @SerializedName("imageUrl")
    @Expose
    private String mImageUrl;

    public String getPriceString(double rate) {
        double price = Double.parseDouble(mPrice.replaceAll(REGEX_NON_DIGIT_NON_DOT, ""));
        return new DecimalFormat("#.#").format(price * rate);
    }

    public String getReadableTitle() {
        return Html.fromHtml(mTitle).toString();
    }

    public String getReadableDescription() {
        return Html.fromHtml(mDescription).toString();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{title: ");
        sb.append(getReadableTitle() + '\n');
        sb.append("description: " + getReadableDescription().substring(0, 100) + "\n");
        sb.append("}");
        return sb.toString();
    }

}
