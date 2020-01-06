package ru.nmatkheev.otus_java.hw3_gc;

public interface LeakMBean {

    void setStringSize(int sz);

    int getStringSize();

    void run() throws InterruptedException;

}
