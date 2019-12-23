package com.example.android.promoter.Home;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.promoter.Adapter.DizhiSousuoAdapter;
import com.example.android.promoter.Entity.DizhiSousuoEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 *首页搜索
 */
public class HomeSousuoActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_sousuo);
//    }

    private ImageView fanhui;
    private EditText editText;
    private ListView listView, listViewXiala;
    private List<DizhiSousuoEntity.ResultsBean> dataDizhi;
    private DizhiSousuoAdapter adapterDizhi;
    private String city, area, sportId, province,chengshi;
    private double mLatitude, mylat, mylong;
    @Override
    public int initContentView() {
        return R.layout.activity_home_sousuo;
    }


    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listViewXiala = findViewById(R.id.changguan_list);
        editText = findViewById(R.id.fujin_sousuo);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        chengshi =  bundle.getString("chengshi");
        dataDizhi = new ArrayList<>();
        adapterDizhi = new DizhiSousuoAdapter(this, dataDizhi);
        /* 当输入关键字变化时，动态更新建议列表 */
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() <= 0) {
                    listViewXiala.setVisibility(View.GONE);
//                    return;
                } else {
                    listViewXiala.setVisibility(View.VISIBLE);
                    sousuo(cs.toString(), chengshi);

                }


//                adapterDizhi.notifyDataSetChanged();
//                adapterDizhi.notifyDataSetInvalidated();
//                /* 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新 */
//                mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
//                        .keyword(cs.toString())
//                        .city(chengshi.getText().toString()));
            }
        });
    }

    @Override
    protected void initData() {

    }

    //搜索信息
    private void sousuo(String name, String shi) {
        LogU.Ld("1608", "搜索信息" + name + "市" + shi);
        OkHttpUtils
                .get()
                .url("http://api.map.baidu.com/place/v2/search")
                .addParams("query", name)
                .addParams("page_size", "10")
                .addParams("ak", "2OjUNUyLZzwOCjwpFKLW5ZTFWe8juGsG")
                .addParams("output", "json")
                .addParams("page_num", "0")
                .addParams("scope", "1")
                .addParams("region", shi)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "搜索信息" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        Gson gson = new Gson();
                        DizhiSousuoEntity entity = gson.fromJson(result, DizhiSousuoEntity.class);
                        List<DizhiSousuoEntity.ResultsBean> list;
                        list = entity.getResults();
                        dataDizhi.clear();
                        dataDizhi.addAll(list);
                        listViewXiala.setAdapter(adapterDizhi);
                        adapterDizhi.notifyDataSetChanged();
                        adapterDizhi.notifyDataSetInvalidated();

//                        listViewXiala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                listViewXiala.setVisibility(View.GONE);
//
//                            }
//                        });
                        listViewXiala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                editText.setText(dataDizhi.get(position).getName());

                                city = dataDizhi.get(position).getCity();
                                area = dataDizhi.get(position).getArea();

                                mylat = dataDizhi.get(position).getLocation().getLat();
                                mylong = dataDizhi.get(position).getLocation().getLng();


                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("city", dataDizhi.get(position).getCity());//添加要返回给页面1的数据
//                              bundle.putString("area",  dataDizhi.get(position).getArea());
                                bundle.putString("area",  "");
                                bundle.putString("content",dataDizhi.get(position).getName());
                                bundle.putDouble("mylat", dataDizhi.get(position).getLocation().getLat());
                                bundle.putDouble("mylong", dataDizhi.get(position).getLocation().getLng());

                                intent.putExtras(bundle);
                                setResult(Activity.RESULT_OK, intent);//返回页面1
                                finish();

                            }
                        });
//                        if (a) {
//                            Gson gson = new Gson();
//                            ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);
//
//
//                        } else {
////                            Gson gson = new Gson();
////                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
////                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//
//                        }
                    }
                });
    }
}
