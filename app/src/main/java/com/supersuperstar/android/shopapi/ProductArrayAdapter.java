package com.supersuperstar.android.shopapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Charles on 02/03/2017.
 */

public class ProductArrayAdapter extends ArrayAdapter<Product> {

    public ProductArrayAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.product_item_layout, parent, false);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.product_item_title);
        TextView tvDetail = (TextView) convertView.findViewById(R.id.product_item_detail);
//        ImageView ivImage = (ImageView) convertView.findViewById(R.id.product_item_image);

        tvTitle.setText(product.mProductTitle);
        tvDetail.setText(product.mProductDetail);

        return convertView;

    }
}
