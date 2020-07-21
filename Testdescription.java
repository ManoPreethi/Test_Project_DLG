package stepDefinition;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
 

public class Testdescription {
	String ErrorMsg="";
	String loginPage="";
	String loggedinPage="";
	String[] arr = new String[7];
	String uname;
	String pswd;
	String msgSuccs="";
	String msgError="";
	int counter;
	public static WebDriver driver;
 @Given("^User is on Home Page$")
 public void user_is_on_Home_Page() throws Throwable {
	 System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumDrivers\\chromedriver.exe");
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://login.dev.qa-experience.com");
	 }
 
 
@When("^User enters \"(.*)\" and \"(.*)\"$")
public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
  loginPage = driver.getCurrentUrl();
  driver.findElement(By.name("loginUsername")).sendKeys(username); 
  driver.findElement(By.name("loginPassword")).sendKeys(password);
  driver.findElement(By.xpath("/html/body/app-root/div/app-login/form/button")).click();
  uname=String.format("%-30s", username);
  pswd=String.format("%-10s", password);
  Thread.sleep(3000);
  loggedinPage = driver.getCurrentUrl();
  try {
  msgError = driver.findElement(By.xpath("//div[@class='form-field__error']")).getText();
  }  catch (Exception e) {
	  ErrorMsg="No";
  }
  try {
  msgSuccs = driver.findElement(By.xpath("/html/body/app-root/div/app-logged-in/div")).getText();
  } catch (Exception e) {
  }
}
 
 @Then("^Message displayed Login Successfully$")
 public void message_displayed_Login_Successfully() throws Throwable {
	 String OutMsg;
 if (loginPage.equals(loggedinPage)) {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page not navigated and no error";
	 } else 
	 {
	 OutMsg="Page not navigated and error   ";
	 }
 }
 else {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page navigated and no error    ";
	 } 
	 else {
	 OutMsg="Page navigated and error       ";
	 }
 }	 
    String OutRpt= uname +"||"+ pswd +"||"+ OutMsg + "||" + msgSuccs + msgError;
    try
    {
    	String filename="D:/Selenium_Rpt/report.txt";
    	FileWriter fw = new FileWriter(filename,true);
    	fw.write(OutRpt);
    	fw.write("\n");
    	fw.close();
    	System.out.println("File Written Successfully" + OutRpt);
    	
     driver.close();
    }
    catch(IOException ioe)
    {
    	System.out.println("File not Written Successfully");
    }
        
 }
 
 @Given("^Header labels$")
 public void Header_labels() throws Throwable {
	 }
 @Then("^Generate Header to the report$")
 public void Generate_Header_to_the_report() throws Throwable {
	 String OutRpt= "USERNAME                      ||PASSWORD  ||RESULT                         ||UI MESSAGE";
	    try
	    {
	    	String filename="D:/Selenium_Rpt/report.txt";
	    	FileWriter fw = new FileWriter(filename,false);
	    	fw.write(OutRpt);
	    	fw.write("\n");
	    	fw.close();
	    	System.out.println("File Written Successfully" + OutRpt);
	    }
	    catch(IOException ioe)
	    {
	    	System.out.println("File not Written Successfully");
	    }
}
}