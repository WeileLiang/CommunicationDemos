package com.will.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    IBinder binder=new MyAIDL.Stub() {
        @Override
        public String toUpperCase(String s) throws RemoteException {
            return s.toUpperCase();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
