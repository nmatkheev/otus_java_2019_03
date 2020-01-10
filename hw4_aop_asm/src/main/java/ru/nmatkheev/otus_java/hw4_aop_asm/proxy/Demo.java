package ru.nmatkheev.otus_java.hw4_aop_asm.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import ru.nmatkheev.otus_java.hw4_aop_asm.TestLogging;
import ru.nmatkheev.otus_java.hw4_aop_asm.TestLoggingInterface;


public class Demo {
    static class MagicHandler implements InvocationHandler {
        private final TestLoggingInterface clsInst;

        public MagicHandler(TestLoggingInterface clsInst) {
            this.clsInst = clsInst;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Annotation[] annotations = method.getDeclaredAnnotations();
            long hasLog = Arrays.stream(annotations).filter((x) -> x.annotationType().getCanonicalName().contains("Log")).count();
            if (hasLog > 0L)
                System.out.println("Called with args: "+ Arrays.toString(args));
            return method.invoke(clsInst, args);
        }
    }

    public static void main(String[] args) {
        InvocationHandler handler = new MagicHandler(new TestLogging());
        TestLoggingInterface ti = (TestLoggingInterface) Proxy.newProxyInstance(Demo.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
        ti.calculation(1);
    }
}
