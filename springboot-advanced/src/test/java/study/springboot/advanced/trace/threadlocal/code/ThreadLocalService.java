package study.springboot.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        try {
            log.info("์ ์ฅ name={} -> newStore={}", name, nameStore.get());
            nameStore.set(name);
            sleep(1000);
            log.info("์กฐํ newStore={}", nameStore.get());
            return nameStore.get();
        } finally {
            nameStore.remove();
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
