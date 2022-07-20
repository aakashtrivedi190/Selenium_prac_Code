package dataManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
static Properties p=null;
	
	
	
	public ConfigReader() throws IOException
	{
	
	p=new Properties();
	
	File file = null;
 
	file=new File(System.getProperty("user.dir")+"/Properties/"+"data.properties");
	 	
	FileInputStream in=new FileInputStream(file);
	p.load(in);
	}
	
	
	public static String getValueFromPropertyFile(String key) 
	{
		if(p==null)
		{
			try {
				new ConfigReader();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String value=p.getProperty(key);
		return value;
	}

}

	


