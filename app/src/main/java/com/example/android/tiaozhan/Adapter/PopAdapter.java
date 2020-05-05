package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.bean.SportTwoEntity;

import java.util.List;

public class PopAdapter extends BaseQuickAdapter<SportTwoEntity.ParentBean, BaseViewHolder> {

    private int checkItemPosition =-1;
    public PopAdapter(int layoutResId, @Nullable List<SportTwoEntity.ParentBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SportTwoEntity.ParentBean item) {
        final TextView textView = helper.getView(R.id.item);
        RelativeLayout right_bg = helper.getView(R.id.right_bg);
        textView.setText(item.getParentName());
        final ImageView youjiantou = helper.getView(R.id.youjiantou);

        LogU.Ld("1608","选中神==="+checkItemPosition);
        LogU.Ld("1608","选中"+helper.getPosition()+"===="+helper.getAdapterPosition()+"=="+helper.getLayoutPosition()+"======"+helper.getOldPosition());
        if (helper.getAdapterPosition()==selectedPositon) {
            right_bg.setBackgroundResource(R.drawable.xiangm_bg);
            // textView.setTextColor(mContext.getResources().getColor(R.color.hongse));
            //  youjiantou.setImageResource(R.mipmap.home_right_yellow);
        } else {
            right_bg.setBackgroundResource(R.drawable.xiangmju_bg_bs);
            textView.setTextColor(mContext.getResources().getColor(R.color.huise));
            //  youjiantou.setImageResource(R.mipmap.home_right);

        }
    }


    public void setCheckItem(int position) {
        checkItemPosition = position;
        //  LogU.Ld("1608","选中"+helper.getPosition()+"===="+helper.getAdapterPosition()+"=="+helper.getLayoutPosition()+"======"+helper.getOldPosition());
        LogU.Ld("1608","选中级==="+checkItemPosition);
    }

    private int selectedPositon=-1;
    public void selectedItemPosition(int position) {
        this.selectedPositon = position;
    }


}
