package com.example.android.promoter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.FQTZXiangmuEntity;
import com.example.android.promoter.Home.FQTZXiangmuActivity;
import com.example.android.promoter.Home.FaqiTiaozhanActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;

import java.util.List;

public class FQTZXiangmulistAdapter extends BaseAdapter {
    private Context context;
    private List<FQTZXiangmuEntity.DataBean> list;
    //    private List<YundongTwoEntity.DataBean> data;
    private FQTZXiangmuGridAdapter adapter3;
    private SPUtileFQTZ spUtils;
    private String yId;
    private SPUtils spUtils1;

    public FQTZXiangmulistAdapter(Context context, List<FQTZXiangmuEntity.DataBean> list) {
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
        ViewHolder viewHolder;
        spUtils = new SPUtileFQTZ();
        spUtils1 = new SPUtils();

        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.fqtz_xiangmu_list, parent, false);
            viewHolder = new ViewHolder(convertView);
            yId = (String) spUtils1.get(context, "yId", "");

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position).getName());


        final List<FQTZXiangmuEntity.DataBean.SportTypeBean> list2 = list.get(position).getSportType();
        adapter3 = new FQTZXiangmuGridAdapter(context, list2);

        viewHolder.gridView.setAdapter(adapter3);
        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positiona, long id) {
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                intent.setClass(context, FaqiTiaozhanActivity.class);
//                bundle.putString("tab",list2.get(position).getName());
//                intent.putExtras(bundle);
//                context.startActivity(intent);

                spUtils.put(context, "fqtzXiangmu", list2.get(positiona).getName());
                spUtils.put(context, "fqtzXiangmusportId", list2.get(positiona).getId()+"");
                spUtils.put(context, "canyurenshu", list2.get(positiona).getPlayerNumber()+"");
                spUtils.put(context, "fqtzXiangmuda", list.get(position).getName());
                spUtils.put(context, "fqtzXiangmudasportId", list.get(position).getId()+"");
                spUtils.put(context, "jingjiYesNo", list.get(position).getAllowCompitationMode()+"");
                spUtils.put(context, "yuleYesNO", list.get(position).getAllowFunyMode()+"");
                spUtils.put(context, "peilianYesNO", list.get(position).getAllowTrainingMode()+"");
                spUtils.put(context, "moshiTag", "1");
                spUtils.put(context, "fqtzqiurenshu", list2.get(positiona).getPlayerNumber()+"");


                spUtils.remove(context,"cgname");
                spUtils.remove(context,"cgid");
                if (FQTZXiangmuActivity.class.isInstance(context)) {
                    // 转化为activity，然后finish就行了
                    FQTZXiangmuActivity activity = (FQTZXiangmuActivity) context;
                    Intent intent = new Intent();
                    intent.setClass(context,FaqiTiaozhanActivity.class);
                    intent.putExtra("tagId",1+"");

                    if (yId.equals("1")){
                        intent.putExtra("ydId",1+"");
                        spUtils1.remove(context,"yId");
                        LogU.Ld("1609","传值==="+yId);
                    }

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    activity.startActivity(intent);

                    activity.finish();
                }



            }
        });
        if (position % 2 != 0)

        {
            viewHolder.beijing.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }


        return convertView;
    }


    public class ViewHolder {
        private TextView textView;
        private GridView gridView;
        private LinearLayout beijing;

        public ViewHolder(View view) {
            textView = view.findViewById(R.id.fqtz_xiangmu_list_text);
            gridView = view.findViewById(R.id.fqtz_xiangmu_list_grid);
            beijing = view.findViewById(R.id.fqtz_xiangmu_list_beijing);
        }


    }

}