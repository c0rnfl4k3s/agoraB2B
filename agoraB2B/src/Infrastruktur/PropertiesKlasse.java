package Infrastruktur;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesKlasse {

    private Properties props = new Properties();
    private FileInputStream fis = null;

    public PropertiesKlasse() {

        try {
            fis = new FileInputStream("resource/config.properties");
            props.load(fis);
        } catch (Exception e) {

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Properties getProp() {
        return props;
    }
}
