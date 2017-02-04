package com.example.a27459.ttime.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.a27459.ttime.R;

import butterknife.ButterKnife;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
    }


}
