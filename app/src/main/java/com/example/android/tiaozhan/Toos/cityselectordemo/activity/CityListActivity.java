package com.example.android.tiaozhan.Toos.cityselectordemo.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.android.tiaozhan.Entity.ZuobiaoEntity;
import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.LogU;
import com.example.android.tiaozhan.Toos.cityselectordemo.adapter.ListAdapter;
import com.example.android.tiaozhan.Toos.cityselectordemo.db.DBHelper;
import com.example.android.tiaozhan.Toos.cityselectordemo.db.DatabaseHelper;
import com.example.android.tiaozhan.Toos.cityselectordemo.entity.City;
import com.example.android.tiaozhan.Toos.cityselectordemo.widgets.MyLetterListView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import okhttp3.Call;

public class CityListActivity extends AppCompatActivity {

	private ListAdapter adapter;
	private ListAdapter resultAdapter;
	private ListView lvCity;
	private ListView lvResult;
	private TextView tvInitial; // 对话框首字母textview
	private FrameLayout frameLayout;
	private MyLetterListView lvLetter; // A-Z listview
	private EditText etSearch;
	private TextView tvNoResult;
    private double mLatitude;
    private double mLongitude;
	private WindowManager windowManager;
	private View view;
	private HashMap<String, Integer> alphaIndexer = new HashMap<String, Integer>();// 存放存在的汉语拼音首字母和与之对应的列表位置
	private Handler handler;
	private OverlayThread overlayThread; // 显示首字母对话框
	private ArrayList<City> allList; // 所有城市列表
	private ArrayList<City> cityList;// 城市列表
	private ArrayList<City> locationList;// 城市列表
	private ArrayList<City> hotList;// 热门城市列表
	private ArrayList<City> resultList;// 搜索结果城市列表
	private ArrayList<City> historyList;// 搜索历史城市列表
	private ArrayList<City> itemList;
	private HashMap<String,ArrayList<City>> listMap = new HashMap<String,ArrayList<City>>();
	private List<ArrayList<City>> results = new ArrayList<ArrayList<City>>();
	private List<ArrayList<City>> lists = new ArrayList<ArrayList<City>>();
	private List<String> keyList = new ArrayList<String>();
	private DatabaseHelper helper;

	private LocationClient locationClient = null;
	private MylocationListener locationOption = null;
    private String cityName;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_list);
		initView();
		initData();
		setListener();
		//初始化定位
		initLocation();
	}

	private void initView(){
		helper = new DatabaseHelper(this);
		lvCity = (ListView) findViewById(R.id.list_view);
		lvResult = (ListView) findViewById(R.id.search_result);
		etSearch = (EditText) findViewById(R.id.sh);
		tvNoResult = (TextView) findViewById(R.id.tv_noresult);
		lvLetter = (MyLetterListView) findViewById(R.id.MyLetterListView01);

		initOverlay();
	}

	private void initData(){
		allList = new ArrayList<City>();
		hotList = new ArrayList<City>();
		locationList = new ArrayList<City>();
		resultList = new ArrayList<City>();
		historyList = new ArrayList<City>();

		handler = new Handler();
		overlayThread = new OverlayThread();

		resultAdapter = new ListAdapter(this,new ArrayList<String>(), results);
		lvResult.setAdapter(resultAdapter);

		locationList.add(new City("正在定位","0"));

		hotCityInit();
		historyCityInit();
		cityInit();

		adapter = new ListAdapter(this, keyList,lists);
		lvCity.setAdapter(adapter);
	}

	private void setListener(){
		lvLetter.setOnTouchingLetterChangedListener(new MyLetterListView.OnTouchingLetterChangedListener() {
			@Override
			public void onTouchingLetterChanged(String s) {
				if (alphaIndexer.get(s) != null) {
					int position = alphaIndexer.get(s);
					lvCity.setSelection(position);
					tvInitial.setText(s);
					frameLayout.setVisibility(View.VISIBLE);
					handler.removeCallbacks(overlayThread);
					// 延迟一秒后执行，让tvInitial为不可见
					handler.postDelayed(overlayThread, 1000);
				}
			}
		});
		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.toString() == null || "".equals(s.toString())) {
					lvLetter.setVisibility(View.VISIBLE);
					lvCity.setVisibility(View.VISIBLE);
					lvResult.setVisibility(View.GONE);
					tvNoResult.setVisibility(View.GONE);
				} else {
					results.clear();
					resultList.clear();
					lvLetter.setVisibility(View.GONE);
					lvCity.setVisibility(View.GONE);
					getResultCityList(s.toString());
					//insertCity(s.toString());
					if (resultList.size() <= 0) {
						tvNoResult.setVisibility(View.VISIBLE);
						lvResult.setVisibility(View.GONE);
					} else {
						tvNoResult.setVisibility(View.GONE);
						lvResult.setVisibility(View.VISIBLE);
						resultAdapter.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		adapter.SetOnButtonClickListener(new ListAdapter.OnButtonClickListener() {
			@Override
			public void onButtonClick(View view, int pos,int position) {
				String name = lists.get(pos).get(position).getName();

				if ("正在定位".equals(name)){

				}else if ("定位失败".equals(name)){
					//startLocation();
					locationList.clear();
					locationList.add(0,new City("正在定位","0"));
					lists.remove(0);
					lists.add(0,locationList);
					adapter.notifyDataSetChanged();
				}else {
					Intent intent = new Intent();
					if (name.equals("当前定位")){
                        intent.putExtra("CityName", cityName);
                        intent.putExtra("CityTag", "3");
                    }else {
                        intent.putExtra("CityName", name);
                        intent.putExtra("CityTag", "1");
                    }
                    insertCity(name);

                    setResult(RESULT_OK,intent);
					Log.e("allList", name);
					finish();
				}
			}
		});
		resultAdapter.SetOnButtonClickListener(new ListAdapter.OnButtonClickListener() {
			@Override
			public void onButtonClick(View view, int position,int pos) {
				Intent intent = new Intent();
				intent.putExtra("CityName",resultList.get(pos).getName());
                intent.putExtra("CityTag", "1");
				setResult(RESULT_OK,intent);
				insertCity(resultList.get(pos).getName());
				Log.e("resultList", resultList.get(pos).getName());
				finish();
			}
		});
	}

	private void insertCity(String name) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from city where name = '" + name + "'", null);
		if (cursor.getCount() > 0) { //
			db.delete("city", "name = ?", new String[] { name });
		}
		db.execSQL("insert into city(name, date) values('" + name + "', " + System.currentTimeMillis() + ")");
		db.close();
	}

	private void cityInit() {
		City city = new City("定位", "0"); // 当前定位城市
		allList.add(city);
		city = new City("最近", "1"); // 最近访问的城市
		allList.add(city);
		city = new City("热门", "2"); // 热门城市
		allList.add(city);
		cityList = getCityList();
		allList.addAll(cityList);
		for (int i = 0; i < allList.size(); i++) {
			// 当前汉语拼音首字母
			String currentStr = getAlpha(allList.get(i).getPinyi());
			keyList.add(currentStr);
		}
		removeDuplicateWithOrder(keyList);
		for (int j = 0; j < keyList.size(); j++){
			listMap.put(keyList.get(j),new ArrayList<City>());
		}
		for (int i = 0; i < allList.size(); i++) {
			// 当前汉语拼音首字母
			String currentStr = getAlpha(allList.get(i).getPinyi());
			itemList = listMap.get(currentStr);
			City city1 = allList.get(i);
			itemList.add(city1);
			listMap.put(currentStr, itemList);
		}

		for (int i = 0; i < keyList.size(); i++) {
			String key = keyList.get(i);
			if ("定位".equals(key)){
				lists.add(locationList);
			}else if ("最近".equals(key)){
				lists.add(historyList);
			}else if ("热门".equals(key)){
				lists.add(hotList);
			}else{
				lists.add(listMap.get(key));
			}
			alphaIndexer.put(keyList.get(i),i);
		}
	}

	/**
	 * 热门城市
	 */
	public void hotCityInit() {
        City  city = new City("北京", "2");
		hotList.add(city);
        city = new City("上海", "2");
		hotList.add(city);
		city = new City("广州", "2");
		hotList.add(city);
		city = new City("深圳", "2");
		hotList.add(city);
		city = new City("武汉", "2");
		hotList.add(city);
		city = new City("天津", "2");
		hotList.add(city);
		city = new City("西安", "2");
		hotList.add(city);
		city = new City("南京", "2");
		hotList.add(city);
		city = new City("杭州", "2");
		hotList.add(city);
		city = new City("成都", "2");
		hotList.add(city);
		city = new City("重庆", "2");
		hotList.add(city);
	}

	/**
	 * 搜索历史城市
	 */
	private void historyCityInit() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from city order by date desc limit 0, 9", null);
		while (cursor.moveToNext()) {
			City city = new City(cursor.getString(1),cursor.getString(2));
			historyList.add(city);
		}
		cursor.close();
		db.close();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<City> getCityList() {
		DBHelper dbHelper = new DBHelper(this);
		ArrayList<City> list = new ArrayList<City>();
		try {
			dbHelper.createDataBase();
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery("select * from city", null);
			City city;
			while (cursor.moveToNext()) {
				city = new City(cursor.getString(1), cursor.getString(2));
				list.add(city);
			}
			cursor.close();
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(list, comparator);
		return list;
	}

	@SuppressWarnings("unchecked")
	private void getResultCityList(String keyword) {
		DBHelper dbHelper = new DBHelper(this);
		try {
			dbHelper.createDataBase();
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from city where name like \"%" + keyword
							+ "%\" or pinyin like \"%" + keyword + "%\"", null);
			City city;
			Log.e("info", "length = " + cursor.getCount());
			while (cursor.moveToNext()) {
				city = new City(cursor.getString(1), cursor.getString(2));
				resultList.add(city);
			}
			cursor.close();
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(resultList, comparator);
		results.add(resultList);
	}

	/**
	 * a-z排序
	 */
	@SuppressWarnings("rawtypes")
    Comparator comparator = new Comparator<City>() {
		@Override
		public int compare(City lhs, City rhs) {
			String a = lhs.getPinyi().substring(0, 1);
			String b = rhs.getPinyi().substring(0, 1);
			int flag = a.compareTo(b);
			if (flag == 0) {
				return a.compareTo(b);
			} else {
				return flag;
			}
		}
	};

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.overlay, null);
		tvInitial = (TextView)view.findViewById(R.id.tv_title);
		frameLayout = (FrameLayout)view.findViewById(R.id.fl_bg);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				300, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(view, lp);
	}

	// 获得汉语拼音首字母
	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else if (str.equals("0")) {
			return "定位";
		} else if (str.equals("1")) {
			return "最近";
		} else if (str.equals("2")) {
			return "热门";
		}else {
			return "#";
		}
	}

	private void removeDuplicateWithOrder(List<String> list)  {
		Set set  =   new HashSet();
		List newList  =   new  ArrayList();
		for  (Iterator iter = list.iterator(); iter.hasNext();)  {
			Object element  =  iter.next();
			if  (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
	}

	// 设置tvInitial不可见
	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			frameLayout.setVisibility(View.GONE);
		}
	}

	/**
	 * 初始化定位
	 */
	private void initLocation(){
		//初始化client

		//locationOption = new AMapLocationClientOption();
	/*	locationOption.setOnceLocation(true);
		locationOption.setOnceLocationLatest(true);
		//设置定位参数
		locationClient.setLocationOption(locationOption);*/
		//startLocation();

		locationClient = new LocationClient(this.getApplicationContext());
		locationOption = new MylocationListener();

		//注册监听器
		locationClient.registerLocationListener(locationOption);
		//注册监听器
//        mlocationClient.registerLocationListener(mlistener);
		//配置定位SDK各配置参数，比如定位模式、定位时间间隔、坐标系类型等
		LocationClientOption mOption = new LocationClientOption();
		//设置坐标类型
		mOption.setCoorType("bd09ll");
		//设置是否需要地址信息，默认为无地址
		mOption.setIsNeedAddress(true);
		//设置是否打开gps进行定位
		mOption.setOpenGps(true);

		//设置扫描间隔，单位是毫秒 当<1000(1s)时，定时定位无效
		int span = 1000;
		mOption.setScanSpan(span);
		//设置 LocationClientOption
		locationClient.setLocOption(mOption);
		locationClient.start();
	}

	//所有的定位信息都通过接口回调来实现
	public class MylocationListener implements BDLocationListener {
		//定位请求回调接口
		private boolean isFirstIn = true;


		//定位请求回调函数,这里面会得到定位信息
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {
			/*if (bdLocation==null){
				return;
			}*/
            if (null != bdLocation) {
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(bdLocation.getCity()!=null){
                    locationList.clear();
                    cityName = bdLocation.getCity().replace("市", "");
                    locationList.add(0,new City(cityName,"0"));
                    locationList.add(1,new City("当前定位","1"));

                   // locationList.add(0,new City(bdLocation.getCity().substring(0,bdLocation.getCity().length() - 1),"0"));
                } else {
                    //定位失败
                    locationList.clear();
                    locationList.add(0,new City("定位失败","0"));
                }
            } else {
                locationList.clear();
                locationList.add(0,new City("定位失败","0"));
            }
            lists.remove(0);
            lists.add(0,locationList);
            adapter.notifyDataSetChanged();

			/*if (bdLocation.getCity()!=null) {
				 bdLocation.getLatitude();
				bdLocation.getLongitude();
                String city = bdLocation.getCity().replace("市", "");
                bdLocation.getDistrict();
				 bdLocation.getProvince();
				 bdLocation.getLocType();
			}*/

			if (locationClient.isStarted()) {
				// 获得位置之后停止定位
                locationClient.stop();
			}

			//   mlocationClient.stop();

		}
	}
	/*private void startLocation(){
		locationClient.start();
		// 设置定位监听
      //  locationClient.setLocOption(new LocationClientOption());
		locationClient.setLocationListener(new AMapLocationListener() {
			@Override
			public void onLocationChanged(AMapLocation aMapLocation) {
				if (null != aMapLocation) {
					//errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
					if(aMapLocation.getErrorCode() == 0){
						locationList.clear();
						locationList.add(0,new City(aMapLocation.getCity().substring(0,aMapLocation.getCity().length() - 1),"0"));
					} else {
						//定位失败
						locationList.clear();
						locationList.add(0,new City("定位失败","0"));
					}
				} else {
					locationList.clear();
					locationList.add(0,new City("定位失败","0"));
				}
				lists.remove(0);
				lists.add(0,locationList);
				adapter.notifyDataSetChanged();
			}
		});
	}*/

	/**
	 * 销毁定位
	 */
	private void destroyLocation(){
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			//locationClient.onDestroy();
            locationClient.stop();
			locationClient = null;
			locationOption = null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		destroyLocation();
		windowManager.removeViewImmediate(view);
	}



    private void zuobiao(final String address) {
        LogU.Ld("1608", "场馆信息");
        OkHttpUtils
                .get()
                .url("http://api.map.baidu.com/geocoder")
                .addParams("address", address)
                .addParams("output", "json")
                .addParams("key", "0xRmvPDRXDbj1Ql18OgKQIZpT6jmCTnH")
                .addParams("city", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        String result = response.toString();
                        LogU.Ld("1608", "坐标" + result);

                        boolean jsonValid = isJSONValid(result);
                        if (jsonValid) {
                            Gson gson = new Gson();
                            ZuobiaoEntity entity = gson.fromJson(result, ZuobiaoEntity.class);

                            mLatitude = entity.getResult().getLocation().getLat();
                            mLongitude = entity.getResult().getLocation().getLng();
                            //  initDownload(page, address, sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg);
                           // initDownload(page, chengshiText.getText().toString(), sxhuodong1, sxhuodong2, sxmoshi, sxfeiyong, sxsex, days, times, lv, lv2, jlTAG, hpTAg, acitvitysort, mLatitude + "", mLongitude + "", bmTAg, Agemin, Agemax);

                        }
                        //  initDownload(page, address, 0, 0, 0, 0, 0, entity.getResult().getLocation().getLat() + "", entity.getResult().getLocation().getLng() + "", joinCondition);

//                        } else {
//                            Gson gson = new Gson();
//                            JiekouSBEntity entity = gson.fromJson(result, JiekouSBEntity.class);
//                            Toast.makeText(ChuangguanActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();

//                        }
                    }
                });
    }

    public final static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
