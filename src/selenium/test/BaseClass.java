package selenium.test;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseClass {
	
	WebDriver webdriver;
	  StopWatch sw;
	  Logger log;
	  SoftAssert sa;
	  
	  
	  @BeforeTest()
	  @Parameters("browser")
	  public void beforeTest(String browserName) {
		    //Instantiate Stopwatch
		    BasicConfigurator.configure();
		    System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
			
		    log = Logger.getLogger("EnergyLogs");
		    sa = new SoftAssert();
		    log.debug("Started Before Test method");
		    
		    sw = new StopWatch();
		    
		    if(browserName.equalsIgnoreCase("chrome"))
		    {
		    //Set System property to drivers physical path. Note: Different for different drivers.
			System.setProperty("webdriver.chrome.driver","c:/resources/chromedriver.exe"); 	//System.getProperty("user.dir").replace("\\", "/") + "/resources/chromedriver.exe");
			//Instantiate webdriver with chrome driver, here we can implement multiple browser concept
			webdriver = new ChromeDriver();
		    }
		    else if(browserName.equalsIgnoreCase("IE"))
		    {
		    	System.setProperty("webdriver.ie.driver", "c:/resources/IEDriverServer.exe");
		    	webdriver = new InternetExplorerDriver();
		    }
		    
		    webdriver.manage().deleteAllCookies();
		    
			//Maximize driver window.
			webdriver.manage().window().maximize();
			//Set minimum timeout settings. Set according to your applications performance requirements
			webdriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	  }

	  @AfterTest
	  public void afterTest() {
		  log.debug("Stopping the test - After Test");
		  //Quit Webdriver
		  webdriver.quit();
		  sw = null;
		  
	  }
	  
	  public static void takeScreenShot(String fileName, WebDriver webdriver) throws Exception{
		  TakesScreenshot ts = (TakesScreenshot)webdriver;
		  File src = ts.getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(src, new File("./Screenshots/"+fileName+".png"));
		  System.out.println("ScreenShot Captured");
	  }

}
