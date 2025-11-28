package com.sprint.sb06deokhugamteam01.config;

import com.sprint.sb06deokhugamteam01.Sb06DeokhugamTeam01Application;
import io.github.openfeign.querydsl.jpa.spring.repository.config.EnableQuerydslRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableQuerydslRepositories(basePackageClasses = Sb06DeokhugamTeam01Application.class)
public class QueryDslConfig {
}
