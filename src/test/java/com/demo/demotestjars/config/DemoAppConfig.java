package com.demo.demotestjars.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;

@TestConfiguration
public class DemoAppConfig {

  @Bean
  static CommonsExecWebServerFactoryBean bookShelfServer() {
    return CommonsExecWebServerFactoryBean.builder()
        .useRandomPort(false)
        .systemProperties((props) -> props.putAll(getBookShelfProperties()))
        .classpath(cp -> cp
            .files("target/test-libs/bookshelf.jar")
        );
  }

  private static Map<String, String> getBookShelfProperties() {
    try (InputStream input = DemoAppConfig.class.getClassLoader()
        .getResourceAsStream("book-shelf.properties")) {
      Properties properties = new Properties();
      properties.load(input);

      Map<String, String> map = new HashMap<>();
      for (String key : properties.stringPropertyNames()) {
        map.put(key, properties.getProperty(key));
      }
      return map;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
