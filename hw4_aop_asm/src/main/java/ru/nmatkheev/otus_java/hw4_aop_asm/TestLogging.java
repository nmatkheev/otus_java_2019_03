package ru.nmatkheev.otus_java.hw4_aop_asm;

public class TestLogging implements TestLoggingInterface{
    @Log
    public void calculation(int param) {
        System.out.println("TestLogging.calculation - param="+param);
    };
}
