package com.gabe.dlrvprjgroup.gabedlrvapi.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.gabe.dlrvprjgroup.gabedlrvdb")
@EnableJpaRepositories(basePackages = "com.gabe.dlrvprjgroup.gabedlrvdb")
public class JpaConfig {
}
