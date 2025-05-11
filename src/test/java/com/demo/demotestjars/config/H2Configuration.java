package com.demo.demotestjars.config;

import java.sql.SQLException;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Configuration {
  private static Server server;

  @Bean(destroyMethod = "stop")
  public Server h2TcpServer() throws SQLException {
    if (server == null) {
      server = Server.createTcpServer(
          "-tcp",
          "-webAllowOthers",
          "-tcpAllowOthers",
          "-tcpPort",
          "9092",
          "-ifNotExists"
      ).start();
    }
    return server;
  }
}
