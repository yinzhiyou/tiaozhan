package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;

import java.util.List;

public class JCFBXiangmuAdapter extends BaseAdapter {
    private Context context;
    private List<YundongEntity.DataBean> list;
    public JCFBXiangmuAdapter (Context context,List<YundongEntity.DataBean> list){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.xiugai_aihao_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());

        return convertView;
    }

    public class ViewHolder {
        private TextView textView;
        private RelativeLayout mRelativeLayout;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.xiugai_aihao_text);
            mRelativeLayout = view.findViewById(R.id.xiugai_aihao_layout);
        }
    }
}
