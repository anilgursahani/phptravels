package com.phptravels;

import com.google.common.base.Verify;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod ;
import org.testng.annotations.Test;

import com.phptravels.pages.HomePage;
import com.phptravels.pages.JourneyBeginsHerePage ;
import java.util.ArrayList;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


public class PhpTravelsTest extends TestNgTestBase {

  private HomePage homepage;
  private JourneyBeginsHerePage journeypage ;
  
@DataProvider(name = "LoginCredentials")
  public Object[][] createData()
  {
      return new Object[][] {
          {"user@phptravels.com", "demouser"},
         
      };
  }
  
  @DataProvider(name = "HotelsAndCities")
 
      public Object[][]createHotelsAndCities()
      {
          return new Object[][]{
              {"Marriot", "Islamabad Marriott Hotel, Islamabad"},
              {"Hilton", "Hilton Head, United States"},
          };
      }
      
      
      @DataProvider(name="selectFromMenuOfHotelsAndCitiesDP")
      public Object[][] hotelsAndCities()
      {
          
          String[]  marrCities = {"Gamarra, Colombia", "Marrakech, Morocco", "Marree, Australia", "Marromeu, Mozambique"};
           return new Object[][]{
    
     
              {"Marr", marrCities, "Marree, Australia"},
              
               };
      }
                
      
  

@BeforeMethod
public void initPageObjects()
{
     
     System.out.println("Driver is " + driver);
    driver.get(baseUrl);
    homepage = PageFactory.initElements(driver, HomePage.class);
    
}

 
  @Test(description="Verify home page")
  public void verifyHomePage() {
   
    String carouselControlsText ;
    carouselControlsText = homepage.carouselControls.getText();
    Assert.assertNotNull(carouselControlsText, "Got null for the carousel controls");
  }
  
@Test(description = "Test login with no captcha",  dataProvider = "LoginCredentials")
 
  public void testLoginNoCaptcha(String username, String password)
  {
     String captchaText ;
     String expectedCaptchaText = "Please complete the captcha and try again.";
      homepage.Login(username, password, false);
      captchaText = homepage.getCaptcha();
      Assert.assertEquals(captchaText, expectedCaptchaText, "Did not get the expected captcha");
  }
  
 @Test(description = "Test login with valid username and password", dataProvider="LoginCredentials")
  
  public void testLoginValidUsernameValidPassword(String username, String password)
  {
      homepage.Login(username, password, true);
  }
  
 @Test (description = "Test front end of the home page")
 public void testHomePageFrontEnd()
 {
     journeypage = homepage.navigateToHomePageFrontEnd();
     String title ;
     title = journeypage.getTitle();
     String titleOfActiveElement ;
     boolean titlesMatch ;
     titlesMatch = title.equals("PHPTRAVELS | Travel Technology Partner");
     
     Verify.verify(titlesMatch, "Page title does not match expected title", (Object) "PHPTRAVELS | Travel Technology Partner");
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("HOTELS"));
 }
 
 @Test(description = "Test selecting Flight link")
 public void testSelectingFlights()
 {
     
     String titleOfActiveElement; 
      journeypage = homepage.navigateToHomePageFrontEnd();
     journeypage.selectFlights();
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("FLIGHTS"));
 }
 
 @Test(description="Test selecting Tour Link")
 public void testSelectingTours()
 {
     String titleOfActiveElement ;
     journeypage = homepage.navigateToHomePageFrontEnd();
     journeypage.selectTours();
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("TOURS"));
 }
 
 @Test(description="Test CarsLink")
 public void testCarsLink()
 {
     String titleOfActiveElement ;
     journeypage = homepage.navigateToHomePageFrontEnd();
     journeypage.selectCars();
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("CARS"));
      try {
        Thread.sleep(5000);
     }
     catch(java.lang.InterruptedException ie)
     {
         
     }
    
 }    
  
 
 @Test(description="Test Hotel Link", dataProvider="HotelsAndCities")
 public void testHotelsLink(String hotelOrCity, String expectedHotelOrCity)
 {
     String titleOfActiveElement;
     String hotelSelected ;
     journeypage = homepage.navigateToHomePageFrontEnd();
     hotelSelected = journeypage.selectHotel(hotelOrCity);
    
     titleOfActiveElement = journeypage.getTitleOfActiveElement();
     Verify.verify(titleOfActiveElement.equals("HOTELS"));
      try {
        Thread.sleep(5000);
     }
     catch(java.lang.InterruptedException ie)
     {
         
     }
      Assert.assertEquals(hotelSelected, expectedHotelOrCity,"Hotel selected does not match expected hotel");
      
 }
 
 @Test(description="Let user select from menu of hotels and cities", dataProvider="selectFromMenuOfHotelsAndCitiesDP")
 
 public void testSelectHotelFromMenu(String textToEnter, String[] cityMenu, String cityToChoose)
 {
      String titleOfActiveElement;
     String hotelSelected ;
     journeypage = homepage.navigateToHomePageFrontEnd();
     hotelSelected = journeypage.selectHotelFromMenu(textToEnter, cityToChoose);
     
 }
}
 
      
 
  
 

 
 
 
  
