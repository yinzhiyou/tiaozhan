package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HDXQEntity;
import com.example.android.tiaozhan.My.Friends.LiaoTianActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

import java.util.List;

public class HDXQBAdapter extends BaseAdapter {
    private Context context;
    private List<HDXQEntity.DataBean.TeamBBean> list;
    private int s;
    private String tag;
    private SPUtils spUtils;
    private String token, bieming, moshiString, zhuangtaiString, uid;

    public HDXQBAdapter(Context context1, List<HDXQEntity.DataBean.TeamBBean> list, int s, String tag, String moshiString,
                        String zhuangtaiString, String uid) {
        this.context = context1;
        this.list = list;
        this.s = s;
        this.tag = tag;
        this.moshiString = moshiString;
        this.zhuangtaiString = zhuangtaiString;
        this.uid = uid;
    }

    @Override
    public int getCount() {
        //   LogU.Ld("1608", "1适配B队人数" + s);
        return s;
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
        spUtils = new SPUtils();
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.hdxq_a_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        LogU.Ld("1608", "2适配B队人数" + position);

        if (position < list.size()) {
            Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);

            if (list.get(position).getHeightLevelName().equals("羽毛球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
            } else if (list.get(position).getHeightLevelName().equals("乒乓球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
            } else if (list.get(position).getHeightLevelName().equals("台球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
            } else if (list.get(position).getHeightLevelName().equals("篮球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
            } else if (list.get(position).getHeightLevelName().equals("足球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
            } else if (list.get(position).getHeightLevelName().equals("排球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
            } else if (list.get(position).getHeightLevelName().equals("网球")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
            } else if (list.get(position).getHeightLevelName().equals("高尔夫")) {
                viewHolder.qiulei.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
                viewHolder.qiulei2.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
            }
            viewHolder.lv.setText(list.get(position).getHeightLevel());
            viewHolder.lv2.setText(list.get(position).getHeightLevel());

//            viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);


            if (list.get(position).getIsQuit() == 2) {
                viewHolder.yaoqingzhong.setText("提前退出");
                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
            } /*else if (list.get(position).getIsQuitInGame() == 2) {
                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                viewHolder.yaoqingzhong.setText("中途退出");
            }*/ else {
                LogU.Ld("1608","签到状态"+list.get(position).getIsSeat()+"==="+list.get(position).getSigninYes());


                if (list.get(position).getIsSignIn() == 1) {


                    if (zhuangtaiString.equals("4")) {
                        viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                        if (list.get(position).getIsConfirmResult() == 0) {
                            viewHolder.yaoqingzhong.setText("待填写");
                        } else if (list.get(position).getIsConfirmResult() == 1) {
                            viewHolder.yaoqingzhong.setText("已填写");
                        } else {
                            if (list.get(position).getIsQuit() == 2) {
                                viewHolder.yaoqingzhong.setText("提前退出");
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                            } else if (list.get(position).getIsQuitInGame() == 2) {
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                viewHolder.yaoqingzhong.setText("中途退出");
                            }
                        }
                    } else if (zhuangtaiString.equals("6")) {

                        viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                        if (list.get(position).getIsComment() == 2) {
                            if (list.get(position).getIsQuit() == 2) {
                                viewHolder.yaoqingzhong.setText("提前退出");
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                            } else if (list.get(position).getIsQuitInGame() == 2) {
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                viewHolder.yaoqingzhong.setText("中途退出");
                            }
                        } else if (list.get(position).getIsComment() == 1) {
                            viewHolder.yaoqingzhong.setText("已评价");
                        } else {
                            viewHolder.yaoqingzhong.setText("待评价");
                        }
                    } else if (zhuangtaiString.equals("8")) {
                        viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                        if (list.get(position).getIsConfirmOver() == 0) {
                            viewHolder.yaoqingzhong.setText("待确认");
                        } else {
                            viewHolder.yaoqingzhong.setText("已确认");
                        }
                    } else if (zhuangtaiString.equals("7")) {

                        viewHolder.yaoqingzhong.setVisibility(View.GONE);


                    } else if (zhuangtaiString.equals("1")||zhuangtaiString.equals("2") || zhuangtaiString.equals("3")||zhuangtaiString.equals("9") ) {

                            if (list.get(position).getIsSignIns() == 1) {
                                if (list.get(position).getIsQuitInGame() == 2){
                                    viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                    viewHolder.yaoqingzhong.setText("中途退出");
                                }else {
                                    viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                    viewHolder.yaoqingzhong.setText("已签到");
                                }
                            } else if (list.get(position).getIsSignIns() == 2) {
                                if (list.get(position).getIsQuitInGame() == 2){
                                    viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                    viewHolder.yaoqingzhong.setText("中途退出");
                                }else {
                                    viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                    viewHolder.yaoqingzhong.setText("迟到");
                                }
                            } else {

                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                viewHolder.yaoqingzhong.setText("未签到");
                            }

                    }
                } else {
                    if (zhuangtaiString.equals("1")) {
                        if (list.get(position).getIsSeat() == 1) {

                            if (uid.equals(list.get(position).getInvitedByPlayerUUID())) {
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                viewHolder.yaoqingzhong.setText("取消邀请");
                            } else if (list.get(position).getInvitedByPlayerUUID().length() > 5 && list.get(position).getResult() == 0 && uid.equals(list.get(position).getUuid())) {
                                viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                                viewHolder.yaoqingzhong.setText("被邀请中");
                            }
                        }else if (list.get(position).getIsSignIns() == 3){
                            viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                            viewHolder.yaoqingzhong.setText("未签到");
                        }else {
                            viewHolder.yaoqingzhong.setVisibility(View.GONE);


                        }
                    } else if (zhuangtaiString.equals("7")) {

                        viewHolder.yaoqingzhong.setVisibility(View.GONE);


                    } else {
                        viewHolder.yaoqingzhong.setVisibility(View.VISIBLE);
                        viewHolder.yaoqingzhong.setText("未签到");
                    }
                }

            }
            if (tag.equals("1")) {
                viewHolder.danliao.setVisibility(View.VISIBLE);
                viewHolder.diejia.setVisibility(View.VISIBLE);

                viewHolder.qiulei.setVisibility(View.GONE);
                viewHolder.lv.setVisibility(View.GONE);
            } else {

                viewHolder.diejia.setVisibility(View.GONE);
                viewHolder.danliao.setVisibility(View.GONE);
                viewHolder.qiulei.setVisibility(View.VISIBLE);
                viewHolder.lv.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.qiulei.setVisibility(View.GONE);
            viewHolder.lv.setVisibility(View.GONE);
            viewHolder.diejia.setVisibility(View.GONE);
        }


        if (tag.equals("1")) {
            viewHolder.touxiang.setImageDrawable(context.getResources().getDrawable(R.mipmap.yaoqingtouxiang));
        } else {
            viewHolder.touxiang.setImageDrawable(context.getResources().getDrawable(R.mipmap.baoming));
        }


        if (!EmptyUtils.isStrEmpty(moshiString)) {
            if (moshiString.equals("3")) {
                viewHolder.no.setText("练习方");
            } else if (moshiString.equals("4")) {
                viewHolder.no.setText("陪练方");
            } else {
                viewHolder.no.setText("B" + (position + 1));
            }
        }
        viewHolder.danliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spUtils.put(context, "haoyouname", list.get(position).getNickname());
                final String b = list.get(position).getUuid().replace("-", "");
                bieming = (String) spUtils.get(context, "bieming", "");
                EMClient.getInstance().login(bieming, "tz1", new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {

                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        LogU.Ld("1608", "登录聊天服务器成功！");
                        Intent intent = new Intent();
                        intent.setClass(context, LiaoTianActivity.class);
                        intent.putExtra(EaseConstant.EXTRA_USER_ID, b);  //对方账号
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                        context.startActivity(intent);

                    }


                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        LogU.Ld("1608", "登录聊天服务器失败！");
                    }
                });
            }
        });
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, qiulei, qiulei2, danliao;
        private TextView lv, no, yaoqingzhong, lv2;
        private RelativeLayout diejia;

        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.hdxq_a_grid_touxiang);
            qiulei = view.findViewById(R.id.hdxq_a_grid_qiulei);
            lv = view.findViewById(R.id.hdxq_a_grid_dengji);
            no = view.findViewById(R.id.hdxq_a_grid_no);
            yaoqingzhong = view.findViewById(R.id.hdxq_a_grid_yqz);
            qiulei2 = view.findViewById(R.id.hdxq_a_grid_qiulei2);
            lv2 = view.findViewById(R.id.hdxq_a_grid_dengji2);
            danliao = view.findViewById(R.id.hdxq_a_grid_danliao);
            diejia = view.findViewById(R.id.hdxq_a_grid_diejia);
        }


    }
}