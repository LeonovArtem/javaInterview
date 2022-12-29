package al.spring.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
    private static final String IMAGE_VERSION = "postgres:14.6";
    private static final String DB_PORT = "55328";
    private static final String DB_NAME = "test-db";
    private static DatabaseContainer container;

    private DatabaseContainer() {
        super(IMAGE_VERSION);

        addExposedPort(Integer.valueOf(DB_PORT));
    }

    public static DatabaseContainer getInstance() {
        if (container == null) {
            container = new DatabaseContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_HOST", container.getHost());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
        System.setProperty("DB_PORT", DB_PORT);
        System.setProperty("DB_NAME", DB_NAME);
    }
}
