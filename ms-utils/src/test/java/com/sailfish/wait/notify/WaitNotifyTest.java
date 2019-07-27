package com.sailfish.wait.notify;

public class WaitNotifyTest {
    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
        new Consumer("Consumer1", producer).start();
        new Consumer("Consumer2", producer).start();
        new Consumer("Consumer3", producer).start();
    }
}
