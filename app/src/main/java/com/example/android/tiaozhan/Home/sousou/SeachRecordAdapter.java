package com.example.android.tiaozhan.Home.sousou;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.android.tiaozhan.Home.greendao.GreenDao;
import com.example.android.tiaozhan.R;

import java.util.List;



/**
 * Created by yi.huangxing on 17/12/13.类描述:
 */

public class SeachRecordAdapter extends BaseRecycleAdapter<GreenDao> {
    public SeachRecordAdapter(List<GreenDao> datas, Context mContext) {
        super(datas, mContext);
    }

    @Override
    protected void bindData(BaseViewHolder holder, final int position) {

        TextView textView= (TextView) holder.getView(R.id.tv_record);
        TextView tv_address= (TextView) holder.getView(R.id.tv_address);
        textView.setText(datas.get(position).getName());
        tv_address.setText(datas.get(position).getAddress());
        //
        holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null!=mRvItemOnclickListener){
                    mRvItemOnclickListener.RvItemOnclick(position);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mRvDataItemOnclickListener){
                   mRvDataItemOnclickListener.RvItemDataOnclick(position);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.search_item;
    }
}
