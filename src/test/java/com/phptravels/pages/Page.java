package com.phptravels.pages;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;
// import static org.hamcrest.Matchers.equalTo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
// import sun.security.x509.PKIXExtensions;
 import org.openqa.selenium.Keys ;

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
    wait = new WebDriverWait(driver,  10);
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
  public void SelectElementWithTextFromPopup(By locator, String textOfElementToChoose)
  {
      
  }
  public void SelectElementFromPopup(By locator, String textOfElementToChoose)
  {
      List<WebElement> webElements ;
      ListIterator<WebElement> listIterator ;
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
      webElements = driver.findElements(locator);
      WebElement webElement ;
      int numElements ;
      listIterator = webElements.listIterator();
      numElements = 0;
      while (listIterator.hasNext())
      {
          String text ;
          numElements++ ;
          webElement = listIterator.next();
          System.out.println("Web element is " + webElement);
          text = webElement.getText();
          int index ;
          index = text.indexOf(textOfElementToChoose);
          if (index >= 0)
    //      if (text.equalsIgnoreCase(textOfElementToChoose))
          {
              webElement.click();
              break ;
          }
          
      }
      
     
      
      
     
    
      
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
  
  protected void WaitForElementTextToBecomeInvisible(By locator, String text)
  {
      wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
  }
  
  protected String GetText(By locator)
  {
      WebElement webElement ;
      webElement = Find(locator);
      return webElement.getText();
      
  }
  
 protected void SendReturn(By locator)
 {
     WebElement webElement ;
      webElement = Find(locator);
      webElement.sendKeys(Keys.RETURN);
 }
  
  protected void WaitForTextToBe(By locator, String expectedText)
  {
      wait.until(ExpectedConditions.textToBe(locator, expectedText));
  }
  
  protected void Type(By locator, String textEntry)
  {
     WebElement webElement ;
      webElement = Find(locator);
      webElement.clear();
      webElement.sendKeys(textEntry);
  }
  
  protected void TypeWithReturn(By locator, String textEntry)
  {
      WebElement webElement ;
      webElement = Find(locator);
      webElement.clear();
      webElement.sendKeys(textEntry + Keys.RETURN);
  }
  protected String InnerHTML(By locator)
  {
      String innerHTML  ;
      WebElement webElement;
      webElement = Find(locator);
     
    //  wait.until(ExpectedConditions.textToBePresentInElement(webElement, textLookingFor));
      innerHTML = webElement.getAttribute("innerHTML");
      return innerHTML ;
  }
  protected String Text(By locator)
  {
      String elementText ;
      WebElement webElement;
      webElement = Find(locator);
     
   //   wait.until(ExpectedConditions.textToBePresentInElement(webElement, textLookingFor));
      elementText = webElement.getText();
      return elementText ;
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
