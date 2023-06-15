package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class InputPropsUtil {

	private static Properties usersProperties;

	private static String usersPropertyFilePath = System.getProperty("user.dir")
			+ "/src/test/resources/config/input-config.properties";

	/*
	 * public static PropsUtil getInstance() { if (instance == null) { instance
	 * = new PropsUtil(); instance.loadData(); } return instance; }
	 */

	private static void loadData() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(usersPropertyFilePath));
			usersProperties = new Properties();
			try {
				usersProperties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + usersPropertyFilePath);
		}
	}

	public static String getPropertyValue(String key) {
		if (usersProperties == null) {
			loadData();
		}
		String propValue = usersProperties.getProperty(key);
		if (propValue != null)
			return propValue;
		else
			throw new RuntimeException("key " + key + " not found in the users-config.properties file.");
	}
}
