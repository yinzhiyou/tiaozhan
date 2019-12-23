package com.example.android.promoter.Promoter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.LuyinEntity;
import com.example.android.promoter.Home.FaqiTiaozhanActivity;
import com.example.android.promoter.Home.StartTimeActivity;
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
/**
 * 推广员投诉审核
 */
public class PromoterSSActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView biaoti, quxiao, tijiao,shanchu,textView;
    private ImageView fanhui;
    private Button mBtnRecordAudio, mBtnPlayAudio;
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_AUDIO = {
            Manifest.permission.RECORD_AUDIO
    };
    private String token, moshiString, uuid,ss,filesURL="",baseURL="",tab;
    private SPUtils spUtils;
    private EditText editText;
    private LinearLayout yincang;
    private SharedPreferences sharePreferences;
    private final int maxNum = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoter_ss);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        mBtnPlayAudio = (Button) findViewById(R.id.main_btn_play_sound);
        fanhui.setOnClickListener(this);
        mBtnRecordAudio = findViewById(R.id.main_btn_record_sound);
        quxiao = findViewById(R.id.promoter_ss_quxiao);
        quxiao.setOnClickListener(this);
        tijiao = findViewById(R.id.promoter_ss_tijiao);
        tijiao.setOnClickListener(this);
        editText = findViewById(R.id.yjfk_edit);
        textView = findViewById(R.id.yjfk_text);
        yincang = findViewById(R.id.promo_ss_yincang);
        shanchu = findViewById(R.id.tousu_shanchu);
        shanchu.setOnClickListener(this);

        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");

        mBtnRecordAudio.setOnClickListener(this);
        mBtnPlayAudio.setOnClickListener(this);

         sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
        sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
        sharePreferences.edit().clear().commit();
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        tab =  bundle.getString("tab");
        uuid = bundle.getString("uuid");
        if (tab.equals("1")){
            biaoti.setText("属实");
            yincang.setVisibility(View.GONE);
        }else{

            biaoti.setText("不属实");
            yincang.setVisibility(View.VISIBLE);
        }
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

//        mBtnRecordAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verifyAudioPermissions(PromoterSSActivity.this);
//                final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance();
//                fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
//                fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
//                    @Override
//                    public void onCancel() {
//                        fragment.dismiss();
//                    }
//                });
//            }
//        });
//        mBtnPlayAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RecordingItem recordingItem = new RecordingItem();
//                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
//                final String filePath = sharePreferences.getString("audio_path", "");
//                long elpased = sharePreferences.getLong("elpased", 0);
//                LogU.Ld("1608", filePath + "------" + sharePreferences);
//                ss = filePath;
//                recordingItem.setFilePath(filePath);
//                recordingItem.setLength((int) elpased);
//                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
//                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.promoter_ss_quxiao:
                sharePreferences= getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                finish();
                break;
            case R.id.promoter_ss_tijiao:
//                ptjrInit();
                SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath1 = sharePreferences1.getString("audio_path", "");
                ss = filePath1;
                LogU.Ld("1608", "是否有音频" + ss);
                if (ss.equals("")){
                    ptjrInit();
                }else{
                    luyin();
                }

                break;
            case R.id.main_btn_record_sound:
                verifyAudioPermissions(PromoterSSActivity.this);
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

                final String filePath = sharePreferences.getString("audio_path", "");
                long elpased = sharePreferences.getLong("elpased", 0);
                LogU.Ld("1608", filePath + "------" + sharePreferences);
                ss = filePath;
                recordingItem.setFilePath(filePath);
                recordingItem.setLength((int) elpased);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
                break;
            case R.id.fanhui:
                sharePreferences= getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                finish();
                break;
            case R.id.tousu_shanchu:
                sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                mBtnPlayAudio.setVisibility(View.GONE);
                shanchu.setVisibility(View.GONE);
                break;
        }
    }

    /*
     * 申请录音权限*/
    public static void verifyAudioPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_AUDIO,
                    GET_RECODE_AUDIO);
        }
    }

    private void luyin() {
        File img = new File(ss);
        LogU.Ld("1608", "音频上传" + img);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/uploadAudio")
                .addFile("files", "multipart/form-data.mp3",img)
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
        LogU.Ld("1608", "投诉" + tab);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainIsTrue")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("status", tab)
                .addParams("title", editText.getText().toString())
                .addParams("baseURL",baseURL)
                .addParams("filesURL",filesURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "投诉" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                            sharePreferences= getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                            sharePreferences.edit().clear().commit();
                            Intent intent = new Intent();
                            intent.setClass(PromoterSSActivity.this,PromoterDCLActivity.class);
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
//    ComplainIsTrue
//    @Override
//    public int initContentView() {
//        return R.layout.activity_promoter_ss;
//    }
//
//    @Override
//    protected void initUIAndListener() {
//        biaoti = findViewById(R.id.biaoti);
//        fanhui = findViewById(R.id.fanhui);
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        mBtnRecordAudio = findViewById(R.id.main_btn_record_sound);
//        mBtnRecordAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance();
//                fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
//                fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
//                    @Override
//                    public void onCancel() {
//                        fragment.dismiss();
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//
//    }


}
