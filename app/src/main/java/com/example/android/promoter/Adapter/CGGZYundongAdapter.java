package com.example.android.promoter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.promoter.R;
import com.example.android.promoter.Entity.CGXXEntity;
import com.example.android.promoter.Entity.YundongEntity;

import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.ToastUitl;

import java.util.List;

public class CGGZYundongAdapter extends  RecyclerView.Adapter {

    private Context context;
    private final int TITLE = 99;
    private final int CONTENT = 100;
    private int Zeng = 0;
    private List<YundongEntity.DataBean> list;
    private  List<CGXXEntity.DataBean.SupportSportIDBean> list2;
    public CGGZYundongAdapter(Context context, List<YundongEntity.DataBean> list, List<CGXXEntity.DataBean.SupportSportIDBean> list2) {
        this.context = context;
        this.list = list;
        this.list2 = list2;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
//        if (viewType == TITLE) {
//            holder = new TitleHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_title, null));
//        } else {
        holder = new LabelHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_content, null));
//        }
//        textView.setSelected(true);
//        list.get(position).setSelect(true);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (getItemViewType(position) == TITLE) {
//            ((TitleHolder) holder).setData(position);
//        } else {
        ((LabelHolder) holder).setData(position);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (list.get(position).getStr().contains("标题")) {
//            return TITLE;
//        } else {
        return CONTENT;
//        }
    }

//    class TitleHolder extends RecyclerView.ViewHolder {
//
//        private final TextView adapterTitle;
//        private final ImageView adapterTitleIv;
//
//        public TitleHolder(View inflate) {
//            super(inflate);
//            adapterTitle = (TextView) inflate.findViewById(R.id.adater_title);
//            adapterTitleIv = (ImageView) inflate.findViewById(R.id.adapter_iv);
//        }

//        @SuppressLint("WrongConstant")
//        public void setData(int position) {
//            switch (list.get(position).getStr()) {
//                case "标题1":
//                    adapterTitleIv.setImageResource(R.drawable.circle);
//                    adapterTitle.setText("以下推荐男生选择");
//                    break;
//                case "标题2":
//                    adapterTitleIv.setImageResource(R.drawable.circle_fen);
//                    adapterTitle.setText("以下推荐女生选择");
//                    break;
//                case "标题3":
//                    adapterTitleIv.setImageResource(R.drawable.circle_bule);
//                    adapterTitle.setText("以下推荐二次元选择");
//                    break;
//                default:
//                    adapterTitleIv.setVisibility(View.GONE);
//                    adapterTitle.setVisibility(View.GONE);
//                    break;
//            }
//        }
//    }

    private class LabelHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private final LinearLayout llContent;

        public LabelHolder(View inflate) {
            super(inflate);
            textView = (TextView) inflate.findViewById(R.id.textViewContent);
            llContent = (LinearLayout) inflate.findViewById(R.id.llContent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 50;
            llContent.setLayoutParams(layoutParams);
        }

        public void setData(final int position) {
//            list2.get(position).getId()
            for (int i =0;i<list2.size();i++){
//                LogU.Ld("1608","走了"+list2.get(i).getId());
//                ++Zeng;
//                textView.setSelected(true);
                list.get(list2.get(i).getId()-1).setSelect(true);

            }
            if ( list.get(position).isSelect()) {
                textView.setSelected(true);
//                list.get(position).setSelect(false);
            } else {
                ++Zeng;
                textView.setSelected(false);
//                list.get(position).setSelect(true);
            }


            textView.setText(list.get(position).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(position).isSelect()) {
                        Zeng--;
                    }
                    if (Zeng < 18) {
                        if (textView.isSelected()) {
                            textView.setSelected(false);
                            list.get(position).setSelect(false);
                        } else {
                            ++Zeng;
                            textView.setSelected(true);
                            list.get(position).setSelect(true);
                        }
                    } else {
//                        ToastUtils.showSingleToast();
                        ToastUitl.longs("最多只能选择4个标签");
                    }
                }
            });
        }
    }
}
