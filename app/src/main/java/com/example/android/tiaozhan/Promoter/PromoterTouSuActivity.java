package com.example.android.tiaozhan.Promoter;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.tiaozhan.Adapter.PromoterTouSuAdapter;
import com.example.android.tiaozhan.Entity.PromoterTouSuEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.luyin.PlaybackDialogFragment;
import com.example.android.tiaozhan.Toos.luyin.RecordingItem;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class PromoterTouSuActivity extends BaseActivity implements View.OnClickListener{
    private TextView biaoti;
    private ImageView fanhui;
    private String uid,token,isHandle="0";
    private SPUtils spUtils;
    private RecyclerView tousu_list;
    private List<PromoterTouSuEntity.DataBean> data;
    private PromoterTouSuAdapter promoterTouSuAdapter;

    @Override
    public int initContentView() {
        return R.layout.activity_promoter_tousu;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);

        tousu_list = findViewById(R.id.tousu_list);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();

        uid = bundle.getString("uuid");
        isHandle = bundle.getString("isHandle");
        spUtils = new SPUtils();

        token = (String) spUtils.get(this, "logintoken", "");

        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
//设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDownload();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                initDownload();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }



    @Override
    protected void initData() {
        biaoti.setText("活动投诉");
        initDownload();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.fanhui:
                finish();
                break;
        }
    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "推广员身份验证" + token+"CardName"+name.getText().toString()+"CardNumber"+sfz.getText().toString()+"PositiveUrl"+ZMurl+"BackUrl"+FMurl);
        LogU.Ld("1608", "投诉列表" + token+"=="+uid+"==="+isHandle);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getPromoterComplaintsList")
                .addHeader("token", token)
                .addParams("publicuuid", uid)
                .addParams("isHandle", isHandle)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "投诉列表" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                          PromoterTouSuEntity entity = gson.fromJson(result, PromoterTouSuEntity.class);

                            data = entity.getData();
                            tousu_list.setLayoutManager(new LinearLayoutManager(PromoterTouSuActivity.this));


                            promoterTouSuAdapter = new PromoterTouSuAdapter(R.layout.promoter_tousu_item,data);
                            /*DividerItemDecoration dec = new DividerItemDecoration(PromoterTouSuActivity.this,DividerItemDecoration.VERTICAL);
                            dec.setDrawable(new ColorDrawable(ContextCompat.getColor(PromoterTouSuActivity.this,R.color.cutting_line)));
                            tousu_list.addItemDecoration(dec);*/
                            tousu_list.setAdapter(promoterTouSuAdapter);


                            promoterTouSuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值
                                    switch (view.getId()) {

                                        case R.id.promo_ty://属实
                                            if (data.get(position).getType() == 1) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "1");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("tabCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            } else if (data.get(position).getType() == 2) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "1");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("re_com_id",data.get(position).getUuid());
                                                bundle.putString("refereeid", data.get(position).getRefereeimg().get(0).getRefereeid());
                                                bundle.putString("tabCP", "2");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            } else if (data.get(position).getType() == 3) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "1");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("tabCP", "3");
                                                intent.putExtras(bundle);
                                                startActivity(intent);

                                            }

                                            break;

                                        case R.id.promo_bty:// 不属实
                                            if (data.get(position).getType() == 1) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "0");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("tabCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 2) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "0");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("re_com_id",data.get(position).getUuid());
                                                bundle.putString("refereeid", data.get(position).getRefereeimg().get(0).getRefereeid());
                                                bundle.putString("tabCP", "2");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 3) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterSSActivity.class);
                                                bundle.putString("tab", "0");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("tabCP", "3");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }

                                            break;

                                        case R.id.promo_xs:// 协商历史
                                            if (data.get(position).getType() == 1) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("uuid", uid);
                                                bundle.putString("tagCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 2) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "2");
                                                bundle.putString("uuid", uid);
                                                bundle.putString("re_com_id", data.get(position).getUuid());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 3) {
                                                intent.setClass(PromoterTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "3");
                                                bundle.putString("uuid", uid);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                            break;
                                        case R.id.promo_xq_bofang:// 用户播放

                                            String luyinYH = data.get(position).getImgurl();
                                            RecordingItem recordingItem = new RecordingItem();
////                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
////                final String filePath = sharePreferences.getString("audio_path", "");
////                long elpased = sharePreferences.getLong("elpased", 0);

                                            MediaPlayer mediaPlayer = new MediaPlayer();
                                            try {
                                                mediaPlayer.setDataSource(getResources().getString(R.string.imgurl) + luyinYH);
                                                mediaPlayer.prepare();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                            long time = mediaPlayer.getDuration();
                                            LogU.Ld("1608", luyinYH + "------" + time);
                                            recordingItem.setFilePath(getResources().getString(R.string.imgurl) + luyinYH);
                                            recordingItem.setLength((int) time);
                                            PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
                                            fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());


                                            break;

                                        case R.id.promo_bofang:// 推广员播放

                                            String luyinTG = data.get(position).getYuyin();
                                            RecordingItem recordingItemTG = new RecordingItem();
////                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
////                final String filePath = sharePreferences.getString("audio_path", "");
////                long elpased = sharePreferences.getLong("elpased", 0);

                                            MediaPlayer mediaPlayerTG = new MediaPlayer();
                                            try {
                                                mediaPlayerTG.setDataSource(getResources().getString(R.string.imgurl) + luyinTG);
                                                mediaPlayerTG.prepare();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                            long timeTG = mediaPlayerTG.getDuration();
                                            LogU.Ld("1608", luyinTG+ "------" + timeTG);
                                            recordingItemTG.setFilePath(getResources().getString(R.string.imgurl) + luyinTG);
                                            recordingItemTG.setLength((int) timeTG);
                                            PlaybackDialogFragment fragmentPlayTG = PlaybackDialogFragment.newInstance(recordingItemTG);
                                            fragmentPlayTG.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());

                                            break;
                                    }
                                    promoterTouSuAdapter.notifyDataSetChanged();
                                }
                            });

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
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
