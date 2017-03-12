package com.supersuperstar.android.shopapi.network;

import com.supersuperstar.android.shopapi.model.CurrencyModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Charles on 09/03/2017.
 */

public interface CurrencyQuotesService {

    String url = "live?access_key=b80b7b5ae3237c768cfaa17ae564cf8a&format=1";
    @GET(url)
    Call<CurrencyModel> getRates();
}
