package com.rafael.os.config;

import com.rafael.os.repositories.TecnicoRepository;
import com.rafael.os.services.DBservice;
import com.rafael.os.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("dev")
@EnableJpaRepositories(basePackages = {
        "com.rafael.os.repositories"
})
public class DevConfig {
    @Autowired
    private DBservice dbService;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;


    public DevConfig(String ddl) {
        this.ddl = ddl;
    }


    @Bean
    public TecnicoRepository tecnicoRepository() {
        return tecnicoRepository;
    }
    @Bean
    public TecnicoService tecnicoService() {
        return tecnicoService;
    }

}