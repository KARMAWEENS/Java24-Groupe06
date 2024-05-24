package org.movieTheatre.java24groupe06.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The PortConfig class provides methods for managing port configurations.
 */
public class PortConfig {
    public static int mainPort;
    public static int ticketPort;
    public static String host;

    private String filePath = "src/main/resources/conf.txt";
    /**
     * Initializes a new instance of the PortConfig class.
     */
    public void loadConfig() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }

        mainPort = Integer.parseInt(properties.getProperty("mainPort"));
        ticketPort = Integer.parseInt(properties.getProperty("ticketPort"));
        host = properties.getProperty("hostIP");
    }

}

