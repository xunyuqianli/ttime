package com.example.a27459.ttime.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 27459 on 2017/1/16.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews = new ArrayList<>();

    public MyPagerAdapter (List<ImageView> imageViews){
        this.imageViews = imageViews;
    }
    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }
    //pageradapger 只缓存三张图片
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }
}
