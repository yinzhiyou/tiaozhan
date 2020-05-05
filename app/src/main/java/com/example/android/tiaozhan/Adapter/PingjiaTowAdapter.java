package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.PingjiaTowEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;

import java.text.DecimalFormat;
import java.util.List;

public class PingjiaTowAdapter extends BaseAdapter {
    private Context context;
    private List<PingjiaTowEntity.DataBean> list;
    private SPUtileFQTZ spUtileFQTZ ;
    private String tag = "pingjia";
    public PingjiaTowAdapter(Context context,List<PingjiaTowEntity.DataBean> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        spUtileFQTZ = new SPUtileFQTZ();
        DecimalFormat df = new DecimalFormat("#.00");
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pingjia_two_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        tag = "pingjia";
        tag = tag+position;
        spUtileFQTZ.put(context, tag,list.get(position).getLabel().get(0).getId()+"");
        viewHolder.duiwu.setText(list.get(position).getTeam());
        viewHolder.name.setText(list.get(position).getNickname());
        viewHolder.jieguo.setText("填写的比赛结果:"+list.get(position).getResult());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.yes.setText(list.get(position).getLabel().get(0).getName());
        viewHolder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "pingjia";
                tag = tag+position;
                spUtileFQTZ.put(context, tag,list.get(position).getLabel().get(0).getId()+"");
                LogU.Ld("1608", "拼接------" + tag+"-----"+list.get(position).getLabel().get(0).getId());
                viewHolder.yes.setBackgroundResource(R.drawable.tousu);
                viewHolder.no.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            }
        });
        viewHolder.no.setText(list.get(position).getLabel().get(1).getName());
        viewHolder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "pingjia";
                tag = tag+position;
                spUtileFQTZ.put(context, tag,list.get(position).getLabel().get(1).getId()+"");
                LogU.Ld("1608", "拼接------" + tag+"-----"+list.get(position).getLabel().get(1).getId());
                viewHolder.yes.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                viewHolder.no.setBackgroundResource(R.drawable.tousu);
            }
        });


        return convertView;
    }

    public class ViewHolder {
        private TextView duiwu,name,jieguo,yes,no;
        private ImageView touxiang;
        private RelativeLayout huizhang;
        public ViewHolder(View view) {
            duiwu = view.findViewById(R.id.pingjia_two_duiwu);
            name = view.findViewById(R.id.pingjia_two_name);
            jieguo = view.findViewById(R.id.pingjia_two_jieguo);
            touxiang = view.findViewById(R.id.pingjia_two_touxiang);
            yes = view.findViewById(R.id.pingjia_two_yes);
            no = view.findViewById(R.id.pingjia_two_no);

        }


    }

}
