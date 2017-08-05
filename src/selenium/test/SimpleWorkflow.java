package selenium.test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class SimpleWorkflow {
  WebDriver webdriver;
  StopWatch sw;
  
  @Test
  public void openURL() {
	  
	  webdriver.get("https://www.google.com");
	  
	  webdriver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[3]/center/input[1]"));
	  

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
	  //webdriver.findElement(By.xpath("//*[@id='latest']/div/div[1]"));
	  sw.stop();
	  System.out.println(sw.toString());
  }
  
  
  @BeforeTest()
  public void beforeTest() {
	    //Instantiate Stopwatch
	    sw = new StopWatch();
	    //Set System property to drivers physical path. Note: Different for different drivers.
		System.setProperty("webdriver.chrome.driver","c:/resources/chromedriver.exe"); 	//System.getProperty("user.dir").replace("\\", "/") + "/resources/chromedriver.exe");
		//Instantiate webdriver with chrome driver, here we can implement multiple browser concept
		webdriver = new ChromeDriver();
		//Maximize driver window.
		webdriver.manage().window().maximize();
		//Set minimum timeout settings. Set according to your applications performance requirements
		webdriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() {
	  //Quit Webdriver
	  webdriver.quit();
	  sw = null;
  }

}
