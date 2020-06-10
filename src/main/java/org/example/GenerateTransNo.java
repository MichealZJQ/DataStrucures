package org.example;

import java.io.IOException;

public class GenerateTransNo {

    private static Object lock = new Object();
    private static long bIndex = 0;

    public static void main(String[] args) throws IOException {

        for (int i=0;i<100;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + nextTransNo(2));
            }).start();
        }

    }

    public static String nextTransNo(int length){
        double max = Math.pow(10, length);
        String currSerial;
        synchronized (lock){
            if (++bIndex > max){
                bIndex = 0;
            }
            currSerial = bIndex + "";
        }
        while (currSerial.length() < length){
            currSerial = "0" + currSerial;
        }
        return currSerial;
    }
}
