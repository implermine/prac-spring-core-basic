package org.example.webscope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.UUID;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

/**
 * Web Scope의 request 스코프를 사용하여
 *
 * 어떤 유저가 들어왔는지 확인하고, 로깅을 수행하도록 함
 */

@Component
@Scope(scopeName = SCOPE_REQUEST)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + this.uuid + "]" + "[" + this.requestURL + "]" + message );
    }

    @PostConstruct
    public void init(){
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + this.uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + this.uuid +"] request scope bean close: " +this);
    }
}
