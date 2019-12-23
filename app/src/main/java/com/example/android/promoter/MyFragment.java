package com.example.android.promoter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Denglu.GRXXActivity;
import com.example.android.promoter.Entity.DengjiEntity;
import com.example.android.promoter.Entity.JCXIEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyTYJBEntity;
import com.example.android.promoter.Home.CGGZActivity;
import com.example.android.promoter.Home.HomeGRTXActivity;
import com.example.android.promoter.Home.XiaoxiActivity;
import com.example.android.promoter.My.About.AboutActivity;
import com.example.android.promoter.My.HelpActivity;
import com.example.android.promoter.My.MyGuanzhuActivity;
import com.example.android.promoter.My.MyHaoyouActivity;
import com.example.android.promoter.My.MyJinbiActivity;
import com.example.android.promoter.My.MyJingcaiActivity;
import com.example.android.promoter.My.MyOpponentPaiHangActivity;
import com.example.android.promoter.My.MyPaihang;
import com.example.android.promoter.My.MyQianbaoActivity;
import com.example.android.promoter.My.MyDSBActivity;
import com.example.android.promoter.My.Setup.MyShezhiActivity;
import com.example.android.promoter.My.MyhuodongActivity;
import com.example.android.promoter.My.YJFKActivity;
import com.example.android.promoter.Promoter.MyPromoterActivity;
import com.example.android.promoter.Toos.BaseFragment;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;

import okhttp3.Call;

/**
 * 个人中心
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout yinying, shezhi, huodong, jinbi, qianbao, haoyou, jingcai, guanzhu, guanyu, bangzhu,yijian,geren,xzcg,tongyong,paihang,my_jishufen;
    private String token,touxiang,nickname,uuid;
    private SPUtils spUtils;
    private TextView tyjb,textNickname;
    private ImageView touxiangImage,qiulei,huodongImage,xiaoxi,weidu;
    private TextView dengji;
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_fragment, container, false);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtil.setStatusBarColor(getActivity(), R.color.colorTransparent);
        }*/



        yinying = rootView.findViewById(R.id.my_yinying);
        shezhi = rootView.findViewById(R.id.my_shezhi);
        shezhi.setOnClickListener(this);
        huodong = rootView.findViewById(R.id.my_huodong);
        huodong.setOnClickListener(this);
        jinbi = rootView.findViewById(R.id.my_jinbi);
        jinbi.setOnClickListener(this);
        qianbao = rootView.findViewById(R.id.my_qianbao);
        qianbao.setOnClickListener(this);
        haoyou = rootView.findViewById(R.id.my_haoyou);
        haoyou.setOnClickListener(this);
        jingcai = rootView.findViewById(R.id.my_jingcai);
        jingcai.setOnClickListener(this);
        guanzhu = rootView.findViewById(R.id.my_guanzhu);
        guanzhu.setOnClickListener(this);
        guanyu = rootView.findViewById(R.id.my_guanyu);
        guanyu.setOnClickListener(this);
        bangzhu = rootView.findViewById(R.id.my_bangzhu);
        bangzhu.setOnClickListener(this);
        yijian = rootView.findViewById(R.id.my_yijian);
        yijian.setOnClickListener(this);
        geren = rootView.findViewById(R.id.my_geren);
        geren.setOnClickListener(this);
        tyjb = rootView.findViewById(R.id.my_tyjb);
        touxiangImage = rootView.findViewById(R.id.my_touxiang);
        touxiangImage.setOnClickListener(this);
        textNickname = rootView.findViewById(R.id.my_nickname);
        qiulei = rootView.findViewById(R.id.my_qiulei);
        dengji = rootView.findViewById(R.id.my_dengji);
        xzcg = rootView.findViewById(R.id.my_xinzengCG);
        xzcg.setOnClickListener(this);
        tongyong = rootView.findViewById(R.id.my_duishoupaihang);
        tongyong.setOnClickListener(this);
        paihang = rootView.findViewById(R.id.my_paihang);
        paihang.setOnClickListener(this);
        huodongImage = rootView.findViewById(R.id.my_huodong_Image);
        huodongImage.setOnClickListener(this);
        xiaoxi = rootView.findViewById(R.id.my_xiaoxi);
        xiaoxi.setOnClickListener(this);
        xiaoxi.setVisibility(View.GONE);
        weidu = rootView.findViewById(R.id.my_xiaoxi_weidu);
        weidu.setVisibility(View.GONE);

        my_jishufen= rootView.findViewById(R.id.my_jishufen);
        my_jishufen.setOnClickListener(this);
        //设置阴影
//        yinying.setOutlineProvider(new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
////                outline.setRect(20,20,view.getWidth()-5,view.getHeight());
//                outline.setRoundRect(0, 0, view.getWidth() , view.getHeight() , 20);
////                outline.setRect(10,10,10,10);
//
//            }
//        });
        spUtils = new SPUtils();
        token = (String) spUtils.get(getContext(), "logintoken", " ");
        touxiang = (String) spUtils.get(getContext(), "touxiang", "无");
        nickname = (String) spUtils.get(getContext(), "nickname", " ");
        uuid= (String) spUtils.get(getContext(), "uuid", " ");
        Glide.with(getContext()).load(getResources().getString(R.string.imgurl)+touxiang).into(touxiangImage);
        textNickname.setText(nickname);
        isPrepared = true;
        setlazyLoad();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.my_shezhi:
                intent.setClass(getContext(), MyShezhiActivity.class);
                startActivity(intent);
                break;
            case R.id.my_huodong:

                intent.setClass(getContext(), MyhuodongActivity.class);
                startActivity(intent);
                break;
            case R.id.my_jinbi://我的对手币

                intent.setClass(getContext(), MyDSBActivity.class);
                startActivity(intent);

                break;
            case R.id.my_qianbao:

                intent.setClass(getContext(), MyQianbaoActivity.class);
                startActivity(intent);
                break;
            case R.id.my_haoyou:

                intent.setClass(getContext(), MyHaoyouActivity.class);
                startActivity(intent);
                break;
            case R.id.my_jingcai:

                intent.setClass(getContext(), MyJingcaiActivity.class);
                startActivity(intent);
                break;
            case R.id.my_guanzhu:

                intent.setClass(getContext(), MyGuanzhuActivity.class);
                startActivity(intent);
                break;
            case R.id.my_guanyu:

                intent.setClass(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.my_bangzhu:

                intent.setClass(getContext(), HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.my_yijian:

                intent.setClass(getContext(), YJFKActivity.class);
                startActivity(intent);
                break;
            case R.id.my_geren:

                intent.setClass(getContext(), GRXXActivity.class);
                bundle.putString("tab","2");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_touxiang:

                intent.setClass(getContext(), HomeGRTXActivity.class);
                bundle.putString("uid",uuid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_xinzengCG:

                intent.setClass(getContext(), CGGZActivity.class);
                bundle.putString("tag","1");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.my_duishoupaihang://对手币排行榜

                intent.setClass(getContext(), MyOpponentPaiHangActivity.class);
                startActivity(intent);
                break;
            case R.id.my_paihang://技术分排行

                intent.setClass(getContext(), MyPaihang.class);
                startActivity(intent);
                break;
            case R.id.my_jishufen://我的技术分

                intent.setClass(getContext(), MyJinbiActivity.class);
                startActivity(intent);
                break;
            case R.id.my_huodong_Image://筛选 球类全部
                intent.setClass(getContext(), MyhuodongActivity.class);
                startActivity(intent);
                break;
            case R.id.my_xiaoxi://筛选 球类全部
                intent.setClass(getContext(), XiaoxiActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "通用金币" +"--"+ token+"--");
            OkHttpUtils
                    .get()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCommonCoins")
                    .addHeader("token",token)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogU.Ld("1608", "通用金币" + e);
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();
                            LogU.Ld("1608", "通用金币" + result);
                            Boolean a = result.indexOf("2000") != -1;
                            if (a) {
                                Gson gson = new Gson();
                                MyTYJBEntity entity = gson.fromJson(result, MyTYJBEntity.class);
                                DecimalFormat df = new DecimalFormat("0.00");

                                tyjb.setText(  df.format(entity.getData().getCoins())+"");
                                dengji();
                            } else {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                                if (entity.getMsg().equals("登录超时")){
                                    Intent intent = new Intent();
                                    intent.setClass(getContext(),DengluActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    });

    }

    private void dengji() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "最高等级" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getHighestLevel")
                .addHeader("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "最高等级" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            DengjiEntity entity = gson.fromJson(result, DengjiEntity.class);
                            dengji.setText(entity.getData().getLevel());
                            if (entity.getData().getId() == 1){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.yumaoqiu));
                            }else  if (entity.getData().getId() == 2){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.pingpangqiu));
                            }else if (entity.getData().getId() == 3){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.taiqiu));
                            }else if (entity.getData().getId() == 4){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.lanqiu));
                            }else if (entity.getData().getId() == 5){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zuqiu));
                            }else if (entity.getData().getId() == 6){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.paiqiu));
                            }else if (entity.getData().getId() == 7){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.wangqiu));
                            }else if (entity.getData().getId() == 8){
                                qiulei.setBackgroundDrawable(getResources().getDrawable(R.mipmap.gaoerfu));
                            }

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    //检测消息
    private void jiancexiaoxi() {
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getNotReadMessageCount")
                .addHeader("token",token)
                .addParams("msgCate","systems " )
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检测消息" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JCXIEntity entity = gson.fromJson(result, JCXIEntity.class);
                            if (entity.getData().getNotReadCount()>0){
                                weidu.setVisibility(View.VISIBLE);

                            }else {
                                weidu.setVisibility(View.GONE);
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

                        }
                    }
                });
    }
    /**
     * 取消碎片预加载，加载数据的方法，只要保证isPrepared和isVisible都为true的时候才往下执行开始加载数据
     */
    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if(!isPrepared || !isVisible) {
            return;
        }
        //TODO 此处填充view中各个控件的数据
        initDownload();
    }

    @Override
    public void onResume() {

        touxiang = (String) spUtils.get(getContext(), "touxiang", "无");
        Glide.with(getContext()).load(getResources().getString(R.string.imgurl)+touxiang).into(touxiangImage);
        nickname = (String) spUtils.get(getContext(), "nickname", " ");
        textNickname.setText(nickname);
        super.onResume();
    }

}
