package com.btzh.fly.zhiwen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.btzh.fly.zhiwen.utils.CommonFinger;

/**
 * 模块名称:
 * Created by fly on 2017/6/14.
 */

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
               new CommonFinger(Splash.this, new CommonFinger.CommonFingerInterface() {
                   @Override
                   public void dealSuccess() {
                       Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                       Splash.this.startActivity(mainIntent);
                       Splash.this.finish();
                   }

                   @Override
                   public void dealFaild() {

                   }
               }).Authentication();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }

}
