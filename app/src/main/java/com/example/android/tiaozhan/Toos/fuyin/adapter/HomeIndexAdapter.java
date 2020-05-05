package com.example.android.tiaozhan.Toos.fuyin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.fuyin.base.BaseAdapter;
import com.example.android.tiaozhan.Toos.fuyin.base.BaseHolder;
import com.example.android.tiaozhan.Toos.fuyin.holder.MomentHolder;
import com.example.android.tiaozhan.Toos.fuyin.interfaces.OnItemPictureClickListener;
import com.example.android.tiaozhan.Toos.fuyin.model.Girl;

import java.util.List;


/**
 * Description
 * Created by Administrator
 * Time 2018/1/2  22:00
 */

public class HomeIndexAdapter extends BaseAdapter<Girl> {

    private List<Girl> list;
    private Context context;
    private final int ITEM_TEXT=0;
    private final int ITEM_EMPTY=1;
    private OnItemPictureClickListener listener;
    public HomeIndexAdapter(Context context, List<Girl> list, OnItemPictureClickListener listener) {
        super(context, list);
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getHolderType(int position) {
        return ITEM_TEXT;
    }



    @Override
    public BaseHolder createBaseHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = null;
        switch (viewType){
            case ITEM_TEXT:
                holder = new MomentHolder(LayoutInflater.from(context).inflate(R.layout.item_wechat_moment,parent,false),listener);
                break;
        }
        return holder;
    }


}
