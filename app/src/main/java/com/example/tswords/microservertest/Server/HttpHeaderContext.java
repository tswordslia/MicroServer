package com.example.tswords.microservertest.Server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tswords on 2017/7/29.
 */

public class HttpHeaderContext {

    private String mUrl;

    private final HashMap<String,String> mRequestHeaderMap;

    private Socket mUnderlieSocket;

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public HttpHeaderContext(){
        mRequestHeaderMap=new HashMap<String, String>();
    }

    public void addRequestHeader(String key,String value){
        mRequestHeaderMap.put(key,value);
    }

    public String getRequestHeaderValue(String key){
        return mRequestHeaderMap.get(key);
    }
    public Socket getmUnderlieSocket() {
        return mUnderlieSocket;
    }

    public void setmUnderlieSocket(Socket mUnderlieSocket) {
        this.mUnderlieSocket = mUnderlieSocket;
    }

}
