package com.example.android.promoter.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.ShopListItemAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.ShopItemEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.AddressPickTask;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import okhttp3.Call;

/**
 *兑换商品地址
 */
public class TianjiaDizhiActivity extends BaseActivity implements View.OnClickListener {

    private EditText xxdizhi,name,shouji,beizhu;
    private RelativeLayout duihuan;
    private ImageView jian,jia,fanhui;
    private TextView shuliang,heji,biaoti,dizhiText;
    private String token,uid,nameString,scjiageString,dizhiString;
    private SPUtils spUtils;
    private int shu ,jiageString,jiage;
    private LinearLayout dizhi;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tianjia_dizhi);
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_tianjia_dizhi;
    }

    @Override
    protected void initUIAndListener() {
        xxdizhi = findViewById(R.id.dizhi_xxdizhi);
        name = findViewById(R.id.dizhi_name);
        shouji = findViewById(R.id.dizhi_shouji);
        beizhu = findViewById(R.id.dizhi_beizhu);
        heji = findViewById(R.id.dizhi_heji);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        shuliang = findViewById(R.id.dizhi_shuliang);
        heji = findViewById(R.id.dizhi_heji);
        jia = findViewById(R.id.dizhi_jia);
        jia.setOnClickListener(this);
        jian = findViewById(R.id.dizhi_jian);
        jian.setOnClickListener(this);
        duihuan = findViewById(R.id.dizhi_duihuan);
        duihuan.setOnClickListener(this);
        dizhi = findViewById(R.id.dizhi_dizhi);
        dizhi.setOnClickListener(this);
        dizhiText = findViewById(R.id.dizhi_dizhi_text);

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uid =  bundle.getString("uid");
        jiageString =  bundle.getInt("jiage");
        shu = bundle.getInt("shu");
        spUtils = new SPUtils();
        token = (String) spUtils.get(TianjiaDizhiActivity.this, "logintoken", "无");

    }

    @Override
    protected void initData() {
        biaoti.setText("添加地址");
        shuliang.setText(shu+"");
        jiage = jiageString*shu;
        heji.setText("通用金币"+jiage+"个");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fanhui:
                finish();
                break;
            case R.id.dizhi_duihuan:
                initDownload();
                break;
            case R.id.dizhi_jian:
                if (shu <=1){

                }else{
                    shu--;
                }
                shuliang.setText(shu+"");
                jiage = jiageString*shu;
                heji.setText("通用金币"+jiage+"个");
                break;
            case R.id.dizhi_jia:
                shu++;
                shuliang.setText(shu+"");
                jiage = jiageString*shu;
                heji.setText("通用金币"+jiage+"个");
                break;
            case R.id.dizhi_dizhi:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        ToastUitl.longs("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
//                            showToast(province.getAreaName() + city.getAreaName()+"2");
                            dizhiText.setText(province.getAreaName() + city.getAreaName());
                            dizhiString = province.getAreaName() + city.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName());
                        } else {
//                            showToast(province.getAreaName() + city.getAreaName() + county.getAreaName()+"1");
                            dizhiText.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                            dizhiString = province.getAreaName() + city.getAreaName() + county.getAreaName();
//                            xiugai("","",province.getAreaName() + city.getAreaName() + county.getAreaName());
                        }
                    }
                });
                task.execute("北京", "北京", "通州");

                break;
        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/changeGoods")
                .addHeader("token",token)
                .addParams("goodsId",uid)
                .addParams("number",shuliang.getText().toString())
                .addParams("address",dizhiString+xxdizhi.getText().toString())
                .addParams("playername",name.getText().toString())
                .addParams("mobile",shouji.getText().toString())
                .addParams("comment",beizhu.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "兑换商品" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(TianjiaDizhiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(TianjiaDizhiActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }
}
