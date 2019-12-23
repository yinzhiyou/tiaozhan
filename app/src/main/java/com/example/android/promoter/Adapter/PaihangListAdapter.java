package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.promoter.Entity.CGTimeEntity;
import com.example.android.promoter.Entity.PaihangEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;

import java.text.DecimalFormat;
import java.util.List;

public class PaihangListAdapter extends BaseAdapter {
    private Context context;
    private   List<PaihangEntity.DataBean.OtherRankInfoBean> list;
    private int qiu;
    private String uuid;
    private SPUtils spUtils;
    public PaihangListAdapter(Context context,List<PaihangEntity.DataBean.OtherRankInfoBean> list,int qiu) {
        this.context = context;
        this.list = list;
        this.qiu = qiu;
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
        DecimalFormat df = new DecimalFormat("#.00");
        spUtils = new SPUtils();

        uuid= (String) spUtils.get(context, "uuid", " ");

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.paihang_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.paiming.setText((position+4)+"");
        viewHolder.paiming2.setText((position+4)+"");
        viewHolder.dengji.setText(list.get(position).getLevel());
        viewHolder.jinbi.setText(list.get(position).getTotal()+"");
        switch (qiu){
            case 1:
                viewHolder.qiu.setText("羽毛球");
                break;
            case 2:
                viewHolder.qiu.setText("乒乓球");
                break;
            case 3:
                viewHolder.qiu.setText("台球");
                break;
            case 4:
                viewHolder.qiu.setText("篮球");
                break;
            case 5:
                viewHolder.qiu.setText("足球");
                break;
            case 6:
                viewHolder.qiu.setText("排球");
                break;
            case 7:
                viewHolder.qiu.setText("网球");
                break;
            case 8:
                viewHolder.qiu.setText("高尔夫");
                break;
        }

        if (list.get(position).getPlayerUUID().equals(uuid)){
            if(list.get(position).getRank()>=10){

            }
            viewHolder.huizhang.setVisibility(View.VISIBLE);
            viewHolder.paiming.setVisibility(View.GONE);

        }else{
            viewHolder.huizhang.setVisibility(View.GONE);
            viewHolder.paiming.setVisibility(View.VISIBLE);

        }
//        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemDeleteListener.onDeleteClick(position);
//            }
//        });




        return convertView;
    }

    public class ViewHolder {
        private TextView name,paiming,qiu,dengji,jinbi,paiming2;
        private ImageView touxiang;
        private RelativeLayout huizhang;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.paihang_list_name);
            paiming = view.findViewById(R.id.paihang_list_paiming);
            paiming2 = view.findViewById(R.id.paihang_list_paiming2);
            qiu = view.findViewById(R.id.paihang_list_qiu);
            dengji = view.findViewById(R.id.paihang_list_dengji);
            huizhang = view.findViewById(R.id.paihang_list_huizhang);

            touxiang = view.findViewById(R.id.paihang_list_touxiang);
            jinbi = view.findViewById(R.id.paihang_list_jinbi);
        }


    }

}