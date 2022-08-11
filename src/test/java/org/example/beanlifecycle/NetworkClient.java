package org.example.beanlifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 인터페이스 InitializingBean, DisposableBean으로 생명주기 관리
 *
 * 단점:
 * 이 인터페이스는 스프링 전용 인터페이스다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
 * 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 * 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
 */
public class NetworkClient implements InitializingBean, DisposableBean {

    private final String url;


    public NetworkClient(String url){
        this.url = url;
        System.out.println("NetworkClient 생성자 호출, url = " + this.url);
    }

    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("connect called... url = " + url);
    }

    public void call(String message){
        System.out.println("call called... url =  " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("close called... url = " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet called...");


        this.connect();
        this.call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("destroy called...");

        this.disconnect();
    }


}
