package com.example.android.promoter.Home;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.promoter.Adapter.StartTimeListOneAdapter;
import com.example.android.promoter.Denglu.DengluActivity;
import com.example.android.promoter.Entity.CGTimeEntity;
import com.example.android.promoter.Entity.JiekouSBEntity;
import com.example.android.promoter.Entity.WebTimeEntity;
import com.example.android.promoter.Entity.YQTimeYesNoEntity;
import com.example.android.promoter.R;
import com.example.android.promoter.Toos.BaseActivity;
import com.example.android.promoter.Toos.EmptyUtils;
import com.example.android.promoter.Toos.LogU;
import com.example.android.promoter.Toos.SPUtileFQTZ;
import com.example.android.promoter.Toos.SPUtils;
import com.example.android.promoter.Toos.ToastUitl;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 *场馆时间选择
 */
public class StartTimeActivity extends BaseActivity implements OnDateSelectedListener,View.OnClickListener{
    private int  xiao=100 , da =100 ,c = 100,i = 0;
    private MaterialCalendarView calendarView;
    private ListView listView1;
    private StartTimeListOneAdapter adapter;
    private String time[] = {"00:00","00:30","  1:00","  1:30","  2:00","  2:30","  3:00","  3:30","  4:00","  4:30","  5:00","  5:30","  6:00"
            ,"  6:30","  7:00","  7:30","  8:00","  8:30","  9:00","  9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30"
            ,"16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30","24:00"};
    private List<String> list;
    private RelativeLayout queding;
    private String timeRI,timeSHI,timeYX,playTime;
    private SPUtileFQTZ spUtileFQTZ;
    private TextView yixuan,shichang,changdifei,biaoti,heji,zuihou,qingkong;
    private SPUtils spUtils;
    private String token,invitedId,fqtzXiangmudaid,fqtzXiangmuid,cgid,hezuo,fqtzXiangmu;
    //    private int tag =1 ;
    private List<String> tag;
    private List<CGTimeEntity.DataBean> data;
    private List<String> riqi;
    private ImageView fanhui;

    private WebView web;
    private ProgressDialog progressDialog;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    private String result;
    private Double placeMoney;
    private String yId;
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_start_time);
//    }


    @Override
    public int initContentView() {
        return R.layout.activity_start_time;
    }

    @Override
    protected void initUIAndListener() {

        //web
        web = findViewById(R.id.xuanz_chuangguan);


// 清缓存和记录，缓存引起的白屏
        web.clearCache(true);
        web.clearHistory();

        web.requestFocus();
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings .setDefaultTextEncodingName("utf-8");

// 缓存白屏
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath() + "/webcache";
// 设置 Application Caches 缓存目录
        settings.setAppCachePath(appCachePath);
        settings.setDatabasePath(appCachePath);

        settings.setAppCacheEnabled(false);
       /* web.evaluateJavascript("javascript:onSubmit()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                LogU.Le("1608","js传值"+value);
            }
        });*/

        web.addJavascriptInterface(new AndroidJs(),"JsAndroid");
        web.addJavascriptInterface(new AndroidStartTime(),"AndroidStartTime");
        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }

        // web.loadUrl("http://www.baidu.com");
        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //  super.onReceivedSslError(view, handler, error);
                if (error.getPrimaryError() == android.net.http.SslError.SSL_DATE_INVALID
                        || error.getPrimaryError() == android.net.http.SslError.SSL_EXPIRED
                        || error.getPrimaryError() == android.net.http.SslError.SSL_INVALID
                        || error.getPrimaryError() == android.net.http.SslError.SSL_UNTRUSTED) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }

                //  handler.proceed();
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url){

                /*view.loadUrl(url);
                return true;*/

                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                    web.stopLoading();
                    return true;
                }
                return false;
            }

        });

        web.setWebChromeClient(new WebChromeClient() {


            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }
            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
            {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }

        });

        //web.loadUrl("https://venue.tiaozhanmeiyitian.com/#/appOrder"+"?siteuid="+cgid+"&sportid="+fqtzXiangmudaid+"&token="+token);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangedListener(this);
        listView1 = findViewById(R.id.start_time_list1);
        queding = findViewById(R.id.start_queding);
        queding.setOnClickListener(this);
        shichang = findViewById(R.id.start_time_shichang);
        changdifei = findViewById(R.id.start_time_changdifei);
        biaoti = findViewById(R.id.biaoti);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        heji = findViewById(R.id.start_time_heji);
        qingkong = findViewById(R.id.start_time_qingkong);
        qingkong.setOnClickListener(this);

//        zuihou = findViewById(R.id.start_time_zuihou);

        list = new ArrayList<>();
        for (int i = 0;i<time.length;i++){
            list.add(time[i]);
        }

        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd");
        Date curDate =  new Date(System.currentTimeMillis());
        timeRI  =   formatter.format(curDate);
        tag = new ArrayList<>();
        data = new ArrayList<>();
        riqi = new ArrayList<>();
        adapter = new StartTimeListOneAdapter(this,listView1,data,tag,riqi);
        spUtileFQTZ = new SPUtileFQTZ();
        yixuan = findViewById(R.id.start_time_yixuean);
        spUtils = new SPUtils();

        token = (String) spUtils.get(this, "logintoken", "");
        invitedId =  (String) spUtils.get(this, "uuid", "");
        yId = (String) spUtils.get(StartTimeActivity.this, "yId", "");
        fqtzXiangmu = (String) spUtileFQTZ.get(this, "fqtzXiangmu", " ");

        cgid = (String) spUtileFQTZ.get(this, "cgid", "无");
        fqtzXiangmudaid = (String) spUtileFQTZ.get(this, "fqtzXiangmudasportId", "无");
        fqtzXiangmuid = (String) spUtileFQTZ.get(this, "fqtzXiangmusportId", "无");
        hezuo = (String) spUtileFQTZ.get(this, "hezuo", "");
        if (hezuo.equals("1")){
            heji.setVisibility(View.VISIBLE);
        }else{
            heji.setVisibility(View.GONE);
        }
        web.loadUrl("https://venue.tiaozhanmeiyitian.com/#/appOrder"+"?siteuid="+cgid+"&sportid="+fqtzXiangmudaid+"&token="+token+"&sporttype="+fqtzXiangmuid);
        LogU.Ld("1609","运动"+fqtzXiangmuid);
    }



    @Override
    protected void initData() {
        calendarView.state().edit()
                .setCalendarDisplayMode(CalendarMode.WEEKS)
//                .TodayDecorator()
                .commit();
        calendarView.setSelectedDate(new Date());
        biaoti.setText("预定时间");


        spUtileFQTZ.put(StartTimeActivity.this, "timeRI",timeRI);

        listView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//            }
//        });

//        //ListView item 中的删除按钮的点击事件
//        adapter.setOnItemDeleteClickListener(new StartTimeListOneAdapter.onItemDeleteListener() {
//            @Override
//            public void onDeleteClick(int i,int b) {
//                LogU.Ld("1608", "传过来 的---"+i );
////                mList.remove(i);
//
//                if (i == 100){
//                    yixuan.setText("");
//                }else{
//                    yixuan.setText(list.get(i)+"-"+list.get(b));
//                    shichang.setText(div((b-i),2,1)+"小时");
//                    playTime = div((b-i),2,1)+"";
//                    spUtileFQTZ.put(StartTimeActivity.this, "timeSHI", list.get(i));
//                    spUtileFQTZ.put(StartTimeActivity.this, "shichang", div((b-i),2,1)+"");
//                    timeSHI = list.get(i);
//                }
//
//                adapter.notifyDataSetChanged();
//            }
//        });
        init(2);
    }


    //日期点击监听器
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        2018-11-21 15:34:30

        timeRI = sdf.format(date.getDate().getTime());
        long time=System.currentTimeMillis();
        Date curDate =  new Date(System.currentTimeMillis());
        String   str   =   sdf.format(curDate);

        if (isDate2Bigger(sdf.format(date.getDate().getTime()),str)){
            data.clear();
            adapter.notifyDataSetChanged();
            ToastUitl.longs("请选择当前日期之后的时间");

        }else{
            timeSHI=null;
            spUtileFQTZ.put(StartTimeActivity.this, "timeRI",timeRI);
            init(1);
        }
        LogU.Ld("1608", "日期" + date.getDate().getTime()+"巴拉巴卡"+sdf.format(date.getDate().getTime())+"----"+time+"----");
        //        onCreate(null);



    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_queding:

                if (timeRI == null||timeSHI == null){
                    Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
                }else {
                    TimeYesorNo();
                }

                break;

            case R.id.fanhui:
                finish();
                break;
            case R.id.start_time_qingkong:

                init(2);
                yixuan.setText("");
                shichang.setText("");
                break;

        }
    }
    //获取时间表
    private void init(int t) {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间列表token--" + token+"cgid--"+cgid+"choseDate--"+timeRI);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/getVenueTimes")
                .addHeader("token",token)
                .addParams("siteUid",cgid)//"828a996e617932bed6b74e7e"
                .addParams("choseDate",timeRI)
                .addParams("sportId",fqtzXiangmudaid)
                .addParams("sporttype",fqtzXiangmu)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogU.Ld("1608", "邀请好友" + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "时间列表" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            CGTimeEntity entity = gson.fromJson(result, CGTimeEntity.class);
                            List<CGTimeEntity.DataBean> list;
                            list = entity.getData();
                            data.clear();
                            data.addAll(list);
                            tag.clear();
                            tag.add("1");
                            riqi.clear();
                            riqi.add(timeRI);
                            listView1.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
//                            zuihou.setText(data.get(data.size()-1).getEndDateTime());
                            //ListView item 中的删除按钮的点击事件
                            adapter.setOnItemDeleteClickListener(new StartTimeListOneAdapter.onItemDeleteListener() {
                                @Override
                                public void onDeleteClick(int i,int b) {
                                    LogU.Ld("1608", "传过来 的---"+i +b);
//                mList.remove(i);

                                    if (i == 100){
                                        yixuan.setText("");
                                        changdifei.setText("");
                                    }else{
                                        yixuan.setText(data.get(i).getStratDateTime()+"-"+data.get(b-1).getEndDateTime());
                                        shichang.setText(div((b-i),2,1)+"小时");

                                        playTime = div((b-i),2,1)+"";
                                        spUtileFQTZ.put(StartTimeActivity.this, "timeSHI",data.get(i).getStratDateTime());
                                        spUtileFQTZ.put(StartTimeActivity.this, "shichang", div((b-i),2,1)+"");
                                        timeSHI = data.get(i).getStratDateTime();


                                        if (hezuo.equals("1")){
                                            double fei = 0;
                                            double yong = 0;
//                                            for(int a = i;a<b;a++){
//                                                fei =  Double.parseDouble(data.get(i).getCostperhour());
//                                                yong = yong+fei;
//                                                LogU.Ld("1608",i+"费用"+fei+"----"+yong);
//
//                                            }
                                            for(int a = i;a<b;a++){
                                                fei =  Double.parseDouble(data.get(a).getCostperhour());
                                                yong = yong+fei;
                                                LogU.Ld("1608",a+"费用"+fei+"----"+yong);

                                            }

                                            DecimalFormat df = new DecimalFormat("0.00");
                                            changdifei.setText( df.format(yong)+"");
                                            // spUtileFQTZ.put(StartTimeActivity.this, "hezuofeiyong", yong+"");

                                        }

                                    }

                                    adapter.notifyDataSetChanged();
                                }
                            });
                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(StartTimeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }

    private void TimeYesorNo(){
        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间判断token--" + token+"invitedId--"+invitedId+"startTime--"+timeRI+" --"+timeSHI+"FirstSportId--"+fqtzXiangmudaid
                +"SecondSportId--"+fqtzXiangmuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkChooseTimes")
                .addHeader("token",token)
                .addParams("startTime",timeRI + " " + timeRI)
                .addParams("playTime",playTime)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogU.Ld("1608", "邀请好友" + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        result = response.toString();
                        LogU.Ld("1608", "时间判断" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);

                            Intent intent = new Intent();
                            intent.setClass(StartTimeActivity.this,FaqiTiaozhanActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("dataa",timeRI + " " + timeSHI);
                            startActivity(intent);
                            finish();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(StartTimeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

                            if (entity.getMsg().equals("登录超时")){
                                Intent intent = new Intent();
                                intent.setClass(StartTimeActivity.this,DengluActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });


    }

    private void initDownload() {
//        http://192.168.0.203/tzOne/public/index.php/api/register?mobile=13321112517&password=wwwwww&password_confirmation=wwwwww&code=944952
        LogU.Ld("1608", "时间判断token--" + token+"invitedId--"+invitedId+"startTime--"+timeRI+" --"+timeSHI+"FirstSportId--"+fqtzXiangmudaid
                +"SecondSportId--"+fqtzXiangmuid);
        OkHttpUtils
                .post()
                .url(getResources().getString(R.string.http_xutils_zpf_al_cs) + "/checkActiveTime")
                .addHeader("token",token)
                .addParams("invitedId",invitedId)
                .addParams("startTime",timeRI + " " + timeSHI)
                .addParams("FirstSportId",fqtzXiangmudaid)
                .addParams("playTime",playTime)
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
                        LogU.Ld("1608", "时间判断" + result);
                        Boolean a = result.indexOf("2000") != -1;
                        if (a) {
                            Gson gson = new Gson();
                            YQTimeYesNoEntity entity = gson.fromJson(result, YQTimeYesNoEntity.class);

                            Intent intent = new Intent();
                            intent.setClass(StartTimeActivity.this,FaqiTiaozhanActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                            finish();

                        } else {
                            Gson gson = new Gson();
                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
                            Toast.makeText(StartTimeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                            if (entity.getMsg().equals("登录超时")){
//                                Intent intent = new Intent();
//                                intent.setClass(getContext(),DengluActivity.class);
//                                startActivity(intent);
//                            }
                        }
                    }
                });

    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }




    public class AndroidJs {

        public AndroidJs() {

        }

        @JavascriptInterface
        public void goback() {
            LogU.Le("1608","点我了退出");
            finish();
        }
        @JavascriptInterface
        public void goTime(String obj) {

            if (!EmptyUtils.isEmpty(obj)) {
                Gson gson = new Gson();
                WebTimeEntity entity = gson.fromJson(obj, WebTimeEntity.class);
                LogU.Le("1608","点我了时间"+obj+"瞎传"+obj.toString());

                placeMoney = entity.getPlaceMoney();
                String placeTimeLen = entity.getPlaceTimeLen();
                String placeTime = entity.getPlaceTime();
                String substring = placeTime.substring(0,5);
                String placeNun = entity.getPlaceNun();
                Intent intent = new Intent();
                intent.setClass(StartTimeActivity.this,FaqiTiaozhanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("dataa",entity.getPlaceDate()+"   "+substring);
                intent.putExtra("placeTimeLen",entity.getPlaceTimeLen());
                intent.putExtra("placeNun",entity.getPlaceNun());
                intent.putExtra("placeMoney",placeMoney+"");
                if (yId.equals("1")){
                    intent.putExtra("ydId",1+"");
                    spUtils.remove(StartTimeActivity.this,"yId");
                }

                startActivity(intent);
                finish();

            } else {
                Gson gson = new Gson();
                JiekouSBEntity entity1 = gson.fromJson(result, JiekouSBEntity.class);
                Toast.makeText(StartTimeActivity.this, entity1.getMsg(), Toast.LENGTH_SHORT).show();

                if (entity1.getMsg().equals("登录超时")){
                    Intent intent = new Intent();
                    intent.setClass(StartTimeActivity.this,DengluActivity.class);
                    startActivity(intent);
                }
            }

        }

    }

    public class AndroidStartTime {

        public AndroidStartTime() {

        }

        @JavascriptInterface
        public void startTime(String urll) {
            urll="https://venue.tiaozhanmeiyitian.com/#/appOrder"+"?siteuid="+cgid+"&sportid="+fqtzXiangmudaid+"&token="+token+"&sporttype="+fqtzXiangmuid;

            LogU.Le("1608","点我了时间");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != HZCGActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(getBaseContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LogU.Le("按了返回了", "但是不退出这个Activity");
            if (web.canGoBack()) {
                web.goBack();//返回上个页面
                return true;
            } else {
                finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    /* @Override
     public void onPause() {
         super.onPause();
         // 加载空白页
         web.loadUrl("about:blank");
     }*/
    //销毁Webview
    @Override
    protected void onDestroy() {
        if (web != null) {
            web.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            web.clearHistory();

            ((ViewGroup) web.getParent()).removeView(web);
            web.destroy();
            web = null;
        }
        super.onDestroy();
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

}
