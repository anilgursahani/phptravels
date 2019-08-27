package com.phptravels.pages;

import java.util.Set;
import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class Page {

  protected  WebDriver driver;
  protected WebDriverWait wait ;

  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver,  5);
  }

  public String getTitle() {
    return driver.getTitle();
  }
  
  public WebElement Find(By locator)
  {
      WebElement webElement ;
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
      webElement = driver.findElement(locator);
      return webElement ;
  }
  
  protected void Click(WebElement we)
  {
     wait.until(ExpectedConditions.elementToBeClickable(we));
     we.click();
  }
  
  protected void Click(By locator)
  {
      WebElement webElement ;
      webElement = Find(locator);
      webElement.click();
      
  }
  
  protected void WaitForElement(By locator)
  {
      wait.until(ExpectedConditions.presenceOfElementLocated(locator));
  }
  
  protected void Type(By locator, String textEntry)
  {
     WebElement webElement ;
      webElement = Find(locator);
      webElement.sendKeys(textEntry);
  }
 
 /**
  * This function selects a window that has the title passed in the titleOfWindowToSwitchTo parameter.
  * On exit - This function returns boolean value of True if it was able to select the window with the given title.  Otherwise it returns False.
  */
  
  protected boolean SelectWindow(String titleOfWindowToSwitchTo)
  {
      
      String firstWindow ;
      firstWindow = driver.getWindowHandle();
      String newWindow = "";
      Set<String> allWindows = driver.getWindowHandles();
      boolean windowSelected = false ;
      int numTriesSelectingWindow = 0;
      while (!windowSelected)
      {
      numTriesSelectingWindow++ ;
      if (numTriesSelectingWindow > 5) // If tried five times before then exit
          break ; 
      for (String window : allWindows)
      {
          if (!window.equals(firstWindow))
          {
              newWindow = window ;
          }
      }
      driver.switchTo().window(firstWindow);
     String driverTitle ;
     driverTitle = driver.getTitle();
    
     Assert.assertNotEquals(driverTitle, titleOfWindowToSwitchTo);
     driver.switchTo().window(newWindow);
     driverTitle = driver.getTitle();
    
     
     if (driverTitle.equals(titleOfWindowToSwitchTo))
     {
        windowSelected = true ;
     }
     else
     {
         windowSelected = false ;
         try{
             Thread.sleep(1000); 
         }
         catch (InterruptedException ie)
         {
             ie.printStackTrace(); 
         }
     }
    }
     return windowSelected ;
    
      
  }

  
 

}
