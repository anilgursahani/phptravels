package com.phptravels;

import com.google.common.base.Verify;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod ;
import org.testng.annotations.Test;

import com.phptravels.pages.HomePage;
import com.phptravels.pages.JourneyBeginsHerePage ;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class PhpTravelsTest extends TestNgTestBase {

  private HomePage homepage;
  private JourneyBeginsHerePage journeypage ;
  
  @DataProvider(name = "LoginCredentials")
  public Object[][] createData1()
  {
      return new Object[][] {
          {"user@phptravels.com", "demouser"},
         
      };
  }

@BeforeMethod
public void initPageObjects()
{
     driver.get(baseUrl);
    homepage = PageFactory.initElements(driver, HomePage.class);
}

 
  @Test(priority = 1)
  public void testHomePageHasAHeader() {
   
    String carouselControlsText ;
    carouselControlsText = homepage.carouselControls.getText();
    Assert.assertNotNull(carouselControlsText, "Got null for the carousel controls");
  }
  
  //@Test(priority=2,  dataProvider = "LoginCredentials")
 
  public void testLoginNoCaptcha(String username, String password)
  {
     String captchaText ;
     String expectedCaptchaText = "Please complete the captcha and try again.";
      homepage.Login(username, password, false);
      captchaText = homepage.getCaptcha();
      Assert.assertEquals(captchaText, expectedCaptchaText, "Did not get the expected captcha");
  }
  
 // @Test(priority=3, dataProvider="LoginCredentials")
  
  public void testLoginValidUsernameValidPassword(String username, String password)
  {
      homepage.Login(username, password, true);
  }
  
 @Test (priority=3)
 public void testHomePageFrontEnd()
 {
     journeypage = homepage.navigateToHomePageFrontEnd();
     String title ;
     title = journeypage.getTitle();
     String titleOfActiveElement ;
     boolean titlesMatch ;
     
     titlesMatch = title.equals("PHPTRAVELS | Travel Technology Partner");
     System.out.println("Title home page front end page is " + title);
     Verify.verify(titlesMatch, "Page title does not match expected title", (Object) "PHPTRAVELS | Travel Technology Partner");
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("HOTELS"));
     journeypage.selectFlights();
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
    
     Verify.verify(titleOfActiveElement.equals("FLIGHTS"));
      System.out.println("titleOfActiveElement is " + titleOfActiveElement);
     journeypage.selectTours();
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("TOURS"));
     System.out.println("titleOfActiveElement is " + titleOfActiveElement);
 }
  
 

 
 
 
  
}
