package com.example.tswords.microservertest.Server;

import android.provider.Settings;

import com.example.tswords.microservertest.MyGlobalContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Tswords on 2017/7/29.
 */

public class IndexResUrlHandler implements IUrlHandler {
    private String acceptPrefix="/";
    @Override
    public boolean accept(String url) {
        return url.startsWith(acceptPrefix);
    }

    @Override
    public void handler(HttpHeaderContext headerContext) throws IOException {
        int startIndex=acceptPrefix.length();
        String assetPath=headerContext.getmUrl().substring(startIndex);
        if(assetPath.equals("")){
            assetPath="index.html";
        }
        InputStream fis= MyGlobalContext.getmContext().getAssets().open(assetPath);
        byte[] raw=StreamUtils.readRowFromStream(fis);
        fis.close();
        OutputStream nos= headerContext.getmUnderlieSocket().getOutputStream();
        PrintStream printer=new PrintStream(nos);
        printer.println("HTTP/1.1 200 OK");
        printer.println("Content-Length:"+raw.length);
        if(assetPath.endsWith(".html")){
            printer.println("Content-Type:text/html");
        }else if(assetPath.endsWith(".css")){
            printer.println("Content-Type:text/css");
        }else if(assetPath.endsWith(".js")){
            printer.println("Content-Type:text/js");
        }else if(assetPath.endsWith(".jpg")){
            printer.println("Content-Type:image/jpg");
        }else if(assetPath.endsWith(".png")){
            printer.println("Content-Type:image/png");
        }
        printer.println();
        printer.write(raw);
        printer.close();
    }
}
