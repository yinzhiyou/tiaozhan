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
import com.example.android.tiaozhan.Entity.PromoterTouSuEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.luyin.PlaybackDialogFragment;
import com.example.android.tiaozhan.Toos.luyin.RecordingItem;

import java.io.IOException;
import java.util.List;

public class PromoterTouSuAdapter extends BaseQuickAdapter<PromoterTouSuEntity.DataBean, BaseViewHolder> {
    public PromoterTouSuAdapter(int layoutResId, @Nullable List<PromoterTouSuEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PromoterTouSuEntity.DataBean item) {

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

        ImageView icon_wanc = helper.getView(R.id.icon_wanc);


        //推广专员
        TextView promoFK = helper.getView(R.id.promo_text1);
        RelativeLayout pro_cp_layout = helper.getView(R.id.pro_cp_layout);
        TextView promoXX = helper.getView(R.id.promo_text2);
        TextView promoJL = helper.getView(R.id.promo_text4);
        TextView promoTM = helper.getView(R.id.promo_time);
        TextView promoTY = helper.getView(R.id.promo_ty);
        TextView promoBTY = helper.getView(R.id.promo_bty);
        TextView promo_xq_bofang = helper.getView(R.id.promo_xq_bofang);
        TextView promo_tg_bofang = helper.getView(R.id.promo_bofang);

        LinearLayout pro_refree = helper.getView(R.id.pro_refree_layout);
        LinearLayout top = helper.getView(R.id.top);
        LinearLayout promo_anniu = helper.getView(R.id.promo_anniu);
        TextView promoXSLS = helper.getView(R.id.promo_xs);
        TextView hdxq_cp_tbh = helper.getView(R.id.hdxq_cp_bh);
        ImageView hdxq_touxiang = helper.getView(R.id.hdxq_touxiang);

        helper.addOnClickListener(R.id.promo_ty)
                .addOnClickListener(R.id.promo_xs)
                .addOnClickListener(R.id.promo_xq_bofang)
                .addOnClickListener(R.id.promo_bofang)
                .addOnClickListener(R.id.promo_bty);
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

        if (item.getType() == 1) {//1场馆未预留场地

        LogU.Ld("1608","场馆未预留场地"+item.getIsHandle());
            if (item.getIsHandle() == 0) {//0 等待推广员处理
                top.setVisibility(View.VISIBLE);
                pro_refree.setVisibility(View.GONE);

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                promo_anniu.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                helper.setText(R.id.promo_hdxq_time, item.getAddTime());
                helper.setText(R.id.promo_hdxq_name, item.getNickname());
                helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                } else {
                    smXX.setText("详细说明：" + "无");
                }

            } else if (item.getIsHandle() == 1) {//1：已处理


            } else if (item.getIsHandle() == 2) {//2：处理中

                top.setVisibility(View.GONE);
                pro_refree.setVisibility(View.VISIBLE);
                tousF.setVisibility(View.VISIBLE);
                jielun.setVisibility(View.VISIBLE);
                pro_cp_layout.setVisibility(View.GONE);
                promoTM.setText(item.getAddTime());
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

            } else if (item.getIsHandle() == 3) {//3：已取消


            }


        } else if (item.getType() == 2) {//2裁判未到场
            LogU.Ld("1608","裁判未到场"+item.getIsHandle());
            if (item.getIsHandle() == 0) {//0 等待推广员处理
                top.setVisibility(View.VISIBLE);
                pro_refree.setVisibility(View.GONE);
                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                promo_anniu.setVisibility(View.VISIBLE);
                cp_Rlayout.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                helper.setText(R.id.promo_hdxq_time, item.getAddTime());
                helper.setText(R.id.promo_hdxq_name, item.getNickname());
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_cp_touxiang);
                hdxq_cp_bh.setText(item.getRefereeimg().get(0).getC());
                helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                } else {
                    smXX.setText("详细说明：" + "无");
                }

            } else if (item.getIsHandle() == 1) {//1：已处理


            } else if (item.getIsHandle() == 2) {//2：处理中

                top.setVisibility(View.GONE);
                pro_refree.setVisibility(View.VISIBLE);
                tousF.setVisibility(View.VISIBLE);
                jielun.setVisibility(View.VISIBLE);
                pro_cp_layout.setVisibility(View.VISIBLE);
                promoTM.setText(item.getAddTime());
                promoFK.setText("反馈：" + item.getEndcom());
                cp_Rlayout.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getRefereeimg().get(0).getRefereeimgurl()).into(hdxq_touxiang);
                hdxq_cp_tbh.setText(item.getRefereeimg().get(0).getC());
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

            } else if (item.getIsHandle() == 3) {//3：已取消


            }


        } else if (item.getType() == 3) {//3陪练未到场
            if (item.getIsHandle() == 0) {//0 等待推广员处理
                top.setVisibility(View.VISIBLE);
                pro_refree.setVisibility(View.GONE);

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                promo_anniu.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) + item.getPlayerimgurl()).into(touxiang);
                helper.setText(R.id.promo_hdxq_time, item.getAddTime());
                helper.setText(R.id.promo_hdxq_name, item.getNickname());
                helper.setText(R.id.promo_hdxq_text1, "发起投诉：" + item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    helper.setText(R.id.promo_hdxq_text2, "详细说明：" + item.getComment());
                } else {
                    smXX.setText("详细说明：" + "无");
                }

            } else if (item.getIsHandle() == 1) {//1：已处理


            } else if (item.getIsHandle() == 2) {//2：处理中

                top.setVisibility(View.GONE);
                pro_refree.setVisibility(View.VISIBLE);
                tousF.setVisibility(View.VISIBLE);
                jielun.setVisibility(View.VISIBLE);
                pro_cp_layout.setVisibility(View.GONE);
                promoTM.setText(item.getAddTime());
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

            } else if (item.getIsHandle() == 3) {//3：已取消


            }

        }
    }


}
