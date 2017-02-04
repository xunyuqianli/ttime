package com.example.a27459.ttime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a27459.ttime.Bean.Goods;
import com.example.a27459.ttime.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 27459 on 2017/1/22.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements View.OnClickListener{


    private static final String TAG = "MyRecyclerViewAdapter";
    private List<Goods.ResultBean> mList;
    private Context mContext;

    public MyRecyclerViewAdapter(List<Goods.ResultBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager_home,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder:name "+mList.get(position).getC_name());
        holder.textView_name_viewPager.setText(mList.get(position).getTitle());
        holder.textView_price_viewPager.setText(mList.get(position).getC_price());
        Picasso.with(mContext).load(mList.get(position).getPro_img()).into(holder.imageView_goodsImg_viewPager);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {

    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_goodsImg_viewPager)
        ImageView imageView_goodsImg_viewPager;
        @BindView(R.id.textView_name_viewPager)
        TextView textView_name_viewPager;
        @BindView(R.id.textView_price_viewPager)
        TextView textView_price_viewPager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
