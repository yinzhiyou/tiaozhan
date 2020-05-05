package com.example.android.tiaozhan.Toos.cityselectordemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.cityselectordemo.entity.City;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjian on 2017/5/15.
 */

public class ResultListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<City> results = new ArrayList<City>();

    public ResultListAdapter(Context context, ArrayList<City> results) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_city, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.city);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(results.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
