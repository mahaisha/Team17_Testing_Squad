package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;
	
	/**
	 * This method is used to load the properties from congig.properties file
	 * @return it returns Properties prop object
	 */
	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
		
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
