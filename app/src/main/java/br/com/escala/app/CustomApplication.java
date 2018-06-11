package br.com.escala.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;

/**
 * ESTA CLASSE É INICIALIZADA TODA VEZ QUE O ANDROID FOR CHAMADO
 * ESTA É A PRIMEIRA CLASSE A SER CHAMADA
 * */

public class CustomApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }
}
