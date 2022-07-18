package messagerenderingtoolAPI.Implementations;

import java.io.*;
import java.util.Properties;

public final class Configuration {
    private static Properties properties;
    private static final String pathToXml = "config.xml";

    private Configuration() {}

    private static void initConfiguration() {
        File configurationFile = new File(pathToXml);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(configurationFile);
            properties = new Properties();
            properties.loadFromXML(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Configuration getInstance () {
        if (properties == null)
            initConfiguration();

        return this;
    }

    public static String getProperty(String name) {
        if (properties == null)
            initConfiguration();

        return properties.getProperty(name);
    }
}
