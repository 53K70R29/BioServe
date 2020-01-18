/*
Server Emulator 1
*/
package bioserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Configuration
 *  reads config.properties in the same directory folder
 */
public class Configuration {
    public String gs_ip;
    public String db_user;
    public String db_password;

    // constructor
    public Configuration() {
        InputStream inputStream = null;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        try {
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);

            this.gs_ip       = prop.getProperty("gs_ip");
            this.db_user     = prop.getProperty("db_user");
            this.db_password = prop.getProperty("db_password");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Exception: " + e);
                }
            }
        }
    }
    
}
