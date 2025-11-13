package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import genericutilities.PropertyFileUtility;

public class fetchTheDataFromPropertiesfile {
public static void main(String[] args) throws IOException {
/*	FileInputStream fis = new FileInputStream("C:\\Users\\ADMIN\\OneDrive\\Documents\\key_value.properties");
	Properties prop = new Properties();
	prop.load(fis);
	
	String browser = prop.getProperty("browser");
	String url = prop.getProperty("url");
String username = prop.getProperty("username");
String password = prop.getProperty("password");
*/
	PropertyFileUtility plib=new PropertyFileUtility();
	String BROWSER =plib.readDataFromProperty("Browser");
	String URL =plib.readDataFromProperty("URL");
	String USERNAME =plib.readDataFromProperty("Username");
	String PASSWORD =plib.readDataFromProperty("password");

System.out.println(BROWSER);
System.out.println(URL);
System.out.println(USERNAME);
System.out.println(PASSWORD);

}
}
