package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;

import java.util.List;

public class JBMXJishuListAdapter extends BaseAdapter {

    private Context context;
    private List<YundongEntity.DataBean> list;
    private int c = 0;
    private String tag;
    public JBMXJishuListAdapter(Context context,List<YundongEntity.DataBean> list,String tag){
        this.context = context;
        this.list =  list;
        this.tag = tag;

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
    public void setSelectItem(int c) {

        this.c = c;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.jishu_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position).getName());
//        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemDeleteListener.onDeleteClick(position);
//            }
//        });



        if(tag.equals("2")){
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.my_hd_tab));
            viewHolder.imageView.setTextColor(context.getResources().getColor(R.color.hongse));
            if (c == position) {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
                viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
//            textc.setTextColor(Color.parseColor("#ff0000"));
            } else {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.my_hd_tab));
                viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.hongse));
//            textc.setTextColor(Color.parseColor("#393939"));
            }


        }else{
            if (c == position) {
                viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.hongse));
//            textc.setTextColor(Color.parseColor("#ff0000"));
            } else {
                viewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
//            textc.setTextColor(Color.parseColor("#393939"));
            }

        }



        return convertView;
    }

    public class ViewHolder {
        private TextView textView,imageView;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.jishu_name);
            imageView = view.findViewById(R.id.jishu_text);

        }


    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
//        void onDeleteClick(int i, int b);

        void onDeleteClick(int position);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

}
