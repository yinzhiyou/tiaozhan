package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.CGCXEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;

import java.util.List;

public class CGCXAdapter extends BaseAdapter {
    private List<CGCXEntity.DataBean.LstBean> list;
    private Context context;

    //    private ArrayFilter mFilter;
//    private ArrayList<DizhiSousuoEntity.ResultsBean> mUnfilteredData;
    public CGCXAdapter(Context context,List<CGCXEntity.DataBean.LstBean> list){
        this.list = list;
        this.context = context;

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
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.dizhi_sousuo, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView1.setText(list.get(position).getName());
        viewHolder.textView2.setText(list.get(position).getAddress());

//            viewHolder.textView2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemDeleteListener.onDeleteClick(position);
//                }
//            });

        return convertView;
    }




    public class ViewHolder {
        private TextView textView1,textView2;
        private LinearLayout layout;
        public ViewHolder(View view) {
            textView1 = view.findViewById(R.id.sousuo_dizhi_text1);
            textView2 = view.findViewById(R.id.sousuo_dizhi_text2);
            layout = view.findViewById(R.id.dizhi_sousuo_layout);
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
