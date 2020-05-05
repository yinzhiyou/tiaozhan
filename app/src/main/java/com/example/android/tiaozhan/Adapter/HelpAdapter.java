package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.R;

public class HelpAdapter extends BaseAdapter {
    private Context context;

    public HelpAdapter (Context context){
        this.context = context;

    }

    @Override
    public int getCount() {
        return 10;
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
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.help_list,parent,false);

            viewHolder.mProblemTitleRl = (RelativeLayout) convertView.findViewById(R.id.layout_home_details_problem_title_rl);
            viewHolder.mProblemContentRl = (RelativeLayout) convertView.findViewById(R.id.layout_home_details_problem_content_rl);
            viewHolder.mProblemTitleIv = (ImageView) convertView.findViewById(R.id.layout_home_details_problem_title_iv);
            viewHolder.mOne = (TextView) convertView.findViewById(R.id.title_ont);
            viewHolder.mTwo = (TextView) convertView.findViewById(R.id.content_one);
            viewHolder.mLine = (View) convertView.findViewById(R.id.content_line);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        HomeListItemEntity.DataBean.FaqBean homeDetailsProblem = mDatas.get(position);
//        if (homeDetailsProblem.isShow()){
//            viewHolder.mProblemTitleIv.setBackgroundResource(R.mipmap.home_details_up);
//            viewHolder.mProblemContentRl.setVisibility(View.VISIBLE);
//        }else {
//            viewHolder.mProblemTitleIv.setBackgroundResource(R.mipmap.home_details_down);
//            viewHolder.mProblemContentRl.setVisibility(View.GONE);
//        }
//
//        viewHolder.mOne.setText(homeDetailsProblem.getQuestion());
//        viewHolder.mTwo.setText(homeDetailsProblem.getAnswer());
//        viewHolder.mLine.setVisibility(View.VISIBLE);
//
//        viewHolder.mProblemTitleRl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != onCommonProblemsClickListener){
//                    onCommonProblemsClickListener.onCommonProlemsClick(v,position);
//                }
//            }
//        });
        return convertView;
    }

    class ViewHolder{
        private RelativeLayout mProblemTitleRl;
        private RelativeLayout mProblemContentRl;
        private ImageView mProblemTitleIv;
        private TextView mOne;
        private TextView mTwo;
        private View mLine;
    }
}
