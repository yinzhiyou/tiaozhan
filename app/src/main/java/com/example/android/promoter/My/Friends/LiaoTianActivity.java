package com.example.android.promoter.My.Friends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.android.promoter.My.Friends.liaotian.MyChatFragment;
import com.example.android.promoter.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class LiaoTianActivity extends AppCompatActivity {

    private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_liao_tian);
        Bundle bundle = new Bundle();//接收
        bundle = this.getIntent().getExtras();
        type =  bundle.getString("type");
//        EaseChatFragment easeChatFragment = new EaseChatFragment();  //环信聊天界面
//        EaseChatFragment easeChatFragment = new EaseChatFragment();
//        easeChatFragment.setArguments(getIntent().getExtras()); //需要的参数
//        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat,easeChatFragment).commit();  //Fragment切换


//        //注册一个监听连接状态的listener
//        EMClient.getInstance().addConnectionListener(new MyConnectionListener(this));
//        //new出EaseChatFragment或其子类的实例
        MyChatFragment chatFragment = new MyChatFragment();
        //传入参数
        Bundle args = new Bundle();
        if(type==null||type==""){

            return;
        }
        if (type.equals("2")){
            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        }else{
            args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        }


        args.putString(EaseConstant.EXTRA_USER_ID, getIntent().getStringExtra(EaseConstant.EXTRA_USER_ID));
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.layout_chat, chatFragment).commit();
    }
}
