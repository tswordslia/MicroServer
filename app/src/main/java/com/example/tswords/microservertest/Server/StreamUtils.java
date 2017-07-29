package com.example.tswords.microservertest.Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Tswords on 2017/7/29.
 */

public class StreamUtils {
    public static String readLine(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        int a=0,b=0;
        while(b!=-1&&!(a=='\r'&&b=='\n')){
            a=b;
            try {
                b=inputStream.read();
                stringBuilder.append((char)b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(stringBuilder.length()==0){
            return null;
        }

        return stringBuilder.toString();
    }

    public static byte[] readRowFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] buffer=new byte[10240];
        int nReaded;
        while((nReaded=is.read(buffer))>0){
            bos.write(buffer,0,nReaded);
        }
        return bos.toByteArray();
    }
}
