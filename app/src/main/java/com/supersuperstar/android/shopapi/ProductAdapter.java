package com.supersuperstar.android.shopapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Charles on 02/03/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private String[] mDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout mProductItem;
        public ViewHolder(RelativeLayout v) {
            super(v);
            mProductItem = v;
        }
    }

    public ProductAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tv = (TextView) holder.mProductItem.findViewById(R.id.product_title);
        tv.setText(mDataSet[position]);

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
