package com.microservices.business.config.controller;

import com.netflix.archaius.DefaultPropertyFactory;
import com.netflix.archaius.api.Property;
import com.netflix.archaius.config.PollingDynamicConfig;
import com.netflix.archaius.config.polling.FixedPollingStrategy;
import com.netflix.archaius.readers.URLConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/props")
public class DynamicPropertiesRestController {

    private final DefaultPropertyFactory factory;

    @Autowired
    public DynamicPropertiesRestController(@Value("${dynamic-properties.path}") String configPath) {
        factory = DefaultPropertyFactory.from(
                new PollingDynamicConfig(
                        new URLConfigReader(configPath),
                        new FixedPollingStrategy(1, TimeUnit.SECONDS)));
    }

    @GetMapping
    public Map<String, String> getDynamicProperties() {
        Property<String> property1 = factory.get("properties.one", String.class);
        Property<String> property2 = factory.get("properties.two", String.class);
        Property<String> property3 = factory.get("properties.three", String.class);

        Map<String, String> props = new HashMap<String, String>();
        props.put(property1.getKey(), property1.get());
        props.put(property2.getKey(), property2.get());
        props.put(property3.getKey(), property3.get());
        return props;
    }
}
