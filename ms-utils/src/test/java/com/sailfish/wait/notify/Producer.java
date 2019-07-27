package com.sailfish.wait.notify;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread {
    private List<Message> pool = new ArrayList<>();

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                pool.add(new Message());
                synchronized (pool){
                    pool.notify();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message waitMessage() throws Exception {
        synchronized (pool) {
            if (0 == pool.size()) {
                pool.wait();
            }
            return pool.get(0);
        }
    }
}
