package com.ppro.qatest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ppro.qatest.utils.Constants;

public class Base {
	
	protected static WebDriver driver;
	private static Properties prop;
	
	public Base(){
		
		try{
			prop = new Properties();
			//FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//com//ppro//qatest//config//config.properties");
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//Resources//properties//config.properties");
			prop.load(fis);
			//prop.getProperty("browser");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		String browserName = prop.getProperty("browser");
		System.out.println("Browser initialized is : " + browserName );
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Resources//executables//chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if(browserName.equals("firefox")){
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Resources//executables//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}


}
