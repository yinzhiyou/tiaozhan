package com.example.android.promoter.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;

import java.util.List;

public class PopAdapter extends BaseQuickAdapter<YundongEntity.DataBean, BaseViewHolder> {

    private int checkItemPosition =-1;
    public PopAdapter(int layoutResId, @Nullable List<YundongEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, YundongEntity.DataBean item) {
        final TextView textView = helper.getView(R.id.item);
        textView.setText(item.getName());
        final ImageView youjiantou = helper.getView(R.id.youjiantou);

        LogU.Ld("1608","选中神==="+checkItemPosition);
            LogU.Ld("1608","选中"+helper.getPosition()+"===="+helper.getAdapterPosition()+"=="+helper.getLayoutPosition()+"======"+helper.getOldPosition());
            if (item.isSelect()) {
                textView.setTextColor(mContext.getResources().getColor(R.color.hongse));
                youjiantou.setImageResource(R.mipmap.home_right_yellow);
            } else {
                textView.setTextColor(mContext.getResources().getColor(R.color.huise));
                youjiantou.setImageResource(R.mipmap.home_right);

        }


    }
    public void setCheckItem(int position) {
        checkItemPosition = position;
        //  LogU.Ld("1608","选中"+helper.getPosition()+"===="+helper.getAdapterPosition()+"=="+helper.getLayoutPosition()+"======"+helper.getOldPosition());
        LogU.Ld("1608","选中级==="+checkItemPosition);
    }
}
