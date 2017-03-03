package com.supersuperstar.android.shopapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Charles on 02/03/2017.
 */

public class ProductDataSource {

    Context mContext;

    public ProductDataSource(Context ctx) {
        mContext = ctx;
    }

    public void getProductListFromServer() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
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

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlBuilder.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("Charles_Tag", "Response is: " + response.substring(0, 500));

                        String[] set = new String[30];

                        for (int i = 0; i < set.length; i++) {
                            set[i] = "Product " + i;
                        }
                        ((MainActivity) mContext).updateDataSet(set);

                    }
                }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Server", error.toString());
                }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
