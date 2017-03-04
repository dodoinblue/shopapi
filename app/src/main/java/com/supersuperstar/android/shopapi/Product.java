package com.supersuperstar.android.shopapi;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by Charles on 02/03/2017.
 */

public class Product {
    private long mId;
    private String mTitle;
    private String mDescription;
    private double mPrice;

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getPrice() {
        return mPrice;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPriceString(double rate) {
        return new DecimalFormat("#.#").format(mPrice * rate);
    }

    private String mImageUrl;

    public Product(long id, String title, String description, float price, String imageUrl) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mPrice = price;
        mImageUrl = imageUrl;
    }

    public Product(JSONObject jsonObject) {

        try {
            mId = jsonObject.getLong("id");
            mTitle = Html.fromHtml(jsonObject.getString("name")).toString();
            mDescription = Html.fromHtml(jsonObject.getString("description")).toString();
            // Assuming all inputs are in dollars
            String priceString = jsonObject.getString("minimumPrice").replaceAll("[^\\d.]+", "");
            mPrice = Double.parseDouble(priceString);
            mImageUrl = jsonObject.getString("imageUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
