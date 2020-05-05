package com.example.android.tiaozhan.My.referee;

import android.content.Intent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.android.tiaozhan.Adapter.RefereeSelecrAdapter;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.RefereeSelectEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class RefereePerfectXXActivity extends BaseActivity implements View.OnClickListener {

    private ImageView fanhui;
    private TextView biaoti;
    private RelativeLayout add_dj;
    private RecyclerView recycler_view;
    private RefereeSelecrAdapter mReferree;
    private String token;
    private int page = 1;
    private List<RefereeSelectEntity.DataBean> data;

    @Override
    public int initContentView() {
        return R.layout.activity_referee_perfect_xx;
    }

    @Override
    protected void initUIAndListener() {
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        biaoti = findViewById(R.id.biaoti);
        add_dj = findViewById(R.id.add_dj);
        add_dj.setOnClickListener(this);

        SPUtils spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        recycler_view = findViewById(R.id.recycler_view);


        //  mReferree.setLoadMoreView(new CustomLoadMoreView());

    }

    @Override
    protected void initData() {
        biaoti.setText("完善裁判信息");

        refereeAdd();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.add_dj:

                intent.setClass(RefereePerfectXXActivity.this, RefereeXmLvActivity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        refereeAdd();
    }

    //平台接入
    private void refereeAdd() {
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/selecrReferee")
                .addHeader("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, final int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "平台接入" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            RefereeSelectEntity entity = gson.fromJson(result, RefereeSelectEntity.class);
                            data = entity.getData();

                            recycler_view.setLayoutManager(new LinearLayoutManager(RefereePerfectXXActivity.this));
                            recycler_view.addItemDecoration(new DividerItemDecoration(RefereePerfectXXActivity.this, DividerItemDecoration.VERTICAL));
                            mReferree = new RefereeSelecrAdapter(R.layout.referee_select_item, data);
                            recycler_view.setAdapter(mReferree);
                            mReferree.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra("sport_name", data.get(position).getSport());
                                    intent.putExtra("image", data.get(position).getCertificate_img());
                                    intent.putExtra("certificate", data.get(position).getCertificate());
                                    intent.putExtra("reason", data.get(position).getReason());
                                    intent.putExtra("grade", data.get(position).getGrade());
                                    intent.putExtra("id", data.get(position).getRefereeid());
                                    intent.putExtra("sport_id", data.get(position).getSportid()+"");
                                    intent.setClass(RefereePerfectXXActivity.this, RefereeUpdateActivity.class);
                                    startActivity(intent);
                                }
                            });

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
