package com.booking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

    /**
     * Loads configuration properties from 'Config.properties' resource file.
     * Provides typed getter methods to retrieve configured values such as URL,
     * destination, occupancy details, and user personal info for tests.
     *
     * Properties are loaded once statically and cached for efficient reuse.
     */

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ReadConfig.class.getClassLoader().getResourceAsStream("Config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String geturl() {
        return properties.getProperty("url");
    }

    public static String getDestination() {
        return properties.getProperty("destination");
    }

    public static String getNoOfAdults() {
        return properties.getProperty("adults");
    }

    public static String getAminity() {
        return properties.getProperty("amenity");
    }

    public static String getFirstName() {
        return properties.getProperty("firstName");
    }

    public static String getLastName() {
        return properties.getProperty("lastName");
    }

    public static String getEmail() {
        return properties.getProperty("email");
    }

    public static String getCountry() {
        return properties.getProperty("country");
    }

    public static String getPhone() {
        return properties.getProperty("phone");
    }
}
