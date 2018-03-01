package com.will.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.will.aidldemo.MyAIDL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //要转换的字符串
    String s = "aaaaaaaaaa";
    TextView tv;

    //获取服务端的AIDL实例，调用方法
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyAIDL myAIDL = MyAIDL.Stub.asInterface(iBinder);
            try {
                tv.setText(myAIDL.toUpperCase(s));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        bindService();
    }

    private void bindService() {
        //隐式开启服务
        Intent intent = new Intent("com.will.aidldemo.MyService");
        //指定远程服务的包名和类名
        intent.setComponent(new ComponentName("com.will.aidldemo", "com.will.aidldemo.MyService"));

        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
