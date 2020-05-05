package com.example.android.tiaozhan.Adapter;

import android.media.MediaPlayer;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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

public class OtherComplaintListAdapter extends BaseQuickAdapter<MyComplaintListEntity.DataBean,BaseViewHolder> {
    public OtherComplaintListAdapter(int layoutResId, @Nullable List<MyComplaintListEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyComplaintListEntity.DataBean item) {
        ImageView touxiang = helper.getView(R.id.promo_hdxq_touxiang_other);
        TextView tousF = helper.getView(R.id.promo_hdxq_text1_other);
        TextView smXX = helper.getView(R.id.promo_hdxq_text2_other);
        TextView xqBF = helper.getView(R.id.promo_xq_bofang_other);
        TextView textHS = helper.getView(R.id.heshi_text_other);
        RelativeLayout cp_Rlayout = helper.getView(R.id.cp_Rlayout_other);
        ImageView hdxq_cp_touxiang = helper.getView(R.id.hdxq_cp_touxiang_other);
        TextView hdxq_cp_bh = helper.getView(R.id.hdxq_a_grid_no_other);

        TextView jielun = helper.getView(R.id.promo_jielun_other);
        TextView promoXSLS = helper.getView(R.id.promo_xsls_other);
        ImageView icon_wanc = helper.getView(R.id.icon_wanc_other);






        helper.setText(R.id.promo_hdxq_time_other,item.getAddTime());
        helper.setText(R.id.promo_hdxq_name_other,item.getNickname());
        helper.addOnClickListener(R.id.promo_xsls_other);



        String luyinYH = item.getImgurl();
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


            if (item.getStatus()==1||item.getStatus()==2||item.getStatus()==3){//1 等待推广员处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    helper.setText(R.id.promo_hdxq_text2_other, "详细说明：" + item.getComment());
                }else {
                    smXX.setText("详细说明："+"无");
                }
            }else if (item.getStatus()==4){//4 用户同意

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==5){//5 用户不同意（平台介入），等待平台处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==6){//6 平台已处理并属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }else if (item.getStatus()==7){//7 平台已处理并不属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }




        }else if (item.getType()==2){//2裁判未到场


            if (item.getStatus()==1||item.getStatus()==2||item.getStatus()==3){//1 等待推广员处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                cp_Rlayout.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getRefereeimg().get(helper.getAdapterPosition()).getRefereeimgurl()).into(hdxq_cp_touxiang);
                hdxq_cp_bh.setText(item.getRefereeimg().get(helper.getAdapterPosition()).getC());
                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
            } else if (item.getStatus()==4){//4 用户同意

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==5){//5 用户不同意（平台介入），等待平台处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==6){//6 平台已处理并属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }else if (item.getStatus()==7){//7 平台已处理并不属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }



        }else if (item.getType()==3){//3陪练未到场
            if (item.getStatus()==1||item.getStatus()==2||item.getStatus()==3){//1 等待推广员处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    helper.setText(R.id.promo_hdxq_text2_other, "详细说明：" + item.getComment());
                }else {
                    smXX.setText("详细说明："+"无");
                }
            }else if (item.getStatus()==4){//4 用户同意

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==5){//5 用户不同意（平台介入），等待平台处理

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.VISIBLE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"反馈：" +item.getEndcom());
                if (!EmptyUtils.isStrEmpty(item.getComment())) {
                    smXX.setText("详细说明：" + item.getComment());

                }else {
                    smXX.setText("详细说明："+"无");
                }
                jielun.setText("结论："+item.getConclu());
            }else if (item.getStatus()==6){//6 平台已处理并属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }else if (item.getStatus()==7){//7 平台已处理并不属实

                tousF.setVisibility(View.VISIBLE);
                smXX.setVisibility(View.GONE);
                textHS.setVisibility(View.GONE);
                jielun.setVisibility(View.VISIBLE);
                promoXSLS.setVisibility(View.VISIBLE);

                icon_wanc.setVisibility(View.VISIBLE);
                if (luyinYH.length() < 1) {
                    xqBF.setVisibility(View.GONE);
                } else {
                    xqBF.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl) +item.getPlayerimgurl()).into(touxiang);

                helper.setText(R.id.promo_hdxq_text1_other,"发起投诉：" +item.getComplainName());

                jielun.setText("系统平台："+item.getConclu());
            }





        }

    }

}
