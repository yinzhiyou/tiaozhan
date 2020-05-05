package com.example.android.tiaozhan.Adapter;

import android.media.MediaPlayer;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.MyComplaintListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.luyin.PlaybackDialogFragment;
import com.example.android.tiaozhan.Toos.luyin.RecordingItem;

import java.io.IOException;
import java.util.List;

public class MyComplaintListAdapter extends BaseQuickAdapter<MyComplaintListEntity.DataBean,BaseViewHolder> {
    public MyComplaintListAdapter(int layoutResId, @Nullable List<MyComplaintListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyComplaintListEntity.DataBean item) {

        ImageView touxiang = helper.getView(R.id.promo_hdxq_touxiang);
        TextView tousF = helper.getView(R.id.promo_hdxq_text1);
        TextView smXX = helper.getView(R.id.promo_hdxq_text2);
        TextView xqBF = helper.getView(R.id.promo_xq_bofang);
        TextView textHS = helper.getView(R.id.heshi_text);
        RelativeLayout cp_Rlayout = helper.getView(R.id.cp_Rlayout);
        ImageView hdxq_cp_touxiang = helper.getView(R.id.hdxq_cp_touxiang);
        TextView hdxq_cp_bh = helper.getView(R.id.hdxq_a_grid_no);
        TextView promo_hdxq_cxts = helper.getView(R.id.promo_hdxq_cxts);
        TextView jielun = helper.getView(R.id.promo_jielun);
        TextView promoXSLS = helper.getView(R.id.promo_xsls);
        ImageView icon_wanc = helper.getView(R.id.icon_wanc);
        LinearLayout promo_hdxq_anniu = helper.getView(R.id.promo_hdxq_anniu);
        //推广专员
        TextView promoFK = helper.getView(R.id.promo_text1);
        RelativeLayout pro_cp_layout = helper.getView(R.id.pro_cp_layout);
        TextView promoXX = helper.getView(R.id.promo_text2);
        TextView promoJL = helper.getView(R.id.promo_text4);
        TextView promoLS = helper.getView(R.id.promo_ls);
        TextView promoTY = helper.getView(R.id.promo_ty);
        TextView promoBTY = helper.getView(R.id.promo_bty);
        TextView promo_time = helper.getView(R.id.promo_time);
        TextView promo_tg_bofang = helper.getView(R.id.promo_tg_bofang);

        ImageView icon_wanc_p = helper.getView(R.id.icon_wanc_p);
        TextView hdxq_cp_tbh = helper.getView(R.id.hdxq_cp_bh);
        ImageView hdxq_touxiang = helper.getView(R.id.hdxq_touxiang);


        LinearLayout pro_refree = helper.getView(R.id.pro_refree_layout);
        LinearLayout top = helper.getView(R.id.top);
        helper.setText(R.id.promo_hdxq_time,item.getAddTime());
        helper.setText(R.id.promo_hdxq_name,item.getNickname());
        helper.addOnClickListener(R.id.promo_hdxq_cxts)
                .addOnClickListener(R.id.promo_xsls)
                .addOnClickListener(R.id.promo_ls)
                .addOnClickListener(R.id.promo_ty)
                .addOnClickListener(R.id.promo_bty)
                .addOnClickListener(R.id.promo_tg_bofang)
                .addOnClickListener(R.id.promo_xq_bofang);



        String luyinYH = item.getImgurl();
        String yuyin = item.getYuyin();
        RecordingItem recordingItem = new RecordingItem();
////                SharedPreferences sharePreferences = getSharedPreferences("sp_name_audio", MODE_PRIVATE);
////                final String filePath = sharePreferences.getString("audio_path", "");
////                long elpased = sharePreferences.getLong("elpased", 0);

        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mContext.getResources().getString(R.string.imgurl) + luyinYH);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


        long time = mediaPlayer.getDuration();
        LogU.Ld("1608", luyinYH + "------" + time);
        recordingItem.setFilePath(mContext.getResources().getString(R.string.imgurl) + luyinYH);
        recordingItem.setLength((int) time);
        PlaybackDialogFragment fragmentPlay = PlaybackDialogFragment.newInstance(recordingItem);
        //fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());

        //fragmentPlay.show(getSupportFragmentManager(), PlaybackDialogFragment.class.getSimpleName());


        if (item.getType()==1){//1场馆未预留场地


            if (item.getSigns().equals("1")){
                if (item.getStatus()==1){//1 等待推广员处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);

                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                }else if (item.getStatus()==2){//2 推广员处理并属实

                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promoFK.setText("反馈："+item.getEndcom());
                    promo_time.setText(item.getAddTime());
                    icon_wanc_p.setVisibility(View.VISIBLE);
                    promoTY.setVisibility(View.GONE);
                    promoBTY.setVisibility(View.GONE);
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }
                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==3){//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈："+item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }

                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==4){//4 用户同意
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"反馈：" +item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                    jielun.setText("结论："+item.getConclu());
                }else if (item.getStatus()==5){//5 用户不同意（平台介入），等待平台处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"反馈：" +item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                    jielun.setText("结论："+item.getConclu());
                }else if (item.getStatus()==6){//6 平台已处理并属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());

                    jielun.setText("系统平台："+item.getConclu());
                }else if (item.getStatus()==7){//7 平台已处理并不属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());

                    jielun.setText("系统平台："+item.getConclu());
                }
            }else {

                if (item.getStatus()==1){//1 等待推广员处理
                    promo_hdxq_anniu.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                }else if (item.getStatus()==2){// 推广属实 不属实
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈："+item.getEndcom());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    icon_wanc_p.setVisibility(View.VISIBLE);

                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }
                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==3){//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈："+item.getEndcom());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }

                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==4||item.getStatus()==5){//4 用户同意 不同意

                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (item.getStatus()==4){
                        icon_wanc.setVisibility(View.VISIBLE);
                    }
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"反馈：" +item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                    jielun.setText("结论："+item.getConclu());
                }else if (item.getStatus()==6||item.getStatus()==7){//6 平台已处理并属实
                    promo_hdxq_anniu.setVisibility(View.VISIBLE);
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());

                    jielun.setText("系统平台："+item.getConclu());
                }
            }





        }else if (item.getType()==2){//2裁判未到场


            if (item.getSigns().equals("1")) {
                if (item.getStatus() == 1) {//1 等待推广员处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    LogU.Ld("1608","位置"+helper.getLayoutPosition()+"=="+helper.getAdapterPosition()+"=="+helper.getOldPosition());
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());
                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                } else if (item.getStatus() == 2) {//2 推广员处理并属实

                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.VISIBLE);
                    icon_wanc_p.setVisibility(View.VISIBLE);
                    promoTY.setVisibility(View.GONE);
                    promoBTY.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_touxiang);
                    hdxq_cp_tbh.setText(item.getRefereeimg().get(0).getC());
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }
                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 3) {//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_touxiang);
                    hdxq_cp_tbh.setText(item.getRefereeimg().get(0).getC());
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }
                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 4) {//4 用户同意
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    icon_wanc.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 5) {//5 用户不同意（平台介入），等待平台处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 6) {//6 平台已处理并属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                } else if (item.getStatus() == 7) {//7 平台已处理并不属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                }
            }else {
                if (item.getStatus() == 1) {//1 等待推广员处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    LogU.Ld("1608","位置"+helper.getLayoutPosition()+"=="+helper.getAdapterPosition()+"=="+helper.getOldPosition());
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());
                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                } else if (item.getStatus() == 2) {//2 推广员处理并属实

                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.VISIBLE);
                    promo_time.setText(item.getAddTime());
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_touxiang);
                    hdxq_cp_tbh.setText(item.getRefereeimg().get(0).getC());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    icon_wanc_p.setVisibility(View.VISIBLE);

                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }
                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 3) {//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_touxiang);
                    hdxq_cp_tbh.setText(item.getRefereeimg().get(0).getC());
                    promo_time.setText(item.getAddTime());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }
                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 4) {//4 用户同意
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    icon_wanc.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 5) {//5 用户不同意（平台介入），等待平台处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 6) {//6 平台已处理并属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                } else if (item.getStatus() == 7) {//7 平台已处理并不属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    cp_Rlayout.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                    hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());

                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                }
            }


        }else if (item.getType()==3) {//3陪练未到场
            if (item.getSigns().equals("1")) {
                if (item.getStatus() == 1) {//1 等待推广员处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                } else if (item.getStatus() == 2) {//2 推广员处理并属实

                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    icon_wanc_p.setVisibility(View.VISIBLE);
                    promoTY.setVisibility(View.GONE);
                    promoBTY.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }
                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 3) {//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);

                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈：" + item.getEndcom());
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    } else {
                        promoXX.setText("详细说明：" + "无");
                    }

                    promoJL.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 4) {//4 用户同意
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 5) {//5 用户不同意（平台介入），等待平台处理
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "反馈：" + item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    } else {
                        smXX.setText("详细说明：" + "无");
                    }
                    jielun.setText("结论：" + item.getConclu());
                } else if (item.getStatus() == 6) {//6 平台已处理并属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                } else if (item.getStatus() == 7) {//7 平台已处理并不属实
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());

                    jielun.setText("系统平台：" + item.getConclu());
                }

            } else {

                if (item.getStatus()==1){//1 等待推广员处理
                    promo_hdxq_anniu.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    top.setVisibility(View.VISIBLE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                }else if (item.getStatus()==2){// 推广属实 不属实
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈："+item.getEndcom());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    icon_wanc_p.setVisibility(View.VISIBLE);

                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }
                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==3){//3 推广员处理并不属实，等待用户处理，
                    top.setVisibility(View.GONE);
                    pro_refree.setVisibility(View.VISIBLE);

                    tousF.setVisibility(View.VISIBLE);
                    jielun.setVisibility(View.VISIBLE);
                    pro_cp_layout.setVisibility(View.GONE);
                    promo_time.setText(item.getAddTime());
                    promoFK.setText("反馈："+item.getEndcom());
                    promoBTY.setVisibility(View.GONE);
                    promoTY.setVisibility(View.GONE);
                    if (yuyin.length() < 1) {
                        promo_tg_bofang.setVisibility(View.GONE);
                    } else {
                        promo_tg_bofang.setVisibility(View.VISIBLE);
                    }
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        promoXX.setText("详细说明：" + item.getComment());

                    }else {
                        promoXX.setText("详细说明："+"无");
                    }

                    promoJL.setText("结论："+item.getConclu());
                }else if (item.getStatus()==4||item.getStatus()==5){//4 用户同意 不同意

                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.VISIBLE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);

                    if (item.getStatus()==4){
                        icon_wanc.setVisibility(View.VISIBLE);
                    }
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"反馈：" +item.getEndcom());
                    if (!EmptyUtils.isStrEmpty(item.getComment())) {
                        smXX.setText("详细说明：" + item.getComment());

                    }else {
                        smXX.setText("详细说明："+"无");
                    }
                    jielun.setText("结论："+item.getConclu());
                }else if (item.getStatus()==6||item.getStatus()==7){//6 平台已处理并属实
                    promo_hdxq_anniu.setVisibility(View.VISIBLE);
                    top.setVisibility(View.VISIBLE);
                    pro_refree.setVisibility(View.GONE);
                    tousF.setVisibility(View.VISIBLE);
                    smXX.setVisibility(View.GONE);
                    textHS.setVisibility(View.GONE);
                    jielun.setVisibility(View.VISIBLE);
                    promoXSLS.setVisibility(View.VISIBLE);
                    promo_hdxq_cxts.setVisibility(View.GONE);
                    icon_wanc.setVisibility(View.VISIBLE);
                    if (luyinYH.length() < 1) {
                        xqBF.setVisibility(View.GONE);
                    } else {
                        xqBF.setVisibility(View.VISIBLE);
                    }
                    Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                    helper.setText(R.id.promo_hdxq_text1,"发起投诉：" +item.getComplainName());

                    jielun.setText("系统平台："+item.getConclu());
                }
            }
        }

    }
}
