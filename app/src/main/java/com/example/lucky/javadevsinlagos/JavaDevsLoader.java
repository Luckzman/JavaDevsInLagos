package com.example.lucky.javadevsinlagos;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.webkit.URLUtil;

import java.net.URL;
import java.util.List;

/**
 * Created by LUCKY on 8/18/2017.
 */
//this class is needed to load asychronous data
public class JavaDevsLoader extends AsyncTaskLoader<List<JavaDevs>> {
    private String url;
    public JavaDevsLoader(Context context, String url){
        super(context);
        this.url = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    //this method runs in the background thread an return a list<JavaDevs> Objects
    @Override
    public List<JavaDevs> loadInBackground() {
        if(url == null){
            return null;
        }
        List<JavaDevs> javadevs = Utils.extractJavaDevInfo(url);
        return javadevs;
    }
}
