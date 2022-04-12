package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class CacheProxy implements Subject {

    private Subject target;
    private String cacheValue;

    public CacheProxy(final Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (Objects.isNull(cacheValue)) {
            cacheValue = target.operation();
        }
        return cacheValue;
    }

}
