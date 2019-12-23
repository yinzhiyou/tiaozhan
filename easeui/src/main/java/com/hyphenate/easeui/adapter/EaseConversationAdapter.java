package com.hyphenate.easeui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMConversation.EMConversationType;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.adapter.EMAConversation;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.HXchaxunEntity;
import com.hyphenate.easeui.HXqunchaxunEntity;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.SPUtils;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseConversationList.EaseConversationListHelper;
import com.hyphenate.easeui.widget.EaseImageView;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.DateUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * conversation list adapter
 */
public class EaseConversationAdapter extends ArrayAdapter<EMConversation> {
    private static final String TAG = "ChatAllHistoryAdapter";
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;
    private ConversationFilter conversationFilter;
    private boolean notiyfyByFilter;

    protected int primaryColor;
    protected int secondaryColor;
    protected int timeColor;
    protected int primarySize;
    protected int secondarySize;
    protected float timeSize;


    private SPUtils spUtils;

    public EaseConversationAdapter(Context context, int resource,
                                   List<EMConversation> objects) {
        super(context, resource, objects);
        conversationList = objects;
        copyConversationList = new ArrayList<EMConversation>();
        copyConversationList.addAll(objects);
    }

    @Override
    public int getCount() {
        return conversationList.size();
    }

    @Override
    public EMConversation getItem(int arg0) {
        if (arg0 < conversationList.size()) {
            return conversationList.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ease_row_chat_history, parent, false);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.unreadLabel = (TextView) convertView.findViewById(R.id.unread_msg_number);
            holder.message = (TextView) convertView.findViewById(R.id.message);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.msgState = convertView.findViewById(R.id.msg_state);
            holder.list_itease_layout = (RelativeLayout) convertView.findViewById(R.id.list_itease_layout);
            holder.motioned = (TextView) convertView.findViewById(R.id.mentioned);
            convertView.setTag(holder);
        }
        holder.list_itease_layout.setBackgroundResource(R.drawable.ease_mm_listitem);

        // get conversation
        EMConversation conversation = getItem(position);
        // get username or group id
        String username = conversation.conversationId();
//        String sha =  conversation.getExtField().;


        if (conversation.getType() == EMConversationType.GroupChat) {

            String groupId = conversation.conversationId();
            Log.d("1608", "aaaa" + "+++++" +"我是群聊"+groupId);
            if (EMClient.getInstance().groupManager().getGroup(groupId) != null){
                Log.d("1608", "aaaa" + "+++++" +"我是群聊"+groupId+EMClient.getInstance().groupManager().getGroup(groupId).toString());
                chaxunqun(EMClient.getInstance().groupManager().getGroup(groupId).toString(),holder);
            }else{



            if (EaseAtMessageHelper.get().hasAtMeMsg(groupId)) {
                holder.motioned.setVisibility(View.VISIBLE);
            } else {
                holder.motioned.setVisibility(View.GONE);
            }
            // group message, show group avatar
            holder.avatar.setImageResource(R.drawable.ease_group_icon);
            EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
            holder.name.setText(group != null ? group.getGroupName() : username);
            }
        } else if (conversation.getType() == EMConversationType.ChatRoom) {
            Log.d("1608", "aaaa" + "+++++" +"我是聊天室");
            holder.avatar.setImageResource(R.drawable.ease_group_icon);
            EMChatRoom room = EMClient.getInstance().chatroomManager().getChatRoom(username);
            holder.name.setText(room != null && !TextUtils.isEmpty(room.getName()) ? room.getName() : username);
            holder.motioned.setVisibility(View.GONE);
        } else {

///**
// * 获取列表最后一条消息是接收还是发送
// * 然后设置昵称和头像
// */

//            EMMessage lastMessage = conversation.getLastMessage();
//            EMMessage lastMessage2 = conversation.getLatestMessageFromOthers();


            Log.d("1608", "aaaa" + "+++++" +conversation.getType());
//            if (!conversation.getType().equals("Chat")) {
//                chaxunqun(username,holder);
//            } else {

                EMMessage message = conversation.getLatestMessageFromOthers();
                if (message == null) {

                    chaxun(username, holder);
                } else {

                    Map<String, Object> ext = message.ext();
                    Log.d("1608", "aaaa" + "---" + ext.get("nickname").toString());
                    try {

                        Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/" + message.getStringAttribute("avatar")).into(holder.avatar);
                        EaseUserUtils.setUserNick(message.getStringAttribute("nickname"), holder.name);

                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
//            }
//            if (lastMessage.direct() == EMMessage.Direct.RECEIVE) {
//
//                try {
////                    String str1 =lastMessage.getStringAttribute("avatar");
////
////                    String str2 =lastMessage.getStringAttribute("nickname");
////
////                    if (lastMessage.getStringAttribute("nickname").indexOf("&") != -1) {
////                        str1.substring(0, str1.indexOf("&"));
////                        str2.substring(0, str2.indexOf("&"));
////                    }
////                    Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/"+str1).into(holder.avatar);
////                    EaseUserUtils.setUserNick( str2, holder.name);
////
//                    EMMessage message = conversation.getLatestMessageFromOthers();
//
//
//                    Map<String, Object> ext = message.ext();
//                    Log.d("1608", "aaaa" + "---" + ext.get("nickname").toString());
//                    Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/" + lastMessage.getStringAttribute("avatar")).into(holder.avatar);
//                    EaseUserUtils.setUserNick(lastMessage.getStringAttribute("nickname"), holder.name);
//                } catch (HyphenateException e) {
//                }
//            } else {
//                try {
////                    EaseUserUtils.setUserNick(lastMessage.getStringAttribute("nickname"), holder.name);
////                    EaseUserUtils.setUserNick("王二二", holder.name);
//
//
////                    String userId1 = lastMessage.getStringAttribute("nickname");
////                    String userId2 = lastMessage.getStringAttribute("avatar");
////                    String userIdJiequ1 = null;
////                    String userIdJiequ2 = null;
////                    if (lastMessage.getStringAttribute("nickname").indexOf("&") != -1){
////
////                         userIdJiequ1 = userId1.substring(userId1.indexOf("&"));
////
////                         userIdJiequ2 = userId2.substring(userId2.indexOf("&"));
////                    }
//
////                    Log.d("1608","aaaa"+lastMessage.getStringAttribute("nickname"));
////                    EaseUserUtils.setUserNick(userIdJiequ1, holder.name);
////                    Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/"+userIdJiequ2).into(holder.avatar);
//
//                    EaseUserUtils.setUserNick(lastMessage.getStringAttribute("nickname"), holder.name);
//                    Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/" + lastMessage.getStringAttribute("avatar")).into(holder.avatar);
//                } catch (HyphenateException e) {
//                }
//            }
        }
        {

//            spUtils = new SPUtils();
//             Log.d("1608","EaseConversationAdapter   "+(String) spUtils.get(getContext(), "bieming", " "));
//            if (conversation.getLastMessage().getFrom().equals((String) spUtils.get(getContext(), "bieming", " "))) {
//                Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/"+(String) spUtils.get(getContext(), "touxiang", " ")).into(holder.avatar);
//                holder.name.setText((String) spUtils.get(getContext(), "nickname", " "));
//                Log.d("1608","EaseConversationAdapter   "+"走上");
//
//            } else {
//                Log.d("1608","EaseConversationAdapter   "+"走下");
//
//                Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/"+(String) spUtils.get(getContext(), "haoyoutouxiang", " ")).into(holder.avatar);
//                holder.name.setText((String) spUtils.get(getContext(), "haoyouname", " "));
//            }
//             EaseUserUtils.setUserAvatar(getContext(), username, holder.avatar);
//             EaseUserUtils.setUserNick(username, holder.name);
//            holder.motioned.setVisibility(View.GONE);


//            EaseUserUtils.setUserAvatar(getContext(), username, holder.avatar);
//            EaseUserUtils.setUserNick(username, holder.name);
//            holder.motioned.setVisibility(View.GONE);
        }

        EaseAvatarOptions avatarOptions = EaseUI.getInstance().getAvatarOptions();
        if (avatarOptions != null && holder.avatar instanceof EaseImageView) {
            EaseImageView avatarView = ((EaseImageView) holder.avatar);
            if (avatarOptions.getAvatarShape() != 0)
                avatarView.setShapeType(avatarOptions.getAvatarShape());
            if (avatarOptions.getAvatarBorderWidth() != 0)
                avatarView.setBorderWidth(avatarOptions.getAvatarBorderWidth());
            if (avatarOptions.getAvatarBorderColor() != 0)
                avatarView.setBorderColor(avatarOptions.getAvatarBorderColor());
            if (avatarOptions.getAvatarRadius() != 0)
                avatarView.setRadius(avatarOptions.getAvatarRadius());
        }
        if (conversation.getUnreadMsgCount() > 0) {
            // show unread message count
            holder.unreadLabel.setText(String.valueOf(conversation.getUnreadMsgCount()));
            holder.unreadLabel.setVisibility(View.VISIBLE);
        } else {
            holder.unreadLabel.setVisibility(View.INVISIBLE);
        }

        if (conversation.getAllMsgCount() != 0) {
            // show the content of latest message
            EMMessage lastMessage = conversation.getLastMessage();
            String content = null;
            if (cvsListHelper != null) {
                content = cvsListHelper.onSetItemSecondaryText(lastMessage);
            }
            holder.message.setText(EaseSmileUtils.getSmiledText(getContext(), EaseCommonUtils.getMessageDigest(lastMessage, (this.getContext()))),
                    BufferType.SPANNABLE);
            if (content != null) {
                holder.message.setText(content);
            }
            holder.time.setText(DateUtils.getTimestampString(new Date(lastMessage.getMsgTime())));
            if (lastMessage.direct() == EMMessage.Direct.SEND && lastMessage.status() == EMMessage.Status.FAIL) {
                holder.msgState.setVisibility(View.VISIBLE);
            } else {
                holder.msgState.setVisibility(View.GONE);
            }
        }

        //set property
        holder.name.setTextColor(primaryColor);
        holder.message.setTextColor(secondaryColor);
        holder.time.setTextColor(timeColor);
        if (primarySize != 0)
            holder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, primarySize);
        if (secondarySize != 0)
            holder.message.setTextSize(TypedValue.COMPLEX_UNIT_PX, secondarySize);
        if (timeSize != 0)
            holder.time.setTextSize(TypedValue.COMPLEX_UNIT_PX, timeSize);

        return convertView;
    }


    //查询群昵称
    private void chaxunqun(final String publishcId, final ViewHolder holder) {
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
                            EaseUserUtils.setUserNick(entity.getData().getGroup_name(), holder.name);
                            Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/" + entity.getData().getImg()).into(holder.avatar);

                        } else {

                        }
                    }
                });

    }

    //查询昵称
    private void chaxun(final String publishcId, final ViewHolder holder) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        Log.d("1608", "昵称---" + "---publishcId---" + publishcId);
        OkHttpUtils
                .get()
                .url("https://app.tiaozhanmeiyitian.com/api" + "/user")
                .addParams("imid", publishcId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        Log.d("1608", "chaxun" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            HXchaxunEntity entity = gson.fromJson(result, HXchaxunEntity.class);
                            EaseUserUtils.setUserNick(entity.getData().getNickname(), holder.name);
                            Glide.with(getContext()).load("https://app.tiaozhanmeiyitian.com/" + entity.getData().getImgURL()).into(holder.avatar);

                        } else {

                        }
                    }
                });

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (!notiyfyByFilter) {
            copyConversationList.clear();
            copyConversationList.addAll(conversationList);
            notiyfyByFilter = false;
        }
    }

    @Override
    public Filter getFilter() {
        if (conversationFilter == null) {
            conversationFilter = new ConversationFilter(conversationList);
        }
        return conversationFilter;
    }


    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setTimeColor(int timeColor) {
        this.timeColor = timeColor;
    }

    public void setPrimarySize(int primarySize) {
        this.primarySize = primarySize;
    }

    public void setSecondarySize(int secondarySize) {
        this.secondarySize = secondarySize;
    }

    public void setTimeSize(float timeSize) {
        this.timeSize = timeSize;
    }


    private class ConversationFilter extends Filter {
        List<EMConversation> mOriginalValues = null;

        public ConversationFilter(List<EMConversation> mList) {
            mOriginalValues = mList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<EMConversation>();
            }
            if (prefix == null || prefix.length() == 0) {
                results.values = copyConversationList;
                results.count = copyConversationList.size();
            } else {
                if (copyConversationList.size() > mOriginalValues.size()) {
                    mOriginalValues = copyConversationList;
                }
                String prefixString = prefix.toString();
                final int count = mOriginalValues.size();
                final ArrayList<EMConversation> newValues = new ArrayList<EMConversation>();

                for (int i = 0; i < count; i++) {
                    final EMConversation value = mOriginalValues.get(i);
                    String username = value.conversationId();

                    EMGroup group = EMClient.getInstance().groupManager().getGroup(username);
                    if (group != null) {
                        username = group.getGroupName();
                    } else {
                        EaseUser user = EaseUserUtils.getUserInfo(username);
                        // TODO: not support Nick anymore
//                        if(user != null && user.getNick() != null)
//                            username = user.getNick();
                    }

                    // First match against the whole ,non-splitted value
                    if (username.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = username.split(" ");
                        final int wordCount = words.length;

                        // Start at index 0, in case valueText starts with space(s)
                        for (String word : words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            conversationList.clear();
            if (results.values != null) {
                conversationList.addAll((List<EMConversation>) results.values);
            }
            if (results.count > 0) {
                notiyfyByFilter = true;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    private EaseConversationListHelper cvsListHelper;

    public void setCvsListHelper(EaseConversationListHelper cvsListHelper) {
        this.cvsListHelper = cvsListHelper;
    }

    private static class ViewHolder {
        /**
         * who you chat with
         */
        TextView name;
        /**
         * unread message count
         */
        TextView unreadLabel;
        /**
         * content of last message
         */
        TextView message;
        /**
         * time of last message
         */
        TextView time;
        /**
         * avatar
         */
        ImageView avatar;
        /**
         * status of last message
         */
        View msgState;
        /**
         * layout
         */
        RelativeLayout list_itease_layout;
        TextView motioned;
    }
}

