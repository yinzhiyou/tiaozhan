package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.R;

public class XiaoxiLTAdapter extends BaseAdapter {
    Context context;
    public XiaoxiLTAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.xiaoxi_lt_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了删除", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了item", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    public class ViewHolder {
        private TextView btnDelete;
        private LinearLayout layout;
        public ViewHolder(View view) {

            btnDelete = view.findViewById(R.id.lt_btnDelete);
            layout = view.findViewById(R.id.xiaoxi_lt_layout);
        }


    }
}
