package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.tiaozhan.R;

import java.util.List;

public class DuiShouPaihangAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int c = 1;

    public DuiShouPaihangAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setSelectItem(int c) {

        this.c = c;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DuiShouPaihangAdapter.ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.jishu_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DuiShouPaihangAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
//        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemDeleteListener.onDeleteClick(position);
//            }
//        });


        if (c == position) {
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.hongse));
            viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.hongse));
//            textc.setTextColor(Color.parseColor("#ff0000"));

        } else {
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.bbbbb));
            viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
//            textc.setTextColor(Color.parseColor("#393939"));
        }


        return convertView;
    }

    public class ViewHolder {
        private TextView textView, imageView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.jishu_name);
            imageView = view.findViewById(R.id.jishu_text);

        }
    }
}