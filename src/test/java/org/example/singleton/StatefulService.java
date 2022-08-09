package org.example.singleton;

/**
 * 싱글턴 패턴에서 상태를 무상태(stateless)가 아닌 유지(stateful)할 경우 발생하는 문제점 예시
 */
public class StatefulService {

    private int price; // 상태를 유지하는 필드
    private final ThreadLocal<Integer> threadLocalPrice = new ThreadLocal<>();

    public void order(String name, int price){
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기가 문제
        threadLocalPrice.set(price); // 여기는 안문제?
    }

    public int getPrice(){
        return price;
    }

    public int getThreadLocalPrice(){
        return threadLocalPrice.get();
    }
}
