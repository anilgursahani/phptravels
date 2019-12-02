package com.phptravels.pages;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebDriver.TargetLocator;

/**
 * Home page this is the page from where we start from
 */
public class HomePage extends Page {
 
  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;
  TargetLocator targetLocator ;
  
  @FindBy(how= How.CLASS_NAME, using="carousel-controls")
  @CacheLookup
  public WebElement carouselControls ;
  
  @FindBy(how=How.CSS, using=".ico.fa.fa-lock")
  @CacheLookup
  public WebElement loginLock ;
  
  private final By loginSubmitLocator = By.cssSelector("#login");
  private final By usernameLocator = By.cssSelector("#inputEmail.form-control");
  private final By passwordLocator = By.cssSelector("#inputPassword.form-control");
  private final By captchaLocator = By.cssSelector("div.alert.alert-danger.text-center");
 private final By captchaCheckmark = By.cssSelector("div.recaptcha-checkbox-checkmark");
 private final By homePageFrontEndLocator = By.cssSelector("a[href='//www.phptravels.net']");
 
         
  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }
 
  
 
  
  public void Login(String username, String password, boolean checkCaptcha )
  {
     WebElement captchaCheckbox ;
      Click(loginLock);
     
      SelectWindow("Client Area - PHPTRAVELS");
    
      WaitForElement(loginSubmitLocator);
      Type(usernameLocator, username);
      Type(passwordLocator, password);
      if (checkCaptcha)
      {
          captchaCheckbox = Find(captchaCheckmark) ;
          captchaCheckbox.click();
      }
      
        Click(loginSubmitLocator);
     
  }
  
  public String getCaptcha()
  {
      
      WebElement captchaElement ;
      String captchaText ;
      captchaElement = Find(captchaLocator);
      captchaText = captchaElement.getText();
      return captchaText ;
      
  }
  
  public JourneyBeginsHerePage navigateToHomePageFrontEnd()
  {
      WebElement homepageFrontendElement ;
      String focusedWindow ;
      String newWindow ;
      String windowTitle ;
      
      focusedWindow = driver.getWindowHandle();
      
      windowTitle = driver.getTitle();
           
      homepageFrontendElement = Find(homePageFrontEndLocator);
      homepageFrontendElement.click();
      Set<String> windowHandles = driver.getWindowHandles();
     
      for (String windowHandle: windowHandles)
      
      {
    	if (!windowHandle.equals(focusedWindow))
    	{
    		driver.switchTo().window(focusedWindow);
    		driver.close();
    		newWindow = windowHandle ;
    		driver.switchTo().window(newWindow);
    		break ;
    	}
      }
     
      focusedWindow = driver.getWindowHandle();
      JourneyBeginsHerePage journeyBeginsHerePage = new JourneyBeginsHerePage(driver);
 
      return journeyBeginsHerePage ;
     
              
  }
}
