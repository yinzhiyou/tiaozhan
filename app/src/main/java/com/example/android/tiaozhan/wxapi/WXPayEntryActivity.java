package com.example.android.tiaozhan.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tiaozhan.Home.HomeZhifuCGActivity;
import com.example.android.tiaozhan.Toos.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {



    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.pay_result);
        TextView tv = new TextView(this);
        tv.setText("支付结果");
        setContentView(tv);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("1608", "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:
                    Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();//传值

                    intent.setClass(WXPayEntryActivity.this, HomeZhifuCGActivity.class);
                    bundle.putString("tag", "2");
//                    bundle.putString("uuid", inviteId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                    break;
                case -1:
                    Toast.makeText(getApplicationContext(), "支付取消", Toast.LENGTH_SHORT);
                    finish();
                    break;
                case -2:
                    Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT);
                    finish();
                    break;
            }
            finish();
        }
    }

}