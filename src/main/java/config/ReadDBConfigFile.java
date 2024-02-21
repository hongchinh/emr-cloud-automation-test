package config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadDBConfigFile {

	private static Properties properties = new Properties();


	public ReadDBConfigFile(){
		String propertyFilePath = Url.URL.contains("uat") ? "src/test/resources/postgresql_creds.properties" : "src/test/resources/postgresql_creds_datadog.properties";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
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
	
	public String getProperty(String propertyKey) {
		String propertyValue = properties.getProperty(propertyKey.trim());
		return propertyValue;
	}
	public int getIntegerProperty(String propertyKey) {
		int propertyValue = Integer.parseInt(properties.getProperty(propertyKey.trim()));
		return propertyValue;
	}
	
	public static void setProperty(String propertyKey, String value) {
		properties.setProperty(propertyKey, value);
	}

}
