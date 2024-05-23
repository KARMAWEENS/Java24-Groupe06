package org.movieTheatre.java24groupe06.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PortConfig {
    public static int mainPort;
    public static int ticketPort;
    public static String host;

    public void loadConfig(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        }

        mainPort = Integer.parseInt(properties.getProperty("mainPort"));
        ticketPort = Integer.parseInt(properties.getProperty("ticketPort"));
        host = properties.getProperty("hostIP");
    }

}

