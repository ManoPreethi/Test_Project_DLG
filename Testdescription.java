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

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
 
public class Testdescription {
//Variable declaration
	String ErrorMsg="";
	String loginPage="";
	String loggedinPage="";
	String TC_status ="";
	String uname;
	String pswd;
	String msgSuccs="";
	String msgError="";
	String scenarioName="";
	public static WebDriver driver;

//Method to open Chrome Driver with the given url
 @Given("^User is on Home Page$")
 public void user_is_on_Home_Page() throws Throwable {
	 System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\SeleniumDrivers\\chromedriver.exe");
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://login.dev.qa-experience.com");
	 }

 //Method to get the Scenario name from feature file
 @Before
public void printScenarioName(Scenario scenario) {
       scenarioName=scenario.getName(); 
} 

 //Method to receive parameters from the feature file
 @When("^User enters \"(.*)\" and \"(.*)\"$")
 public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
  loginPage = driver.getCurrentUrl();
  driver.findElement(By.name("loginUsername")).sendKeys(username); 
  driver.findElement(By.name("loginPassword")).sendKeys(password);
  driver.findElement(By.xpath("/html/body/app-root/div/app-login/form/button")).click();
  uname=username;
  pswd=password;
  Thread.sleep(3000);
  loggedinPage = driver.getCurrentUrl();

  //Observe message from the login page
  try {
  msgError = driver.findElement(By.xpath("//div[@class='form-field__error']")).getText();
  }  catch (Exception e) {
	  ErrorMsg="No";
  }
  //Observe message after the logging-in
  try {
  msgSuccs = driver.findElement(By.xpath("/html/body/app-root/div/app-logged-in/div")).getText();
  } catch (Exception e) {
  }
}
 
 //Method to check if login has been successful and update TC_STATUS
 @Then("^Login Successful$")
 public void Login_Successful() throws Throwable {
	 String OutMsg;
 if (loginPage.equals(loggedinPage)) {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page not navigated and no error";
	 TC_status="FAILED";
	 } else 
	 {
	 OutMsg="Page not navigated and error   ";
	 TC_status="FAILED";
	 }
 }
 else {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page navigated and no error    ";
	 TC_status="PASSED";
	 } 
	 else {
	 OutMsg="Page navigated and error       ";
	 TC_status="FAILED";
	 }
 }	 
    //Writing the report file
 	String OutRpt=scenarioName +"," + uname +","+ pswd +","+ OutMsg + "," + msgSuccs + msgError +","+TC_status ;
    try
    {
    	String filename="D:/Selenium_Rpt/test_report.csv";
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
 //Method to check if login has not been successful
 @Then("^Login Not Successful$")
 public void Login_Not_Successful() throws Throwable {
	 String OutMsg;
 if (loginPage.equals(loggedinPage)) {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page not navigated and no error";
	 TC_status="FAILED";
	 } else 
	 {
	 OutMsg="Page not navigated and error   ";
	 TC_status="PASSED";
	 }
 }
 else {
	 if (ErrorMsg.equals("No")) {
	 OutMsg="Page navigated and no error    ";
	 TC_status="FAILED";
	 } 
	 else {
	 OutMsg="Page navigated and error       ";
	 TC_status="FAILED";
	 }
 }	
    //Writing the report file
    String OutRpt=scenarioName +"," + uname +","+ pswd +","+ OutMsg + "," + msgSuccs + msgError +","+TC_status ;
    try
    {
    	String filename="D:/Selenium_Rpt/test_report.csv";
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
 //Method to generate header to the report file
 @Given("^Header labels$")
 public void Header_labels() throws Throwable {
	 }
 @Then("^Generate Header to the report$")
 public void Generate_Header_to_the_report() throws Throwable {
	 String OutRpt="SCENARIO,USERNAME,PASSWORD,ACTUAL RESULT,UI MESSAGE,TC_STATUS";
	    try
	    {
	    	String filename="D:/Selenium_Rpt/test_report.csv";
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