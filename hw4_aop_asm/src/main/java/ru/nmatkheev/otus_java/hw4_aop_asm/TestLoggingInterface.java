package ru.nmatkheev.otus_java.hw4_aop_asm;


public interface TestLoggingInterface {
    @Log
    void calculation(int param);
}
