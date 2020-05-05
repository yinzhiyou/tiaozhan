package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.PingjiaListEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;

import java.util.List;

public class PingJUsersInfoAdapter extends BaseQuickAdapter<PingjiaListEntity.DataBean.UsersInfoBean.ResBean,BaseViewHolder> {
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
    List<PingjiaListEntity.DataBean.UsersInfoBean.ResBean> mMyLiveList;
    public PingJUsersInfoAdapter(int layoutResId, @Nullable List<PingjiaListEntity.DataBean.UsersInfoBean.ResBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PingjiaListEntity.DataBean.UsersInfoBean.ResBean item) {
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

                }else {
                    buttonView.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    buttonView.setTextColor(mContext.getResources().getColor(R.color.login_forget));
                }
            }
        });

    }

}
