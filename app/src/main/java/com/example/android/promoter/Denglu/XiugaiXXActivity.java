package com.example.android.promoter.Denglu;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.XiuGaiSportAdapter;
import com.example.android.promoter.Adapter.XiugaiAihaoAdapter;
import com.example.android.promoter.Entity.HqSportEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.DataBase;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *修改个人信息
 */
public class XiugaiXXActivity extends BaseActivity {

    private TextView biaoti, textView, youshangjiao;
    private ImageView fanhui;
    private EditText editText;
    private GridView gridView;
    private RecyclerView recyclerView;
    private XiugaiAihaoAdapter adapter;
    private String tab, biaotiString, token,uuid;
    private SPUtils spUtils;
    private List<HqSportEntity.DataBean> data;


    private HqSportEntity entity;

    private XiuGaiSportAdapter xiuGaiSportAdapter;


    private StringBuffer ss;
    private String name;
    private String leve;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xiugai_xx);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_xiugai_xx;

    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editText = findViewById(R.id.xiugai_xx_edit);
        textView = findViewById(R.id.xiugai_xx_text);
       // gridView = findViewById(R.id.xiugai_grid);
        recyclerView= findViewById(R.id.xiugai_grid);
        youshangjiao = findViewById(R.id.youshangjiao);
        youshangjiao.setVisibility(View.VISIBLE);

        data = new ArrayList<HqSportEntity.DataBean>();

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        tab = bundle.getString("tab");
        biaotiString = bundle.getString("biaoti");
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        uuid=(String) spUtils.get(this, "uuid", "无");

    }

    @Override
    protected void initData() {
        youshangjiao.setText("保存");
        biaoti.setText(biaotiString);
//        editText.setHint("ssss");

        if (tab.equals("2")) {
            editText.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
           // gridView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            huoquyundong();
        } else if (tab.equals("3")) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            textView.setVisibility(View.GONE);
            editText.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 4 )});
        } else if (tab.equals("4")) {
            textView.setVisibility(View.GONE);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 4 )});
        } else if (tab.equals("5")) {
            textView.setVisibility(View.GONE);
        }
        //保存监听器
        youshangjiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (tab.equals("1")) {
                    LogU.Ld("1608", "我是名字");
                    initDownload(editText.getText().toString(), "", "", "", "");
                } else if (tab.equals("2")) {
                    if(ss==null){
                        Toast.makeText(XiugaiXXActivity.this, "请至少选择一项运动", Toast.LENGTH_SHORT).show();
                    }else {

                        //removeAllSpacetwo(removeAllSpace(getData() + ""));

                         initDownload("", "", "",ss.toString(), "");
                    }
                } else if (tab.equals("3")) {
                    LogU.Ld("1608", "我是身高");

//
                    if (TextUtils.isEmpty(editText.getText())) {

                    } else {
                        int a = Integer.parseInt(editText.getText().toString());
                        if (a <= 250 && a >= 100) {

                            initDownload("", editText.getText().toString(), "", "", "");
                        } else {

                            showNormalDialog("请输入真实身高  100-250cm");
                            editText.setText("");
                        }
                    }



                } else if (tab.equals("4")) {
                    LogU.Ld("1608", "我是体重");
                    if (TextUtils.isEmpty(editText.getText())) {

                    } else {
                        int a = Integer.parseInt(editText.getText().toString());
                        if (a <= 200 && a >= 30) {

                            initDownload("", "", editText.getText().toString(), "", "");
                        } else {

                            showNormalDialog("请输入真实体重  30-200kg");
                            editText.setText("");
                        }
                    }



                } else if (tab.equals("5")) {
                    initDownload("", "", "", "", editText.getText().toString());
                }
            }
        });
        Intent name_fh= getIntent();
        String cn = name_fh.getStringExtra("cn");
        String cj = name_fh.getStringExtra("cj");
        String zj = name_fh.getStringExtra("zj");
        String gj = name_fh.getStringExtra("gj");
        LogU.Le("cn",cn+cj+zj+gj);
    }

    //修改信息
    private void initDownload(String name, String shengao, String tizhong, String yundong, String jianjie) {
        LogU.Ld("1608", "修改信息" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PerfectInfo")
                .addHeader("token", token)
                .addParams("nickname", name)
                .addParams("sex", "")
                .addParams("birthday", "")
                .addParams("address", "")
                .addParams("imgURL", "")
                .addParams("sport_id", yundong)
                .addParams("tall", shengao)
                .addParams("weight", tizhong)
                .addParams("comment", jianjie)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "修改信息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Intent intent = new Intent();
//                            intent.setClass(XiugaiXXActivity.this, GRXXActivity.class);
//                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(XiugaiXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    private void huoquyundong() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getuuidInfos")
                .addParams("uuid",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final String result = response.toString();
                        LogU.Ld("1608", "运动项目" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            entity = gson.fromJson(result, HqSportEntity.class);
                            final List<HqSportEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);

                          /*  adapter = new XiugaiAihaoAdapter(XiugaiXXActivity.this, data);

                            gridView.setAdapter(adapter);*/
                            recyclerView.setLayoutManager(new GridLayoutManager(XiugaiXXActivity.this,3));
                            xiuGaiSportAdapter = new XiuGaiSportAdapter(R.layout.xiugai_aihao_grid,data);

                          //  mlist = xiuGaiSportAdapter.getSelectList();
                            recyclerView.setAdapter(xiuGaiSportAdapter);
                            xiuGaiSportAdapter.setJsCallBack(new XiuGaiSportAdapter.JsCallBack() {
                                @Override
                                public void callBack(String text, int spId) {

                                }
                            });
                          xiuGaiSportAdapter.setCallBack(new XiuGaiSportAdapter.CallBack() {
                               @Override
                               public void callBack(Map<String, String> selectList) {

                                    ss = new StringBuffer();
                                   for(Map.Entry<String, String> entry : selectList.entrySet()){
                                       ss.append(entry.getKey()+","+entry.getValue()+"|");
                                   }
                               }
                           });


                            xiuGaiSportAdapter.notifyDataSetChanged();

                            //爱好的运动点击监听器
                       /*  gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    //将当前点击的position传递过去做相应的状态改变
                                    adapter.choiceState(position);

                                }
                            });*/


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(XiugaiXXActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void showNormalDialog(String neirong) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(XiugaiXXActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(neirong);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });

        // 显示

        normalDialog.show();
    }

    /**
     * 通过查找数据库,拿到里面的数据
     */
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        Cursor query = DataBase.getInstances(this).query();
        if (query.moveToFirst()) {
            do {
                String channel = query.getString(query.getColumnIndex("channel"));
                list.add(channel);
            } while (query.moveToNext());
        }
        //关闭查询游标
        query.close();
        return list;
    }

    //方法一
    public String removeAllSpace(String str) {
        String tmpstr = str.replace("[", "");
        return tmpstr;
    }

    public String removeAllSpacetwo(String str) {
        String tmpstr = str.replace("]", "");
        return tmpstr;
    }

    public void saveSport(){




    }

}
