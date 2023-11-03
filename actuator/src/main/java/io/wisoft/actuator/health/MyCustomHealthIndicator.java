package io.wisoft.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() { // /health에 데이터를 넣어줄 수 있음

        if (isStatusUp()) {
            return Health.up()
                    .withDetail("key1", "value1")
                    .withDetail("key2", "value2")
                    .build();
        }

        return Health.down()
                .withDetail("key3", "value3")
                .withDetail("key4", "value4")
                .build();
    }

    boolean isStatusUp() {
        return System.currentTimeMillis() % 2 == 0;
    }
}
