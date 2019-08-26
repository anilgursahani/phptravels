/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author anilg
 */
public class JourneyBeginsHerePage extends HomePage
{
    private final By activeElementLocator = By.cssSelector("li.text-center.active");
    private final By flightsElementLocator = By.cssSelector("i.fa.fa-plane");
    private final By toursElementSelector = By.cssSelector("i.fa.fa-suitcase");
    private final By roundTripRadioButtonSelector = By.cssSelector("label[for='round']");
    private final By guestsSelector = By.cssSelector("select[id='adults']");
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
     WaitForElement(roundTripRadioButtonSelector);
 }
 
 public void selectTours()
 {
     WebElement toursElement ;
     toursElement = Find(toursElementSelector);
     toursElement.click();
     WaitForElement(guestsSelector);
     
    
 }
}
    
