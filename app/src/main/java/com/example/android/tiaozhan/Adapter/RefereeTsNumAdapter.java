package com.example.android.tiaozhan.Adapter;

import androidx.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.tiaozhan.Entity.RefereeGetJudgeEntity;
import com.example.android.tiaozhan.R;

import java.util.ArrayList;
import java.util.List;

public class RefereeTsNumAdapter extends BaseQuickAdapter<RefereeGetJudgeEntity.DataBean,BaseViewHolder> {

    private BoxItemCallBack boxItemCallBack;
    //选中要删除用户

    private List<String> selectSet=new ArrayList<>();
    /**
     * 获取选中要删除的列表
     *
     * @return
     */
    public List<String> getSelectSet() {
        return selectSet;
    }

    public void setSelectSet(List<String> selectSet) {
        this.selectSet = selectSet;
    }


    public void setBoxItemCallBack(BoxItemCallBack boxItemCallBack) {
        this.boxItemCallBack = boxItemCallBack;
    }

    public RefereeTsNumAdapter(int layoutResId, @Nullable List<RefereeGetJudgeEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final RefereeGetJudgeEntity.DataBean dataBean) {
        Glide.with(mContext).load(mContext.getResources().getString(R.string.imgurl)+dataBean.getImgURL())
                .placeholder(R.mipmap.icon_touxiang_wu).error(R.mipmap.icon_touxiang_wu).into((ImageView) baseViewHolder.getView(R.id.icon_cp));
        CheckBox box = baseViewHolder.getView(R.id.check_box);
        box.setOnCheckedChangeListener(null);
        box.setChecked(dataBean.getBoxChecked());
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            dataBean.setBoxChecked(isChecked);

                if (isChecked) {
                    selectSet.add(dataBean.getUuid());
                } else {
                    selectSet.remove(dataBean.getUuid());
                }
                boxItemCallBack.onCallBack();

            }
        });

    }

    public interface BoxItemCallBack{
        public void onCallBack();
    }

}
