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
import com.example.android.promoter.Entity.OpponentBiPaihangEntity;
import com.example.android.promoter.Entity.PaihangEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;

import java.text.DecimalFormat;
import java.util.List;

public class OpponentListAdapter extends BaseAdapter{
    private Context context;
    private List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> list;
    private int qiu;
    private String uuid;
    private SPUtils spUtils;
    public OpponentListAdapter(Context context,List<OpponentBiPaihangEntity.DataBean.OtherRankInfoBean> list,int qiu) {
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
        OpponentListAdapter.ViewHolder viewHolder;
        DecimalFormat df = new DecimalFormat("#.00");
        spUtils = new SPUtils();

        uuid= (String) spUtils.get(context, "uuid", " ");
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_ds_paihang_list, parent, false);
            viewHolder = new OpponentListAdapter.ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OpponentListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.paiming.setText((position+4)+"");
        viewHolder.paiming2.setText((position+4)+"");

        viewHolder.jinbi.setText(list.get(position).getTotal()+"");
        LogU.Ld("1609","uuid"+list.get(position).getPlayerUUID()+"我的"+uuid);
        if (list.get(position).getPlayerUUID().equals(uuid)){
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
        private TextView name,paiming,jinbi,paiming2;
        private ImageView touxiang;
        private RelativeLayout huizhang;
        public ViewHolder(View view) {
            name = view.findViewById(R.id.ds_paihang_list_name);
            paiming = view.findViewById(R.id.ds_paihang_list_paiming);
            paiming2 = view.findViewById(R.id.ds_paihang_list_paiming2);


            huizhang = view.findViewById(R.id.ds_paihang_list_huizhang);

            touxiang = view.findViewById(R.id.ds_paihang_list_touxiang);
            jinbi = view.findViewById(R.id.ds_paihang_list_jinbi);
        }


    }
}
