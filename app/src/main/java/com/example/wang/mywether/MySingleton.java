package com.example.wang.mywether;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by PKUWa on 2017/3/27.
 */
public class MySingleton {
    private static MySingleton ourInstance ;
    private RequestQueue mRequestQueue;
    private Context mCtx;
    private ImageLoader mImageLoader;

    public static MySingleton getInstance(Context context) {
        if(ourInstance==null){
            ourInstance=new MySingleton(context);
        }
        return ourInstance;
    }

    private MySingleton(Context context) {
        mCtx =context;
        mRequestQueue =getRequestQueue();
        mImageLoader=new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> cache=new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    private RequestQueue getRequestQueue(){
        if(mRequestQueue ==null){
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return  mImageLoader;
    }
}
