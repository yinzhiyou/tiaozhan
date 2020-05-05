package com.example.android.tiaozhan.Toos;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public abstract  class CheckHelper {
    RecyclerView recyclerView;
    public CheckHelper(RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
    }

    //如果点击事件不是整个item的话，传入要点击的view的id
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, int redID) {
        bindViewHolder(viewHolder, viewHolder.itemView.findViewById(redID));
    }

    //默认点击事件是整个item
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder) {
        bindViewHolder(viewHolder, viewHolder.itemView);
    }

    abstract void bindViewHolder(RecyclerView.ViewHolder viewHolder, View clickView);

    //用来处理选中状态改变后状况，比如你想修改文本内容

    public void stateChange(RecyclerView.ViewHolder viewHolder, Boolean checked) {
        viewHolder.itemView.setSelected(checked);
        handleChange(viewHolder, checked);
    }

    abstract boolean isCheckedPosition(int position);

    public interface HandleStateChange {
        void stateChange(RecyclerView.ViewHolder viewHolder, boolean checked);
        void clickWhich(int position);
    }

    public HandleStateChange handleStateChange;

    protected void handleChange(RecyclerView.ViewHolder viewHolder, boolean checked){
        if (handleStateChange != null) {
            handleStateChange.stateChange(viewHolder, checked);
        }
    }
    protected void clickWhich(int position){
        if (handleStateChange != null) {
            handleStateChange.clickWhich(position);
        }
    }
}
