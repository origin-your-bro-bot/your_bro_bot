package com.yourbro.configurationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationService {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationService.class);

    public Properties getPropertiesFile(String file) {
        Properties properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream(file)) {
            properties.load(is);
        } catch (IOException e) {
            LOG.error("Cannot load properties file {}", e.getMessage());
        }
        return properties;
    }
}
