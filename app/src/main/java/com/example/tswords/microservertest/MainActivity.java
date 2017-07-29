package com.example.tswords.microservertest;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tswords.microservertest.Server.IndexResUrlHandler;
import com.example.tswords.microservertest.Server.MicroServer;
import com.example.tswords.microservertest.Server.WebConfig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private MicroServer microServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebConfig webConfig=new WebConfig(8000,50);
        TextView textView= (TextView) findViewById(R.id.text);
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        textView.setText(ip);
        MyGlobalContext.setmContext(getApplicationContext());
        microServer=new MicroServer(webConfig);
        microServer.registerUrlHandler(new IndexResUrlHandler());
        microServer.startAsyn();

    }

    private String intToIp(int i) {

        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }

    @Override
    protected void onDestroy() {
        microServer.stopAsyn();
        super.onDestroy();
    }
}
