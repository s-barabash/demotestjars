package com.demo.demotestjars.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
import org.springframework.experimental.boot.server.exec.MavenClasspathEntry;
import org.springframework.experimental.boot.server.exec.ResourceClasspathEntry;

@TestConfiguration
//@EnableDynamicProperty - does not work
public class DemoAppConfig {

  @Bean
//  @DynamicProperty(name = "server.port", value = "18083") - does not work
  static CommonsExecWebServerFactoryBean bookShelfServer() {
    return CommonsExecWebServerFactoryBean.builder()
        .useRandomPort(false)
        .classpath(cp -> cp
            .entries(
                new MavenClasspathEntry("com.example:bookshelf:0.0.1-SNAPSHOT"),
                new MavenClasspathEntry("com.h2database:h2:2.3.232"),
                new ResourceClasspathEntry("book-shelf.properties", "application.properties")
                )
        );
  }

}
