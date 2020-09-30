package com.example.serivceedu;

/**
 * create by Freedom on 2020/9/3
 */
public class ServiceEduTest {
    public static void main(String[] args) {
        A a = new A();
        a.name = "freedom";
        System.out.println("a = " + a);
        change(a);
        System.out.println("a = " + a);
    }

    private static void change(A a) {
        a.name = "222“”";
    }
}

class A{
    String name;

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                '}';
    }
}
