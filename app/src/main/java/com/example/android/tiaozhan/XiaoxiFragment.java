package com.example.android.tiaozhan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Adapter.XiaoxiLTAdapter;
import com.example.android.tiaozhan.Adapter.XiaoxiXTAdapter;
import com.example.android.tiaozhan.Denglu.DengluActivity;
import com.example.android.tiaozhan.Entity.HXchaxunEntity;
import com.example.android.tiaozhan.Entity.HXqunchaxunEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.XiaoxiXTEntity;
import com.example.android.tiaozhan.Home.XiaoxiItem;
import com.example.android.tiaozhan.My.Friends.LiaoTianActivity;
import com.example.android.tiaozhan.Toos.BaseFragment;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 消息中心
 */
public class XiaoxiFragment extends BaseFragment implements View.OnClickListener {
    private PullToRefreshListView listView;
    private TextView biaoti, xitongText, liaotianText;
    private ImageView fanhui, xitongImage, liaotianImage;
    private LinearLayout xitong, liaotian;
    private XiaoxiXTAdapter adapter;
    private XiaoxiLTAdapter ltAdapter;
    private List<XiaoxiXTEntity.DataBean.LstBean> data;

    private String token, touxiang, nickname, uuid;
    private SPUtils spUtils;
    private int page = 1;

    private EaseConversationListFragment easeConversationListFragment;
    private ActionBar actionBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_xiaoxi, container, false);

        listView = rootView.findViewById(R.id.xiaoxi_list);
        biaoti = rootView.findViewById(R.id.biaoti);
        fanhui = rootView.findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        fanhui.setVisibility(View.GONE);
        xitong = rootView.findViewById(R.id.xiaoxi_xitong);
        xitong.setOnClickListener(this);
        liaotian = rootView.findViewById(R.id.xiaoxi_liaotian);
        liaotian.setOnClickListener(this);
        xitongText = rootView.findViewById(R.id.xiaoxi_xitong_text);
        xitongImage = rootView.findViewById(R.id.xiaoxi_xitong_image);
        liaotianText = rootView.findViewById(R.id.xiaoxi_liaotian_text);
        liaotianImage = rootView.findViewById(R.id.xiaoxi_liaotian_image);

        ltAdapter = new XiaoxiLTAdapter(getActivity());

        data = new ArrayList<>();
        adapter = new XiaoxiXTAdapter(getActivity(), data);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        spUtils = new SPUtils();
        token = (String) spUtils.get(getActivity(), "logintoken", "");
        uuid = (String) spUtils.get(getActivity(), "uuid", "");
        adapter.setOnItemDeleteClickListener(new XiaoxiXTAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int position) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         *要执行的操作
                         */
                        initDownload(page);
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);//3秒后执行Runnable中的run方法


                LogU.Ld("1608", "点击了");
            }
        });


        listView.setAdapter(adapter);
        biaoti.setText("消息中心");
        shuaxin();
        initDownload(page);


        return rootView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
//                finish();
                break;
            case R.id.xiaoxi_xitong:
                xitongImage.setBackground(getResources().getDrawable(R.mipmap.xtxxhong));
                liaotianImage.setBackground(getResources().getDrawable(R.mipmap.ltxxhuang));
                listView.setAdapter(adapter);
                listView.setMode(PullToRefreshBase.Mode.BOTH);
//                listView.setOnRefreshListener(mListViewOnRefreshListener2);
                listView.setVisibility(View.VISIBLE);
                break;
            case R.id.xiaoxi_liaotian:
                xitongImage.setBackground(getResources().getDrawable(R.mipmap.xxxxhuang));
                liaotianImage.setBackground(getResources().getDrawable(R.mipmap.ltxxhong));
//                listView.setAdapter(ltAdapter);
//                listView.setMode(PullToRefreshBase.Mode.BOTH);
                listView.setVisibility(View.GONE);
//                listView.setOnRefreshListener(mListViewOnRefreshListener2);

                init();

                break;
        }
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
        LogU.Ld("1608", "消息中心" + token);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getMessagesLst")
                .addHeader("token", token)
                .addParams("msgCate", "systems")
                .addParams("page", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "消息中心" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            XiaoxiXTEntity entity = gson.fromJson(result, XiaoxiXTEntity.class);
                            final List<XiaoxiXTEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
//                            data.clear();
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
                                    LogU.Ld("1608","点击了");
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    intent.setClass(getContext(),XiaoxiItem.class);
                                    bundle.putString("uuid",data.get(position-1).getUuid());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(getActivity(), entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(getActivity(),DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });

    }

    private void init(){
        easeConversationListFragment=new EaseConversationListFragment();
        easeConversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {

//                EMClient.getInstance().groupManager().getAllGroups().toString();
//                Map<String, Object> ext = null;
//                EMMessage message = conversation.getLatestMessageFromOthers();


//                LogU.Ld("1608", "chaxun" +  EMClient.getInstance().groupManager().getGroup(conversation.conversationId()).toString() );


//                Map<String, Object> ext = message.ext();
//                ext.toString();
//                ext.get("subject").toString()
//                LogU.Ld("1608", "chaxun" +    conversation.getType()+conversation.conversationId().matches("[0-9]+")+ ext.toString());
                if (conversation.conversationId().matches("[0-9]+")){
                    if (EMClient.getInstance().groupManager().getGroup(conversation.conversationId()) != null){
                        chaxunqun(EMClient.getInstance().groupManager().getGroup(conversation.conversationId()).toString(),"2",conversation.conversationId());

                    }else{
                        Intent intent=new Intent(getActivity(),LiaoTianActivity.class);
                        Bundle bundle = new Bundle();//传值
                        //传入参数
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.GroupChat); //群聊模式
                        intent.putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
                        bundle.putString("type", "2");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }else{
                    chaxun(conversation.conversationId(),"1");
                }


                //进入聊天页面
//                startActivity(new Intent(getActivity(), LiaoTianActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
//                Intent intent = new Intent();
//                intent.setClass(getContext(),LiaoTianActivity.class);
//                intent.putExtra(EaseConstant.EXTRA_USER_ID, b);  //对方账号
//                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
//                startActivity(intent);
            }
        });

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_chat,easeConversationListFragment)
                .commit();

    }
    //查询群昵称
    private void chaxunqun(final String publishcId, final String tag, final String qunid) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        Log.d("1608", "群昵称---" + "---publishcId---" + publishcId);
        OkHttpUtils
                .get()
                .url("https://app.tiaozhanmeiyitian.com/api" + "/getUserimg")
                .addParams("orderId", publishcId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "群查询" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HXqunchaxunEntity entity = gson.fromJson(result, HXqunchaxunEntity.class);

                            spUtils.put(getContext(), "haoyouname",entity.getData().getGroup_name());
                            Intent intent=new Intent(getActivity(),LiaoTianActivity.class);
                            Bundle bundle = new Bundle();//传值
                            //传入参数
                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.GroupChat); //群聊模式
                            intent.putExtra(EaseConstant.EXTRA_USER_ID,qunid);
                            bundle.putString("type", tag);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {

                        }
                    }
                });

    }


    //查询昵称
    private void chaxun(final String publishcId, final String tag) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "单聊昵称---" + token + "---publishcId---" + publishcId);
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/user")
                .addParams("imid", publishcId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "单聊昵称" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HXchaxunEntity entity = gson.fromJson(result, HXchaxunEntity.class);

                            spUtils.put(getContext(), "haoyouname",entity.getData().getNickname());
                            Intent intent=new Intent(getActivity(),LiaoTianActivity.class);
                            Bundle bundle = new Bundle();//传值
                            //传入参数
                            intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                            if (tag.equals("1")){
                                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                            }else{
                                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.GroupChat); //群聊模式
                            }

                            intent.putExtra(EaseConstant.EXTRA_USER_ID,publishcId);
                            bundle.putString("type", tag);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else {

                        }
                    }
                });

    }


    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //接收到新的消息
//            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
////            EaseUI.getInstance().getNotifier().vibrateAndPlayTone(message);
//            r.play();

            refreshUIWithMessage();
            for (EMMessage message : list) {
                String userName = message.getStringAttribute("nickname", "");
                String userPic = message.getStringAttribute("avatar", "");
                LogU.Ld("1608","nickname---------"+userName+"avatar"+userPic);
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {
            LogU.Ld("1608","我是穿透---------");
        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };

    private void refreshUIWithMessage() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                // refresh conversation list
                if (easeConversationListFragment != null) {
                    easeConversationListFragment.refresh();
                }
            }
        });
    }

    @Override
    public void onResume() {
        initDownload(page);
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
    }

    @Override
    public void onRestart() {

        super.onRestart();

    }
}

