package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;

import java.util.List;

public class GrenAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onChildClickListen(int position);
    }

    public GrenAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 9) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gren, null);
            holder = new ViewHolder();
            holder.mImg = (ImageView) convertView.findViewById(R.id.img);
            holder.mDel = (ImageView) convertView.findViewById(R.id.delimg);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).into(holder.mImg);
            holder.mDel.setVisibility(View.VISIBLE);
            holder.mImg.setVisibility(View.VISIBLE);
        } else {
            Glide.with(mContext).load(R.mipmap.icon_shangchuan).into(holder.mImg);
            holder.mDel.setVisibility(View.GONE);
            // holder.mImg.setImageResource(R.drawable.after2);//最后一个显示加号图片
        }

        holder.mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (onClickListener!=null) {
                    onClickListener.onChildClickListen(position);
                }
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView mImg;
        ImageView mDel;
    }
}
