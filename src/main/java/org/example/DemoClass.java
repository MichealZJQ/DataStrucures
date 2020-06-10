package org.example;

public class DemoClass extends ParentClass{
    static {
        System.out.println("In DemoClass Block");
    }

    public DemoClass(){
        super(34);
        System.out.println("In DemoClass Constructor");
    }

    public void print(){
        System.out.println("Just One Line");
    }

    public static void main(String[] args) {
        new DemoClass().print();
    }
}


class ParentClass{
    static {
        System.out.println("Parent Static Block");
    }
    ParentClass(){
        System.out.println("In Parent Constructor");
    }
    ParentClass(int val){
        System.out.println("In Parent Constructor"+val);
    }
}