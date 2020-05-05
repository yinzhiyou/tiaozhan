package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.ToastUitl;


import java.util.List;

/**
 * Created by wangtao on 2018/4/18.
 */

public class LinkLabelAdapter extends RecyclerView.Adapter {

    private Context context;
    private final int TITLE = 99;
    private final int CONTENT = 100;
    private int Zeng = 0;
//    private List<PingjiaListEntity.DataBean.LabelBean> list;
    private List<BiaoqianEntity.DataBean> list;
    //    private String s;
    private String []s;
    private int item;
    private String jieguo = "",tag = "pingjia";
    private SPUtileFQTZ spUtileFQTZ ;
    public LinkLabelAdapter(Context context,List<BiaoqianEntity.DataBean>  list, int item) {
        this.context = context;
        this.list = list;
        this.item = item;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
//        if (viewType == TITLE) {
//            holder = new TitleHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_title, null));
//        } else {
        holder = new LabelHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_content, null));
//        }
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
            layoutParams.leftMargin = 20;
            llContent.setLayoutParams(layoutParams);

        }

        public void setData(final int position) {
            textView.setText(list.get(position).getLabelName());
//            final List<String> s = new ArrayList<>();
            spUtileFQTZ = new SPUtileFQTZ();
            s = new String[list.size()];
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    jieguo = "";
                    tag = "pingjia";
                    if (list.get(position).isSelect()) {
                        Zeng--;
                    }
                    if (Zeng < 999) {
                        if (textView.isSelected()) {
                            textView.setSelected(false);
                            list.get(position).setSelect(false);
                            s[position] = "";
//                            ToastUitl.longs(s.toString());
                            for(int i=0;i<s.length;i++){
                                LogU.Ld("1608","点击标签"+s[i]);
                                if (s[i]!=null){
                                    jieguo =jieguo+s[i];
                                }
                            }
                            tag = tag+item;
                            if (jieguo.length()>0){
                                spUtileFQTZ.put(context, tag, jieguo.substring(0,jieguo.length()-1));
                                LogU.Ld("1608","点击标签结果=="+tag+"===="+jieguo.substring(0,jieguo.length()-1));
                            }


                        } else {
                            ++Zeng;
                            textView.setSelected(true);
                            list.get(position).setSelect(true);
//                            s.add(position,list.get(position).getLabelName());
                            s[position] = list.get(position).getId()+"|";
//                            s[position] = "aaaa";
//                            ToastUitl.longs(s.toString());
                            for(int i=0;i<s.length;i++){
                                LogU.Ld("1608","点击标签"+s[i]);
                                if (s[i]!=null){
                                    jieguo =jieguo+s[i];
                                }
                            }
                            tag = tag+item;
                            spUtileFQTZ.put(context, tag, jieguo.substring(0,jieguo.length()-1));
                            LogU.Ld("1608","点击标签结果--------"+tag+"===="+jieguo.substring(0,jieguo.length()-1));

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
