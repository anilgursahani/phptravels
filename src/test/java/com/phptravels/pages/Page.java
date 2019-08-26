package com.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
  
  protected void SelectWindow(String titleOfWindowToSwitchTo)
  {
     
      Object[] allWindows = driver.getWindowHandles().toArray();
   
      String switchedToWindowTitle  = null ;
     
      for (Object window:allWindows)
      {
          String windowTitle;
          windowTitle = driver.getTitle();
          if (windowTitle.equals(titleOfWindowToSwitchTo))
          {
              break ;
          }
          driver.switchTo().window(window.toString());
         
         
          
      }
      
    
  }
  
  

  
 

}
