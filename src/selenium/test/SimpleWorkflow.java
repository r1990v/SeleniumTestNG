package selenium.test;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleWorkflow extends BaseClass{
  
  @Test(priority=1)
  public void openURL() throws Exception {
	  
	  //throw new SkipException("Skipped the workflow");
	  
	  log.debug("Started Test");
	  webdriver.get("https://www.google.com");
	  
	  webdriver.findElement(By.xpath("//*[@id='tsf']/div[2]/div[3]/center/input[1]"));
	  log.debug("Browsed to google URL");
	  //takeScreenShot("GoogleHomePage", webdriver);
	  webdriver.findElement(By.xpath(".//input[@value=\"I'm Feeling Lucky\"]")).click();
	  //takeScreenShot("IamFeelingLucky", webdriver);
	  try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  //Actions ac = new Actions(webdriver);
	  
	  
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
  
//  @Test(priority=2, dependsOnMethods={"openURL"})
//  public void searchGoogle() {
//      String actual="Oracle";
//      String expected="Oracle";
//      //Assert.assertEquals(actual, expected);
//      //Assert.assertTrue(2<3, "2 is not greater than three");
//      //Assert.assertTrue("is element found"), "Unable to find button 'I am lucky'");
//      //Assert.assertEquals(actual, expected);
//      
//      sa.assertEquals(actual, expected);
//      sa.fail("Message");
//      sa.assertAll();
//  }
  
  
//  @Test(priority=1, dataProvider="getData")
//  public void doLoginTest(String username,String password,String passOrFail,String browser,int phone){
//     System.out.println(username+" "+password+" "+passOrFail+" "+browser+" "+phone);
//     
//  }
  
 

  
  @DataProvider
  public Object[][] getData(){
	  Object[][] data =new Object[2][5];
	  
	  data[0][0]="User1";
	  data[0][1]="Password1";
	  data[0][2]="Pass";
	  data[0][3]="Chrome";
	  data[0][4]=99998202;
	  

	  data[1][0]="User2";
	  data[1][1]="Password2";
	  data[1][2]="Passed";
	  data[1][3]="IE";
	  data[1][4]=995577;
	  
	  return data;
			  
  }
}
