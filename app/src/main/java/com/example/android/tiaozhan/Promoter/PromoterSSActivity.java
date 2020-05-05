package com.example.android.tiaozhan.Promoter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.LuyinEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.RecordAudioDialogFragment;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.example.android.tiaozhan.Toos.luyin.PlaybackDialogFragment;
import com.example.android.tiaozhan.Toos.luyin.RecordingItem;
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
    private String token, moshiString, uuid,re_com_id,refereeid="",referee="",ss,filesURL="",baseURL="",tab,tabCG;
    private SPUtils spUtils;
    private EditText editText;
    private LinearLayout yincang;
    private SharedPreferences sharePreferences,sharePreferencesCP,sharePreferencesPL;
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
        re_com_id = bundle.getString("re_com_id");
        refereeid = bundle.getString("refereeid");
        tabCG = bundle.getString("tabCP");
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
                LogU.Ld("1608","投诉"+tabCG);
                if (!EmptyUtils.isStrEmpty(tabCG)) {
                    if (tabCG.equals("2")) {
                        SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                        final String filePath1 = sharePreferences1.getString("audio_path", "");
                        ss = filePath1;
                        LogU.Ld("1608", "是否有音频" + ss);
                        if (ss.equals("")) {
                            if (tab.equals("1")){
                                referee="";
                                ptjrRefereeInit();
                            }else {
                                referee=refereeid;
                                ptjrRefereeInit();
                            }
                        } else {
                            luyin();
                        }
                        finish();
                    } else if (tabCG.equals("1")){
                        SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                        final String filePath1 = sharePreferences1.getString("audio_path", "");
                        ss = filePath1;
                        LogU.Ld("1608", "是否有音频" + ss);
                        if (ss.equals("")) {
                            ptjrInit();
                        } else {
                            luyin();
                        }
                        finish();
                    }else if (tabCG.equals("3")){
                        SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                        final String filePath1 = sharePreferences1.getString("audio_path", "");
                        ss = filePath1;
                        LogU.Ld("1608", "是否有音频" + ss);
                        if (ss.equals("")) {
                            sparrIsTrue();
                        } else {
                            luyin();
                        }
                        finish();
                    }

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
                            if (tabCG.equals("1")){
                                ptjrInit();
                            }else if (tabCG.equals("2")){
                                if (tab.equals("1")){
                                    referee="";
                                    ptjrRefereeInit();
                                }else {
                                    referee=refereeid;
                                    ptjrRefereeInit();
                                }

                            }else if (tabCG.equals("3")){
                                sparrIsTrue();
                            }

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
  //  推广员~投诉属实不属实（场馆未预留场地）
    private void ptjrInit() {
        LogU.Ld("1608", "投诉场馆未预留场地" + tab+"=="+editText.getText().toString()+"=="+baseURL+"==="+filesURL);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainIsTrue")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("status", tab)
                .addParams("title", editText.getText().toString())
                .addParams("baseURL",baseURL)
                .addParams("filesURL",filesURL)
                .addParams("iscoopera",0+"")
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


//推广员~推广员属实不属实（裁判未到场）
    private void ptjrRefereeInit() {
        LogU.Ld("1608", "投诉裁判" + tab+"=="+editText.getText().toString()+"=="+baseURL+"==="+filesURL+"=="+refereeid+"===="+referee);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereeIsTrue")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("status", tab)
                .addParams("title", editText.getText().toString())
                .addParams("baseURL",baseURL)
                .addParams("filesURL",filesURL)
                .addParams("re_com_id",re_com_id)
                .addParams("notreferee",referee)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608","失败原因"+e.getMessage());
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


    //推广员~推广员属实不属实（陪练未到场）
    private void sparrIsTrue() {
        LogU.Ld("1608", "投诉陪练" + tab+"=="+editText.getText().toString()+"=="+baseURL+"==="+filesURL);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/SparrIsTrue")
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
