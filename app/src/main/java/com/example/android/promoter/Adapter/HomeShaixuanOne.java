package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;

import java.util.List;

public class HomeShaixuanOne extends BaseAdapter {
    private Context context;
    private List<YundongEntity.DataBean> list;
    private String tag;
    public HomeShaixuanOne(Context context,List<YundongEntity.DataBean> list,String tag){
        this.context = context;
        this.list =  list;
        this.tag = tag;
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
        ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.home_shaixuan_one, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.textView.setText(list.get(position).getName());
        if (tag.equals("2")){
            viewHolder.imageView.setVisibility(View.GONE);
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.home_shaixuan_text);
            imageView = view.findViewById(R.id.home_shaixuan_image);
        }


    }
}
