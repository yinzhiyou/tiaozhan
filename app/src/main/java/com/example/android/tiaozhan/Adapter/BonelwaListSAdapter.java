package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class BonelwaListSAdapter extends BaseQuickAdapter<HDXQEntity.DataBean.AloseBuserInfoBean,BaseViewHolder> {
    public BonelwaListSAdapter(int layoutResId, @Nullable List<HDXQEntity.DataBean.AloseBuserInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HDXQEntity.DataBean.AloseBuserInfoBean item) {
        helper.setText(R.id.bonelwa_name,item.getNickname());
        helper.setText(R.id.hdxq_bh,item.getTeam());
        Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl)+item.getImgURL()).into((ImageView) helper.getView(R.id.bonelwa_touxiang));

    }
}
