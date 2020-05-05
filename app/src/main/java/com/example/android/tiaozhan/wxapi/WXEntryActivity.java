package com.example.android.tiaozhan.wxapi;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.android.tiaozhan.R;
import com.example.android.tiaozhan.Toos.Constants;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.weixin.callback.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {
    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);

       // DemoApplication.mWxApi.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.d("WXEntryActivity", "同意++++++++++==="+req);
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("1608", "onPayFinish, errCode = " + resp.errCode);
        int errorCode = resp.errCode;
        switch (errorCode) {
            case BaseResp.ErrCode.ERR_OK:
                //用户同意
                String code = ((SendAuth.Resp) resp).code;
                //WxShareManagerUtil.getInstance().sendResult(code);
                Log.d("WXEntryActivity", "同意++++++++++==="+code);
              //  WXLogUtils.d("同意" + code);
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝
                finish();
              //  WXLogUtils.d("+++++++++++++++拒绝");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                finish();
               // WXLogUtils.d("+===+++++++++++++=取消");
                break;
            default:
                break;
        }
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);

            if (String.valueOf(resp.errCode).equals("-2")){
//				builder.setMessage("支付失败");
                Toast.makeText(WXEntryActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                finish();
            }else{
//				builder.setMessage("支付成功");
                Toast.makeText(WXEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                finish();
            }
//			builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();
        }
    }


}
