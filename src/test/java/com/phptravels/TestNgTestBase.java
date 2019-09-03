package com.phptravels;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestNgTestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;
 
 protected SuiteConfiguration config ;
 protected String browserName ;
 private ChromeDriverService service ;
  @BeforeSuite
  public void initTestSuite() throws IOException {
    
     
      config  = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    try {
    capabilities = config.getCapabilities();
   }
   catch (IOException ioe)
    {
        System.out.println(ioe.getLocalizedMessage());
    }
    
  }
  
  @BeforeClass
public void BeforeClass () 
{
    
    String userDir ;
   
     
      browserName = capabilities.getBrowserName();
      
      userDir = System.getProperty("user.dir");
      
      
      if (browserName.equalsIgnoreCase("chrome"))
      {
          String chromePath ;
          File chromeFile ;
          
          chromePath = userDir + "\\vendor\\" + "chromedriver.exe";
          //System.setProperty("webdriver.chrome.driver", chromePath);
          chromeFile = new File(chromePath);
          service = new ChromeDriverService.Builder().usingDriverExecutable(chromeFile).usingAnyFreePort().build();
          try {
            service.start();
          }
          catch(IOException ioexception)
          {
              System.out.println(ioexception.getLocalizedMessage());
          }
      
      }
      else if  (browserName.equalsIgnoreCase("firefox"))
              {
                 String firefoxPath ;
                  firefoxPath = userDir + "\\vendor\\" + "geckodriver.exe";
                  System.setProperty("webdriver.gecko.driver", firefoxPath);
              }
     
    
      
      
  }

  @BeforeMethod
  public void initWebDriver() {
     
      if (browserName.equalsIgnoreCase("chrome"))
      {
      
      ChromeOptions chromeOptions ;
      
     chromeOptions = new ChromeOptions();
     chromeOptions.addArguments("start-maximized");
    // driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
     driver = new RemoteWebDriver(service.getUrl(), chromeOptions);
     
      } 
      else if (browserName.equalsIgnoreCase("firefox"))
      {
          driver = new FirefoxDriver();
      }
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
  
  @AfterClass
  public void createAndStopService()
  {
     if (browserName.equalsIgnoreCase("chrome"))
     {
     
      service.stop();
     }
      
  }
  
  @AfterMethod
  public void quitDriver()
  {
    
      driver.quit();
     
  }
}
