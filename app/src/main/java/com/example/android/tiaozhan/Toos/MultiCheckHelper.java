package com.example.android.tiaozhan.Toos;

import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;

public class MultiCheckHelper  extends CheckHelper {
    public MultiCheckHelper(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    void bindViewHolder(final RecyclerView.ViewHolder viewHolder, View clickView) {
        final int position = viewHolder.getAdapterPosition();
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean contain = isCheckedPosition(position);
                if (contain) {
                    checkedArrays.delete(position);
                } else {
                    checkedArrays.put(position, 1);
                }
                stateChange(viewHolder, !contain);
            }
        });
        stateChange(viewHolder, isCheckedPosition(position));
    }

    private SparseIntArray checkedArrays =new SparseIntArray();

    public SparseIntArray getCheckedArrays() {
        return checkedArrays;
    }

    public void setCheckedArrays(SparseIntArray checkedArrays) {
        this.checkedArrays = checkedArrays;
    }

    @Override
    boolean isCheckedPosition(int position) {
        if (checkedArrays.size() == 0) {
            return false;
        }
        return checkedArrays.get(position) != 0;
    }
}
