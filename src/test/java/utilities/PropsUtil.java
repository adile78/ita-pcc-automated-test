package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropsUtil {

	private static Properties properties;
	private final static String separator = ",";
	protected static Logger logger = LogManager.getLogger();

	private static String propertyFilePath = System.getProperty("user.dir")
			+ "/src/test/resources/config/configuration.properties";

	/*
	 * public static PropsUtil getInstance() { if (instance == null) { instance
	 * = new PropsUtil(); instance.loadData(); } return instance; }
	 */

	private static void loadData() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static String getPropertyValue(String key) {
		if (properties == null) {
			loadData();
		}
		String propValue = properties.getProperty(key);
		if (propValue != null)
			return propValue;
		else{
			
			logger.debug("key " + key + " not found in the configuration.properties file.");
			throw new RuntimeException("key " + key + " not found in the configuration.properties file.");			
		}
	}

	public static List<String> getPropertyValues(String key) {
		if (properties == null) {
			loadData();
		}
		String propValue = properties.getProperty(key);
		if (propValue != null){
			List<String> propValueList = new ArrayList<String>();
			propValueList = Arrays.asList(propValue.split(separator));
			return propValueList;
		}
		else {
			
			logger.debug("key " + key + " not found in the configuration.properties file.");
			throw new RuntimeException("key " + key + " not found in the configuration.properties file.");
		}
	}
	
	public static String getSeparator(){
		
		return separator;
	}
}
