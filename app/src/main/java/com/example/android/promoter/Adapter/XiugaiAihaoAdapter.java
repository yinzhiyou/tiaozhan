package com.example.android.promoter.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.promoter.Entity.YundongEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.DataBase;
import com.example.android.promoter.Toos.LogU;

import java.util.ArrayList;
import java.util.List;

public class XiugaiAihaoAdapter extends BaseAdapter {
    private Context context;
    private List<YundongEntity.DataBean> channelList;
    private JsCallBack jsCallBack;
    private int num=1;
    public JsCallBack getJsCallBack() {
        return jsCallBack;
    }

    public void setJsCallBack(JsCallBack jsCallBack) {
        this.jsCallBack = jsCallBack;
    }

    private boolean[] isCheck;
    //数据库
    private DataBase dataBase;
    public XiugaiAihaoAdapter(Context context,List<YundongEntity.DataBean> channelList) {
        this.context = context;
        this.channelList = channelList;

        dataBase = new DataBase(context);
        //给数组设置大小,并且全部赋值为false
        if (channelList != null) {
            isCheck = new boolean[channelList.size()];
            for (int i = 0; i < channelList.size(); i++) {
                isCheck[i] = false;
            }
        }
    }

    @Override
    public int getCount() {
        return channelList.size();
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
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.xiugai_aihao_grid, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.textView.setText(channelList.get(position).getName());
        /*
        数组里面放置的是所有的position的是否选中的状态
        如果当前position的状态是true就是选中状态
        如果当前position的状态是false就不是选中状态
         */
        LogU.Ld("1608", isCheck.length+"下标" + position);

        if (isCheck[position]) {
            viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.ellipse_home_details);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.my_tab));


        } else {
            viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.xiugai_huodong_layout);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.login_forget));

        }

        /*viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    jsCallBack.IntPosition(position,num);


            }
        });*/
        /**
         * 刚进去就去拿数据库里面以前选中的数据
         * 如果数据库拿到的数据和集合里面取出来的数据一样,说明当前position处于选中状态,
         * 并且数组里面的当前position也要赋值true
         */

        List<String> choiceChannel = getData();
        LogU.Ld("1608", choiceChannel.size()+"适配器选中" + getData());
        for (int j = 0; j < choiceChannel.size(); j++) {
            if (String.valueOf(channelList.get(position).getId()).equals(choiceChannel.get(j))) {
                viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.ellipse_home_details);
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.my_tab));
                isCheck[position] = true;
            }

        }

        return convertView;
    }


    public class ViewHolder {
        private TextView textView;
        private RelativeLayout mRelativeLayout;
        public ViewHolder(View view) {
            textView = view.findViewById(R.id.xiugai_aihao_text);
            mRelativeLayout = view.findViewById(R.id.xiugai_aihao_layout);
        }
    }
    /**
     * 改变某一个选项的状态
     * @param post
     */
    public void choiceState(int post) {
        /**
         *  传递过来所点击的position,如果是本身已经是选中状态,就让他变成不是选中状态,
         *  如果本身不是选中状态,就让他变成选中状态
         */

        isCheck[post] = isCheck[post] == true ? false : true;
        if (isCheck[post]) {
            //如果当前position是选中状态的的position也就是当前position是true,就把集合当前position的值存入数据库
            dataBase.insert(channelList.get(post).getId()+"");

        } else {
            //反之则从数据库里面删除
            dataBase.delete(channelList.get(post).getId()+"");
        }
        this.notifyDataSetChanged();
    }
    /**
     * 通过查找数据库,拿到里面的数据
     */
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        Cursor query = DataBase.getInstances(context).query();
        if(query!=null) {
            if (query.moveToFirst()) {
                do {
                    String channel = query.getString(query.getColumnIndex("channel"));
                    list.add(channel);
                } while (query.moveToNext());
            }
        }
        //关闭查询游标
        query.close();
        return list;
    }

    public interface JsCallBack{
        void IntPosition(int position,int num);
    }



}