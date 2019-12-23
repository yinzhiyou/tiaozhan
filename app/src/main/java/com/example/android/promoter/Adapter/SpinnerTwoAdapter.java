package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.promoter.Entity.AQWentiEnditu;
import com.example.android.promoter.R;

import java.util.List;

public class SpinnerTwoAdapter extends BaseAdapter {
    private List<AQWentiEnditu.DataBean.TwoQuestionBean> list;
    private Context mContext;

    public SpinnerTwoAdapter(Context pContext, List<AQWentiEnditu.DataBean.TwoQuestionBean> list) {
        this.mContext = pContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.spinner_item, null);
        if(convertView!=null)
        {
            TextView _TextView1=(TextView)convertView.findViewById(R.id.spinner_text);

            _TextView1.setText(list.get(position).getQuestion());
        }
        return convertView;
    }
}
