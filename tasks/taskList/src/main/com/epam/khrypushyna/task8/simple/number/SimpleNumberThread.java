package com.epam.khrypushyna.task8.simple.number;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SimpleNumberThread extends Thread implements Callable<List<Integer>>{

    private int from;
    private int to;
    private volatile List<Integer> result;

    public SimpleNumberThread(int from, int to) {
        this.from = from;
        this.to = to;
        result = new ArrayList<>();
    }

    public SimpleNumberThread(int from, int to, List<Integer> list){
        this.from = from;
        this.to = to;
        this.result = list;
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            if (isSimple(i)) {
                result.add(i);
            }
        }
    }

    private boolean isSimple(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> getResult(){
        return result;
    }

    @Override
    public List<Integer> call() throws Exception {
        run();
        return result;
    }
}
