package com.supersuperstar.android.shopapi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Charles on 02/03/2017.
 */

public class HttpDataSource {

    private static final String SHOP_BASE_URL = "https://api.shop.com/AffiliatePublisherNetwork/v1/products";
    private static final String SHOP_REQUEST_PUBLISHER_ID = "publisherID";
    private static final String SHOP_REQUEST_LOCALE = "locale";
    private static final String SHOP_REQUEST_PAGESIZE = "perPage";
    private static final String SHOP_REQUEST_APIKEY = "apikey";

    private static final String SHOP_PUBLISHER_TEST = "TEST";
    private static final String SHOP_APIKEY = "l7xxa82df7dab11d4d2f8dfac696387486bf";
    private static final int SHOP_PAGESIZE_VALUE = 15;
    private static final String SHOP_LOCALE_VALUE = "en_US";

    private static final String CURRENCY_BASE_URL = "http://www.apilayer.net/api/live";
    private static final String CURRENCY_APIKEY = "access_key";
    private static final String CURRENCY_APIKEY_VALUE = "b80b7b5ae3237c768cfaa17ae564cf8a";
    private static final String CURRENCY_REQUEST_FORMAT = "format";
    private static final int CURRENCY_REQUEST_FORMAT_JSON = 1;

    private static final String SHOP_RESPONSE_PRODUCT_KEY = "products";

    private static final String CURRENCY_RESPONSE_QUOTES = "quotes";

    private static final String LOG_TAG = "HttpDataSource";

    private Context mContext;
    private RequestQueue mQueue;

    public HttpDataSource(Context ctx) {
        mContext = ctx;
        mQueue = Volley.newRequestQueue(mContext);
    }

    public void getProductList() {
        StringBuilder urlBuilder = new StringBuilder(SHOP_BASE_URL);
        urlBuilder.append("?");
        urlBuilder.append(SHOP_REQUEST_PUBLISHER_ID + "=" + SHOP_PUBLISHER_TEST);
        urlBuilder.append("&" + SHOP_REQUEST_LOCALE + "=" + SHOP_LOCALE_VALUE);
        urlBuilder.append("&" + SHOP_REQUEST_PAGESIZE + "=" + SHOP_PAGESIZE_VALUE);
        urlBuilder.append("&" + SHOP_REQUEST_APIKEY + "=" + SHOP_APIKEY);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlBuilder.toString(), null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                ArrayList<Product> result;
                                try {
                                    JSONArray jarray = response.getJSONArray(SHOP_RESPONSE_PRODUCT_KEY);
                                    result = new ArrayList<Product>();
                                    for (int i = 0; i < jarray.length(); i++) {
                                        if (jarray.get(i) instanceof JSONObject) {
                                            result.add(new Product(jarray.getJSONObject(i)));
                                        }
                                    }
                                    ((ProductListActivity) mContext).updateProductList(result);
                                } catch (JSONException e) {
                                    Log.i(LOG_TAG, e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, mContext.getText(R.string.network_error),
                                Toast.LENGTH_SHORT).show();
                        ((ProductListActivity) mContext).handleProductListLoadingError();
//                        Log.i(LOG_TAG, error.getMessage());
                    }

                });

        // Add the request to the RequestQueue.
        mQueue.add(jsObjRequest);
    }

    public void getCurrencyAndQuotes() {
        StringBuilder urlBuilder = new StringBuilder(CURRENCY_BASE_URL);
        urlBuilder.append("?");
        urlBuilder.append(CURRENCY_APIKEY + "=" + CURRENCY_APIKEY_VALUE);
        urlBuilder.append("&" + CURRENCY_REQUEST_FORMAT + "=" + CURRENCY_REQUEST_FORMAT_JSON);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlBuilder.toString(), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject quotesObj = response.getJSONObject(CURRENCY_RESPONSE_QUOTES);
                            HashMap<String, Double> quotesMap = new HashMap<String, Double>();
                            Iterator it = quotesObj.keys();
                            while (it.hasNext()) {
                                String currencyName = (String) it.next();
                                double convertRate = quotesObj.getDouble(currencyName);
                                quotesMap.put(currencyName, convertRate);
                            }
                            ((ProductListActivity) mContext).updateConvertRate(quotesMap);
                        } catch (JSONException e) {
                            Log.i(LOG_TAG, e.getMessage());
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                Log.i(LOG_TAG, error.getMessage());
                                error.printStackTrace();
                            }
                        });
        mQueue.add(jsObjRequest);
    }

}
