package org.example.inject;

import org.example.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * Member는 스프링 빈이 아니므로 등록되어있지 않다.
 */
public class InjectionTestClass {

    // 호출이 아예 안됌
    @Autowired(required = false)
    public void setNoBean1(Member member){
        System.out.println("setNoBean1 = " + member);
    }

    // null 호출
    @Autowired
    public void setNoBean2(@Nullable Member member){
        System.out.println("setNoBean2 = " + member);
    }

    // Optional.empty 호출
    @Autowired(required = false)
    public void setNoBean3(Optional<Member> memberOptional){
        System.out.println("setNoBean3 = " + memberOptional);
    }


}
