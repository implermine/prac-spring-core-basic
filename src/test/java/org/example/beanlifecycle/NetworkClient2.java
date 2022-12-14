package org.example.beanlifecycle;


/**
 * 빈 등록 초기화, 소멸 메서드 지정
 *
 * 설정 정보에 @Bean(initMethod = "init", destroyMethod = "close") 처럼 초기화, 소멸 메서드를 지정할 수 있다.
 */
public class NetworkClient2 {

    private final String url;


    public NetworkClient2(String url){
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


    public void init(){
        System.out.println("Init called...");
        this.connect();
        call("초기화 연결 메시지");
    }

    public void close(){
        System.out.println("close called...");
        disconnect();
    }
}
