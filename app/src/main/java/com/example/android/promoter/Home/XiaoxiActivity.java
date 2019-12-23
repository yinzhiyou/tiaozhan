package com.example.android.promoter.Home;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.XiaoxiLTAdapter;
import com.example.android.promoter.Adapter.XiaoxiXTAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.MyQBMXEntity;
import com.example.android.promoter.Entity.XiaoxiXTEntity;
import com.example.android.promoter.My.Friends.LiaoTianActivity;
import com.example.android.promoter.My.QBMXActivity;
import com.example.android.promoter.My.QBMXItemActivity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtils;
import com.google.gson.Gson;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.util.EMLog;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 *消息列表
 */
public class XiaoxiActivity extends AppCompatActivity implements View.OnClickListener {

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


//    @Override
//    public int initContentView() {
//        return R.layout.activity_xiaoxi;
//    }

//    @Override
//    protected void initUIAndListener() {
//        listView = findViewById(R.id.xiaoxi_list);
//        biaoti = findViewById(R.id.biaoti);
//        fanhui = findViewById(R.id.fanhui);
//        fanhui.setOnClickListener(this);
//        xitong = findViewById(R.id.xiaoxi_xitong);
//        xitong.setOnClickListener(this);
//        liaotian = findViewById(R.id.xiaoxi_liaotian);
//        liaotian.setOnClickListener(this);
//        xitongText = findViewById(R.id.xiaoxi_xitong_text);
//        xitongImage = findViewById(R.id.xiaoxi_xitong_image);
//        liaotianText = findViewById(R.id.xiaoxi_liaotian_text);
//        liaotianImage = findViewById(R.id.xiaoxi_liaotian_image);
//
//        ltAdapter = new XiaoxiLTAdapter(this);
//
//        data = new ArrayList<>();
//        adapter = new XiaoxiXTAdapter(this, data);
//        listView.setMode(PullToRefreshBase.Mode.BOTH);
//        spUtils = new SPUtils();
//        token = (String) spUtils.get(this, "logintoken", "");
//        uuid = (String) spUtils.get(this, "uuid", "");
//        adapter.setOnItemDeleteClickListener(new XiaoxiXTAdapter.onItemDeleteListener() {
//            @Override
//            public void onDeleteClick(int position) {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        /**
//                         *要执行的操作
//                         */
//                        initDownload(page);
//                        adapter.notifyDataSetChanged();
//                    }
//                }, 1000);//3秒后执行Runnable中的run方法
//
//
//                LogU.Ld("1608", "点击了");
//            }
//        });
//
//
//        //                写一个布局文件，布局文件中设置一个容器，用来接收 环信的消息列表。
//        EaseConversationListFragment cf = new EaseConversationListFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_contains,cf).commit();
//
//    }

//    @Override
//    protected void initData() {
//        biaoti.setText("消息中心");
////        listView.setAdapter(adapter);
////        listView.setOnRefreshListener(mListViewOnRefreshListener2);
//
//        shuaxin();
//        initDownload(page);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);

        listView = findViewById(R.id.xiaoxi_list);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        xitong = findViewById(R.id.xiaoxi_xitong);
        xitong.setOnClickListener(this);
        liaotian = findViewById(R.id.xiaoxi_liaotian);
        liaotian.setOnClickListener(this);
        xitongText = findViewById(R.id.xiaoxi_xitong_text);
        xitongImage = findViewById(R.id.xiaoxi_xitong_image);
        liaotianText = findViewById(R.id.xiaoxi_liaotian_text);
        liaotianImage = findViewById(R.id.xiaoxi_liaotian_image);

        ltAdapter = new XiaoxiLTAdapter(this);

        data = new ArrayList<>();
        adapter = new XiaoxiXTAdapter(this, data);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        spUtils = new SPUtils();
        token = (String) spUtils.get(this, "logintoken", "");
        uuid = (String) spUtils.get(this, "uuid", "");
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



        biaoti.setText("消息中心");
        shuaxin();
        initDownload(page);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui:
                finish();
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
                            List<XiaoxiXTEntity.DataBean.LstBean> list;
                            list = entity.getData().getLst();
//                            data.clear();
//                            data.addAll(list);
//                            listView.setAdapter(adapter);
                            if (page == 1){
                                data.clear();
                                data.addAll(list);
                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }else {
                                data.addAll(list);
//                                listView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                listView.onRefreshComplete();
                            }

//                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Intent intent = new Intent();
//                                    Bundle bundle = new Bundle();
//                                    intent.setClass(QBMXActivity.this, QBMXItemActivity.class);
//                                    bundle.putString("uid", data.get(position).getUUID());
//                                    intent.putExtras(bundle);
//
//                                    startActivity(intent);
//                                }
//                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(XiaoxiActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(XiaoxiActivity.this,DengluActivity.class);
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
                Intent intent=new Intent(XiaoxiActivity.this,LiaoTianActivity.class);
                //传入参数
                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                intent.putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId());
//                intent.putExtra("conversation",args);
                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setClass(getContext(),LiaoTianActivity.class);
//                intent.putExtra(EaseConstant.EXTRA_USER_ID, b);  //对方账号
//                intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
//                startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_chat,easeConversationListFragment)
                .commit();


    }

    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //接收到新的消息
            refreshUIWithMessage();
            for (EMMessage message : list) {
                String userName = message.getStringAttribute("nickname", "");
                String userPic = message.getStringAttribute("avatar", "");
                LogU.Ld("1608","nickname---------"+userName+"avatar"+userPic);
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

                Ringtone r = RingtoneManager.getRingtone(XiaoxiActivity.this, notification);

                r.play();

            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

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
        runOnUiThread(new Runnable() {
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
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
    }
}
