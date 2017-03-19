package com.supersuperstar.android.shopapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Charles on 11/03/2017.
 */

public class ProductList {

    @SerializedName("numberOfProducts")
    @Expose
    public long numberOfProducts;

    @SerializedName("products")
    @Expose
    public ArrayList<ProductModel> products;
}
