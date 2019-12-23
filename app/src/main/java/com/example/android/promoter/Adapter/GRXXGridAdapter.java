package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.GRXXEntity;
import com.example.android.promoter.Entity.GRxxInfoEntity;
import com.example.android.promoter.R;

import java.util.List;

public class GRXXGridAdapter extends BaseAdapter {
    private Context context;
    private List<GRxxInfoEntity.DataBean.FaveriteSportBean> list;
    public GRXXGridAdapter (Context context,List<GRxxInfoEntity.DataBean.FaveriteSportBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.xiugai_aihao_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

      //  viewHolder.textView.setText(list.get(position).getName());
    // viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.ellipse_home_details);
    // viewHolder.textView.setTextColor(context.getResources().getColor(R.color.my_tab));
        viewHolder.item_check.setText(list.get(position).getName() + "/" + list.get(position).getGrade_name());
        viewHolder.item_check.setBackgroundResource(R.drawable.ellipse_home_details);
        viewHolder.item_check.setTextColor(context.getResources().getColor(R.color.my_tab));
        return convertView;
}


    public class ViewHolder {
        private TextView textView;
        private RelativeLayout mRelativeLayout;
        private CheckBox item_check;
        public ViewHolder(View view) {
            item_check= view.findViewById(R.id.item_check);
            textView = view.findViewById(R.id.xiugai_aihao_text);
            mRelativeLayout = view.findViewById(R.id.xiugai_aihao_layout);
        }
    }
}