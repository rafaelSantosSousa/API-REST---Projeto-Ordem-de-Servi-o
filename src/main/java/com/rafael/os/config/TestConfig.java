package com.rafael.os.config;

import com.rafael.os.services.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("Test")
public class TestConfig {
    @Autowired
    private DBservice dbService;
}

