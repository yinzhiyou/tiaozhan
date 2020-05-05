package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.RefereeSelectEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class RefereeSelecrAdapter extends BaseQuickAdapter<RefereeSelectEntity.DataBean,BaseViewHolder> {
    public RefereeSelecrAdapter(int layoutResId, @Nullable List<RefereeSelectEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder help, RefereeSelectEntity.DataBean item) {
        help.setText(R.id.sport_name,item.getSport());
        if (item.getStatus()==0){
            help.setText(R.id.srats_dj,"待审核");
        }else if (item.getStatus()==1){
            help.setText(R.id.srats_dj,"已通过");
        }else if (item.getStatus()==2){
            help.setText(R.id.srats_dj,"未通过");
        }


    }
}
