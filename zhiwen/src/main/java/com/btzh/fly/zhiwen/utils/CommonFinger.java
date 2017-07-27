package com.btzh.fly.zhiwen.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import com.btzh.fly.zhiwen.R;

/**
 * 模块名称:
 * Created by fly on 2017/6/14.
 */

public class CommonFinger {
    
    private Context mContext;
    private CommonFingerInterface fingerInterface;
    
    public CommonFinger(Context mContext,CommonFingerInterface fingerInterface){
        this.mContext = mContext;
        this.fingerInterface = fingerInterface;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Authentication() {

        FingerprintUtil.callFingerPrint(new FingerprintUtil.OnCallBackListenr() {
            AlertDialog dialog;
            @Override
            public void onSupportFailed() {
                showToast("当前设备不支持指纹");
            }

            @Override
            public void onInsecurity() {
                showToast("当前设备未处于安全保护中");
            }

            @Override
            public void onEnrollFailed() {
                showToast("请到系统设置中添加指纹");
            }

            @Override
            public void onAuthenticationStart() {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Dialog_Fullscreen);
//                View view = LayoutInflater.from(Main.this).inflate(R.layout.layout_fingerprint,null);
//                builder.setView(view);
                builder.setCancelable(false);
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        FingerprintUtil.cancel();
//                    }
//                });
                dialog = builder.create();
                dialog.show();
                Window window = dialog.getWindow();
                window.getDecorView().setPadding(0, 0, 0, 0);
                window.setGravity(Gravity.CENTER);

                window.setContentView(R.layout.layout_fingerprint);
            }

            @Override
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                showToast(errString.toString());
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                }
            }

            @Override
            public void onAuthenticationFailed() {
                showToast("解锁失败");
                fingerInterface.dealFaild();
            }

            @Override
            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                showToast(helpString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                showToast("解锁成功");
                if (dialog != null  &&dialog.isShowing()){
                    dialog.dismiss();
                }
                fingerInterface.dealSuccess();
            }
        },mContext);
    }


    public void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    
    
    public interface  CommonFingerInterface{
        void dealSuccess();
        void dealFaild();
    }
}
