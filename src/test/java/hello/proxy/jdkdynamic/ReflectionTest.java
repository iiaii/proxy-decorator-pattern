package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    @DisplayName("reflection")
    public void reflection() {
        Hello hello = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String resultA = hello.callA();
        log.info("resultA={}", resultA);
        // 공통 로직 1 종료

        // 공통 로직 2 시작
        log.info("start");
        String resultB = hello.callB();
        log.info("resultB={}", resultB);
        // 공통 로직 2 종료
    }

    @Test
    @DisplayName("reflection1")
    public void reflection1() throws Exception {
        // 클래스 정보
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello hello = new Hello();

        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object resultA = methodCallA.invoke(hello);
        log.info("resultA={}", resultA);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object resultB = methodCallB.invoke(hello);
        log.info("resultB={}", resultB);
    }

    @Test
    @DisplayName("reflection2")
    public void reflection2() throws Exception {
        // 클래스 정보
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello hello = new Hello();

        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, hello);

        // callA 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, hello);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }

    }


}
