package com.example.android.promoter.Promoter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.PromoterDDAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.BastEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.PromoterDDEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.MyListView;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * 推广员我的订单
 */
public class PromterDDActivity extends BaseActivity implements View.OnClickListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promter_dd);
//    }

    private TextView biaoti, timeText1, timeText2, shouru,zanwu_dd;
    private ImageView fanhui;
    private PullToRefreshListView listView;
    private PromoterDDAdapter adapter;
    private LinearLayout time1, time2;
    private int mYear, mMonth, mDay;
    private String days = "", str;
    private int tag;
    private String token, uid, nameString, scjiageString, ZMurl, FMurl;
    private SPUtils spUtils;

    private List<PromoterDDEntity.DataBean> data;
    private int page = 1;

    @Override
    public int initContentView() {
        return R.layout.activity_promter_dd;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        listView = findViewById(R.id.prom_dd_list);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        fanhui.setOnClickListener(this);
        time1 = findViewById(R.id.promo_dd_time1);
        time1.setOnClickListener(this);
        time2 = findViewById(R.id.promo_dd_time2);
        time2.setOnClickListener(this);
        timeText1 = findViewById(R.id.promo_dd_text_time1);
        timeText2 = findViewById(R.id.promo_dd_text_time2);
        shouru = findViewById(R.id.prom_dd_shouru);
        zanwu_dd = findViewById(R.id.zanwu_dd);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        biaoti.setText("我的订单");


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");


        data = new ArrayList<>();
        adapter = new PromoterDDAdapter(this, data);
        listView.setAdapter(adapter);


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        str = formatter.format(curDate);
        timeText1.setText(str);
        timeText2.setText(str);
    }

    @Override
    protected void initData() {
        initDownload(str, str, 1);
        shuaxin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.promo_dd_time1:
                tag = 1;
                //TODO 调用时间选择器
                new DatePickerDialog(PromterDDActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.promo_dd_time2:
                //TODO 调用时间选择器
                tag = 2;
                new DatePickerDialog(PromterDDActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;


        }
    }

    private void jrsr() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTodayMoney")
                .addHeader("token", token)
                .addParams("startdate", "startdate")
                .addParams("enddate", "enddate")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "今日收入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            BastEntity entity = gson.fromJson(result, BastEntity.class);
                            shouru.setText(entity.getData().getTodaymoney() + "");

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void shuaxin() {


        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page + "");

                        initDownload(timeText1.getText().toString(), timeText2.getText().toString(), page);

                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page + "");
                        initDownload(timeText1.getText().toString(), timeText2.getText().toString(), page);

                    }
                });

    }

    private void initDownload(String start, String end, final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        LogU.Ld("1608", "推广员我的订单" + page + "start" + start + "end" + end);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterOrder")
                .addHeader("token", token)
                .addParams("page", page + "")
                .addParams("limit", "10")
                .addParams("start", start)
                .addParams("end", end)
                .addParams("orderId", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "推广员我的订单" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            PromoterDDEntity entity = gson.fromJson(result, PromoterDDEntity.class);
                            zanwu_dd.setVisibility(View.GONE);
                            List<PromoterDDEntity.DataBean> list;
                            list = entity.getData();
                            shouru.setText(entity.getOther() + "");
                            if (page == 1) {
                                data.clear();
                                data.addAll(list);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            } else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                LogU.Ld("1608","数据"+EmptyUtils.isListEmpty(data)+ list.size()+data.size());
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }
                            if(EmptyUtils.isListEmpty(data)){
                            LogU.Ld("1608",""+EmptyUtils.isListEmpty(list)+ data.size()+EmptyUtils.isListEmpty(data));
                                zanwu_dd.setVisibility(View.VISIBLE);
                            }

//                            jrsr();
                        } else {
                            adapter.notifyDataSetChanged();

                            listView.onRefreshComplete();
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(PromterDDActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(PromterDDActivity.this, DengluActivity.class);
                            startActivity(intent);
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
//            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }
            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }
            }
            String pattern = "yyyy-MM-dd";


            if (tag == 1) {
                timeText1.setText(days);
                initDownload(days, timeText2.getText().toString(), page);
            } else {
                if (getStringToDate(timeText1.getText().toString(), pattern) > getStringToDate(days, pattern)) {
                    ToastUitl.longs("开始时间不能大于结束时间");
                } else {
                    timeText2.setText(days);
                    initDownload(timeText1.getText().toString(), days, page);
                }
            }


//            xiugai("",days,"");
        }
    };

    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

}
