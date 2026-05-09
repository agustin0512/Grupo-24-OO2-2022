package com.example;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        configurePostgresUrlFromEnvironment();
        SpringApplication.run(DemoApplication.class, args);
    }

    private static void configurePostgresUrlFromEnvironment() {
        String databaseUrl = firstNotBlank(
                System.getenv("SPRING_DATASOURCE_URL"),
                System.getenv("DB_URL"),
                System.getenv("DATABASE_URL"));

        if (databaseUrl == null || databaseUrl.startsWith("jdbc:")) {
            return;
        }

        if (!databaseUrl.startsWith("postgres://") && !databaseUrl.startsWith("postgresql://")) {
            return;
        }

        URI uri = URI.create(databaseUrl);
        String jdbcUrl = "jdbc:postgresql://" + uri.getHost() + ":" + resolvePort(uri) + uri.getPath();

        if (uri.getQuery() != null && !uri.getQuery().isBlank()) {
            jdbcUrl += "?" + uri.getQuery();
        }

        System.setProperty("spring.datasource.url", jdbcUrl);

        String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            String[] credentials = userInfo.split(":", 2);
            setIfMissing("spring.datasource.username", "SPRING_DATASOURCE_USERNAME", "DB_USER", credentials[0]);
            if (credentials.length > 1) {
                setIfMissing("spring.datasource.password", "SPRING_DATASOURCE_PASSWORD", "DB_PASS", credentials[1]);
            }
        }
    }

    private static int resolvePort(URI uri) {
        return uri.getPort() == -1 ? 5432 : uri.getPort();
    }

    private static void setIfMissing(String propertyName, String primaryEnvName, String fallbackEnvName, String value) {
        if (firstNotBlank(System.getenv(primaryEnvName), System.getenv(fallbackEnvName)) == null) {
            System.setProperty(propertyName, value);
        }
    }

    private static String firstNotBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }
}
