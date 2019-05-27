package objectRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class ObjRepositoryTestNG 
{
	//Set up Web driver
	WebDriver driver = null;	
	@Test
	public void ObjRepositorytest() throws IOException{
		//Set up WebDriver properties
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Users\\HECTOR\\Downloads\\chromedriver.exe");				
		//Read object repository
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream("C:/Users/HECTOR/workspace/"+
				"objectRepository/Repo/setUp.properties");
		prop.load(ip);		
		String browserName = prop.getProperty("browser");
		//Method to select a browser
		if(browserName.equals("Google Chrome")){
			driver = new ChromeDriver();
			System.out.println("Browser Selected was: "+prop.getProperty("browser"));
			}else if(browserName.equals("FF")){
			driver = new FirefoxDriver();
			}else if(browserName.equals("Safari")){
			driver = new SafariDriver();
			}else if(browserName.equals("IE")){
			driver = new InternetExplorerDriver();
			}else{
			System.out.println("invalid browser");
			}
			//Open browser && navigate through a URL
			driver.get(prop.getProperty("url"));
			//Implicit wait
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Maximize window
			driver.manage().window().maximize();
			//Print ULR from the page
			System.out.println("The url from the page is: "+driver.getCurrentUrl());
			//Assert
			boolean mystr = driver.findElement(By.xpath(prop.getProperty("assertLocator"))).isDisplayed();
			System.out.println("Result: " +mystr+" - Element on web page is Displayed");		
			//WebDriver performed actions
			driver.findElement(By.name(prop.getProperty("nameLocator"))).sendKeys(prop.getProperty("username"));
			driver.findElement(By.name(prop.getProperty("lastnameLocator"))).sendKeys(prop.getProperty("lastname"));
			driver.findElement(By.id(prop.getProperty("sexLocator"))).click();
			driver.findElement(By.id(prop.getProperty("idLocator"))).click();
			driver.findElement(By.id(prop.getProperty("datepickerLocator"))).sendKeys(prop.getProperty("date"));
			driver.findElement(By.id(prop.getProperty("professionLocator"))).click();
			driver.findElement(By.id(prop.getProperty("automationToolLocator"))).click();
			//WebElement list to print the options on screen
			List<WebElement> uno = driver.findElements(By.id(prop.getProperty("continentsLocator")));
			for(WebElement dos:uno){
				String tres = dos.getText();			
				System.out.println("The available continents are: ");
				System.out.println(tres);
			}
			//WebDriver performed actions
			driver.findElement(By.id(prop.getProperty("continentsLocator"))).click();
			driver.findElement(By.xpath(prop.getProperty("contOpTresLocator"))).click();
			driver.findElement(By.id(prop.getProperty("submitLocator"))).submit();
			//Close driver
			driver.quit();
			}
				
	}

