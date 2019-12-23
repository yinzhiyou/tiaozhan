package com.example.android.promoter.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Entity.CGTimeEntity;
import com.example.android.promoter.Home.StartTimeActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class StartTimeListOneAdapter extends BaseAdapter {

    private Context context;
    private int c, i = 0, xiao = 100, da = 100;
    private ListView listView;
    //    private String tag[] = new String[];
//    private List<String> list;
    private SPUtileFQTZ spUtils;
    private List<Integer> xiabiao;
    private List<CGTimeEntity.DataBean> list;
    private List<String> tag;
    private Date date1, date2,date3,date4;
    private     SimpleDateFormat formatter,formatter2;
    private  String str,time,time1;
    private List<String> riqi;
    public StartTimeListOneAdapter(Context context, ListView listView, List<CGTimeEntity.DataBean> list, List<String> tag,List<String> riqi) {
        this.context = context;
        this.listView = listView;
        this.list = list;
        this.tag = tag;
        this.riqi = riqi;
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

    public void setSelectItem(int c) {

        this.c = c;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        spUtils = new SPUtileFQTZ();
//        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
         formatter = new SimpleDateFormat("HH:mm");
        formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
         str = formatter.format(curDate);
        time1 =  formatter2.format(curDate);


        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.start_time_list_one, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.zuo.setText(list.get(position));
//        viewHolder.you.setText(list.get(position));
        viewHolder.you.setText(list.get(position).getStratDateTime());
        viewHolder.zuo.setText(list.get(position).getStratDateTime());
        viewHolder.qian.setText(list.get(position).getCostperhour());
        if (position % 2 == 0) {
            viewHolder.you.setVisibility(View.INVISIBLE);
            viewHolder.zuo.setVisibility(View.VISIBLE);
        } else {
            viewHolder.zuo.setVisibility(View.INVISIBLE);
            viewHolder.you.setVisibility(View.VISIBLE);
        }



        try {
            date1 = formatter.parse(str);//开始时间
            date2 = formatter.parse(list.get(position).getStratDateTime());
//            LogU.Ld("1608", "当前日期" + time1+"---选中日期---"+riqi.get(0));
            if (time1.equals(riqi.get(0))) {
                if (list.get(position).getNotUsedCount() == 0 || date1.getTime() > date2.getTime()) {

                    viewHolder.layout.setBackgroundResource(R.drawable.time_item);//灰色

                } else {
                    viewHolder.layout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                }
            }else{
                if (list.get(position).getNotUsedCount() == 0) {

                    viewHolder.layout.setBackgroundResource(R.drawable.time_item);


                } else {
                    viewHolder.layout.setBackgroundResource(R.drawable.zhifu_cg_yuanjiao);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (tag.get(0).equals("1")) {
//            LogU.Ld("1608", "tag----" + tag.get(0));
            xiao = 100;
            da = 100;
        } else {
//            LogU.Ld("1608", "tag----" + tag.get(0));
        }


        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LogU.Ld("1608", position+"当前时间----" + str+"----开始时间----"+list.get(position).getStratDateTime());
                try {
                    date3 = formatter.parse(str);//开始时间
                    date4 = formatter.parse(list.get(position).getStratDateTime());//
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                tag.clear();
                tag.add("2");
                boolean t = false;
                if (time1.equals(riqi.get(0))) {
                    if (list.get(position).getNotUsedCount() == 0 || date3.getTime() > date4.getTime()) {
//                        Toast.makeText(context, "请选择可选时间", Toast.LENGTH_SHORT).show();
                    }else {
                        if (xiao != 100 && xiao < position) {
                            for (int i = xiao; i <= position; i++) {
                                if (list.get(i).getNotUsedCount() == 0) {
                                    t = true;
                                }
                            }
                        } else if (xiao != 100 && xiao > position) {
                            for (int i = position; i <= xiao; i++) {
                                if (list.get(i).getNotUsedCount() == 0) {
                                    t = true;
                                }
                            }
                        }


                        if (t) {
                            Toast.makeText(context, "时间冲突", Toast.LENGTH_SHORT).show();
                        } else {


                            if (xiao == 100) {
                                xiao = position;
                                da = position + 1;
//                    view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);

//                                LogU.Ld("1608", "第1----" + xiao + "---" + da + "----" + position);
                            } else {
                                if (xiao > position) {
                                    xiao = position;
//                        view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);

//                                    LogU.Ld("1608", "第2---" + xiao + "---" + da + "----" + position);
                                } else if (xiao == position) {
                                    xiao = xiao + 1;
//                                    LogU.Ld("1608", "第3---" + xiao + "---" + da + "----" + position);
                                } else {
                                    if (xiao > position || position >= da) {
                                        if (da >= position + 1) {
                                            da = position;
//                                            LogU.Ld("1608", "第4---" + xiao + "---" + da + "----" + position);
                                        } else {
                                            da = position + 1;
//                                            LogU.Ld("1608", "第5---" + xiao + "---" + da + "----" + position);
//                                view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);
                                        }
                                    } else {
                                        da = position;
//                                        LogU.Ld("1608", "第6----" + xiao + "---" + da + "----" + position);
                                    }
                                }
                            }


                            if (xiao == da) {
//            self.timeLB.text = @"";
//            self.lengthLB.text = @"";
                                xiao = 100;
                            } else {
//            self.timeLB.text = [NSString stringWithFormat:@"%@-%@",self.timeArr[self.minSeleted],self.timeArr[self.maxSeleted]];
//            self.lengthLB.text = [NSString stringWithFormat:@"%.1f小时",0.5*(self.maxSeleted - self.minSeleted)];
                            }


                            mOnItemDeleteListener.onDeleteClick(xiao, da);

                        }
                    }
                }
               else {

                    if (list.get(position).getNotUsedCount() == 0 ) {
                        Toast.makeText(context, "请选择可选时间", Toast.LENGTH_SHORT).show();
                    }else {
                    if (xiao != 100 && xiao < position) {
                        for (int i = xiao; i <= position; i++) {
                            if (list.get(i).getNotUsedCount() == 0) {
                                t = true;
                            }
                        }
                    } else if (xiao != 100 && xiao > position) {
                        for (int i = position; i <= xiao; i++) {
                            if (list.get(i).getNotUsedCount() == 0) {
                                t = true;
                            }
                        }
                    }


                    if (t) {
                        Toast.makeText(context, "时间冲突", Toast.LENGTH_SHORT).show();
                    } else {


                        if (xiao == 100) {
                            xiao = position;
                            da = position + 1;
//                    view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);

//                            LogU.Ld("1608", "第1----" + xiao + "---" + da + "----" + position);
                        } else {
                            if (xiao > position) {
                                xiao = position;
//                        view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);

//                                LogU.Ld("1608", "第2---" + xiao + "---" + da + "----" + position);
                            } else if (xiao == position) {
                                xiao = xiao + 1;
//                                LogU.Ld("1608", "第3---" + xiao + "---" + da + "----" + position);
                            } else {
                                if (xiao > position || position >= da) {
                                    if (da >= position + 1) {
                                        da = position;
//                                        LogU.Ld("1608", "第4---" + xiao + "---" + da + "----" + position);
                                    } else {
                                        da = position + 1;
//                                        LogU.Ld("1608", "第5---" + xiao + "---" + da + "----" + position);
//                                view.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    da = position;
//                                    LogU.Ld("1608", "第6----" + xiao + "---" + da + "----" + position);
                                }
                            }
                        }


                        if (xiao == da) {
//            self.timeLB.text = @"";
//            self.lengthLB.text = @"";
                            xiao = 100;
                        } else {
//            self.timeLB.text = [NSString stringWithFormat:@"%@-%@",self.timeArr[self.minSeleted],self.timeArr[self.maxSeleted]];
//            self.lengthLB.text = [NSString stringWithFormat:@"%.1f小时",0.5*(self.maxSeleted - self.minSeleted)];
                        }


                        mOnItemDeleteListener.onDeleteClick(xiao, da);

                    }
                }}
            }
        });


        if (xiao != da) {
//            LogU.Ld("1608", "算法1---" + xiao + "---" + da + "----" + position);
            if (xiao <= position && position < da) {
//                for (int i = xiao;i<da;i++){

                viewHolder.xuanzhong.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.VISIBLE);
//                LogU.Ld("1608", "算法2----" + xiao + "---" + da + "----" + i);

//                }
            } else {
//                LogU.Ld("1608", "算法3----" + xiao + "---" + da + "----" + tag.get(0));
                viewHolder.xuanzhong.findViewById(R.id.start_time_list_one_xuanzhong).setVisibility(View.GONE);
            }

        }

        if((list.size()-1)%2 == 0){
            if (position == list.size()-1){
                viewHolder.youxia.setVisibility(View.VISIBLE);
                viewHolder.youxia.setText(list.get(list.size()-1).getEndDateTime());
                viewHolder.zuoxia.setVisibility(View.GONE);

            }else{
                viewHolder.youxia.setVisibility(View.GONE);
            }
        }else {
            if (position == list.size()-1){
                viewHolder.zuoxia.setVisibility(View.VISIBLE);
                viewHolder.zuoxia.setText(list.get(list.size()-1).getEndDateTime());
                viewHolder.youxia.setVisibility(View.GONE);


            }else{
                viewHolder.zuoxia.setVisibility(View.GONE);
            }
        }

        return convertView;
    }


    public class ViewHolder {
        private TextView zuo, you, qian,zuoxia,youxia;
        private RelativeLayout layout;
        private ImageView xuanzhong;

        public ViewHolder(View view) {
            you = view.findViewById(R.id.start_time_list_one_you);
            zuo = view.findViewById(R.id.start_time_list_one_zuo);
            layout = view.findViewById(R.id.start_time_list_one_layout);
            qian = view.findViewById(R.id.start_time_list_one_qian);
            xuanzhong = view.findViewById(R.id.start_time_list_one_xuanzhong);
            zuoxia = view.findViewById(R.id.start_time_list_one_zuo_xia);
            youxia = view.findViewById(R.id.start_time_list_one_you_xia);
        }
    }


    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i, int b);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }


}