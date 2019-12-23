package com.example.android.promoter.My;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Adapter.DingdanFragmentAdapter;
import com.example.android.promoter.MainActivity;
import com.example.android.promoter.My.Friends.Badminton;
import com.example.android.promoter.My.Friends.Basketball;
import com.example.android.promoter.My.Friends.Billiards;
import com.example.android.promoter.My.Friends.Football;
import com.example.android.promoter.My.Friends.Golf;
import com.example.android.promoter.My.Friends.MyHYQBFragment;
import com.example.android.promoter.My.Friends.Pingpong;
import com.example.android.promoter.My.Friends.Tennis;
import com.example.android.promoter.My.Friends.Volleyball;
import com.example.android.promoter.R;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的好友
 */
public class MyHaoyouActivity extends AppCompatActivity {

    private TextView biaoti;
    private ImageView fanhui;
    private List<String> listString;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragment;
    private MyHYQBFragment quanbu;
    private Basketball lanqiu;
    private Football zuqiu;
    private Pingpong pingpang;
    private Badminton yumaoqiu;
    private Billiards taiqiu;
    private Tennis wangqiu;
    private Volleyball paiqiu;
    private Golf gaoerfu;


    public static final int MOVABLE_COUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),100);

        setContentView(R.layout.activity_my_haoyou);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout =  findViewById(R.id.home_list_tab);
        viewPager =  findViewById(R.id.home_list_pager);
        biaoti.setText("我的好友");

        listString = new ArrayList<>();
        listString.add("全部");
        listString.add("篮球");
        listString.add("足球");
        listString.add("乒乓球");
        listString.add("羽毛球");
        listString.add("台球");
        listString.add("网球");
        listString.add("排球");
        listString.add("高尔夫球");

        quanbu = new MyHYQBFragment();
        lanqiu = new Basketball();
        zuqiu = new Football();
        pingpang = new Pingpong();
        yumaoqiu = new Badminton();
        taiqiu = new Billiards();
        wangqiu = new Tennis();
        paiqiu = new Volleyball();
        gaoerfu = new Golf();

        listFragment = new ArrayList<>();
        listFragment.add(quanbu);
        listFragment.add(lanqiu);
        listFragment.add(zuqiu);
        listFragment.add(pingpang);
        listFragment.add(yumaoqiu);
        listFragment.add(taiqiu);
        listFragment.add(wangqiu);
        listFragment.add(paiqiu);
        listFragment.add(gaoerfu);


        viewPager.setAdapter(new DingdanFragmentAdapter(getSupportFragmentManager(), listFragment, listString));
        tabLayout.setupWithViewPager(viewPager);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setTabMode(listString.size() <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getPosition();
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
                viewPager.setCurrentItem(tab.getPosition(), false);
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
