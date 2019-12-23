package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Entity.PromoterDDEntity;
import com.example.android.promoter.R;

import java.util.List;

public class PromoterDDAdapter extends BaseAdapter {
    private Context context;
    private List<PromoterDDEntity.DataBean> list;
    public PromoterDDAdapter(Context context, List<PromoterDDEntity.DataBean> list){
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

            convertView = LayoutInflater.from(context).inflate(R.layout.prom_dd_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText("活动ID    "+list.get(position).getOrderId());
        viewHolder.time.setText(list.get(position).getCreateTime()+"——"+list.get(position).getFinishedTime());
        viewHolder.dizhi.setText(list.get(position).getSiteName()+"    "+list.get(position).getSport());
        viewHolder.cdf.setText("场地费"+list.get(position).getSiteMoney()+"");
        viewHolder.tcl.setText("提成"+list.get(position).getRoyalty()+"%");
        viewHolder.tcf.setText("+"+list.get(position).getRoyaltyMoney());


        if (list.get(position).getPublicStatus() == 1){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pipeizhong));
        }else if (list.get(position).getPublicStatus() == 2){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daichufa));
        }else if (list.get(position).getPublicStatus() == 3){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.huodongzhong));
        }else if (list.get(position).getPublicStatus() == 4){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.tianxiejieguo));
        }else if (list.get(position).getPublicStatus() == 5){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiwancheng));
        }else if (list.get(position).getPublicStatus() == 6){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daipingjia));
        }else if (list.get(position).getPublicStatus() == 7){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiquxiao));
        }else if (list.get(position).getPublicStatus() == 8){
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.querenjieshu));
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView id,time,dizhi,cdf,tcl,tcf;
        private ImageView zhuangtai;
        public ViewHolder(View view) {
            id = view.findViewById(R.id.prom_dd_list_id);
            time = view.findViewById(R.id.prom_dd_list_time);
            dizhi = view.findViewById(R.id.prom_dd_list_dizhi);
            cdf = view.findViewById(R.id.prom_dd_list_feiyong);
            tcl = view.findViewById(R.id.prom_dd_list_tcl);
            tcf = view.findViewById(R.id.prom_dd_list_tcf);
            zhuangtai = view.findViewById(R.id.prom_dd_list_zhuangtai);

        }


    }
}
