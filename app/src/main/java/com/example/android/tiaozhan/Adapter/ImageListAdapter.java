package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.MyApplication;
import com.example.android.tiaozhan.R;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ImageListAdapter extends BaseQuickAdapter<List<String>,BaseViewHolder> {
    private List<String> data;
    private final int mCountLimit = 9;

    public ImageListAdapter(int layoutResId, @Nullable List<String> data) {

        super(layoutResId, Collections.singletonList(data));
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, List<String> item) {
        ImageView view = helper.getView(R.id.img);
        ImageView close = helper.getView(R.id.delimg);
        if (helper.getAdapterPosition() == getItemCount() - 1 && data.size() < mCountLimit) {
            view.setImageResource(R.mipmap.icon_shangchuan);

        } else {
            Glide.with(MyApplication.getContext().getApplicationContext()).load(new File(data.get(helper.getAdapterPosition()))).into(view);
        }
        helper.addOnClickListener(R.id.delimg);
    }

    @Override
    public int getItemCount() {
        // 满 9张图就不让其添加新图
        if (data != null && data.size() >= mCountLimit) {
            return mCountLimit;
        } else {
            return data == null ? 1 : data.size() + 1;
        }
    }
}
