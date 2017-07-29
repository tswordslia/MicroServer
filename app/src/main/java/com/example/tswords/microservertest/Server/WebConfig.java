package com.example.tswords.microservertest.Server;

/**
 * Created by Tswords on 2017/7/29.
 */

public class WebConfig {

    private int ports;
    private int maxParallels;
    public  WebConfig(int ports, int maxParallels){
        this.ports=ports;
        this.maxParallels=maxParallels;
    }
    public int getPorts() {
        return ports;
    }

    public void setPorts(int ports) {
        this.ports = ports;
    }

    public int getMaxParallels() {
        return maxParallels;
    }

    public void setMaxParallels(int maxParallels) {
        this.maxParallels = maxParallels;
    }

}
