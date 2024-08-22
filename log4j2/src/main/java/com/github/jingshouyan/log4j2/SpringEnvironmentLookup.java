package com.github.jingshouyan.log4j2;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Plugin(name="spring", category = StrLookup.CATEGORY)
public class SpringEnvironmentLookup extends AbstractLookup implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private ConfigurableApplicationContext context;
    @Override
    public String lookup(LogEvent event, String key) {
        if (context != null) {
            return context.getEnvironment().getProperty(key);
        }
        return null;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        this.context = applicationContext;
    }
}
