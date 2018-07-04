package online.omnia.propellerads;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lollipop on 09.08.2017.
 */
public class Utils {
    private static FileWriter writer;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");

    public static void writeLog(String text) {
        try { if (writer == null) writer = new FileWriter("PropellerAdsLog.log", true);
            writer.write(dateFormat.format(new Date()) + "\n" + text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static synchronized Map<String, String> iniFileReader() {
        Map<String, String> properties = new HashMap<>();
        try (BufferedReader iniFileReader = new BufferedReader(new FileReader("sources_stat.ini"))) {
            String property;
            String[] propertyArray;
            while ((property = iniFileReader.readLine()) != null) {
                propertyArray = property.split("=");
                if (property.contains("=")) {
                    properties.put(propertyArray[0], propertyArray[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
