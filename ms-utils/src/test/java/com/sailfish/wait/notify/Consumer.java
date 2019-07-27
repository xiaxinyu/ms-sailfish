package com.sailfish.wait.notify;

public class Consumer extends Thread {
    private String name;
    private Producer producer;

    public Consumer(String name, Producer producer) {
        this.name = name;
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                Message message = producer.waitMessage();
                System.out.println(String.format("Consumer[%s] gains Message[%s]", name, message.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
