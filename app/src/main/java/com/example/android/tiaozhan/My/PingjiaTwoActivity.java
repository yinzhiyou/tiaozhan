package com.example.android.tiaozhan.My;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Adapter.PingjiaTowAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.PingjiaTowEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 活动评价 2
 */
public class PingjiaTwoActivity extends BaseActivity implements View.OnClickListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pingjia_two);
//    }

    private TextView biaoti;
    private ImageView fanhui;
    private String token,uuid,siteUid,syUID,tag;
    private SPUtils spUtils;
    private PingjiaTowAdapter adapter;
    private List<PingjiaTowEntity.DataBean> data;
    private ListView listView;
    private RelativeLayout tijiao;
    private JSONArray array1;
    private SPUtileFQTZ spUtileFQTZ;
    @Override
    public int initContentView() {
        return R.layout.activity_pingjia_two;
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
        listView = findViewById(R.id.pingjia_two_list);
        tijiao = findViewById(R.id.pingjia_two_tijiao);
        tijiao.setOnClickListener(this);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "无");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid =  bundle.getString("uuid");
        spUtileFQTZ = new SPUtileFQTZ();

        array1 =  new JSONArray();
        data = new ArrayList<>();
        adapter = new PingjiaTowAdapter(this,data);
    }

    @Override
    protected void initData() {
        biaoti.setText("发表评价");
        yijianhaoping();
    }


    //评价TOW
    private void yijianhaoping() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "一键好评---" + token + "---uuid---" + syUID);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/secondtime")
                .addHeader("token", token)
                .addParams("uuid",uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "评价TOW" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            PingjiaTowEntity entity = gson.fromJson(result, PingjiaTowEntity.class);
                            List<PingjiaTowEntity.DataBean> list;
                            list = entity.getData();
                            data.addAll(list);
                            listView.setAdapter(adapter);


                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //提交二次评价
    private void tijiao() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "提交二次评价---" + token + "---uuid---" + uuid+"array1.toString()"+array1.toString());
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addCommentator")
                .addHeader("token", token)
                .addParams("publicUuid",uuid)
                .addParams("userComment",array1.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "提交二次评价" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            finish();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pingjia_two_tijiao:
                initjihe();

                break;


        }
    }

    private void initjihe() {


        JSONObject object3 = new JSONObject();

//        StarBar starBar;
//        RecyclerView recyclerView;
//        TextView textView;

        for (int i = 0;i<data.size();i++){
            JSONObject object2 = new JSONObject();
//                starBar =  listView.getChildAt(i).findViewById(R.id.starBar);
//                recyclerView =  listView.getChildAt(i).findViewById(R.id.recyclerview);
//                textView = recyclerView.getChildAt(i).findViewById(R.id.textViewContent);


            try {
                tag = (String) spUtileFQTZ.get(this, "pingjia"+i, "");
                object2.put("uuid", data.get(i).getPlayerUUID());
                object2.put("label", tag);
            } catch (JSONException e) {
                e.printStackTrace();
            }

                array1.put(object2);
                LogU.Ld("1608", i+"拼接------" + array1.toString());

        }

        LogU.Ld("1608", data.size()+"评价拼接json------" + array1.toString());
        tijiao();
    }
}
