package com.example.android.promoter.Adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.android.promoter.Denglu.XiugaiXXActivity;
import com.example.android.promoter.Entity.GRxxInfoEntity;
import com.example.android.promoter.Entity.HqSportEntity;
import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiuGaiSportAdapter extends BaseQuickAdapter<HqSportEntity.DataBean, BaseViewHolder> {

    private CallBack callBack;

    private Dialog dialog;
    private TextView tv_primary, tv_intermediate, tv_advanced, tv_self_rating;
    private CheckBox tv_newbie;

    private String tv_cn;
    private String tv_cj;
    private String tv_zj;
    private String tv_gj, textt;
    private int tag , spId;
    private ImageView icon_close;
    private JsCallBack jsCallBack;
    private Map<String, String> selectList;
    private Map<Integer, Boolean> map = new HashMap<>();

    private CheckBox item_check;

    public void setJsCallBack(JsCallBack jsCallBack) {
        this.jsCallBack = jsCallBack;
    }

    /**
     * 被动回调
     */
    public interface CallBack {
        void callBack(Map<String, String> selectList);

    }


    /**
     * 暴露接口
     */
    public void setCallBack(CallBack back) {
        callBack = back;
    }

    /**
     * 主动获取
     */
    public Map<String, String> getSelectList() {
        //弹窗选择运动
        return selectList;
    }

    public XiuGaiSportAdapter(int layoutResId, final @Nullable List<HqSportEntity.DataBean> data) {
        super(layoutResId, data);
        selectList = new HashMap<>();

    }

    @Override
    protected void convert(final BaseViewHolder helper, final HqSportEntity.DataBean item) {

        final RelativeLayout mRelativeLayout = helper.getView(R.id.xiugai_aihao_layout);
        item_check = helper.getView(R.id.item_check);
        if (item.getGrade_status() != 4) {
            item_check.setChecked(true);
            item_check.setText(item.getSport_name() + "/" + item.getGrade_name());
            item_check.setBackgroundResource(R.drawable.ellipse_home_details);
            item_check.setTextColor(mContext.getResources().getColor(R.color.my_tab));
        } else {
            item_check.setText(item.getSport_name() + "");
        }

        LogU.Le("yudong", item.getGrade_name() + "级别"+textt);
        item_check.setOnCheckedChangeListener(null);
        item_check.setChecked(item.isSelect());
        item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LogU.Ld("1608","真假"+isChecked);
                if (isChecked) {
                    buttonView.setBackgroundResource(R.drawable.ellipse_home_details);
                    buttonView.setTextColor(mContext.getResources().getColor(R.color.my_tab));
                    chooserSport();
                    tv_self_rating.setText(item.getSport_name() + "技术水平自我评定");
                    setJsCallBack(new JsCallBack() {
                        @Override
                        public void callBack(String text, final int spId) {
                            textt=text;
                            selectList.put(item.getSport_id() + "", spId + "");
                            item_check.setText(item.getSport_name() + "/" + textt);
                            LogU.Le("yudong", item.getSport_name() + "bbb====="+textt);
                            //选中后被动回调到activity
                            callBack.callBack(selectList);
                            LogU.Le("yudong", "buttonView.getText().toString()" + helper.getPosition());

                        }
                    });

                } else {
                    buttonView.setBackgroundResource(R.drawable.xiugai_huodong_layout);
                    buttonView.setTextColor(mContext.getResources().getColor(R.color.login_forget));
                    selectList.remove(String.valueOf(item.getSport_id()));
                    //选中后被动回调到activity
                    callBack.callBack(selectList);
                }

            }
        });


    }

    //技术水平评定弹窗
    private void chooserSport() {
        dialog = new Dialog(mContext, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(mContext).inflate(
                R.layout.fragment_xiai_sport, null);
        tv_self_rating = sport.findViewById(R.id.tv_self_rating);
        tv_newbie = sport.findViewById(R.id.tv_newbie);
        tv_primary = sport.findViewById(R.id.tv_primary);
        tv_intermediate = sport.findViewById(R.id.tv_intermediate);
        tv_advanced = sport.findViewById(R.id.tv_advanced);
        icon_close = sport.findViewById(R.id.icon_close);
        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.TOP;
        window.setAttributes(lp);


        dialog.show();

        tv_newbie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_newbie.setBackgroundResource(R.drawable.login_rounded_corners);
                tv_newbie.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

                tv_primary.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_primary.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_intermediate.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_intermediate.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_advanced.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_advanced.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_cn = tv_newbie.getText().toString();
                tag = 0;
                jsCallBack.callBack(tv_cn, 0);

                dialog.dismiss();
            }
        });
        tv_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_primary.setBackgroundResource(R.drawable.login_rounded_corners);
                tv_primary.setTextColor(mContext.getResources().getColor(R.color.colorWhite));


                tv_newbie.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_newbie.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_intermediate.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_intermediate.setTextColor(mContext.getResources().getColor(R.color.huise));


                tv_advanced.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_advanced.setTextColor(mContext.getResources().getColor(R.color.huise));
                tv_cj = tv_primary.getText().toString();
                tag = 1;
                jsCallBack.callBack(tv_cj, 1);

                dialog.dismiss();
            }
        });
        tv_intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_intermediate.setBackgroundResource(R.drawable.login_rounded_corners);
                tv_intermediate.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

                tv_primary.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_primary.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_newbie.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_newbie.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_advanced.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_advanced.setTextColor(mContext.getResources().getColor(R.color.huise));
                tv_zj = tv_intermediate.getText().toString();
                tag = 2;
                jsCallBack.callBack(tv_zj, 2);

                dialog.dismiss();
            }
        });
        tv_advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_advanced.setBackgroundResource(R.drawable.login_rounded_corners);
                tv_advanced.setTextColor(mContext.getResources().getColor(R.color.colorWhite));

                tv_newbie.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_newbie.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_intermediate.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_intermediate.setTextColor(mContext.getResources().getColor(R.color.huise));

                tv_primary.setBackgroundResource(R.drawable.login_rounded_wu);
                tv_primary.setTextColor(mContext.getResources().getColor(R.color.huise));
                tv_gj = tv_advanced.getText().toString();
                tag = 3;
                jsCallBack.callBack(tv_gj, 3);

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public interface JsCallBack {
        void callBack(String text, int spId);
    }


}
