package io.wisoft.actuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MyCustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(final Info.Builder builder) { // /info에 데이터를 넣어줄 수 있음

        final HashMap<String, String> map = new HashMap<>();

        map.put("key1", "value1");
        map.put("key2", "value2");

        builder.withDetail("myCustomInfo", map);
    }
}
