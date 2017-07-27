package com.btzh.fly.zhiwen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.btzh.fly.zhiwen.utils.CommonFinger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        new CommonFinger(MainActivity.this, new CommonFinger.CommonFingerInterface() {
            @Override
            public void dealSuccess() {

            }

            @Override
            public void dealFaild() {

            }
        }).Authentication();
    }

}
