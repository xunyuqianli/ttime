package com.example.a27459.ttime.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a27459.ttime.Bean.Goods;
import com.example.a27459.ttime.R;
import com.example.a27459.ttime.UI.SearchActivity;
import com.example.a27459.ttime.Url.urls;
import com.example.a27459.ttime.adapter.MyPagerAdapter;
import com.example.a27459.ttime.adapter.MyRecyclerViewAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 27459 on 2016/12/28.
 */

public class Fragment_home extends Fragment {
    @BindView(R.id.toorBar_homeFragment)
    Toolbar toorbar_home;
    @BindView(R.id.linearLayout_select_dots)
    LinearLayout linearLayout_dots;
    @BindView(R.id.linearLayout_search_home)
    RelativeLayout linearLayout_search_home;
    @BindView(R.id.imageview_saleOfMan_home)
    ImageView imageview_saleOfMan_home;
    @BindView(R.id.imageview_image_fashion)
    ImageView imageview_image_fashion;
    @BindView(R.id.imageview_image_womman)
    ImageView imageview_image_womman;
    @BindView(R.id.text_saleOfMan_home)
    TextView text_saleOfMan_home;
    @BindView(R.id.text_title_womman)
    TextView text_title_womman;
    @BindView(R.id.text_content_womman)
    TextView text_content_womman;
    @BindView(R.id.text_title_fashion)
    TextView text_title_fashion;
    @BindView(R.id.text_content_fashion)
    TextView text_content_fashion;
    @BindView(R.id.imageview_online_home)
    ImageView imageview_online_home;
    @BindView(R.id.imageview_live_home)
    ImageView imageview_live_home;
    @BindView(R.id.imageview_sale_home)
    ImageView imageview_sale_home;
    @BindView(R.id.textView_online_home)
    TextView textView_online_home;
    @BindView(R.id.textView_live_home)
    TextView textView_live_home;
    @BindView(R.id.textView_sale_home)
    TextView textView_sale_home;
    @BindView(R.id.recyclerView_home)
    RecyclerView mRecyclerView;

    private String TAG ="Fragment_home";
    private String jsonStr;
    private String imagPaths[] ;
    private List<ImageView> listImages = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private Timer timer;
    private List<Map<String,String>> listFenLei = new ArrayList<>();
    private List<Goods.ResultBean> mGoodsList = new ArrayList<>();
    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;
    private int goodsListPageIndex = 1;
    private LinearLayoutManager manager;
    private int lastVisibalCount;
    private boolean isDown = false;


    @BindView(R.id.viewPager_carousel_home)
    ViewPager viewPager_carousel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化除了view以外的东西
    }

    /*
    toorbar的使用:
    toorbar就像一个控件可以在activity,fragment中设置布局.
    使toorbar中的标题居中,就不设置title而是在里面加一个
    textView;使toorbar在activity的上面就把其他布局放置在toorbar的下面
     */
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //与初始化view相关的写在这里
        View view = inflater.from(this.getContext()).inflate(R.layout.fragment_home,container,false);
        ButterKnife.bind(this,view);
        toorbar_home.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toorbar_home);
        initOkhttp(urls.viewPagerImageUrl);
        pagerAdapter = new MyPagerAdapter(listImages);
        viewPager_carousel.setAdapter(pagerAdapter);
        scroolViewPager();
        initViewPagerDots();
        initSearchWatch();
        initFenLeiJson(urls.fenLei);
        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(mGoodsList,getContext());
        manager = new LinearLayoutManager(getContext());
        //要设置布局管理器
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mMyRecyclerViewAdapter);
        initRecycerView(String.format(urls.goodsList,goodsListPageIndex));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int vivisibalCount = recyclerView.getChildCount();
                int totalCount = manager.getItemCount();
                Log.e(TAG, "onScrollStateChanged: "+vivisibalCount+"--"+totalCount+"--"+lastVisibalCount );
                if (vivisibalCount>0&& newState == RecyclerView.SCROLL_STATE_IDLE&& lastVisibalCount  >= totalCount-1 ) {
                    Log.e(TAG, "onScrollStateChanged:加载更多 " );
                    if (!isDown ) {
                        isDown = true;
                        goodsListPageIndex++;
                        initRecycerView(String.format(urls.goodsList,goodsListPageIndex));
                    }
                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lmanager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibalCount = lmanager.findLastVisibleItemPosition();




            }
        });
        return view;
    }

    private void initRecycerView(String str) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(str)
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e(TAG, "onResponse:json "+json );
                Gson gson = new Gson();
                Goods goods =gson.fromJson(json,Goods.class);
                mGoodsList.addAll(goods.getResult());
                Log.e(TAG, "onResponse:mGoodsList "+mGoodsList.get(0).toString() );
                //刷新适配器要在主线程
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mMyRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });

            }
        });



    }

    private void initFenLeiJson(String str) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(str)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonobject = new JSONObject(json);
                    JSONArray jsonoArray =jsonobject.getJSONArray("result");
                    for (int i = 0; i < jsonoArray.length(); i++) {
                        String title = jsonoArray.getJSONObject(i).getString("title");
                        String subtitle = jsonoArray.getJSONObject(i).getString("subtitle");
                        String imgPath = jsonoArray.getJSONObject(i).getString("img");
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("title",title);
                        map.put("subtitle",subtitle);
                        map.put("imgPath",imgPath);
                        listFenLei.add(map);
                        handler.sendEmptyMessage(2);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }



    private void initSearchWatch() {
        linearLayout_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void scroolViewPager() {
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },0,2000);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (listImages.size()!= 0) {
                    int index = (viewPager_carousel.getCurrentItem()+1)%listImages.size();
                    viewPager_carousel.setCurrentItem(index);
                }

            }
            if (msg.what == 2) {
                Picasso.with(getContext()).load(listFenLei.get(0).get("imgPath")).into(imageview_saleOfMan_home);
                Picasso.with(getContext()).load(listFenLei.get(1).get("imgPath")).into(imageview_image_womman);
                Picasso.with(getContext()).load(listFenLei.get(2).get("imgPath")).into(imageview_image_fashion);
                Picasso.with(getContext()).load(listFenLei.get(3).get("imgPath")).into(imageview_online_home);
                Picasso.with(getContext()).load(listFenLei.get(4).get("imgPath")).into(imageview_live_home);
                Picasso.with(getContext()).load(listFenLei.get(5).get("imgPath")).into(imageview_sale_home);
                text_saleOfMan_home.setText(listFenLei.get(0).get("title"));
                text_title_womman.setText(listFenLei.get(1).get("title"));
                text_title_fashion.setText(listFenLei.get(2).get("title"));
                text_content_womman.setText(listFenLei.get(1).get("subtitle"));
                text_content_fashion.setText(listFenLei.get(2).get("subtitle"));
                textView_online_home.setText(listFenLei.get(3).get("title"));
                textView_live_home.setText(listFenLei.get(4).get("title"));
                textView_sale_home.setText(listFenLei.get(5).get("title"));

            }
        }
    };

    private void initViewPagerDots() {
        viewPager_carousel.setCurrentItem(0);
        ((ImageButton) linearLayout_dots.getChildAt(0)).setSelected(true);
        viewPager_carousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 3; i++) {
                    ((ImageButton) linearLayout_dots.getChildAt(i)).setSelected(false);
                }
                ((ImageButton) linearLayout_dots.getChildAt(position)).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }


    private void initPicasso(String str[]) {
        listImages.clear();
        for (int i = 0; i < 3; i++) {
            ImageView imageview = new ImageView(this.getContext());
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            listImages.add(imageview);
            Picasso.with(this.getContext()).load(str[i]).into(listImages.get(i));

        }
    }

    public void initOkhttp(String path) {
        OkHttpClient client = new OkHttpClient();
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //当前是子线程
                jsonStr = response.body().string();
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = jsonStr;
                handler.sendMessage(msg);
                if (jsonStr != null) {
                    getImagePaths(jsonStr);
                }
                if (imagPaths != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initPicasso(imagPaths);
                            //更新适配器
                            pagerAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        //return jsonStr;不能在这个时候返回,因为这个时候还没下载到数据

    }

    public void getImagePaths(String str){
        try {
            JSONObject json = new JSONObject(jsonStr);
            JSONArray jsonArray = json.getJSONArray("result");
            imagPaths = new String [jsonArray.length()] ;
            for (int i = 0; i < jsonArray.length(); i++) {
                imagPaths[i] = jsonArray.getJSONObject(i).getString("img");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
