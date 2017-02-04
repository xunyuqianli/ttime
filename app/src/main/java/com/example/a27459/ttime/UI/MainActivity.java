package com.example.a27459.ttime.UI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.a27459.ttime.Fragment.Fragment_home;
import com.example.a27459.ttime.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.button_home)
    Button button_home;
    @BindView(R.id.button_news)
    Button button_news;
    @BindView(R.id.button_search)
    Button button_search;
    @BindView(R.id.button_square)
    Button button_square;
    @BindView(R.id.button_me)
    Button button_me;

    private int selectDrawbles [] = {R.drawable.selector_home,R.drawable.selector_news,R.drawable.selector_search,R.drawable.selector_square,R.drawable.selector_me};

    private Button buttons[] = new Button[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initButtons();
        initSelectImage();
        initShowTransaction(new Fragment_home());

    }

    private void initButtons() {
        buttons[0] = button_home;
        buttons[1] = button_news;
        buttons[2] = button_search;
        buttons[3] = button_square;
        buttons[4] = button_me;
    }

    //初始化最下面那排选择图片以及文字
    private void initSelectImage() {
        //
        for (int i = 0; i < 5; i++) {
            if (buttons[i] == null) {
                return;
            }
            if (i == 0) {
                Drawable drawable = getResources().getDrawable(selectDrawbles[i]);
                drawable.setBounds(0,10,75,80);
                buttons[i].setCompoundDrawables(null,drawable,null,null);
                buttons[i].setSelected(true);
                buttons[i].setTextColor(this.getResources().getColor(R.color.black));
                buttons[i].setEnabled(false);
            }else{
                Drawable drawable = getResources().getDrawable(selectDrawbles[i]);
                drawable.setBounds(0,10,75,80);
                buttons[i].setCompoundDrawables(null,drawable,null,null);
                buttons[i].setTextColor(this.getResources().getColor(R.color.darkgray));
            }
        }

    }

    //开启事务并展示
    private void initShowTransaction(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_container_main,fragment);
        transaction.commit( );
    }

    public void ChangeFragment(View view) {
        //先把所有的都设置成非选中
        for (int i = 0; i < 5; i++) {
            buttons[i].setSelected(false);
            buttons[i].setTextColor(this.getResources().getColor(R.color.darkgray));
            buttons[i].setEnabled(true);
        }
        view.setSelected(true);
        ((Button) view).setTextColor(this.getResources().getColor(R.color.black));
        view.setEnabled(false);
    }
}
