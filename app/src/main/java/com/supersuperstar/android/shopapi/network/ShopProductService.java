package com.supersuperstar.android.shopapi.network;

import com.supersuperstar.android.shopapi.model.CurrencyModel;
import com.supersuperstar.android.shopapi.model.ProductList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Charles on 09/03/2017.
 */

public interface ShopProductService {

    String url = "products?publisherID=TEST&locale=en_US&perPage=15&apikey=l7xxa82df7dab11d4d2f8dfac696387486bf";
    @GET(url)
    Call<ProductList> getProducts();
}
