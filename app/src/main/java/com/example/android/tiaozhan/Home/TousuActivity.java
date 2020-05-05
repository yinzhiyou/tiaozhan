package com.example.android.tiaozhan.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.RefereeTsNumAdapter;
import com.example.android.tiaozhan.Denglu.TiaoKuanActivity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.LuyinEntity;
import com.example.android.tiaozhan.Entity.RefereeGetJudgeEntity;
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
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.baidu.location.g.j.e;
import static com.example.android.tiaozhan.Promoter.PromoterSSActivity.verifyAudioPermissions;

/**
 * 投诉
 */
public class TousuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView biaoti, textView, qita, dianhua, tiaokuan, shanchu, zishu, tousu_referee;
    private ImageView fanhui, yuedu;
    private EditText editText;
    private RelativeLayout tijiao;
    private String token, leixing = "其他", uuid, hezuo, ss, filesURL = "", baseURL = "",pltag,cgtag;
    private SPUtils spUtils;
    private Button mBtnRecordAudio, mBtnPlayAudio;
    private SharedPreferences sharePreferences;
    private boolean tab = false;
    private final int maxNum = 200;
    private RecyclerView referee_recview;
    private LinearLayout referee_wd;
    private String uuid1;
    private int cpId;
    private boolean boxChecked;
    private List<String> cp_uuid;
    private String cp_ts;

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
        tousu_referee = findViewById(R.id.tousu_referee);
        tousu_referee.setOnClickListener(this);

        referee_recview = findViewById(R.id.tousu_referee_num);

        referee_wd = findViewById(R.id.referee_wd);

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
        pltag = bundle.getString("pltag");
        cgtag = bundle.getString("cgtag");
        if (!EmptyUtils.isStrEmpty(cgtag)){
            tousu_referee.setVisibility(View.GONE);
            qita.setVisibility(View.GONE);
        }
        if (!EmptyUtils.isStrEmpty(pltag)){
            if (pltag.equals("1")){
                tousu_referee.setVisibility(View.GONE);
                qita.setVisibility(View.GONE);
            }else if (pltag.equals("2")){
                tousu_referee.setVisibility(View.VISIBLE);
                qita.setVisibility(View.GONE);
            }else if (pltag.equals("4")){
                tousu_referee.setVisibility(View.GONE);
                qita.setVisibility(View.VISIBLE);

            }
           
        }else {
          //  qita.setVisibility(View.GONE);
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
                zishu.setText("剩余字数：" + (maxNum - s.length()));
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

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tousu_text1:
                textView.setBackgroundResource(R.drawable.tousu);
                qita.setBackgroundResource(R.drawable.tousu_two);
                tousu_referee.setBackgroundResource(R.drawable.tousu_two);
                referee_wd.setVisibility(View.GONE);
                if (hezuo.equals("1")) {
                    leixing = "场馆未预留场地";
                } else {
                    leixing = "发布者签到但未到场";

                }
                cpId=0;
                break;
            case R.id.tousu_referee:
                tousu_referee.setBackgroundResource(R.drawable.tousu);
                qita.setBackgroundResource(R.drawable.tousu_two);
                textView.setBackgroundResource(R.drawable.tousu_two);
                referee_wd.setVisibility(View.VISIBLE);
                getJudge();
                cpId = 1;
                leixing = "裁判未到场";


                break;
            case R.id.fanhui:
                sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                sharePreferences.edit().clear().commit();
                finish();
                break;
            case R.id.tousu_qita:
                textView.setBackgroundResource(R.drawable.tousu_two);
                qita.setBackgroundResource(R.drawable.tousu);
                cpId = 2;
                leixing = "陪练未到场";
                break;
            case R.id.tousu_tijiao:
                SharedPreferences sharePreferences1 = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
                final String filePath1 = sharePreferences1.getString("audio_path", "");
                ss = filePath1;

                LogU.Ld("1608", "aaaaaa" + ss);

                if (!tab) {
                    if (cpId==0){
                        if (ss.equals("")) {
                            quxiaoyaoqing();
                        } else {
                            luyin();
                        }
                    }else if(cpId==1) {
                        if (!EmptyUtils.isListEmpty(cp_uuid)) {
                            if (ss.equals("")) {
                                addRefreeComplai();
                            } else {
                                luyinCP();
                            }
                        }else {
                            ToastUitl.longs("请选择裁判");
                        }
                    }else if (cpId==2){
                        if (ss.equals("")) {
                            addsparr();
                        } else {
                            luyin();
                        }
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
                            if (cpId==0){
                                quxiaoyaoqing();
                            }else if (cpId==2){
                                addsparr();
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

    private void luyinCP() {
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
                            addRefreeComplai();
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
        LogU.Ld("1608", "举报" + token+uuid+leixing+editText.getText().toString()+baseURL+filesURL);
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

                      //  if (a) {

                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        ToastUitl.longs(entity.getMsg());
                   //         ToastUitl.longs("您的投诉已提交成功，正等待后台处理");

                            sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                            sharePreferences.edit().clear().commit();
                            finish();
                       /* } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
    }


    //举报陪练
    private void addsparr() {
        LogU.Ld("1608", "举报陪练" + token+uuid+leixing+editText.getText().toString()+baseURL+filesURL);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addsparr")
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
                        LogU.Ld("1608", "举报陪练" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        //  if (a) {

                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        ToastUitl.longs(entity.getMsg());
                        //         ToastUitl.longs("您的投诉已提交成功，正等待后台处理");

                        sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                        sharePreferences.edit().clear().commit();
                        finish();
                       /* } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
    }


    //用户投诉裁判
    private void addRefreeComplai() {
        LogU.Ld("1608", "举报裁判" + token+uuid+"类型"+leixing+editText.getText().toString()+baseURL+filesURL+"=="+cp_ts);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/addRefreeComplai")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("complainName", leixing)
                .addParams("comment", editText.getText().toString())
                .addParams("baseURL", baseURL)
                .addParams("imgs", filesURL)
                .addParams("RefereeID", cp_ts)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "举报裁判" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        Gson gson = new Gson();
                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                        ToastUitl.longs(entity.getMsg());
                        sharePreferences = getSharedPreferences("sp_name_audio", Context.MODE_PRIVATE);
                        sharePreferences.edit().clear().commit();
                        finish();
 /*                       if (a) {
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
                        }*/
                    }
                });
    }


    //投诉时候获取裁判

    public void getJudge() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getJudge")
                .addHeader("token", token)
                .addParams("uuid", uuid)
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
                            Gson gson = new Gson();
                            final RefereeGetJudgeEntity entity = gson.fromJson(result, RefereeGetJudgeEntity.class);
                            final RefereeTsNumAdapter refereeTsNumAdapter = new RefereeTsNumAdapter(R.layout.referee_getjde_item, entity.getData());
                            referee_recview.setLayoutManager(new GridLayoutManager(TousuActivity.this, 6));
                            referee_recview.setAdapter(refereeTsNumAdapter);
                            cp_uuid = new ArrayList<>();

                          refereeTsNumAdapter.setBoxItemCallBack(new RefereeTsNumAdapter.BoxItemCallBack() {

                                @Override
                                public void onCallBack() {

                                    cp_uuid = refereeTsNumAdapter.getSelectSet();

                                    cp_ts = TextUtils.join(",", cp_uuid);
                                    LogU.Ld("1608", "举报" + cp_uuid+ cp_ts);
                                    refereeTsNumAdapter.notifyDataSetChanged();
                                }
                            });


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
