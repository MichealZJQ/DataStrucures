package org.example;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.stream.IntStream;

public class SnowFlake {

    private final static long SEQUENCE_BIT = 9L;
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT); // 序号的最大值 2^SEQUENCE_BIT - 1

    private static long sequence = 100L; //初始序列号
    private long lastStmp = -1L;//上一次时间戳

    private static SnowFlake snowFlake = new SnowFlake();

    public static SnowFlake getInstance(){
        return snowFlake;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized String nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
                sequence = 100L;
            }
        } else {
            //不同秒内，序列号置为0
            sequence = 100L;
        }

        lastStmp = currStmp;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(new Date());
        return str + sequence;
//        return sequence;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return Instant.now().getEpochSecond();
    }

    public synchronized String generateId(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(new Date());
        return str + snowFlake.nextId();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(0,1000).forEach(a -> {
            new Thread(() -> {
                System.out.println(SnowFlake.getInstance().nextId());
            }).start();
        });
    }
}