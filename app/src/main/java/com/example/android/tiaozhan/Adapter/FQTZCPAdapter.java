package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;

public class FQTZCPAdapter extends BaseAdapter {
    private Context context;
    //    private List<Map<String, Object>> list;

    private int s;
    private SPUtileFQTZ spUtileFQTZ;
    private String FQHDmoshihao;

    public FQTZCPAdapter(Context context1, int s) {
        this.context = context1;
        this.s = s;
    }

    @Override
    public int getCount() {

        return s;
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
        spUtileFQTZ = new SPUtileFQTZ();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.fqtz_cp_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.no.setText("C" + (position + 1));

        return convertView;
    }


    public void getNum(int num){
        this.s=num;

    }

    public class ViewHolder {
        private ImageView touxiang, qiulei;
        private TextView lv, no;

        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.hdxq_a_grid_touxiang);
            qiulei = view.findViewById(R.id.hdxq_a_grid_qiulei);
            lv = view.findViewById(R.id.hdxq_a_grid_dengji);
            no = view.findViewById(R.id.hdxq_a_grid_no);
        }


    }
}
