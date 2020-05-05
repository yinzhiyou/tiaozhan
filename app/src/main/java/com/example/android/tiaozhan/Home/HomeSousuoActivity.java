package com.example.android.tiaozhan.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.DizhiSousuoAdapter;
import com.example.android.tiaozhan.DaoSession;
import com.example.android.tiaozhan.Entity.DizhiSousuoEntity;
import com.example.android.tiaozhan.Entity.SouSuoEntity;
import com.example.android.tiaozhan.GreenDaoDao;

import com.example.android.tiaozhan.Home.greendao.GreenDao;
import com.example.android.tiaozhan.Home.sousou.BaseRecycleAdapter;
import com.example.android.tiaozhan.Home.sousou.SeachRecordAdapter;
import com.example.android.tiaozhan.MyApplication;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.bean.addressBean;
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
   private RecyclerView mRecyclerView;
    private TextView mtv_deleteAll;
    private SeachRecordAdapter mAdapter;



    private ImageView fanhui;
    private EditText editText;
    private ListView listView, listViewXiala;
    private List<DizhiSousuoEntity.ResultsBean> dataDizhi;
    private DizhiSousuoAdapter adapterDizhi;
    private String city, area, sportId, province,chengshi;
    private LinearLayout kong_layout;
    private double mLatitude, mylat, mylong;
    private GreenDaoDao greenDaoDao;
    private GreenDao demo;
    private List<GreenDao> greenDaos;

    @Override
    public int initContentView() {
        return R.layout.activity_home_sousuo;
    }


    @Override
    protected void initUIAndListener() {

        //获取GreenDao数据库操作对象
        final DaoSession daoSession = MyApplication.getDaoSession();
//获取指定的操作对象
        greenDaoDao = daoSession.getGreenDaoDao();

        greenDaos = daoSession.loadAll(GreenDao.class);
        LogU.Ld("1608","搜索==传值"+"==="+greenDaos);

        mtv_deleteAll = (TextView) findViewById(R.id.tv_deleteAll);
        mtv_deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greenDaoDao.delete(demo);
               // mDbDao.deleteData();

                mAdapter.updata(greenDaos);

                // mAdapter.updata(mDbDao.queryData(""));
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        mAdapter =new SeachRecordAdapter(greenDaos,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
               // mDbDao.delete(mDbDao.queryData("").get(position));
                greenDaoDao.deleteByKey(greenDaos.get(position).getId());

                greenDaos.remove(position);
                mAdapter.notifyDataSetChanged();

            }
        });

      mAdapter.setRvItemDataOnclickListener(new BaseRecycleAdapter.RvItemDataOnclickListener() {
          @Override
          public void RvItemDataOnclick(int position) {

              Intent intent = new Intent();
              Bundle bundle = new Bundle();
              bundle.putString("city", greenDaos.get(position).getCity());//添加要返回给页面1的数据
//                              bundle.putString("area",  dataDizhi.get(position).getArea());
              bundle.putString("area", "");
              bundle.putString("content", greenDaos.get(position).getName());
              bundle.putDouble("mylat", greenDaos.get(position).getLat());
              bundle.putDouble("mylong", greenDaos.get(position).getLng());
              bundle.putString("CityTag", "2");
              intent.putExtras(bundle);
              setResult(Activity.RESULT_OK, intent);//返回页面1
              finish();
              LogU.Ld("1608","搜索传值"+greenDaos.get(position).getCity()+"===="+greenDaos.get(position).getName()+"==="+greenDaos.get(position).getLat());

          }
      });



        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listViewXiala = findViewById(R.id.changguan_list);
        editText = findViewById(R.id.fujin_sousuo);
        kong_layout = findViewById(R.id.kong_layout);
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
                LogU.Ld("1608","搜索框"+cs+"===="+cs.toString().length()+"==="+cs.length());
                if (cs.length() <= 0) {
                    listViewXiala.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    kong_layout.setVisibility(View.VISIBLE);
//                    return;
                } else {
                    listViewXiala.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                    kong_layout.setVisibility(View.GONE);
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
                        List<String> stringlist = new ArrayList<>();
                        List<String> stringlist2 = new ArrayList<>();
                        List<DizhiSousuoEntity.ResultsBean> list;
                        list = entity.getResults();



                            SouSuoEntity msou = new SouSuoEntity();

                            List<DizhiSousuoEntity.ResultsBean> newList=new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {


                                if (EmptyUtils.isStrEmpty(list.get(i).getCity())){
                                    list.remove(i);
                                }else {
                                    msou.setName(list.get(i).getName());
                                    msou.setAddress(list.get(i).getAddress());
                                    newList.add( list.get(i));
                                }
                            }
                        LogU.Ld("1608","搜索数据"+list+"==="+newList.size());
                        if (newList.size()>0){
                            dataDizhi.clear();
                            dataDizhi.addAll(newList);

                            listViewXiala.setAdapter(adapterDizhi);
                            adapterDizhi.notifyDataSetChanged();
                            adapterDizhi.notifyDataSetInvalidated();
                            kong_layout.setVisibility(View.GONE);
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

                                    bundle.putString("city", dataDizhi.get(position).getCity().replace("市", ""));//添加要返回给页面1的数据
//                              bundle.putString("area",  dataDizhi.get(position).getArea());
                                    bundle.putString("area", "");
                                    bundle.putString("content", dataDizhi.get(position).getName());
                                    bundle.putDouble("mylat", dataDizhi.get(position).getLocation().getLat());
                                    bundle.putDouble("mylong", dataDizhi.get(position).getLocation().getLng());
                                    bundle.putString("CityTag", "2");
                                    intent.putExtras(bundle);
                                    //   startActivity(intent);
                                    setResult(Activity.RESULT_OK, intent);//返回页面1

                                    demo = new GreenDao();
                                    demo.setCity(dataDizhi.get(position).getCity().replace("市", ""));
                                    demo.setName(dataDizhi.get(position).getName());
                                    demo.setAddress(dataDizhi.get(position).getAddress());
                                    demo.setLat(dataDizhi.get(position).getLocation().getLat());
                                    demo.setLng(dataDizhi.get(position).getLocation().getLng());
                               //     daoSession.insert(demo);
//或
                                    greenDaoDao.insert(demo);
                                    /*List<addressBean> addressBeanList=new ArrayList<>();
                                    addressBean beanList=new addressBean();
                                    beanList.setCity(dataDizhi.get(position).getCity().replace("市", ""));
                                   beanList.setAddress(dataDizhi.get(position).getAddress());
                                   beanList.setLat(dataDizhi.get(position).getLocation().getLat());
                                   beanList.setLng(dataDizhi.get(position).getLocation().getLng());
                                   addressBeanList.add(beanList);
                                    if (dataDizhi.get(position).getName().length() != 0){
                                        boolean hasData = mDbDao.hasData(addressBeanList);
                                        if (!hasData){
                                            mDbDao.insertData(dataDizhi.get(position).getName(),dataDizhi.get(position).getAddress());
                                        }else {
                                            Toast.makeText(HomeSousuoActivity.this, "该内容已在历史记录中", Toast.LENGTH_SHORT).show();
                                        }

                                        //
                                        mAdapter.updata(mDbDao.queryData(""));

                                    }*/
                                    finish();

                                }
                            });

                        }else {
                            dataDizhi.clear();
                            kong_layout.setVisibility(View.VISIBLE);
                        }
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
