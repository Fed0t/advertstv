package com.advertstv.serverui;

import com.advertstv.server.ServerProvider;
import javafx.application.Platform;

public class ServerStart extends Thread {
    private ServerProvider server;

    public void run(){
        System.out.println("MyThread running");
        server = new ServerProvider();
//        server.connect();
    }
}
