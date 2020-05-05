package com.example.android.tiaozhan.My;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.JBMXItemEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 金币明细
 */
public class JBMXItemActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView biaoti, jinbi, leixing, time, dangqian, beizhu;
    private ImageView fanhui;
    private String token;
    private SPUtils spUtils;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbmxitem);
        StatusBarUtil.setColor(JBMXItemActivity.this,getResources().getColor(R.color.white),0);


        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        jinbi = findViewById(R.id.jbmx_list_item_jine);
        leixing = findViewById(R.id.jbmx_list_item_leixing);
        time = findViewById(R.id.jbmx_list_item_time);
        dangqian = findViewById(R.id.jbmx_list_item_shengyu);
        beizhu = findViewById(R.id.jbmx_list_item_beizhu);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid = bundle.getString("uid");

        biaoti.setText("对手币明细");
      /*  jinbi.setText("");
        time.setText("");
        dangqian.setText("");
        beizhu.setText("");
        leixing.setText("");*/
        initDownload();

    }

    //    getCommonCoinInfo
    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "金币详情" + token + "--goldType--"+uid);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCommonCoinInfo")
                .addHeader("token", token)
                .addParams("coinsUUID", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "金币详情" + result);
                        Boolean a = result.indexOf("2000") != -1;
                    if (a) {
                        Gson gson = new Gson();
                        JBMXItemEntity entity = gson.fromJson(result, JBMXItemEntity.class);
                        jinbi.setText(entity.getData().getGetCommonCoins());
                        time.setText(entity.getData().getCoinsDate());
                        dangqian.setText(entity.getData().getTotalCoins()+"");
                        beizhu.setText(entity.getData().getReason());
                        if (entity.getData().getGetCommonCoins().indexOf("-") != -1){
                            jinbi.setTextColor(getResources().getColor(R.color.heise));
                            leixing.setText("支出");
                        }else{
                            jinbi.setTextColor(getResources().getColor(R.color.hongse));
                            leixing.setText("收入");
                        }
                        jinbi.setTextColor(getResources().getColor(R.color.hongse));
                    } else {
                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        Toast.makeText(JBMXItemActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
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
