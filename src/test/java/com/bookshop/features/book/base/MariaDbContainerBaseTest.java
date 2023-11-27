package com.bookshop.features.book.base;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class MariaDbContainerBaseTest {

    @ServiceConnection
    public static MariaDBContainer<?> mariaDB = new MariaDBContainer<>(DockerImageName.parse("mariadb:11.0.3"))
            .withDatabaseName("kipme")
            .withUsername("kipme")
            .withPassword("kipme-secret");

    static {
        mariaDB.start();
    }
}
