package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.BiaoqianEntity;
import com.example.android.tiaozhan.Entity.PingjiaListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;

import java.util.ArrayList;
import java.util.List;

public class PingJBiaoQianAdapter extends BaseQuickAdapter<BiaoqianEntity.DataBean,BaseViewHolder> {

    private final int TITLE = 99;
    private final int CONTENT = 100;
    private int Zeng = 0;
    //    private List<PingjiaListEntity.DataBean.LabelBean> list;
    private List<PingjiaListEntity.DataBean.UsersInfoBean.ResBean> list;
    //    private String s;
    private String []s;
    private int item;
    private String jieguo = "",tag = "pingjia";
    private SPUtileFQTZ spUtileFQTZ ;
    private CheckBox textView;
    private List<String> listArray=new ArrayList<>();
    public PingJBiaoQianAdapter(int layoutResId, @Nullable List<BiaoqianEntity.DataBean> data,int position) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final BiaoqianEntity.DataBean item) {
        textView = helper.getView(R.id.textViewContent);
        helper.setText(R.id.textViewContent,item.getLabelName());
        textView.setOnCheckedChangeListener(null);

        textView.setChecked(item.isSelect());
        textView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean select = item.isSelect();
                select=isChecked;
                if (select){
                    buttonView.setBackgroundResource(R.drawable.ellipse_home_details);
                    buttonView.setTextColor(mContext.getResources().getColor(R.color.my_tab));
                    listArray.add(item.getId()+"");
                }else {
                    listArray.remove(item.getId()+"");
                    buttonView.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    buttonView.setTextColor(mContext.getResources().getColor(R.color.login_forget));
                }
                String str = TextUtils.join("|", listArray);

                LogU.Ld("1608","集合"+str);
            }
        });


    }

}
