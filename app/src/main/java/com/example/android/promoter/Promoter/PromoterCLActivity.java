package com.example.android.promoter.Promoter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.LuyinEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.RecordAudioDialogFragment;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.example.android.promoter.Toos.luyin.PlaybackDialogFragment;
import com.example.android.promoter.Toos.luyin.RecordingItem;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

import static com.example.android.promoter.Promoter.PromoterSSActivity.verifyAudioPermissions;

/**
 * 推广员处理投诉
 */
public class PromoterCLActivity extends BaseActivity implements View.OnClickListener {

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_promoter_cl);
//    }
    private String token, moshiString, uuid, filesURL="", baseURL="", tab = "1", sss,nameString,tag,uid,isHandle;
    private SPUtils spUtils;
    private TextView biaoti, ss, bss, tijiao,shanchu,textView;
    private ImageView fanhui;
    private LinearLayout yc;
    private EditText editText;
    private Button mBtnRecordAudio, mBtnPlayAudio;
    private SharedPreferences sharePreferences;
    private final int maxNum = 200;
    @Override
    public int initContentView() {
        return R.layout.activity_promoter_cl;

    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        biaoti.setText("处理投诉");
        fanhui.setOnClickListener(this);
        ss = findViewById(R.id.promo_cl_ss);
        ss.setOnClickListener(this);
        bss = findViewById(R.id.promo_cl_bss);
        bss.setOnClickListener(this);
        yc = findViewById(R.id.promo_cl_yincang);
        editText = findViewById(R.id.yjfk_edit);
        textView = findViewById(R.id.yjfk_text);
        mBtnPlayAudio = (Button) findViewById(R.id.main_btn_play_sound);
        mBtnRecordAudio = findViewById(R.id.main_btn_record_sound);
        tijiao = findViewById(R.id.promoter_ss_tijiao);
        tijiao.setOnClickListener(this);
        mBtnRecordAudio.setOnClickListener(this);
        mBtnPlayAudio.setOnClickListener(this);
        shanchu = findViewById(R.id.tousu_shanchu);
        shanchu.setOnClickListener(this);

        sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
        sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
        sharePreferences.edit().clear().commit();


        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();

        uuid = bundle.getString("uuid");
        tag = bundle.getString("tag");
        nameString =  bundle.getString("name");
        uid =  bundle.getString("uid");
        isHandle = bundle.getString("isHandle");
    }

    @Override
    protected void initData() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                if (s.length() + (after-count) >=maxNum) {
//
//                    ToastUitl.longs("不能超过" + maxNum + "字！");
//                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView.setText("剩余字数："+ (maxNum-s.length()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                sharePreferences= getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                finish();
                break;
            case R.id.promo_cl_ss:
                ss.setBackgroundResource(R.drawable.hong_bk);//修改背景
                ss.setTextColor(getResources().getColor(R.color.hongse));//字体上色
                bss.setBackgroundResource(R.drawable.hui_bk);//修改背景
                bss.setTextColor(getResources().getColor(R.color.huise));//字体上色
                yc.setVisibility(View.GONE);
                tab = "1";
                break;
            case R.id.promo_cl_bss:
                ss.setBackgroundResource(R.drawable.hui_bk);//修改背景
                ss.setTextColor(getResources().getColor(R.color.huise));//字体上色
                bss.setBackgroundResource(R.drawable.hong_bk);//修改背景
                bss.setTextColor(getResources().getColor(R.color.hongse));//字体上色
                yc.setVisibility(View.VISIBLE);
                tab = "0";
                break;
            case R.id.promoter_ss_tijiao:
//                ptjrInit();
                SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath1 = sharePreferences1.getString("audio_path", "");
                sss = filePath1;
                if (sss.equals("")){
                    ptjrInit();
                }else{
                    luyin();
                }

                break;
            case R.id.main_btn_record_sound:
                verifyAudioPermissions(PromoterCLActivity.this);
                final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance();
                fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
                fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
                    @Override
                    public void onCancel() {
                        fragment.dismiss();
                        String filePath2 = sharePreferences.getString("audio_path", "");
                        LogU.Ld("1608", "走了" + filePath2);

                        if (filePath2.length() < 2) {
                            mBtnPlayAudio.setVisibility(View.GONE);
                            shanchu.setVisibility(View.GONE);
                        } else {
                            mBtnPlayAudio.setVisibility(View.VISIBLE);
                            shanchu.setVisibility(View.VISIBLE);
                        }

                    }
                });
                break;
            case R.id.main_btn_play_sound:
                RecordingItem recordingItem = new RecordingItem();
//                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath = sharePreferences.getString("audio_path", "");
                long elpased = sharePreferences.getLong("elpased", 0);
                LogU.Ld("1608", filePath + "------" + sharePreferences);
                sss = filePath;
                recordingItem.setFilePath(filePath);
                recordingItem.setLength((int) elpased);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
                break;
            case R.id.tousu_shanchu:
                sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                mBtnPlayAudio.setVisibility(View.GONE);
                shanchu.setVisibility(View.GONE);
                break;
        }
    }


    private void luyin() {
        File img = new File(sss);
        LogU.Ld("1608", "音频上传" + img);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadAudio")
                .addFile("files", "multipart/form-data.mp3", img)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "音频上传" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            LuyinEntity entity = gson.fromJson(result, LuyinEntity.class);
//                            ToastUitl.longs(entity.getMsg());
                            baseURL = entity.getData().getBaseURL();
                            filesURL = entity.getData().getFilesURL();
                            ptjrInit();
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void ptjrInit() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainIsTrue")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("status", tab)
                .addParams("title", editText.getText().toString())
                .addParams("baseURL", baseURL)
                .addParams("filesURL", filesURL)
                .addParams("iscoopera","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "投诉处理" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
                            sharePreferences= getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                            sharePreferences.edit().clear().commit();
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();//传值
                            intent.setClass(PromoterCLActivity.this,PromoterCGXXActivity.class);
                            bundle.putString("tag",tag);
                            bundle.putString("uid",uid);
                            bundle.putString("name",nameString);
                            bundle.putString("isHandle",isHandle);
                            intent.putExtras(bundle);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(ShopListActivity.this,DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}
