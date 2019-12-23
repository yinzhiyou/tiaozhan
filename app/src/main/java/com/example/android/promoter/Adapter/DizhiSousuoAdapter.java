package com.example.android.promoter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.promoter.Entity.DizhiSousuoEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;

import java.util.ArrayList;
import java.util.List;

public class DizhiSousuoAdapter extends BaseAdapter  {
    private List<DizhiSousuoEntity.ResultsBean> list;
    private Context context;

//    private ArrayFilter mFilter;
//    private ArrayList<DizhiSousuoEntity.ResultsBean> mUnfilteredData;
    public DizhiSousuoAdapter(Context context,List<DizhiSousuoEntity.ResultsBean> list){
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


//    @Override
//    public Filter getFilter() {
//        if (mFilter == null) {
//            mFilter = new ArrayFilter();
//        }
//        return mFilter;
//    }

//    private class ArrayFilter extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence prefix) {
//            FilterResults results = new FilterResults();
//
//            if (mUnfilteredData == null) {
//                mUnfilteredData = new ArrayList<>(list);
//            }
//
//            if (prefix == null || prefix.length() == 0) {
//                ArrayList<DizhiSousuoEntity.ResultsBean> list = mUnfilteredData;
//                results.values = list;
//                results.count = list.size();
//            } else {
//                String prefixString = prefix.toString().toLowerCase();
//
//                ArrayList<DizhiSousuoEntity.ResultsBean> unfilteredValues = mUnfilteredData;
//                int count = unfilteredValues.size();
//
//                ArrayList<DizhiSousuoEntity.ResultsBean> newValues = new ArrayList<DizhiSousuoEntity.ResultsBean>(count);
//
//                for (int i = 0; i < count; i++) {
//                    DizhiSousuoEntity.ResultsBean pc = unfilteredValues.get(i);
//                    if (pc != null) {
//
//                        if(pc.getName()!=null && pc.getName().startsWith(prefixString)){
//
//                            newValues.add(pc);
//                        }else if(pc.getName()!=null && pc.getName().startsWith(prefixString)){
//
//                            newValues.add(pc);
//                        }
//                    }
//                }
//
//                results.values = newValues;
//                results.count = newValues.size();
//            }
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint,
//                                      FilterResults results) {
//            //noinspection unchecked
//            list = (List<DizhiSousuoEntity.ResultsBean>) results.values;
//            if (results.count > 0) {
//                notifyDataSetChanged();
//            } else {
//                notifyDataSetInvalidated();
//            }
//        }
//
//    }
}
