package com.supersuperstar.android.shopapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Charles on 02/03/2017.
 */

public class HttpDataSource {

    Context mContext;
    RequestQueue mQueue;

    public HttpDataSource(Context ctx) {
        mContext = ctx;
        mQueue = Volley.newRequestQueue(mContext);
    }

    public void getProductList() {
        StringBuilder urlBuilder = new StringBuilder("https://api.shop.com/AffiliatePublisherNetwork/v1/products");
        urlBuilder.append("?");
        try {
            urlBuilder.append(URLEncoder.encode("publisherID","UTF-8") + "=" + URLEncoder.encode("TEST", "UTF-8") + "&");
            urlBuilder.append(URLEncoder.encode("locale","UTF-8") + "=" + URLEncoder.encode("en_US", "UTF-8") + "&");
            urlBuilder.append(URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("15", "UTF-8") + "&");
            urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xxa82df7dab11d4d2f8dfac696387486bf", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, urlBuilder.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Product> result;
                        try {
                            JSONArray jarray = response.getJSONArray("products");
                            result = new ArrayList<Product>();
                            for (int i = 0; i < jarray.length(); i++) {
                                if (jarray.get(i) instanceof JSONObject) {
                                    result.add(new Product(jarray.getJSONObject(i)));
                                }
                            }
                            ((ProductListActivity) mContext).updateDataSet(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

        // Add the request to the RequestQueue.
        mQueue.add(jsObjRequest);
    }

    public void getCurrencyAndQuotes() {
        String url = "http://www.apilayer.net/api/live?access_key=b80b7b5ae3237c768cfaa17ae564cf8a&format=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject quotesJsonObj = response.getJSONObject("quotes");
                            HashMap<String, Double> quotesMap = new HashMap<String, Double>();
                            Iterator it = quotesJsonObj.keys();
                            while (it.hasNext()) {
                                String currencyName = (String) it.next();
                                double convertRate = quotesJsonObj.getDouble(currencyName);
                                quotesMap.put(currencyName, convertRate);
                            }
                            ((ProductListActivity) mContext).updateConvertRate(quotesMap);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
        mQueue.add(jsObjRequest);
    }

}
