
package academy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {
	WebDriver driver;
	Properties prop;
	
	public WebDriver initializeBrowser() throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.properties");
		prop = new Properties();

		prop.load(inputStream);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\30083949\\Desktop\\Selenium\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\30083949\\Desktop\\Selenium\\FireFox_Gekodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\30083949\\Desktop\\Selenium\\IE Driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenshotPath(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
	}

}
