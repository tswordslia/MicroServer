package com.example.tswords.microservertest.Server;

import java.io.IOException;

/**
 * Created by Tswords on 2017/7/29.
 */

public interface IUrlHandler {

    public boolean accept(String url);

    public void handler(HttpHeaderContext headerContext) throws IOException;
}
