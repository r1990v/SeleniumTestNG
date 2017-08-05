package selenium.test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;

public class SimpleWorkflow {
  WebDriver webdriver;
  StopWatch sw;
  Logger log;
  
  @Test
  public void openURL() {
	  
	  log.debug("Started Test");
	  webdriver.get("https://www.google.com");
	  
	  webdriver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[3]/center/input[1]"));
	  log.debug("Browsed to google URL");

	  webdriver.findElement(By.xpath(".//input[@value=\"I'm Feeling Lucky\"]")).click();
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  WebDriverWait wt = new WebDriverWait(webdriver, 200);
	  //Start Timer
	  sw.start();
	  //Start Logger
	  wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='latest']/div/div[1]")));
	  log.debug("Found Text after clicking on lucky content");
	  //webdriver.findElement(By.xpath("//*[@id='latest']/div/div[1]"));
	  sw.stop();
	  System.out.println(sw.toString());
  }
  
  
  @BeforeTest()
  public void beforeTest() {
	    //Instantiate Stopwatch
	    log = Logger.getLogger("EnergyLogs");
	    
	    log.debug("Started Before Test method");
	    sw = new StopWatch();
	    //Set System property to drivers physical path. Note: Different for different drivers.
		System.setProperty("webdriver.chrome.driver","c:/resources/chromedriver.exe"); 	//System.getProperty("user.dir").replace("\\", "/") + "/resources/chromedriver.exe");
		//Instantiate webdriver with chrome driver, here we can implement multiple browser concept
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
		webdriver = new ChromeDriver();
		//Maximize driver window.
		webdriver.manage().window().maximize();
		//Set minimum timeout settings. Set according to your applications performance requirements
		webdriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  log.debug("Stopping the test - After Test");
	  //Quit Webdriver
	  webdriver.quit();
	  sw = null;
	  
  }

}
