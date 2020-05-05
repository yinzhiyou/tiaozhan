package com.example.android.tiaozhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.HQQREntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.MyHDEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.List;

import okhttp3.Call;

public class MyHdAdapter extends BaseAdapter {

    private Context context;
    private List<MyHDEntity.DataBean.PublicLstBean> list;
    private String token;
    private SPUtils spUtils;

    private boolean isClickable2 = true;


    public MyHdAdapter(Context context, List<MyHDEntity.DataBean.PublicLstBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        final ViewHolder viewHolder;
        spUtils = new SPUtils();
        DecimalFormat df = new DecimalFormat("0.00");

        token = (String) spUtils.get(context, "logintoken", "无");
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.my_hd_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        initDownload(list.get(position).getUuid());

        Glide.with(context).load(context.getResources().getString(R.string.imgurl) + list.get(position).getImgURL()).into(viewHolder.touxiang);
        viewHolder.name.setText(list.get(position).getNickname());
        if (list.get(position).getReserve().equals("1")){
            viewHolder.moshi.setText("场馆预订");
        }else {

                if (list.get(position).getSportMode() == 1) {
                    viewHolder.moshi.setText("娱乐模式");
                    viewHolder.feiyong.setText("打赏费："+"¥"+  df.format(list.get(position).getTips()) + "/人");

                    // viewHolder.feiyong.setText("打赏费" + df.format(list.get(position).getTips() / (list.get(position).getNeedPlayerNumber() - 1)) + "元/人");
                } else if (list.get(position).getSportMode() == 2) {
                    viewHolder.moshi.setText("竞技模式");
                    viewHolder.feiyong.setText("打赏费：" +"¥"+ df.format(list.get(position).getTips()) + "/人");
                    //  viewHolder.feiyong.setText("打赏费" + df.format(list.get(position).getTips() / (list.get(position).getNeedPlayerNumber() - 1)) + "元/人");
                } else if (list.get(position).getSportMode() == 3) {
                    viewHolder.moshi.setText("我是陪练");
                    viewHolder.feiyong.setText("陪练费：" +"¥"+ df.format(list.get(position).getMoneyPerhour()) + "/人");
                } else if (list.get(position).getSportMode() == 4) {
                    viewHolder.moshi.setText("我找陪练");
                    viewHolder.feiyong.setText("陪练费：" +"¥"+ df.format(list.get(position).getMoneyPerhour()) + "/人");
                }

        }

        viewHolder.qiuleiText.setText(list.get(position).getSportName());
        viewHolder.renshu.setText(list.get(position).getSportTypeName());
        viewHolder.queren.setText("缺" + list.get(position).getLackCount() + "人");
        viewHolder.time1.setText(list.get(position).getStartDays());
        viewHolder.time2.setText(list.get(position).getWeek());
        viewHolder.time3.setText(list.get(position).getStartTimes() + "-" + list.get(position).getEndTimes());
        viewHolder.dizhi.setText(list.get(position).getSitesAddress());
        if (list.get(position).getPaySiteMoneyType() == 1) {
            viewHolder.fangshi.setText("场地费：AA");
        } else if (list.get(position).getPaySiteMoneyType() == 2) {
            viewHolder.fangshi.setText("场地费：输方买单");
        }


        viewHolder.dengji.setText(list.get(position).getUserLevel());
//        viewHolder.juli.setText(list.get(position).getRange());
        if (list.get(position).getSportName().equals("羽毛球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        } else if (list.get(position).getSportName().equals("乒乓球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        } else if (list.get(position).getSportName().equals("台球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        } else if (list.get(position).getSportName().equals("篮球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        } else if (list.get(position).getSportName().equals("足球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        } else if (list.get(position).getSportName().equals("排球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        } else if (list.get(position).getSportName().equals("网球")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        } else if (list.get(position).getSportName().equals("高尔夫")) {
            viewHolder.qiuleiImag.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        if (list.get(position).getSex() == 0) {
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        } else if (list.get(position).getSex() == 1) {
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }
        if (list.get(position).getReserve().equals("1")) {
            // viewHolder.ydz.setVisibility(View.INVISIBLE);
            viewHolder.queren.setVisibility(View.GONE);
            viewHolder.fangshi.setText("场地费：发布者买单");
            viewHolder.feiyong.setVisibility(View.GONE);
        }
        int complaint = list.get(position).getComplaint();
        int refereeComplaint = list.get(position).getRefereeComplaint();


        if (list.get(position).getPublicStatus() == 1) {

                viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
                viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pipeizhong));


            LogU.Ld("1608", "能否签到" + list.get(position).getSigninYes()+list.get(position).getReserve());


            if (list.get(position).getIsPublisher() == 1) {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {
                    if (list.get(position).getSigninYes() == 1) {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);
                        viewHolder.dianjiwenzi.setText("取消发布");
                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                        viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                        if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                            if (list.get(position).getIsSignIn() == 1) {
                                viewHolder.dianjiwenzi2.setText("已签到");
                                viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                isClickable2 = false;
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");
                                isClickable2 = true;
                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            }
                        } else {
                            viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                            //   viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        }
                    } else {
                       /* if (list.get(position).getReserve().equals("1")) {
                            viewHolder.dianjiwenzi.setText("取消预订");
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            viewHolder.zhuangtai.setVisibility(View.VISIBLE);

                        } else {*/
                            viewHolder.dianjiwenzi.setText("取消发布");
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                       // }
                    }
                } else {

                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    if (list.get(position).getSigninYes() == 1) {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);
                        viewHolder.dianjiwenzi.setText("取消发布");
                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                        viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                        if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                            if (list.get(position).getIsSignIn() == 1) {
                                viewHolder.dianjiwenzi2.setText("已签到");
                                viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                isClickable2 = false;
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");
                                isClickable2 = true;
                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            }
                        } else {
                            viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                            //   viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        }
                    } else {
                       /* if (list.get(position).getReserve().equals("1")) {
                            viewHolder.dianjiwenzi.setText("取消预订");
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);


                        } else {*/
                            viewHolder.dianjiwenzi.setText("取消发布");
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                      //  }
                    }
                }
            } else {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                    if (list.get(position).getSigninYes() == 1) {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);
                        viewHolder.dianjiwenzi.setText("取消报名");
                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        if (list.get(position).getIsSignIn() == 1) {
                            viewHolder.dianjiwenzi2.setText("已签到");
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);

                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }
                    } else {

                        viewHolder.dianjiwenzi.setText("取消报名");
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    }
                } else {
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    if (list.get(position).getSigninYes() == 1) {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);

                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        if (list.get(position).getIsSignIn() == 1) {
                            viewHolder.dianjiwenzi2.setText("已签到");
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);

                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }
                    } else {

                        viewHolder.dianjiwenzi.setText("取消报名");
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    }
                }

            }
        } else if (list.get(position).getPublicStatus() == 2) {

                viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
                viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daichufa));


            if (list.get(position).getIsPublisher() == 1) {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                    if (list.get(position).getReserve().equals("1")) {
                        // viewHolder.ydz.setVisibility(View.INVISIBLE);
                        if (list.get(position).getYescancel()==1) {
                            viewHolder.dianjiwenzi.setText("取消预订");
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }else {
                            viewHolder.dianji.setVisibility(View.GONE);
                            viewHolder.dianji2.setVisibility(View.GONE);
                        }
                    } else {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);

                        if (list.get(position).getIsQuit() == 2) {
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianjiwenzi.setText("提前退出");
                            viewHolder.dianjiwenzi2.setText("场馆签到");
                            isClickable2 = false;
                        } else {
                            viewHolder.dianjiwenzi.setText("提前退出");
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            isClickable2 = true;

                            LogU.Ld("1608", "是否签到" + list.get(position).getIsSignIn());
                            if (list.get(position).getIsSignIn() == 1) {
                                viewHolder.dianjiwenzi2.setText("已签到");
                                viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                // viewHolder.dianji2.setClickable(false);
                                isClickable2 = false;
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");
                                isClickable2 = true;
                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            }

                        }
                    }


                } else {
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);


                    if (list.get(position).getReserve().equals("1")) {
                        // viewHolder.ydz.setVisibility(View.INVISIBLE);
                        viewHolder.dianjiwenzi.setText("取消预订");
                        viewHolder.dianji2.setVisibility(View.GONE);
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                    } else {
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);

                        if (list.get(position).getIsQuit() == 2) {
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianjiwenzi.setText("提前退出");
                            viewHolder.dianjiwenzi2.setText("场馆签到");
                            isClickable2 = false;
                        } else {
                            viewHolder.dianjiwenzi.setText("提前退出");
                            viewHolder.dianji.setVisibility(View.VISIBLE);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            isClickable2 = true;

                            LogU.Ld("1608", "是否签到" + list.get(position).getIsSignIn());
                            if (list.get(position).getIsSignIn() == 1) {
                                viewHolder.dianjiwenzi2.setText("已签到");
                                viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                // viewHolder.dianji2.setClickable(false);
                                isClickable2 = false;
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");
                                isClickable2 = true;
                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            }

                        }
                    }

                    //   viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                }

                /*if (list.get(position).getReserve().equals("1")) {
                    // viewHolder.ydz.setVisibility(View.INVISIBLE);
                    viewHolder.dianjiwenzi.setText("取消预订");
                    viewHolder.dianji2.setVisibility(View.GONE);
                    viewHolder.dianji.setVisibility(View.VISIBLE);
                    viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);

                } else {
                    viewHolder.dianji.setVisibility(View.VISIBLE);
                    viewHolder.dianji2.setVisibility(View.VISIBLE);
                }*/
            } else {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianjiwenzi.setText("提前退出");
                        viewHolder.dianjiwenzi2.setText("场馆签到");
                    } else {
                        viewHolder.dianjiwenzi.setText("提前退出");
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);

                        if (list.get(position).getIsSignIn() == 1) {
                            viewHolder.dianjiwenzi2.setText("已签到");
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);

                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }

                    }
                } else {
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianjiwenzi.setText("提前退出");
                        viewHolder.dianjiwenzi2.setText("场馆签到");
                    } else {
                        viewHolder.dianjiwenzi.setText("提前退出");
                        viewHolder.dianji.setVisibility(View.VISIBLE);
                        viewHolder.dianji2.setVisibility(View.VISIBLE);

                        if (list.get(position).getIsSignIn() == 1) {
                            viewHolder.dianjiwenzi2.setText("已签到");
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);

                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                        }

                    }
                    //   viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                }
            }

        } else if (list.get(position).getPublicStatus() == 3) {

                viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.huodongzhong));


            if (list.get(position).getEnd() == 2) {
                viewHolder.dianji.setVisibility(View.GONE);
                viewHolder.dianji2.setVisibility(View.GONE);
            }
           /* if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

            } else {

            }*/
            if (list.get(position).getIsPublisher() == 1) {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setVisibility(View.GONE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                        isClickable2 = false;
                    } else {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuitInGame() == 2) {
                                // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                viewHolder.dianji.setVisibility(View.GONE);
                            } else {
                                viewHolder.dianjiwenzi.setText("中途退出");
                                viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.VISIBLE);
                                viewHolder.tousuzhong.setVisibility(View.GONE);

                            }
                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");
                            isClickable2 = true;

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    }
                } else {
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setVisibility(View.GONE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                        isClickable2 = false;
                    } else {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuitInGame() == 2) {
                                // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                viewHolder.dianji.setVisibility(View.GONE);
                            } else {
                                viewHolder.dianjiwenzi.setText("中途退出");
                                viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.VISIBLE);
                                viewHolder.tousuzhong.setVisibility(View.GONE);

                            }
                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");
                            isClickable2 = true;

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    }
                    // viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                }


                if (list.get(position).getReserve().equals("1")) {
                    // viewHolder.ydz.setVisibility(View.INVISIBLE);
                    viewHolder.dianji.setVisibility(View.GONE);
                    viewHolder.dianji2.setVisibility(View.GONE);
                }
            } else {
                if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setVisibility(View.GONE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                    } else {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuitInGame() == 2) {
                                // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                viewHolder.dianji.setVisibility(View.GONE);
                            } else {
                                viewHolder.dianjiwenzi.setText("中途退出");
                                viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.VISIBLE);
                                viewHolder.tousuzhong.setVisibility(View.GONE);


                            }
                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    }
                } else {
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    if (list.get(position).getIsQuit() == 2) {
                        viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                        viewHolder.dianji.setVisibility(View.GONE);
                        viewHolder.dianji2.setVisibility(View.GONE);

                    } else {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuitInGame() == 2) {
                                // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                viewHolder.dianji.setVisibility(View.GONE);
                            } else {
                                viewHolder.dianjiwenzi.setText("中途退出");
                                viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.VISIBLE);
                                viewHolder.tousuzhong.setVisibility(View.GONE);


                            }
                        } else {
                            viewHolder.dianjiwenzi2.setText("场馆签到");

                            viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                            viewHolder.dianji2.setVisibility(View.VISIBLE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    }
                    // viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                }


            }


        } else if (list.get(position).getPublicStatus() == 4) {

            // viewHolder.dianji2.setVisibility(View.GONE);
           /* //initDownload(list.get(position).getUuid(), viewHolder, position);
            LogU.Ld("1609", "模塑====" + list.get(position).getSportMode());

            if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {
                if (list.get(position).getIsConfirmOver() == 1) {
//                    viewHolder.dianjiwenzi.setText("已确认结束");
                    viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                } else {
                    LogU.Ld("1609", "模塑++++++" + list.get(position).getComplaint() + "==" + list.get(position).getIsConfirmOver());
//                    viewHolder.dianjiwenzi.setText("确认结束");
                    viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                }
                viewHolder.tousuzhong.setVisibility(View.GONE);
            } else {
                viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                if (list.get(position).getIsConfirmOver() == 1) {
//                    viewHolder.dianjiwenzi.setText("已确认结束");
                    viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                    isClickable=false;
                   // isClickable2=false;
                } else {
//                    viewHolder.dianjiwenzi.setText("确认结束");
                    viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                    isClickable=false;
                  //  isClickable2=false;
                }
            }*/


            if (list.get(position).getSportMode() == 2) {
                viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
                viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.tianxiejieguo));

                if (list.get(position).getIsPublisher() == 1) {
                    if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuit() == 1) {
                                if (list.get(position).getIsQuitInGame() == 1) {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.VISIBLE);

                                    if (list.get(position).getIsConfirmResult() == 1) {
                                        viewHolder.dianjiwenzi.setText("已填写");
                                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);


                                    } else if (list.get(position).getIsConfirmResult() == 2) {
                                        viewHolder.dianji2.setVisibility(View.GONE);
                                        viewHolder.dianji.setVisibility(View.GONE);
                                    } else {

                                        //  isClickable=false;
                                        viewHolder.dianjiwenzi.setText("填写结果");
                                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                        // viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                                    }
                                } else {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.GONE);
                                }
                            } else {
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }
                        } else {
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    } else {

                        if (list.get(position).getIsSignIn() == 0 || list.get(position).getIsQuit() == 2 || list.get(position).getIsQuitInGame() == 2) {
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        } else {

                            if (list.get(position).getIsSignIn() == 1) {
                                if (list.get(position).getIsQuit() == 1) {
                                    if (list.get(position).getIsQuitInGame() == 1) {
                                        viewHolder.dianji2.setVisibility(View.GONE);
                                        viewHolder.dianji.setVisibility(View.VISIBLE);

                                        if (list.get(position).getIsConfirmResult() == 1) {
                                            viewHolder.dianjiwenzi.setText("已填写");
                                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);


                                        } else {

                                            viewHolder.dianjiwenzi.setText("填写结果");
                                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                                        }
                                    } else {
                                        viewHolder.dianji2.setVisibility(View.GONE);
                                        viewHolder.dianji.setVisibility(View.GONE);
                                    }
                                } else {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.GONE);
                                }
                            } else {
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }

                        }
                    }
                    if (list.get(position).getReserve().equals("1")) {
                        // viewHolder.ydz.setVisibility(View.INVISIBLE);
                        viewHolder.dianji.setVisibility(View.GONE);
                        viewHolder.dianji2.setVisibility(View.GONE);
                    }
                } else {

                    if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {
                        if (list.get(position).getIsSignIn() == 1) {
                            if (list.get(position).getIsQuit() == 1) {
                                if (list.get(position).getIsQuitInGame() == 1) {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.VISIBLE);

                                    if (list.get(position).getIsConfirmResult() == 1) {
                                        viewHolder.dianjiwenzi.setText("已填写");
                                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);


                                    } else {

                                        //  isClickable=false;
                                        viewHolder.dianjiwenzi.setText("填写结果");
                                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                        // viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                                    }
                                } else {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.GONE);
                                }
                            } else {
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }
                        } else {
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        }


                    } else {

                        if (list.get(position).getIsSignIn() == 0 || list.get(position).getIsQuit() == 2 || list.get(position).getIsQuitInGame() == 2) {
                            viewHolder.dianji2.setVisibility(View.GONE);
                            viewHolder.dianji.setVisibility(View.GONE);
                        } else {

                            if (list.get(position).getIsSignIn() == 1) {
                                if (list.get(position).getIsQuit() == 1) {
                                    if (list.get(position).getIsQuitInGame() == 1) {
                                        viewHolder.dianji2.setVisibility(View.GONE);
                                        viewHolder.dianji.setVisibility(View.VISIBLE);

                                        if (list.get(position).getIsConfirmResult() == 1) {
                                            viewHolder.dianjiwenzi.setText("已填写");
                                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);


                                        } else {

                                            viewHolder.dianjiwenzi.setText("填写结果");
                                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                                        }
                                    } else {
                                        viewHolder.dianji2.setVisibility(View.GONE);
                                        viewHolder.dianji.setVisibility(View.GONE);
                                    }
                                } else {
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.GONE);
                                }
                            } else {
                                viewHolder.dianji2.setVisibility(View.GONE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }

                        }
                    }
                }


            }

            //竞技  填写结果   合作  确认结束
        } else if (list.get(position).getPublicStatus() == 5) {
            viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
            viewHolder.tousuzhong.setVisibility(View.GONE);
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiwancheng));
            viewHolder.dianji.setVisibility(View.GONE);
            viewHolder.dianji2.setVisibility(View.GONE);
            if (list.get(position).getReserve().equals("1")) {
                //  viewHolder.ydz.setVisibility(View.INVISIBLE);

                viewHolder.dianji.setVisibility(View.GONE);
                viewHolder.dianji2.setVisibility(View.GONE);
            }
        } else if (list.get(position).getPublicStatus() == 6) {
            viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
            viewHolder.tousuzhong.setVisibility(View.GONE);
            viewHolder.dianji2.setVisibility(View.GONE);
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.daipingjia));
            viewHolder.dianji.setVisibility(View.VISIBLE);
            viewHolder.dianjiwenzi.setText("去评价");

            LogU.Ld("1610", "评价" + list.get(position).getIsQuit() + "===" + list.get(position).getIsSignIn() + "+++=" + list.get(position).getIsComment());
            if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {
                if (list.get(position).getIsQuit() == 2 || list.get(position).getIsSignIn() == 0) {
                    viewHolder.dianji.setVisibility(View.GONE);
                } else {
                    LogU.Ld("1608", "评价我是====" + list.get(position).getIsComment());

                    if (list.get(position).getComment() == 1) {
                        viewHolder.dianjiwenzi.setText("已评价");
                        viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                        //isClickable2=false;
                    } else if (list.get(position).getComment() == 2) {
                        viewHolder.dianji.setVisibility(View.GONE);
                    } else {

                        viewHolder.dianjiwenzi.setText("去评价");
                        viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                    }
                }

                if (list.get(position).getReserve().equals("1")) {
                    //  viewHolder.ydz.setVisibility(View.INVISIBLE);

                    viewHolder.dianji.setVisibility(View.GONE);
                    viewHolder.dianji2.setVisibility(View.GONE);
                }
            } else {
                viewHolder.dianjiwenzi.setText("去评价");
                viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
            }
        } else if (list.get(position).getPublicStatus() == 7) {
            viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
            viewHolder.tousuzhong.setVisibility(View.GONE);
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yiquxiao));
            viewHolder.dianji.setVisibility(View.GONE);
            viewHolder.dianji2.setVisibility(View.GONE);
            if (list.get(position).getReserve().equals("1")) {
                viewHolder.dianji.setVisibility(View.GONE);
                viewHolder.dianji2.setVisibility(View.GONE);
                // viewHolder.ydz.setVisibility(View.INVISIBLE);
            }
        } else if (list.get(position).getPublicStatus() == 9) {
            viewHolder.zhuangtai_out.setVisibility(View.VISIBLE);
            viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.icon_tousuzhong));
            if (list.get(position).getEnd() == 2) {
                viewHolder.dianji.setVisibility(View.GONE);
                viewHolder.dianji2.setVisibility(View.GONE);
            } else {
                if (list.get(position).getReserve().equals("1")){
                    viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                    viewHolder.dianji.setVisibility(View.GONE);
                    viewHolder.dianji2.setVisibility(View.GONE);
                }else {
                    if (list.get(position).getIsPublisher() == 1) {


                        if (list.get(position).getIsQuit() == 2) {
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setVisibility(View.GONE);
                            viewHolder.dianji2.setVisibility(View.GONE);

                            isClickable2 = false;
                        } else {
                            if (list.get(position).getIsSignIn() == 1) {
                                if (list.get(position).getIsQuitInGame() == 2) {
                                    // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                    viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                    viewHolder.dianji.setVisibility(View.GONE);
                                } else {
                                    viewHolder.dianjiwenzi.setText("中途退出");
                                    viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.VISIBLE);
                                    viewHolder.tousuzhong.setVisibility(View.GONE);

                                }
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");
                                isClickable2 = true;

                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.VISIBLE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }

                        }

                        if (list.get(position).getReserve().equals("1")) {
                            // viewHolder.ydz.setVisibility(View.INVISIBLE);
                            viewHolder.dianji.setVisibility(View.GONE);
                            viewHolder.dianji2.setVisibility(View.GONE);
                        }
                    } else {

                        viewHolder.tousuzhong.setVisibility(View.VISIBLE);
                        if (list.get(position).getIsQuit() == 2) {
                            viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);
                            viewHolder.dianji.setVisibility(View.GONE);
                            viewHolder.dianji2.setVisibility(View.GONE);

                        } else {
                            if (list.get(position).getIsSignIn() == 1) {
                                if (list.get(position).getIsQuitInGame() == 2) {
                                    // viewHolder.dianji2.setBackgroundResource(R.drawable.hui_5dip);
                                    viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);

                                    viewHolder.dianji.setVisibility(View.GONE);
                                } else {
                                    viewHolder.dianjiwenzi.setText("中途退出");
                                    viewHolder.dianji.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                    viewHolder.dianji2.setVisibility(View.GONE);
                                    viewHolder.dianji.setVisibility(View.VISIBLE);
                                    viewHolder.tousuzhong.setVisibility(View.GONE);


                                }
                            } else {
                                viewHolder.dianjiwenzi2.setText("场馆签到");

                                viewHolder.dianji2.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                                viewHolder.dianji2.setVisibility(View.VISIBLE);
                                viewHolder.dianji.setVisibility(View.GONE);
                            }


                        }
                        // viewHolder.dianji.setBackgroundResource(R.drawable.hui_5dip);



                    }
                }

            }
        }

        if (complaint == 0 || complaint == 2 || refereeComplaint == 0 || refereeComplaint == 2) {
            // viewHolder.ydz.setVisibility(View.VISIBLE);
            viewHolder.tousuzhong.setVisibility(View.VISIBLE);
        } else {

            viewHolder.tousuzhong.setVisibility(View.GONE);
        }

        if (list.get(position).getComplaint() == 4 || list.get(position).getComplaint() == 3 || list.get(position).getComplaint() == 1) {

        } else {
            if (list.get(position).getEnd() == 2) {
                viewHolder.dianji.setVisibility(View.GONE);
                viewHolder.dianji2.setVisibility(View.GONE);
            }
        }


        viewHolder.dianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initDownload( list.get(position).getPublicUUID());LogU.Ld("1610", "没点完1" + isClickable + "==" + isClick+"==="+list.get(position).getIsPublisher());

                //是否提前退出
                if (viewHolder.dianjiwenzi.getText().toString().equals("取消预订")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else if (viewHolder.dianjiwenzi.getText().toString().equals("取消发布")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else if (viewHolder.dianjiwenzi.getText().toString().equals("取消报名")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else if (viewHolder.dianjiwenzi.getText().toString().equals("提前退出")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else if (viewHolder.dianjiwenzi.getText().toString().equals("中途退出")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else if (viewHolder.dianjiwenzi.getText().toString().equals("去评价")) {
                    mOnItemDeleteListener.onDeleteClick(position, 1);
                } else {
                    if (list.get(position).getIsConfirmResult() == 0) {
                        if (list.get(position).getIsQuitInGame() == 1) {
                            if (list.get(position).getIsQuit() == 1) {
                                mOnItemDeleteListener.onDeleteClick(position, 1);
                            }
                        }
                    }
                }


            }
        });

        viewHolder.dianji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initDownload( list.get(position).getPublicUUID());

                if (viewHolder.dianjiwenzi2.getText().toString().equals("场馆签到")) {
                    mOnItemDeleteListener.onDeleteClick(position, 2);
                }


            }
        });
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, qiuleiImag, zhuangtai, sex;
        private TextView name, moshi, qiuleiText, renshu, queren, time1, time2, time3, dizhi, fangshi, feiyong, dengji,
                juli, dianjiwenzi, dianjiwenzi2, tousuzhong;
        private RelativeLayout dianji, dianji2;
        private LinearLayout ydz,zhuangtai_out;

        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.my_hd_list_touxiang);
            qiuleiImag = view.findViewById(R.id.my_hd_list_image_qiu);
            zhuangtai = view.findViewById(R.id.my_hd_list_zhuangtai);

            zhuangtai_out = view.findViewById(R.id.zhuangtai_out);
            name = view.findViewById(R.id.my_hd_list_name);
            sex = view.findViewById(R.id.my_hd_list_sex);
            moshi = view.findViewById(R.id.my_hd_list_moshi);
            qiuleiText = view.findViewById(R.id.my_hd_list_text_qiu);
            renshu = view.findViewById(R.id.my_hd_list_renshu);
            queren = view.findViewById(R.id.my_hd_list_queren);
            time1 = view.findViewById(R.id.my_hd_list_time1);
            time2 = view.findViewById(R.id.my_hd_list_time2);
            time3 = view.findViewById(R.id.my_hd_list_time3);
            dizhi = view.findViewById(R.id.my_hd_list_dizhi);
            fangshi = view.findViewById(R.id.my_hd_list_fangshi);
            feiyong = view.findViewById(R.id.my_hd_list_dashang);
            dengji = view.findViewById(R.id.my_hd_list_dengji);
            dianji = view.findViewById(R.id.my_hd_list_dianji);
            dianjiwenzi = view.findViewById(R.id.my_hd_list_text_dianji);
            dianji2 = view.findViewById(R.id.my_hd_list_dianji2);
            dianjiwenzi2 = view.findViewById(R.id.my_hd_list_text_dianji2);
            tousuzhong = view.findViewById(R.id.my_hd_list_tousuzhong);
            ydz = view.findViewById(R.id.cg_ydz);
        }
    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i, int tag);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    private void initDownload(String publishcId, final ViewHolder viewHolder, final int position) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        OkHttpUtils
                .get()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getConfirmButton")
                .addHeader("token", token)
                .addParams("uuid", publishcId)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "获取确认按钮" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HQQREntity entity = gson.fromJson(result, HQQREntity.class);
//                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            LogU.Ld("1610", "比赛结果" + entity.getData().getType());
                            if (entity.getData().getType() == 1) {
                                if (list.get(position).getIsConfirmOver() == 1) {
                                    viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.querenjieshu));
                                    viewHolder.dianjiwenzi.setText("已确认结束");
                                    viewHolder.dianji.setVisibility(View.VISIBLE);
                                } else {
                                    viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.querenjieshu));
                                    viewHolder.dianjiwenzi.setText("确认结束");
                                    viewHolder.dianji.setVisibility(View.VISIBLE);
                                }


                            } else if (entity.getData().getType() == 2) {
                                viewHolder.dianji.setVisibility(View.GONE);
                            } else if (entity.getData().getType() == 0) {
                                viewHolder.zhuangtai.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.tianxiejieguo));
                                viewHolder.dianjiwenzi.setText("填写结果");
                                viewHolder.dianji.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
//    userArrivalSignin

}

