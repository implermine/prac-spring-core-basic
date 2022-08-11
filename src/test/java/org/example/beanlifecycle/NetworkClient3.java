package org.example.beanlifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient3 {

    private final String url;


    public NetworkClient3(String url){
        this.url = url;
        System.out.println("Constructor called... url = " + this.url);
    }

    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("connect called... url = " + url);
    }

    public void call(String message){
        System.out.println("call called... url = " + url + " message " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("disconnect called... " + url);
    }


    @PostConstruct
    public void init(){
        System.out.println("Init called...");
        this.connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        System.out.println("close called...");
        disconnect();
    }
}
