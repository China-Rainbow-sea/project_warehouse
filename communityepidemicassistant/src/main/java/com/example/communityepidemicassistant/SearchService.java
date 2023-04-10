package com.example.communityepidemicassistant;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SearchService extends Service {
    public SearchService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public class Binder extends android.os.Binder{
        public SearchService getService(){
            return SearchService.this;
        }
    }
}
