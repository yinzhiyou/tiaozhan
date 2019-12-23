package com.example.android.promoter.Adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.promoter.Entity.YundongTwoEntity;
import com.example.android.promoter.R;

import java.util.List;

public class PopAdapterTwo extends BaseQuickAdapter<YundongTwoEntity.DataBean,BaseViewHolder> {
    public PopAdapterTwo(int layoutResId, @Nullable List<YundongTwoEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YundongTwoEntity.DataBean item) {
        TextView textView = helper.getView(R.id.item);
        textView.setText(item.getName());
    }
}
