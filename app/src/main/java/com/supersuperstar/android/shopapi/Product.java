package com.supersuperstar.android.shopapi;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by Charles on 02/03/2017.
 */

public class Product {

    private static final String JSON_KEY_PRODUCT_ID = "id";
    private static final String JSON_KEY_PRODUCT_TITLE = "name";
    private static final String JSON_KEY_PRODUCT_DESCRIPTION = "description";
    private static final String JSON_KEY_PRODUCT_MIN_PRICE = "minimumPrice";
    private static final String JSON_KEY_PRODUCT_IMAGE_URL = "imageUrl";
    private static final String REGEX_NON_DIGIT_NON_DOT = "[^\\d.]+";

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
            mId = jsonObject.getLong(JSON_KEY_PRODUCT_ID);
            mTitle = Html.fromHtml(jsonObject.getString(JSON_KEY_PRODUCT_TITLE)).toString();
            mDescription = Html.fromHtml(jsonObject.getString(JSON_KEY_PRODUCT_DESCRIPTION))
                    .toString();
            // Assuming all inputs are in dollars
            String priceString = jsonObject.getString(JSON_KEY_PRODUCT_MIN_PRICE)
                    .replaceAll(REGEX_NON_DIGIT_NON_DOT, "");
            mPrice = Double.parseDouble(priceString);
            mImageUrl = jsonObject.getString(JSON_KEY_PRODUCT_IMAGE_URL);
        } catch (JSONException e) {
            Log.i(this.getClass().getSimpleName(), e.getMessage());
        }
    }
}
