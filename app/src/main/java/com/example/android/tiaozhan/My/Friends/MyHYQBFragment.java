package com.example.android.tiaozhan.My.Friends;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.YQHYListAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.HaoyouEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


//全部
public class MyHYQBFragment extends Fragment {

    private PullToRefreshListView listView;
    private YQHYListAdapter adapter;
    private SPUtils spUtils;
    private String token,bieming;
    private List<HaoyouEntity.DataBean.LstBean> data;
    private int page = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_haoyou_quanbu,container,false);

        listView = rootView.findViewById(R.id.my_haoyou_quanbu);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        data = new ArrayList<>();
        adapter = new YQHYListAdapter(getContext(),data);

        spUtils = new SPUtils();
        token = (String) spUtils.get(getContext(), "logintoken", "");
        bieming = (String) spUtils.get(getContext(), "bieming", "");
        listView.setAdapter(adapter);

        initDownload(page);
        shuaxin();
        return rootView;

    }

    private void shuaxin() {


        listView
                .setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
                {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullDownToRefresh");
                        //这里写下拉刷新的任务

                        page = 1;
                        data.clear();
                        LogU.Ld("1608", "下拉" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<ListView> refreshView)
                    {
                        Log.e("TAG", "onPullUpToRefresh");
                        //这里写上拉加载更多的任务
                        page++;
                        LogU.Ld("1608", "上啦" + page+"");
                        initDownload(page);
//                        listView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                listView.onRefreshComplete();
//                            }
//                        }, 1000);
                    }
                });

    }
    private void initDownload(final int page) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "好友全部" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMyFriends")
                .addHeader("token",token)
                .addParams("type","all")
                .addParams("term","myfriend")
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "好友全部" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HaoyouEntity entity = gson.fromJson(result, HaoyouEntity.class);
                            List<HaoyouEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
                            if (page == 1){
                                data.clear();
                                data.addAll(list);

                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    final String  b= data.get(position-1).getUuid().replace("-","");
                                    spUtils.put(getContext(), "haoyouname", data.get(position-1).getUserInfo().getNickname());
                                    spUtils.put(getContext(), "haoyoutouxiang", data.get(position-1).getUserInfo().getImgURL());
                                    spUtils.put(getContext(), "haoyouid", b);

                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();//传值
                                    intent.setClass(getContext(),LiaoTianActivity.class);
                                    intent.putExtra(EaseConstant.EXTRA_USER_ID, b);  //对方账号

                                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
//                                    intent.putExtra(EaseConstant.EXTRA_USER_NAME,data.get(position-1).getUserInfo().getNickname());
                                    bundle.putString("type", "1");
                                    intent.putExtras(bundle);
                                    startActivity(intent);


//
//                                    LogU.Ld("1608","别名"+bieming);
//                                    EMClient.getInstance().login(bieming,"tz1",new EMCallBack() {//回调
//                                        @Override
//                                        public void onSuccess() {
//                                            getActivity().runOnUiThread(new Runnable() {
//                                                public void run() {
//                                                    EMClient.getInstance().groupManager().loadAllGroups();
//                                                    EMClient.getInstance().chatManager().loadAllConversations();
//                                                    LogU.Ld("1608", "登录聊天服务器成功！");
//                                                    Intent intent = new Intent();
//                                                    intent.setClass(getContext(),LiaoTianActivity.class);
//                                                    intent.putExtra(EaseConstant.EXTRA_USER_ID, b);  //对方账号
//                                                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
//                                                    startActivity(intent);
//                                                }
//                                            });
//                                        }
//
//
//                                        @Override
//                                        public void onProgress(int progress, String status) {
//
//                                        }
//
//                                        @Override
//                                        public void onError(int code, String message) {
//                                            LogU.Ld("1608", "登录聊天服务器失败！");
//                                        }
//                                    });
//
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(getContext(),DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        initDownload(page);
        super.onResume();
    }


}
