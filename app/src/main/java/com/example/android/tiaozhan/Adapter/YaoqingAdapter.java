package com.example.android.tiaozhan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.tiaozhan.Entity.CJYQEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.YQTimeYesNoEntity;
import com.example.android.tiaozhan.Entity.YaoqingEntidy;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtileFQTZ;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.example.android.tiaozhan.Toos.ToastUitl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class YaoqingAdapter extends BaseAdapter {

    private Context context;
    private List<YaoqingEntidy.DataBean.LstBean> list;
    private SPUtileFQTZ spUtileFQTZ;
    private SPUtils spUtils;
    private String tab ,touxiang,token,timeRI,timeSHI,fqtzXiangmudaid,fqtzXiangmuid,jieguo,uuid,FirstSportId,SecondSportId,team;
    private Activity act;
    private List<String> listA,listB;
    public YaoqingAdapter(Context context, List<YaoqingEntidy.DataBean.LstBean> list,String tab,Activity act,String sportid) {
        this.context = context;
        this.list = list;
        this.tab = tab;
        this.act = act;
        this.listA = listA;
        this.listB = listB;
        this.FirstSportId=sportid;
    }
    public YaoqingAdapter(Context context, List<YaoqingEntidy.DataBean.LstBean> list,String tab,Activity act,String uuid,String FirstSportId,
                          String SecondSportId,String team) {
        this.context = context;//FirstSportId,SecondSportId,uuid
        this.list = list;
        this.tab = tab;
        this.act = act;
        this.uuid = uuid;
        this.FirstSportId = FirstSportId;
        this.SecondSportId = SecondSportId;
        this.team = team;
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
        spUtileFQTZ = new SPUtileFQTZ();
        touxiang =  (String) spUtileFQTZ.get(context, "YQtouxiang", "");
        timeRI = (String) spUtileFQTZ.get(context, "timeRI", "无");
        timeSHI = (String) spUtileFQTZ.get(context, "timeSHI", "无");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(context, "fqtzXiangmudasportId", "无");
        fqtzXiangmuid = (String) spUtileFQTZ.get(context, "fqtzXiangmusportId", "无");


        spUtils = new SPUtils();

        token = (String) spUtils.get(context, "logintoken", "");
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.yqhy_list_adapter, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        Glide.with(context).load(context.getResources().getString(R.string.imgurl)+list.get(position).getUserInfo().getImgURL()).into(viewHolder.touxiang);

        if (list.get(position).getUserInfo().getSex() == 0){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbienan));
        }else   if (list.get(position).getUserInfo().getSex() == 1){
            viewHolder.sex.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.xingbie));
        }
        if (FirstSportId.equals("1")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.yumaoqiu));
        }else  if (FirstSportId.equals("2")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.pingpangqiu));
        }else if (FirstSportId.equals("3")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.taiqiu));
        }else if (FirstSportId.equals("4")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.lanqiu));
        }else if (FirstSportId.equals("5")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.zuqiu));
        }else if (FirstSportId.equals("6")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.paiqiu));
        }else if (FirstSportId.equals("7")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.wangqiu));
        }else if (FirstSportId.equals("8")){
            viewHolder.qiuImage.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.gaoerfu));
        }

        viewHolder.name.setText(list.get(position).getUserInfo().getNickname());
        viewHolder.jibie.setText(list.get(position).getUserLevel());
        viewHolder.nianling.setText("年龄："+list.get(position).getUserInfo().getAge()+"岁");
        viewHolder.tizhong.setText("体重："+list.get(position).getUserInfo().getWeight()+"kg");
        viewHolder.shengao.setText("身高："+list.get(position).getUserInfo().getTall()+"cm");
        viewHolder.yundong.setText("喜爱运动项目："+list.get(position).getSportidNameStr());
        viewHolder.haoyou_juli.setText(list.get(position).getRange()+"km");

        viewHolder.anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.Ld("1608","邀请"+token+"=="+uuid+"==="+team);
                if (tab.equals("1")||tab.equals("2")){
                   /* OkHttpUtils
                            .post()
                            .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/judge")
                            .addHeader("token",token)
                            .addParams("uuid",list.get(position).getPlayerUUID())
                            .addParams("StartTime",1+"")
                            .addParams("PlayTime",team)
                            .addParams("Agemin",uuid)
                            .addParams("Agemax",1+"")
                            .addParams("SportId",team)
                            .addParams("TeamMateSex",uuid)
                            .addParams("SportMode",1+"")
                            .addParams("TeamMateLevelMin",team)
                            .addParams("TeamMateLevelMax",team)
                            .build()*/
                    OkHttpUtils
                            .post()
                            .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition")
                            .addHeader("token", token)
                            .addParams("uuid", list.get(position).getPlayerUUID())
                            .addParams("mark", 2 + "")
                            .addParams("team", team)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    LogU.Ld("1608", "创建邀请失败" + e.getMessage());
                                }
                                @Override
                                public void onResponse(String response, int id) {
                                    String result = response.toString();
                                    LogU.Ld("1608", "创建成功后邀请" + result);
                                    Boolean a = result.indexOf("2000") != -1;
                                    if (a) {
                                        initDownload(list.get(position).getFriendUUID(),position);
                                    } else {
                                        Gson gson = new Gson();
                                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                        ToastUitl.longs(entity.getMsg());

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                                    }
                                }
                            });

                    LogU.Ld("1608","邀请好友走了"+tab);

                }else {
                    OkHttpUtils
                            .post()
                            .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition")
                            .addHeader("token", token)
                            .addParams("uuid", uuid)
                            .addParams("mark", 2 + "")
                            .addParams("team", team)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    LogU.Ld("1608", "创建邀请失败" + e.getMessage());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    String result = response.toString();
                                    LogU.Ld("1608", "创建成功后邀请" + result);
                                    Boolean a = result.indexOf("2000") != -1;
                                    if (a) {
                                        yaoqing(list.get(position).getFriendUUID(), team);
                                    } else {
                                        Gson gson = new Gson();
                                        JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                                        ToastUitl.longs(entity.getMsg());

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                                    }
                                }
                            });

                }

            }

        });
        return convertView;
    }


    public class ViewHolder {
        private ImageView touxiang, sex, qiuImage;
        private TextView name, jibie, nianling, tizhong, shengao, yundong ,haoyou_juli;
        private RelativeLayout anniu;
        public ViewHolder(View view) {

            touxiang = view.findViewById(R.id.haoyou_touxiang);

            sex = view.findViewById(R.id.haoyou_sex);
            qiuImage = view.findViewById(R.id.haoyou_image_qiu);
            name = view.findViewById(R.id.haoyou_name);
            jibie = view.findViewById(R.id.haoyou_jibie);
            nianling = view.findViewById(R.id.haoyou_nianling);
            tizhong = view.findViewById(R.id.haoyou_tizhong);
            shengao = view.findViewById(R.id.haoyou_shengao);
            yundong = view.findViewById(R.id.haoyou_yundong);
            anniu = view.findViewById(R.id.haoyou_anniu);
            haoyou_juli = view.findViewById(R.id.haoyou_juli);

        }

    }



    private void initDownload(String invitedId, final int position) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
//        LogU.Ld("1608", "邀请好友--" + token+"city--"+city+"area--"+area+"mylat--"+mylat+"mylng--"+mylng+"type--"+type+"sportid--"+sportid+"teamSex--"+teamSex
//                +"minlevel--"+minlevel+"maxlevel--"+maxlevel+"team--"+team);
        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkActiveTime")
                .addHeader("token",token)
                .addParams("invitedId",invitedId)
                .addParams("startTime",timeRI + " " + timeSHI)
                .addParams("FirstSportId",fqtzXiangmudaid)
                .addParams("playTime","2")
                .addParams("SecondSportId",fqtzXiangmuid)
                .addParams("teamSex","2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogU.Ld("1608", "邀请好友" + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "邀请好友时间判断" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            YQTimeYesNoEntity entity = gson.fromJson(result, YQTimeYesNoEntity.class);
                            jieguo = "1";

                            String tag1 = "0";
                            for(int i = 0;i<listA.size();i++){
                                if (listA.get(i).equals(list.get(position).getUserInfo().getImgURL())){
                                    tag1 = "1";
                                }

                            }
                            for(int i = 0;i<listB.size();i++) {
                                if (listB.get(i).equals(list.get(position).getUserInfo().getImgURL())) {
                                    tag1 = "1";
                                }
                            }
                            if (tag1.equals("1")){
                                Toast.makeText(context, "他已经在您的活动中", Toast.LENGTH_SHORT).show();
                            }else {
                                spUtileFQTZ.put(context, "YQtab",tab);
                                spUtileFQTZ.put(context, "YQtouxiang",list.get(position).getUserInfo().getImgURL());
                                spUtileFQTZ.put(context, "YQuuid",list.get(position).getFriendUUID());
                                spUtileFQTZ.put(context, "YQdengji",list.get(position).getUserLevel());
                              //  spUtileFQTZ.put(context, "YQname",list.get(position).getNickName());
LogU.Ld("1608","邀请好友"+list.get(position).getNickName()+"===="+list.get(position).getFriendUUID()+"=="+list.get(position).getUserLevel());
                                act.finish();
                            }
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            jieguo = "2";
//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    //创建成功后邀请
    private void yaoqing(String invitedId,String team) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952

        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/InviteFriends")
                .addHeader("token",token)
                .addParams("publishId",uuid)
                .addParams("invitedId",invitedId)
                .addParams("team",team)
                .addParams("SecondSportId",SecondSportId)
                .addParams("FirstSportId",FirstSportId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "创建邀请失败" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "创建成功后邀请" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            CJYQEntity entity = gson.fromJson(result, CJYQEntity.class);
//                            List<YaoqingEntidy.DataBean.LstBean> list;
//                            list = entity.getData().getLst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
                            ToastUitl.longs(entity.getMsg());
                            act.finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }


    //验证发布和加入的条件
    private void getCondition(String invitedId,String team) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952

        OkHttpUtils
                .post()
                .url(context.getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getCondition")
                .addHeader("token",token)
                .addParams("uuid",uuid)
                .addParams("mark",invitedId)
                .addParams("team",team)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogU.Ld("1608", "创建邀请失败" + e.getMessage());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "创建成功后邀请" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            ToastUitl.longs(entity.getMsg());

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
}