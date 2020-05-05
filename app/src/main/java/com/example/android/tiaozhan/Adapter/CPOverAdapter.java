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
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.R;

import java.util.List;

public class CPOverAdapter extends BaseAdapter {

    private Context context;
    private List<HDXQEntity.DataBean.GetRefereeResultBean> list;

    private int staust;
    public CPOverAdapter(Context context, List<HDXQEntity.DataBean.GetRefereeResultBean> list) {
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
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.cpjg_layout, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(viewHolder.my_touxiang!=null) {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getPlayerimg()).into(viewHolder.my_touxiang);
        }
        viewHolder.no.setText(list.get(position).getReferee());

        if (list.get(position).getResult()==0){
            if (staust==4){
                viewHolder.cp_text_jg.setText("未填写");
            }else if (staust==5||staust==6){
                viewHolder.cp_text_jg.setText("弃权");
            }

        }else if (list.get(position).getResult()==1){
            viewHolder.cp_bg_jg.setBackgroundResource(R.drawable.cp_bg1);
            viewHolder.cp_text_jg.setText("A赢B");
        }else if (list.get(position).getResult()==2){
            viewHolder.cp_bg_jg.setBackgroundResource(R.drawable.cp_bg2);
            viewHolder.cp_text_jg.setText("A输B");
        }else if (list.get(position).getResult()==3){
            viewHolder.cp_bg_jg.setBackgroundResource(R.drawable.cp_bg2);
            viewHolder.cp_text_jg.setText("A平B");
        }
        return convertView;
    }


    public class ViewHolder {
        private ImageView my_touxiang;
        private TextView no,cp_text_jg;
        private RelativeLayout cp_bg_jg;
        public ViewHolder(View view) {
            my_touxiang = view.findViewById(R.id.my_touxiang);
            no = view.findViewById(R.id.hdxq_a_grid_no);
            cp_bg_jg = view.findViewById(R.id.cp_bg_jg);
            cp_text_jg = view.findViewById(R.id.cp_text_jg);
        }


    }
public void setStaust(int staust){
    this.staust = staust;
}
}
