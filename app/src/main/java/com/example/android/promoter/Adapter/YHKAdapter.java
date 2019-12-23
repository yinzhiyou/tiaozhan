package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.YHKEntity;
import com.example.android.promoter.R;

import java.util.List;

public class YHKAdapter extends BaseAdapter{

    private Context context;
    private List<YHKEntity.DataBean> list;
    int c ;
    public  YHKAdapter (Context context,List<YHKEntity.DataBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setSelectItem(int c) {

        this.c = c;

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

            convertView = LayoutInflater.from(context).inflate(R.layout.yhk_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String substr = list.get(position).getBankCardNum();
        String subst2r=substr.substring(substr.length()-4,substr.length());
        viewHolder.textView.setText(list.get(position).getBankName()+"("+subst2r+")");

        if (c == position){
            viewHolder.dian.setBackgroundResource(R.mipmap.hongdian);
        }else{
            viewHolder.dian.setBackgroundResource(R.mipmap.huidian);
        }


        return convertView;
    }


    public class ViewHolder {
        private TextView textView;
        private ImageView dian;
        public ViewHolder(View view) {

            textView = view.findViewById(R.id.yhk_text);
            dian = view.findViewById(R.id.yhk_dian);
        }


    }
}

