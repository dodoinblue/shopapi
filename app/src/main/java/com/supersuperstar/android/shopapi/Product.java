package com.supersuperstar.android.shopapi;

import org.json.JSONException;
import org.json.JSONObject;

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
            mTitle = jsonObject.getString("name");
            mDescription = jsonObject.getString("description");

            // Assuming all inputs are in dollars
            String priceString = jsonObject.getString("minimumPrice").replaceAll("[^\\d.]+", "");
            mPrice = Double.parseDouble(priceString);
            mImageUrl = jsonObject.getString("imageUrl");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
