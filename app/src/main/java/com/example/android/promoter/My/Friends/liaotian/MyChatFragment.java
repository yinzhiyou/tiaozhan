package com.example.android.promoter.My.Friends.liaotian;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.promoter.Toos.SPUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

public class MyChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    private SharedPreferences sharedPreferences;
    private SPUtils spUtils;
    public MyChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setChatFragmentHelper(this);
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        spUtils = new SPUtils();

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void setUpView() {


        super.setUpView();
        titleBar.setTitle((String) spUtils.get(getContext(), "haoyouname", " "));
    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {
        //set message extension
//        message.setAttribute("em_robot_message", EMClient.getInstance().getCurrentUser());
//        //设置要发送扩展消息用户昵称
//        message.setAttribute(Constant.USER_NAME, sharedPreferences.getString("nick",""));
//        message.setAttribute(Constant.USER, EMClient.getInstance().getCurrentUser());
//        //设置要发送扩展消息用户头像
//        message.setAttribute(Constant.HEAD_IMAGE_URL, sharedPreferences.getString("url",""));

        try {
            //我的信息，一般本地自己取出来
            message.setAttribute("bieming",(String) spUtils.get(getContext(), "bieming", " "));
            message.setAttribute("avatar",(String) spUtils.get(getContext(), "touxiang", " "));
            message.setAttribute("nickname",(String) spUtils.get(getContext(), "nickname", " "));
            //对方的信息，一般上个界面传值传过来
            message.setAttribute("bieming", (String) spUtils.get(getContext(), "haoyouid", " "));
            message.setAttribute("avatar",(String) spUtils.get(getContext(), "haoyoutouxiang", " "));
            message.setAttribute("nickname", (String) spUtils.get(getContext(), "haoyouname", " "));
        } catch (Exception e) {

        }
        Log.e("1608","MyChatFragment   "+ (String) spUtils.get(getContext(), "haoyouname", " ")+(String) spUtils.get(getContext(), "nickname", " "));

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {
        Toast.makeText(getActivity(),"头像被点击了",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
