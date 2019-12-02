/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phptravels.pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.implementation.bytecode.Duplication;

/**
 *
 * @author anilg
 */
public class JourneyBeginsHerePage extends HomePage
{
    private final By activeElementLocator = By.cssSelector("a.text-center.hotels.active");
    private final By hotelsElementLocator = By.cssSelector("i.fa.fa-hotel");
    private final By flightsElementLocator = By.cssSelector("i.fa.fa-plane");
    private final By toursElementLocator= By.cssSelector("i.fa.fa-suitcase");
    private final By carRentalLocator = By.cssSelector("i.fa.fa-car");
    private final By roundTripRadioButtonLocator = By.cssSelector("label[for='round']");
    private final By guestsLocator = By.cssSelector("select[id='adults']");
    private final By hotelTextLink = By.cssSelector(".hotelsearch.locationlistthhotels");
    //private final By hotelTextLink = By.cssSelector("span.select2-chosen");
    private final By hotelLink = By.cssSelector(" #s2id_location > a > span.select2-chosen");
    // s2id_location > a > span.select2-chosen
    //private final By chooseAHotelLocator = By.cssSelector("#select2-drop > div > input");
    private final By chooseAHotelLocator = By.id("location");
    private final By myAccountDropdown = By.cssSelector("div.dropdown.dropdown-login.dropdown-tab");
    private final By LoginElementLocator = By.linkText("Login");

    // private final By hotelChosenLocator = By.cssSelector("#s2id_location span.select2-chosen");

   
//private final By hotelChosenLocator = By.cssSelector("li.select2-results-dept-1.select2-result.select2-result-selectable.select2-highlighted");
    private final By hotelChosenLocator = By.cssSelector("#s2id_location > .select2-choice > span.select2-chosen");

    private final By hotelElementsSelector = By.cssSelector("div.select2-result-label");
    private final By hotelSelectedSelector = By.cssSelector("#select2-drop > ul > li > div");
    private final By nameOfHotelSelectedSelector = By.cssSelector("li.select2-results-dept-1.select2-result.select2-result-selectable div.select2-result-label");
    private final By popupSelector = By.id("select2-drop");
    private final By multiElementsPopupSelector = By.cssSelector("li.select2-results-dept-1.select2-result.select2-result-selectable > div.select2-result-label");
    private final By nameOfHotelSelectedFromMultiPopup = By.cssSelector("#s2id_location > .select2-choice > span.select2-chosen");
    
    private final By loginSelector = By.linkText("Login");
    
   // div#s2id_location.select2-choice.select2-chosen
    
   

    
    
    public JourneyBeginsHerePage(WebDriver wehDriver)   
    {
        super(wehDriver);
    }
    
 public String getTitleOfActiveElement()
 {
     WebElement activeElement ;
     activeElement = Find(activeElementLocator);
     return (activeElement.getText());
 }
 
 public void selectFlights()
 {
     WebElement flightsElement ;
     flightsElement = Find(flightsElementLocator);
     flightsElement.click();
     WaitForElement(roundTripRadioButtonLocator);
 }
 
 public void selectTours()
 {
     WebElement toursElement ;
     toursElement = Find(toursElementLocator);
     toursElement.click();
     WaitForElement(guestsLocator);
    
 }
 
 public void selectCars()
 {
     WebElement carRentalElement ;
     carRentalElement = Find(carRentalLocator);
     carRentalElement.click();
     
 }
 
 public void selectHotel(String cityOrHotel) 
 {

    String hotelSelectedText = "Hotels";
   
     Click (hotelLink);
     
     Type(chooseAHotelLocator, cityOrHotel);
     WaitForElement(popupSelector);
     
     WaitForElementTextToBecomeInvisible(popupSelector, "Searching...");
     
     SelectElementFromPopup(multiElementsPopupSelector, cityOrHotel);
 
 }
 
 public void selectHotelFromMenu(String textToEnter, String cityToChoose)
 {
     String returnedHotel ;
     Click(hotelLink);
     Type(chooseAHotelLocator, textToEnter);
     WaitForElement(popupSelector);
     
     WaitForElementTextToBecomeInvisible(popupSelector, "Searching...");

     SelectElementFromPopup(multiElementsPopupSelector, cityToChoose);
     
 }
 
 public String getHotelChosenFromPopup()
 {
     String hotelChosen ;
     WaitForElementTextToBecomeInvisible(popupSelector, "Searching...");
     hotelChosen = Text(hotelChosenLocator);
     return hotelChosen ;
 }



public void LoginToDemo(String username, String password)
 
{
	WebElement myAccountDropdownElement ;
	By usernameLocator = By.cssSelector("input[name='username']");
    By passwordLocator = By.cssSelector("input[type='password']");
    By loginSubmit = By.cssSelector("button.btn.btn-primary.btn-lg.btn-block.loginbtn");
	FluentWait<WebElement> fluentWait ;
	WebElement loginElement ;
	myAccountDropdownElement = Find(myAccountDropdown);
	myAccountDropdownElement.click();
	
	fluentWait = new FluentWait<WebElement>(myAccountDropdownElement).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(500)).
			withMessage("Login button did not appear");
	
	
	loginElement = fluentWait.until(new LoginElementAppears());
	loginElement.click();
	Type(usernameLocator, "user@phptravels.com");
	Type(passwordLocator, "demouser");
	Find(loginSubmit).click();
	
	
	
}	
	private class LoginElementAppears implements Function<WebElement, WebElement>
	{
		public LoginElementAppears()
		{
			
		}
		@Override
		public WebElement apply(final WebElement parentElement) {
			// TODO Auto-generated method stub
			WebElement loginElement ;
			loginElement = parentElement.findElement(loginSelector);
			return loginElement ;
			
		}
	}
}

	


    
