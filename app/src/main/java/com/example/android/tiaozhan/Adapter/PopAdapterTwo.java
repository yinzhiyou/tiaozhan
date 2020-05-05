package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.bean.SportTwoEntity;

import java.util.ArrayList;

public class PopAdapterTwo extends BaseQuickAdapter<SportTwoEntity.ParentBean.ChildBean,BaseViewHolder> {
    public PopAdapterTwo(int layoutResId, @Nullable ArrayList<SportTwoEntity.ParentBean.ChildBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SportTwoEntity.ParentBean.ChildBean item) {
        TextView textView = helper.getView(R.id.item);
        textView.setText(item.getName());
        /*if (item.getId()==19){
            getData().remove(helper.getAdapterPosition());
        }*/
    }
}
