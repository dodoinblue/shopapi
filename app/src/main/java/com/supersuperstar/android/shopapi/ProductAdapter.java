package com.supersuperstar.android.shopapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Charles on 02/03/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> mProductList;
    private Context mContext;
    private double mRate = 1.0d;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mProductItem;
        public ViewHolder(RelativeLayout v) {
            super(v);
            mProductItem = v;
        }
    }

    public void setRate(double rate) {
        mRate = rate;
    }

    public ProductAdapter(ArrayList<Product> dataSet) {
        mProductList = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.product_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tvTitle = (TextView) holder.mProductItem.findViewById(R.id.product_title);
        TextView tvDetail = (TextView) holder.mProductItem.findViewById(R.id.product_detail);
        TextView tvPrice = (TextView) holder.mProductItem.findViewById(R.id.price);
        ImageView tvIcon = (ImageView) holder.mProductItem.findViewById(R.id.productIcon);
        tvTitle.setText(mProductList.get(position).getTitle());
        tvDetail.setText(mProductList.get(position).getDescription());
        tvPrice.setText(Double.toString(mProductList.get(position).getPrice() * mRate));
        Picasso.with(mContext).load(mProductList.get(position).getImageUrl()).into(tvIcon);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
