package com.example.tswords.microservertest.Server;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tswords on 2017/7/29.
 */

public class MicroServer {

    private Set<IUrlHandler> mUrlHandler;
    private WebConfig webConfig;

    private boolean mIsEnable=true;

    private ExecutorService mThreadPool=Executors.newCachedThreadPool();

    private ServerSocket mServerSocket;

    public MicroServer(WebConfig webConfig){
        this.webConfig=webConfig;
        mUrlHandler=new HashSet<>();
    }

    public void startAsyn(){
        mThreadPool.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    mServerSocket=new ServerSocket(webConfig.getPorts());

                    while(mIsEnable){
                        Socket socket=mServerSocket.accept();
                        handlerProcAsyn(socket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stopAsyn(){
        if(!mIsEnable)
        {
            return;
        }
        mIsEnable=false;
        try {
            mServerSocket.close();
            mServerSocket=null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerProcAsyn(Socket socket){
        try {
            HttpHeaderContext httpHeaderContext=new HttpHeaderContext();
            httpHeaderContext.setmUnderlieSocket(socket);
            InputStream input=socket.getInputStream();
            String headLine=null;
            headLine=StreamUtils.readLine(input);
            String url=headLine.split(" ")[1];
            httpHeaderContext.setmUrl(url);
            Log.d("------test",headLine);
            while((headLine=StreamUtils.readLine(input))!=null){
                if(headLine.equals("\r\n")){
                    break;
                }
                String[] tmp=headLine.split(":");
                httpHeaderContext.addRequestHeader(tmp[0],tmp[1]);
                Log.d("------test",headLine);
            }

            for(IUrlHandler item:mUrlHandler){
                if(!item.accept(httpHeaderContext.getmUrl())){
                    continue;
                }
                item.handler(httpHeaderContext);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerUrlHandler(IUrlHandler iUrlHandler){
        mUrlHandler.add(iUrlHandler);
    }
}
