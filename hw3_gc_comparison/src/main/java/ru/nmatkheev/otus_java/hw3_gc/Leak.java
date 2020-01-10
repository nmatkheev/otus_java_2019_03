package ru.nmatkheev.otus_java.hw3_gc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Leak implements LeakMBean{

    private final int iterations; // 4_000_000
    private final int batch;
    private volatile int stringSize; // 10, 20, 100000
    // emulating exponential growth of ArrayList - batch^i elements per each iteration

    private List<String> buffer = new ArrayList<>();

    public Leak(int iters, int batch) {
        this.iterations = iters;
        this.batch = batch;
        this.stringSize = 1;
    }


    @Override
    public void setStringSize(int sz) {
        System.out.println("New string size: " + sz);
        this.stringSize = sz;
    }

    @Override
    public int getStringSize() {
        return this.stringSize;
    }

    @Override
    public void run() throws InterruptedException {
        for (int idx = 1; idx <= iterations; idx++) {
            int exp = (int) (Math.pow(2, batch));
//            int exp = batch * idx;

            for (int sx = 1; sx < exp; sx++) {
                StringBuilder sb = new StringBuilder();
                IntStream.range(sx, stringSize).forEach(sb::append);
                this.buffer.add(sb.toString());
            }
            Thread.sleep(120);
        }
    }
}
