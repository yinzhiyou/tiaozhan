package com.example.android.promoter.My;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.JBMXItemEntity;
import com.example.android.promoter.Entity.JBMXItemTwoEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;

import okhttp3.Call;

/**
 * 金币明细2
 */
public class JBMXItemTwoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView biaoti,jinbi,time,yljb,yldj,dqjb,dqdj,textView,leixing;
    private ImageView fanhui;
    private String token;
    private SPUtils spUtils;
    private String uid,sportID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbmxitem_two);
        biaoti = findViewById(R.id.biaoti);
        jinbi = findViewById(R.id.jbmx_two_list_item_jine);
        time = findViewById(R.id.jbmx_two_list_item_time);
        yljb = findViewById(R.id.jbmx_two_list_item_yljb);
        yldj = findViewById(R.id.jbmx_two_list_item_yldj);
        dqjb = findViewById(R.id.jbmx_two_list_item_dqjb);
        dqdj = findViewById(R.id.jbmx_two_list_item_dqdj);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        leixing = findViewById(R.id.jbmx_two_list_item_leixing);

        textView = findViewById(R.id.jbmx_two_list_item_text);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid = bundle.getString("uid");
        sportID = bundle.getString("sportID");

        biaoti.setText("技术分明细");
        initDownload();
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "技术金币详情" + token + "--coinsUUID--"+uid+"sportID"+sportID);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getTechCoinInfo")
                .addHeader("token", token)
                .addParams("coinsUUID", uid)
                .addParams("sportName",sportID)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "技术金币详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JBMXItemTwoEntity entity = gson.fromJson(result, JBMXItemTwoEntity.class);
                            DecimalFormat df = new DecimalFormat("#.00");


                            jinbi.setText(entity.getData().getGetCoins()+"");
                            time.setText(entity.getData().getCoinsDate());
                            yljb.setText(   df.format(entity.getData().getOldTotalCoins())+"");
                            yldj.setText(entity.getData().getOldLevel());
                            dqjb.setText( df.format(entity.getData().getNowTotalCoins())+"");
                            dqdj.setText(entity.getData().getNowLevel());

                            if (entity.getData().getGetCoins()<0){
                                jinbi.setTextColor(getResources().getColor(R.color.heise));
                                textView.setText("输掉金币");
                                leixing.setText("支出");
                            }else{
                                jinbi.setTextColor(getResources().getColor(R.color.hongse));
                                textView.setText("奖励金币");
                                leixing.setText("收入");
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(JBMXItemTwoActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
            case R.id.fanhui:
                finish();
                break;


        }


    }
}
