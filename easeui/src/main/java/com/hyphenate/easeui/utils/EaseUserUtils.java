package com.hyphenate.easeui.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;

public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
//        if(userProvider != null)
//            return userProvider.getUser(username);

        return null;
    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView){
//    	EaseUser user = getUserInfo(username);
//        Log.d("1608",  "第一步"+username+"----"+user);
//        if(user != null && user.getAvatar() != null){
//            try {
//                int avatarResId = Integer.parseInt(user.getAvatar());
//                Glide.with(context).load("http://app.tiaozhanmeiyitian.com/"+avatarResId).into(imageView);
//                Log.d("1608",  "懂1");
//
//            } catch (Exception e) {
//                //use default avatar
//                Log.d("1608",  "懂2");
//                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
//            }
//        }else{
//            Log.d("1608",  "懂3");
////            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
//            Glide.with(context).load("http://app.tiaozhanmeiyitian.com/"+username).asBitmap().into(imageView);
//
//        }

        EaseUser user = getUserInfo(username);
        if(user != null && user.getAvatar() != null){
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
            }
        }else{
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
//        if(textView != null){
//
//        	EaseUser user = getUserInfo(username);
//            Log.d("1608",  "第一步昵称"+username+"----"+user);
//        	if(user != null && user.getNickname() != null){
//        		textView.setText(user.getNickname());
////                textView.setText(username);
//                Log.d("1608",  "第二步昵称"+username+"----"+user);
//        	}else{
//        		textView.setText(username);
//                Log.d("1608",  "第三步昵称"+username+"----"+user);
////                textView.setText(user.getNickname());
//        	}
//        }

        if(textView != null){
            EaseUser user = getUserInfo(username);
            if(user != null && user.getNick() != null){
                textView.setText(user.getNick());
            }else{
                textView.setText(username);
            }
        }
    }
    
}
