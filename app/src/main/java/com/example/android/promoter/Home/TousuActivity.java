package com.example.android.promoter.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Denglu.TiaoKuanActivity;
import com.example.android.promoter.Entity.LuyinEntity;
import com.example.android.promoter.Promoter.PromoterSSActivity;
import com.example.android.promoter.R;
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
 *投诉
 */
public class TousuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView biaoti, textView, qita, dianhua, tiaokuan, shanchu,zishu;
    private ImageView fanhui, yuedu;
    private EditText editText;
    private RelativeLayout tijiao;
    private String token, leixing = "其他", uuid, hezuo, ss, filesURL = "", baseURL = "";
    private SPUtils spUtils;
    private Button mBtnRecordAudio, mBtnPlayAudio;
    private SharedPreferences sharePreferences;
    private boolean tab = false;
    private final int maxNum = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tousu);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        textView = findViewById(R.id.tousu_text1);
        textView.setOnClickListener(this);
        qita = findViewById(R.id.tousu_qita);
        qita.setOnClickListener(this);
        tijiao = findViewById(R.id.tousu_tijiao);
        tijiao.setOnClickListener(this);
        editText = findViewById(R.id.tousu_edit);
        zishu = findViewById(R.id.yjfk_text);
        mBtnRecordAudio = findViewById(R.id.main_btn_record_sound);
        mBtnPlayAudio = (Button) findViewById(R.id.main_btn_play_sound);
        mBtnRecordAudio.setOnClickListener(this);
        mBtnPlayAudio.setOnClickListener(this);
        yuedu = findViewById(R.id.tousu_yuedu);
        yuedu.setOnClickListener(this);
        dianhua = findViewById(R.id.tousu_dianhua);
        dianhua.setOnClickListener(this);
        tiaokuan = findViewById(R.id.tousu_tiaokuan);
        tiaokuan.setOnClickListener(this);
        shanchu = findViewById(R.id.tousu_shanchu);
        shanchu.setOnClickListener(this);

        sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
        sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
        sharePreferences.edit().clear().commit();
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        biaoti.setText("投诉");

        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        uuid = bundle.getString("uuid");
        hezuo = bundle.getString("hezuo");
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
                zishu.setText("剩余字数："+ (maxNum-s.length()));
            }
        });
//
//        if (hezuo.equals("1")){
//
//            textView.setText("场馆未预留场地");
//        }else{
//
//            textView.setText("发布者签到但未到场");
//        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tousu_text1:
                textView.setBackgroundResource(R.drawable.tousu);
                qita.setBackgroundResource(R.drawable.tousu_two);
                if (hezuo.equals("1")) {
                    leixing = "场馆未预留场地";
                } else {
                    leixing = "发布者签到但未到场";

                }

                break;
            case R.id.fanhui:
                sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                finish();
                break;
            case R.id.tousu_qita:
                textView.setBackgroundResource(R.drawable.tousu_two);
                qita.setBackgroundResource(R.drawable.tousu);
                leixing = "其他";
                break;
            case R.id.tousu_tijiao:
                SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath1 = sharePreferences1.getString("audio_path", "");
                ss = filePath1;

                LogU.Ld("1608", "aaaaaa" + ss);

                if (!tab) {
                    if (ss.equals("")) {
                        quxiaoyaoqing();
                    } else {
                        luyin();
                    }
                } else {
                    Toast.makeText(this, "请阅读条款", Toast.LENGTH_SHORT).show();
                }


//                quxiaoyaoqing();

                break;
            case R.id.main_btn_record_sound:

                 final SharedPreferences sharePreferences2 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                 String filePath2 = sharePreferences2.getString("audio_path", "");
                ss = filePath2;

                LogU.Ld("1608", "aaaaaa" + ss);
                verifyAudioPermissions(TousuActivity.this);
                final RecordAudioDialogFragment fragment = RecordAudioDialogFragment.newInstance();

                fragment.show(getSupportFragmentManager(), RecordAudioDialogFragment.class.getSimpleName());
                fragment.setOnCancelListener(new RecordAudioDialogFragment.OnAudioCancelListener() {
                    @Override
                    public void onCancel() {

                        fragment.dismiss();

//                        SharedPreferences sharePreferences2 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
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
//                ss = filePath;
                recordingItem.setFilePath(filePath);
                recordingItem.setLength((int) elpased);
                PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());
                break;
            case R.id.tousu_yuedu:
                if (tab) {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.hongdian));
                } else {
                    yuedu.setImageDrawable(getResources().getDrawable(R.mipmap.huidian));
                }
                tab = !tab;

                break;
            case R.id.tousu_dianhua:

                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "010-80895077");
                intent1.setData(data);
                startActivity(intent1);
                break;

            case R.id.tousu_tiaokuan:
                intent.setClass(this, TiaoKuanActivity.class);
                startActivity(intent);

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
        File img = new File(ss);
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
                            quxiaoyaoqing();
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

    //举报
    private void quxiaoyaoqing() {
        LogU.Ld("1608", "举报" + token);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/userAddComplain")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("complainName", leixing)
                .addParams("comment", editText.getText().toString())
                .addParams("baseURL", baseURL)
                .addParams("imgs", filesURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "举报" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs("您的投诉已提交成功，正等待后台处理");

                            sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                            sharePreferences.edit().clear().commit();
                            finish();
                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
