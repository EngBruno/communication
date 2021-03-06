package com.communication.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlContainerCustom  extends PostgreSQLContainer<PostgresqlContainerCustom> {

    private static final String IMAGE_VERSION = "postgres:11.1";
    private static PostgresqlContainerCustom container;

    private PostgresqlContainerCustom() {
        super(IMAGE_VERSION);
    }

    public static PostgresqlContainerCustom getInstance() {
        if (container == null) {
            container = new PostgresqlContainerCustom();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
