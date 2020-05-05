package com.example.android.tiaozhan.Denglu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Entity.DengluEntity;
import com.example.android.tiaozhan.Entity.JiekouSBEntity;
import com.example.android.tiaozhan.Entity.WXEntity;
import com.example.android.tiaozhan.MainActivity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.AndroidWorkaround;
import com.example.android.tiaozhan.Toos.BaseActivity;
import com.example.android.tiaozhan.Toos.EmptyUtils;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.RegexUtils;
import com.example.android.tiaozhan.Toos.SPUtils;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Call;


/**
 *登陆
 */
public class DengluActivity extends BaseActivity implements View.OnClickListener {
    private Dialog dialog;
    private TextView ds_xz;
    private TextView textViewZC, textViewWJ,textTab;
    private RelativeLayout denglu;
    private ImageView weixin, fanhuii,main_denglu_yan;
    private EditText shouji, mima;
    private LinearLayout linearLayout;
    private ProgressDialog progressDialog;
    private SPUtils spUtils;
    private String logintoken;
    private ImageView imageView1, imageView2;
    private boolean isSee = false;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_denglu);
//
//
//    }

    @Override
    public int initContentView() {
        return R.layout.activity_denglu;
    }

    @Override
    protected void initUIAndListener() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        textViewZC = findViewById(R.id.denglu_zhuce);
        textViewZC.setOnClickListener(this);
        textViewWJ = findViewById(R.id.denglu_wangji);
        textViewWJ.setOnClickListener(this);
        denglu = findViewById(R.id.Denglu_denglu);
        denglu.setOnClickListener(this);
        weixin = findViewById(R.id.denglu_weixin);
        weixin.setOnClickListener(this);
        fanhuii = findViewById(R.id.fanhuii);
        fanhuii.setOnClickListener(this);
        textTab = findViewById(R.id.biaoti);
        shouji = findViewById(R.id.denglu_shouji);
        mima = findViewById(R.id.denglu_mima);

        linearLayout = findViewById(R.id.root);
        spUtils = new SPUtils();

        imageView1 = findViewById(R.id.main_denglu_image1);
        imageView1.setOnClickListener(this);
        imageView2 = findViewById(R.id.main_denglu_image2);
        imageView2.setOnClickListener(this);
        main_denglu_yan=findViewById(R.id.main_denglu_yan);
        main_denglu_yan.setOnClickListener(this);

        shouji.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    imageView1.setVisibility(View.GONE);
                    Log.e("abc", "et1获取到焦点了。。。。。。");
                } else {
                    imageView1.setVisibility(View.GONE);
                    Log.e("abc", "et1失去焦点了。。。。。。");
//                    et1.setBackgroundResource(R.drawable.shape_edit2);  //失去焦点后更改背景色
                }
            }
        });

        mima.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    imageView2.setVisibility(View.GONE);
                } else {
                    imageView2.setVisibility(View.GONE);
                }
            }
        });


        SharedPreferences sharedPre = getSharedPreferences("config", MODE_PRIVATE);
        String username = sharedPre.getString("username", "");
        String password = sharedPre.getString("password", "");
        shouji.setText(username);
        mima.setText(password);
    }

    @Override
    protected void initData() {
        textTab.setText("登录");
       /* main_denglu_yan.setImageResource(R.mipmap.yan_open);
        mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());*/
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        immersiveStatusBar();
//        controlKeyboardLayout(linearLayout, denglu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // StatusBarUtil.setStatusBarColor(this, R.color.white);

            LogU.Le("sou","1111");
        }else {
            //  StatusBarUtil.setTransparent(this);
            StatusBarUtil.setColor(this,getResources().getColor(R.color.white),0);
            LogU.Le("sou","000");
        }

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fanhuii:
//                intent.setClass(this, MainActivity.class);
//                startActivity(intent);

                intent.setClass(DengluActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.denglu_zhuce:
                intent.setClass(this, ZhuCeActivity.class);
                startActivity(intent);
                break;
            case R.id.denglu_wangji:
                intent.setClass(this, WangJiActivity.class);
                startActivity(intent);
                break;
            case R.id.Denglu_denglu:
                initDownload();
//                intent.setClass(this,GRXXActivity.class);
//                startActivity(intent);
                break;
            case R.id.denglu_weixin:

                Log.d("1608","点击微信登陆");
                authorization(SHARE_MEDIA.WEIXIN,"wechat");


//                intent.setClass(this, BangDingActivity.class);
//                startActivity(intent);
                break;
            case R.id.main_denglu_image1:
                shouji.setText("");
                break;
            case R.id.main_denglu_image2:
                mima.setText("");
                break;
            case R.id.main_denglu_yan:
                if (isSee) {
                    mima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_open);
                    isSee = false;
                } else {
                    mima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    main_denglu_yan.setImageResource(R.mipmap.yan_close);
                    isSee = true;
                }


                break;
        }
    }


    private void initDownload() {



//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        if (TextUtils.isEmpty(shouji.getText().toString())||!RegexUtils.isMobile(shouji.getText().toString())) {
            Toast.makeText(this, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mima.getText().toString())||mima.getText().toString().length()<6) {
            Toast.makeText(this, "请填写6位以上登录密码", Toast.LENGTH_SHORT).show();
        } else {
            showDialog();
            OkHttpUtils
                    .post()
                    .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/login")
                    .addParams("mobile", shouji.getText().toString())
                    .addParams("password", mima.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            String result = response.toString();
                            LogU.Ld("1608", "登陆信息" + result);
                            Boolean a = result.indexOf("2000") != -1;
                            progressDialog.cancel();
                            if (a) {
                                Gson gson = new Gson();
                                DengluEntity entity = gson.fromJson(result, DengluEntity.class);
                                spUtils.put(DengluActivity.this, "logintoken", entity.getData().getToken());
                                spUtils.put(DengluActivity.this, "touxiang", entity.getData().getImgURL());
                                spUtils.put(DengluActivity.this, "nickname", entity.getData().getNickname());
                                spUtils.put(DengluActivity.this, "uuid", entity.getData().getUuid());
                                spUtils.put(DengluActivity.this, "shouji", entity.getData().getTelephone());
                                spUtils.put(DengluActivity.this, "tuiguang", entity.getData().getIsPromoter()+"");
//                                Set<String> set = new HashSet<>();
//                                set.add("18201395884");//名字任意，可多添加几个
////                            JPushInterface.setTags(this, set, null);//设置标签
                                String b = entity.getData().getUuid().replace("-", "");
                                LogU.Ld("1608", "UUID转换" + b);
                                LogU.Ld("1608", "token转换" + entity.getData().getToken());
                             spUtils.put(DengluActivity.this, "bieming", b);
                                JPushInterface.setAlias(DengluActivity.this, b, new TagAliasCallback() {
                                    @Override
                                    public void gotResult(int i, String s, Set<String> set) {
                                        switch (i) {
                                            case 0:
                                                //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
//                                                UserUtils.saveTagAlias(getHoldingActivity(), true);
                                                LogU.Ld("1608","Set tag and alias success极光推送别名设置成功");
                                                break;
                                            case 6002:
                                                //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                                                LogU.Ld("1608","Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试");
                                                break;
                                            default:
                                                LogU.Ld("1608","极光推送设置失败，Failed with errorCode = " + i);
                                                break;
                                        }

                                    }
                                });
                                /*Intent intent=new Intent();
                                if (!EmptyUtils.isEmpty(entity)){

                                    if (entity.getData().getIsPromoter()==0){
                                        intent.setClass(DengluActivity.this, PromoterTWOActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if (entity.getData().getIsPromoter()==1){
                                        intent.setClass(DengluActivity.this, MyPromoterActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if (entity.getData().getIsPromoter()==2){
                                        intent.setClass(DengluActivity.this, PromoterErrorActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                    if (entity.getData().getIsPromoter()==3){
                                        Toast.makeText(DengluActivity.this, "您还没有身份认证", Toast.LENGTH_SHORT).show();
                                        intent.setClass(DengluActivity.this, PromoterONEActivity.class);
                                        startActivity(intent);
                                    }
                                }else {
                                    return;
                                }*/
                                LogU.Ld("1608", "登陆信息是" + entity.getData().getIsPromoter());
                                logintoken = entity.getData().

                                        getToken();

                                jiance(logintoken);

                                saveLoginInfo(DengluActivity.this, shouji.getText().

                                        toString(), mima.

                                        getText().

                                        toString());


//                                final String  b= data.get(position-1).getUuid().replace("-","");
//                                LogU.Ld("1608","别名"+bieming);
                                EMClient.getInstance().login(b,"tz1",new EMCallBack() {//回调
                                    @Override
                                    public void onSuccess() {
                                       runOnUiThread(new Runnable() {
                                            public void run() {
                                                EMClient.getInstance().groupManager().loadAllGroups();
                                                EMClient.getInstance().chatManager().loadAllConversations();
                                                LogU.Ld("1608", "登录聊天服务器成功！");

                                            }
                                        });
                                    }


                                    @Override
                                    public void onProgress(int progress, String status) {

                                    }

                                    @Override
                                    public void onError(int code, String message) {
                                        LogU.Ld("1608", "登录聊天服务器失败！");
                                    }
                                });
                            } else {
                                Gson gson = new Gson();
                                JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                               Toast.makeText(DengluActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */

        dialog = new Dialog(this, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        RelativeLayout sport;
        sport = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.pop_quxiao_layout, null);
        TextView icon_close = sport.findViewById(R.id.icon_close);
        TextView icon_que = sport.findViewById(R.id.icon_que);

        ds_xz = sport.findViewById(R.id.ds_xz);

        ds_xz.setText("为了更方便发布和报名活动，\n" +
                "请您完善您的个人信息");


        dialog.setContentView(sport);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        icon_que.setText("去完善");
        icon_close.setText("先看看");
        dialog.show();

        icon_que.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();//传值
                intent.setClass(DengluActivity.this, GRXXActivity.class);
                bundle.putString("tab", "1");
                intent.putExtras(bundle);

                startActivity(intent);

                dialog.dismiss();
            }
        });
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DengluActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        /*final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(DengluActivity.this);
        normalDialog.setIcon(R.mipmap.logo2x);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage("\"为了更方便发布和报名活动，\n" +
                "请完善个人信息\"");
        normalDialog.setPositiveButton("先看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        intent.setClass(DengluActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("去完善",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();//传值
                        intent.setClass(DengluActivity.this, GRXXActivity.class);
                        bundle.putString("tab", "1");
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                });
        // 显示

        normalDialog.show();*/
    }

    private void jiance(String ss) {
        LogU.Ld("1608", "进入检验资料");
        OkHttpUtils
                .get()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkUserPerfectInfo")
                .addHeader("token", ss)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "检验资料" + result);
                        Boolean a = result.indexOf("2000") != -1;

                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Intent intent = new Intent();

                            intent.setClass(DengluActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Intent intent = new Intent();
//                            Bundle bundle = new Bundle();//传值
//                            intent.setClass(DengluActivity.this, GRXXActivity.class);
//                            bundle.putString("tab","1");
//                            intent.putExtras(bundle);
//                            startActivity(intent);
                            showNormalDialog();
                          //  Toast.makeText(DengluActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);
                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (location[1] + scrollToView
                                    .getHeight()) - rect.bottom;
                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }


    //沉浸式状态栏
    private void immersiveStatusBar() {

        Window window = getWindow();
        //4.4版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //5.0版本及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.BLACK);
        }
        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
    }

    //进度条
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }

        progressDialog.setMessage("加载中...");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     * @param password
     */
    public static void saveLoginInfo(Context context, String username, String password) {
        //获取SharedPreferences对象
        SharedPreferences sharedPre = context.getSharedPreferences("config", context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPre.edit();
        //设置参数
        editor.putString("username", username);
        editor.putString("password", password);
        //提交
        editor.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent intent = new Intent();
            intent.setClass(DengluActivity.this,MainActivity.class);
            LogU.Ld("1608", "跳转" );
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return false;
        }
        return false;

    }

    //授权
    private void authorization(SHARE_MEDIA share_media, final String tt) {
        showDialog();

        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d("1608", "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d("1608", "onComplete " + "授权完成");
                progressDialog.cancel();
                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                final String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                Log.d("1608","登陆微信返回信息"+name+"===="+gender+"===="+iconurl);
          //      Toast.makeText(getApplicationContext(), "name=" + name + ",gender=" + gender+ ",iconurl=" + iconurl, Toast.LENGTH_SHORT).show();
//                spUtils.put(DengluActivity.this, "uid", uid);
                //拿到信息去请求登录接口。。。
                OkHttpUtils
                        .post()
                        .url(getResources().getString(R.string.http_xutils_zpf_al_cs)+"/wechatLogin")
                        .addParams("openId",uid)
                        .addParams("sex",gender)
                        .addParams("nickname",name)
                        .addParams("imgURL",iconurl)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }
                            @Override
                            public void onResponse(String response, int id) {
                                String result = response.toString();
                                Log.d("1608","登陆微信返回"+result);
                                Boolean a = result.indexOf("2000") != -1;
                                if(!a){
                                    progressDialog.cancel();
                                    Toast.makeText(DengluActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                }else {
                                    progressDialog.cancel();
                                    Gson gson = new Gson();
                                    WXEntity entity = gson.fromJson(result,WXEntity.class);

//                                    if (entity.getMsg().equals("注册成功")) {


                                    LogU.Ld("1608","微信登录"+entity.getData().getTelephone());

                                        if (EmptyUtils.isStrEmpty(entity.getData().getTelephone())){
                                            Intent intent = new Intent();
                                            Bundle bundle = new Bundle();
                                            intent.setClass(DengluActivity.this, BangDingActivity.class);
                                            bundle.putString("uid",uid);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }else{

                                            spUtils.put(DengluActivity.this, "logintoken", entity.getData().getToken());
                                            spUtils.put(DengluActivity.this, "touxiang", entity.getData().getImgURL());
                                            spUtils.put(DengluActivity.this, "nickname", entity.getData().getNickname());
                                            spUtils.put(DengluActivity.this, "uuid", entity.getData().getUuid());
                                            spUtils.put(DengluActivity.this, "shouji", entity.getData().getTelephone());
                                            Intent intent = new Intent();
                                            intent.setClass(DengluActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }


//                                    }
                                }
                            }
                        });


            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                progressDialog.cancel();
                Log.d("1608", "onError " + "授权失败"+share_media+"throwable"+throwable);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                progressDialog.cancel();
                Log.d("1608", "onCancel " + "授权取消");
            }
        });
    }

  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            //这里写需要重写的方法
            return false;
        }
        return false;

    }*/

    @Override
    protected void onResume() {
        shouji.requestFocus();
        shouji.setSelection(shouji.getText().length());
        super.onResume();
    }
}
