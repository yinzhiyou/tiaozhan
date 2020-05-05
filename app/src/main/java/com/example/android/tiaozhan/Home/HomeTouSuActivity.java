package com.example.android.tiaozhan.Home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.tiaozhan.Adapter.MyComplaintListAdapter;
import com.example.android.tiaozhan.Adapter.OtherComplaintListAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyComplaintListEntity;
import com.example.android.tiaozhan.Promoter.PromoterLSActivity;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.RecycleViewDivider;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
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

public class HomeTouSuActivity extends BaseActivity implements View.OnClickListener {

    private TextView biaoti;
    private ImageView fanhui;
    private TextView wofabu, wobaoming;
    private View fb, bm;
    private String token, uuid;
    private SPUtils spUtils;
    private int type = 1;
    private RecyclerView tousu_list;
    private List<MyComplaintListEntity.DataBean> data;
    private List<MyComplaintListEntity.DataBean> dataOther;
    private MyComplaintListAdapter complaintListAdapter;
    private OtherComplaintListAdapter otherComplaintListAdapter;

    @Override
    public int initContentView() {
        return R.layout.activity_home_tousu;
    }

    @Override
    protected void initUIAndListener() {
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);

        wofabu = findViewById(R.id.my_hd_wofabu);
        wofabu.setOnClickListener(this);
        wobaoming = findViewById(R.id.my_hd_wobaoming);
        wobaoming.setOnClickListener(this);
        fb = findViewById(R.id.my_fabu);
        bm = findViewById(R.id.my_baoming);
        tousu_list = findViewById(R.id.tousu_list);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        uuid = bundle.getString("uuid");
        final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
//设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refereeResult();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refereeResult();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    protected void initData() {

        biaoti.setText("活动投诉");
        refereeResult();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();

                break;

            case R.id.my_hd_wofabu://我投诉的
                wofabu.setTextColor(getResources().getColor(R.color.huise));
                wobaoming.setTextColor(getResources().getColor(R.color.bbbbb));
                fb.setVisibility(View.VISIBLE);
                bm.setVisibility(View.INVISIBLE);

                type = 1;
                refereeResult();
                break;
            case R.id.my_hd_wobaoming://其他的
                wofabu.setTextColor(getResources().getColor(R.color.bbbbb));
                wobaoming.setTextColor(getResources().getColor(R.color.huise));
                bm.setVisibility(View.VISIBLE);
                fb.setVisibility(View.INVISIBLE);
                type = 2;
                refereeResult();
              //  refereeResultOther();
                break;

        }
    }


    //获取数据
    private void refereeResult() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "投诉列表" + token + "==" + type + "===" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyComplaintList")
                .addHeader("token", token)
                .addParams("type", type + "")
                .addParams("publicuuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUitl.longs("网络繁忙");
                        LogU.Ld("1608", "网络繁忙" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "投诉列表" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            final MyComplaintListEntity entity = gson.fromJson(result, MyComplaintListEntity.class);
                            data = entity.getData();
                            tousu_list.setLayoutManager(new LinearLayoutManager(HomeTouSuActivity.this));
                            complaintListAdapter = new MyComplaintListAdapter(R.layout.mycomplainlist_item, data);
                           // DividerItemDecoration dec = new DividerItemDecoration(HomeTouSuActivity.this,DividerItemDecoration.VERTICAL);

                          //  dec.setDrawable(ContextCompat.getDrawable(HomeTouSuActivity.this, R.color.cutting_line));
                            tousu_list.addItemDecoration(new RecycleViewDivider(
                                    HomeTouSuActivity.this, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.cutting_line)));

                           // tousu_list.addItemDecoration(dec);
                            tousu_list.setAdapter(complaintListAdapter);


                            complaintListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    switch (view.getId()) {

                                        case R.id.promo_hdxq_cxts://撤销投诉
                                            if (data.get(position).getType() == 1) {
                                                quxiaotousu();
                                                data.remove(position);

                                            } else if (data.get(position).getType() == 2) {
                                                quxiaotousuCP(data.get(position).getUuid());
                                                data.remove(position);
                                                //  complaintListAdapter.notifyDataSetChanged();
                                            } else if (data.get(position).getType() == 3) {

                                                quxiaotousuPL();
                                                data.remove(position);
                                            }
                                            break;

                                        case R.id.promo_xsls://用户协商历史
                                            if (data.get(position).getType() == 1) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("tagCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 2) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "2");
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("re_com_id", data.get(position).getUuid());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 3) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "3");
                                                bundle.putString("uuid", uuid);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                            break;

                                        case R.id.promo_ls://推广专员协商历史
                                            if (data.get(position).getType() == 1) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("tagCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 2) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "2");
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("re_com_id", data.get(position).getUuid());
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (data.get(position).getType() == 3) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "3");
                                                bundle.putString("uuid", uuid);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                            break;

                                        case R.id.promo_ty://同意
                                            if (data.get(position).getType() == 1) {
                                                tongyi();
                                            } else if (data.get(position).getType() == 2) {
                                                tongyiCP(data.get(position).getUuid());
                                            } else if (data.get(position).getType() == 3) {
                                                sparrComplainAgree();
                                            }

                                            break;

                                        case R.id.promo_bty:// 不同意
                                            if (data.get(position).getType() == 1) {
                                                ptjrInit();
                                            } else if (data.get(position).getType() == 2) {
                                                ptjrRefereeInit(data.get(position).getUuid());
                                            } else if (data.get(position).getType() == 3) {
                                                sparrPlatformIntervention();
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

                                        case R.id.promo_tg_bofang:// 推广员播放

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
                                    complaintListAdapter.notifyDataSetChanged();
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeTouSuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    //用户同意处理结果 场馆未预留
    private void tongyi() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/ComplainAgree")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "同意" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //同意处理结果 裁判
    private void tongyiCP(String re_com_id) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereeComplainAgree")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("re_com_id", re_com_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "同意" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //同意处理结果 陪练
    private void sparrComplainAgree() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "通用金币" + userShare);

        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/SparrComplainAgree")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "同意" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
//                            Gson gson = new Gson();
//                            MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);

                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(RenWuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RenWuActivity.this, DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //平台接入 不同意推广员~平台介入（场馆未预留场地）
    private void ptjrInit() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/PlatformIntervention")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

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

    //推广员~平台介入（裁判未到场）
    private void ptjrRefereeInit(String re_com_id) {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereePlatformIntervention")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("re_com_id", re_com_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

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


    //推广员~平台介入（裁判未到场）
    private void sparrPlatformIntervention() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/SparrPlatformIntervention")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

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

    //取消投诉 场馆未预留
    private void quxiaotousu() {
        LogU.Ld("1608", "取消投诉" + token + "==" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/CancellationOfcomplaints")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消投诉" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //取消投诉裁判未到
    private void quxiaotousuCP(String re_com_id) {
        LogU.Ld("1608", "取消投诉" + token + "====" + uuid+"=="+re_com_id);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/RefereeCancellationOfcomplaint")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .addParams("re_com_id", re_com_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消投诉" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //取消投诉陪练未到
    private void quxiaotousuPL() {
        LogU.Ld("1608", "取消投诉" + token + "====" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/SparrCancellationOfcomplaints")
                .addHeader("token", token)
                .addParams("publicUUID", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "取消投诉" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);


                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(HomeHDXQActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //获取数据
    private void refereeResultOther() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "投诉列表" + token + "==" + type + "===" + uuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyComplaintList")
                .addHeader("token", token)
                .addParams("type", type + "")
                .addParams("publicuuid", uuid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUitl.longs("网络繁忙");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "投诉列表" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            MyComplaintListEntity entity = gson.fromJson(result, MyComplaintListEntity.class);
                            dataOther = entity.getData();
                            tousu_list.setLayoutManager(new LinearLayoutManager(HomeTouSuActivity.this));

                            otherComplaintListAdapter = new OtherComplaintListAdapter(R.layout.othercomplainlist_item, dataOther);


                            tousu_list.addItemDecoration(new DividerItemDecoration(HomeTouSuActivity.this, DividerItemDecoration.VERTICAL));
                            tousu_list.setAdapter(otherComplaintListAdapter);


                            otherComplaintListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    switch (view.getId()) {

                                        case R.id.promo_xsls_other://用户协商历史
                                            if (dataOther.get(position).getType() == 1) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("uuid", uuid);
                                                bundle.putString("tagCP", "1");
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (dataOther.get(position).getType() == 2) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "2");
                                                bundle.putString("uuid", uuid);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            } else if (dataOther.get(position).getType() == 3) {
                                                intent.setClass(HomeTouSuActivity.this, PromoterLSActivity.class);
                                                bundle.putString("tagCP", "3");
                                                bundle.putString("uuid", uuid);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                            break;

                                    }
                                    otherComplaintListAdapter.notifyDataSetChanged();
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(HomeTouSuActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
