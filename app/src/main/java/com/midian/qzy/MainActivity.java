package com.midian.qzy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.w.pik.sdk.PikBaseActivity;
import com.w.pik.sdk.PikSDK;

public class MainActivity extends PikBaseActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvResult);
        PikSDK.getInstance().init(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                PikSDK.getInstance().login_qq();
                break;
            case R.id.btn_share:
                Bundle bundle = getShareParams();
                PikSDK.getInstance().share_qq(bundle);
                break;
        }
    }

    @Override
    public void onResult(int i, Object... objects) {
        super.onResult(i, objects);
        String result = null;
        if(objects.length > 0){
            result = (String) objects[0];
        }
        switch (i){
            case PikSDK.CODE_QQ_LOGIN_SUCCESS:
                tvResult.setText(result);
                break;
            case PikSDK.CODE_QQ_LOGIN_CANCEL:
                tvResult.setText(R.string.qq_login_cancel);
                break;
            case PikSDK.CODE_QQ_SHARE_SUCCESS:
                tvResult.setText(R.string.qq_share_success);
                break;
            case PikSDK.CODE_QQ_SHARE_CANCEL:
                tvResult.setText(R.string.qq_share_cancel);
                break;
        }
    }

    private Bundle getShareParams(){
        Bundle params = new Bundle();
        params.putString("title", "腾讯新闻分享标题");
        params.putString("imageUrl", "http://inews.gtimg.com/newsapp_bt/0/876781763/1000");
        params.putString("targetUrl", "http://www.qq.com/");
        params.putString("summary", "新闻分享摘要内容,这里是QQ分享摘要测试, 字数不够? 再加点?");
        params.putString("appName", "Pik工具");
        return params;
    }
}
