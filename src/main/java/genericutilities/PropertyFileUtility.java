package genericutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	
	public String readDataFromProperty(String key) throws IOException {
		
	
	FileInputStream fis = new FileInputStream("C:\\Users\\ADMIN\\OneDrive\\Documents\\key_value.properties");
	Properties prop = new Properties();
	prop.load(fis);
	String value=prop.getProperty(key);
	return value;
}
}
