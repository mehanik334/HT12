package ua.epam.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class PropertiesDB {

    public static ArrayList<String> getProperties() {
        ArrayList<String> listProperties = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\app.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);

            listProperties.add(properties.getProperty("db.url"));
            listProperties.add(properties.getProperty("db.login"));
            listProperties.add(properties.getProperty("db.password"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listProperties;
    }
}
