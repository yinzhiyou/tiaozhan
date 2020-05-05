package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.FQTZXiangmuEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;

import java.util.List;

public class FQTZXiangmuGridAdapter extends BaseAdapter{
    private Context context;
    private SPUtils spUtils;
    private String zId;
    private List<FQTZXiangmuEntity.DataBean.SportTypeBean> list;
    public FQTZXiangmuGridAdapter(Context context,List<FQTZXiangmuEntity.DataBean.SportTypeBean> list){
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
        spUtils = new SPUtils();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_huoqu_sport, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        zId = (String) spUtils.get(context, "zId", "");
        LogU.Ld("1609","传值"+zId+list.get(position).getId()+list.size());
        viewHolder.textView.setText(list.get(position).getName());
        if (zId.equals("0")){
            if (list.get(position).getId()==19){

                viewHolder.mRelativeLayout.setVisibility(View.GONE);
                list.remove(position).getId();
                spUtils.remove(context,"zId");
            }
        }else {
            viewHolder.mRelativeLayout.setVisibility(View.VISIBLE);
        }


        viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.mos_bg);
        viewHolder.textView.setTextColor(context.getResources().getColor(R.color.huise));
        return convertView;
    }


    public class ViewHolder {
        private TextView textView;
        private RelativeLayout mRelativeLayout;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.hq_sport_text);
            mRelativeLayout = view.findViewById(R.id.hq_sport_layout);
        }
    }
}