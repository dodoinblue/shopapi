package com.supersuperstar.android.shopapi;

/**
 * Created by Charles on 02/03/2017.
 */

public class Product {

    public String mProductTitle;
    public String mProductDetail;
    public String mImageUrl;
    public int mProductPrice;
    public String mProductUrl;

    public Product(String title, String detail, String imageUrl, int price, String link) {
        mProductTitle = title;
        mProductDetail = detail;
        mImageUrl = imageUrl;
        mProductPrice = price;
        mProductUrl = link;
    }
}
