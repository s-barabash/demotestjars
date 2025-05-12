package com.demo.demotestjars.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
//import static org.springframework.experimental.boot.server.exec.MavenClasspathEntry; - does not exist in testjars

@TestConfiguration
//@EnableDynamicProperty - does not work
public class DemoAppConfig {

  @Bean
//  @DynamicProperty(name = "server.port", value = "18083") - does not work
  static CommonsExecWebServerFactoryBean bookShelfServer() {
    return CommonsExecWebServerFactoryBean.builder()
        .useRandomPort(false)
        .systemProperties((props) -> props.putAll(getBookShelfProperties()))
        .classpath(cp -> cp
            .files("target/test-libs/bookshelf.jar")
//            .entries(new MavenClasspathEntry("com.example:bookshelf:0.0.1-SNAPSHOT") - does not exist in testjars
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
