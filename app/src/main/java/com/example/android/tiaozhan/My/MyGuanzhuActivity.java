package com.example.android.tiaozhan.My;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.GuanzhuHaoyouAdapter;
import com.example.android.tiaozhan.Entity.HaoyouEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 我的关注
 */
public class MyGuanzhuActivity extends BaseActivity {

    private ListView listView;
    private TextView biaoti;
    private ImageView fanhui;
    private GuanzhuHaoyouAdapter adapter;
    private SPUtils spUtils;
    private String token;
    private List<HaoyouEntity.DataBean.LstBean> data;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_guanzhu);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_my_guanzhu;

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
        listView = findViewById(R.id.my_guanzhu_list);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        data = new ArrayList<>();
        adapter = new GuanzhuHaoyouAdapter(this, data, token);

    }

    @Override
    protected void initData() {


        biaoti.setText("我的关注");
        initDownload();

        //ListView item 中的删除按钮的点击事件
        adapter.setOnItemDeleteClickListener(new GuanzhuHaoyouAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {

                quxiao(data.get(i).getUuid()+"");

            }
        });

    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "好友全部" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyFriends")
                .addHeader("token", token)
                .addParams("type", "all")
                .addParams("term", "myfollow")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "好友全部" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HaoyouEntity entity = gson.fromJson(result, HaoyouEntity.class);
                            List<HaoyouEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
                            data.clear();
                            data.addAll(list);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyGuanzhuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void quxiao(String uid) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "取消关注" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/cancelFollow")
                .addHeader("token", token)
                .addParams("followUUID", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消关注" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyGuanzhuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            initDownload();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(MyGuanzhuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


}
