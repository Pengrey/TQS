package com.example.seleniumWebdriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SeleniumJupiter.class)
public class FinalTest {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
    }

    @Test
    public void findFlights() {
        // Create object of HomePage Class and Test it
        HomePage home = new HomePage(driver);
        assertEquals(home.getToPortSelect().getAttribute("value"), "Buenos Aires");
        assertEquals(home.getFromPortSelect().getAttribute("value"), "Paris");
        home.clickOnFindFlightsButton();

        // Create object of ReservePage Class
        ReservePage reservepage = new ReservePage(driver);
        reservepage.clickOnChooseThisFlightButton();

        // Create object of PurchasePage Class and Test it
        PurchasePage purchagepage = new PurchasePage(driver);

        // Set Name and test it
        purchagepage.setInputNameElem("Tricia Dire");
        assertEquals(purchagepage.getInputNameElem().getAttribute("value"), "Tricia Dire");

        // Set Address and test it
        purchagepage.setAddressElem("751 Yeager St");
        assertEquals(purchagepage.getAddressElem().getAttribute("value"), "751 Yeager St");

        // Set City and Test it
        purchagepage.setCityElem("Vallejo");
        assertEquals(purchagepage.getCityElem().getAttribute("value"), "Vallejo");

        // Set State and Test it
        purchagepage.setStateElem("Wyoming");
        assertEquals(purchagepage.getStateElem().getAttribute("value"), "Wyoming");

        // Set Zip and test it
        purchagepage.setZipCodeElem("43912");
        assertEquals(purchagepage.getZipCodeElem().getAttribute("value"), "43912");

        // Test Card Type
        assertEquals(purchagepage.getCardTypeElem().getAttribute("value"), "visa");

        // Set CC Number and Test it
        purchagepage.setCreditCardNumberElem("4554963859117344");
        assertEquals(purchagepage.getCreditCardNumberElem().getAttribute("value"), "4554963859117344");

        // Set CC month and Test it
        purchagepage.setCreditCardMonthElem("11");
        assertEquals(purchagepage.getCreditCardMonthElem().getAttribute("value"), "11");

        // Set CC year and Test it
        purchagepage.setCreditCardYearElem("2017");
        assertEquals(purchagepage.getCreditCardYearElem().getAttribute("value"), "2017");

        // Set Name on Card and Test it
        purchagepage.setNameOnCardElem("joand askda");
        assertEquals(purchagepage.getNameOnCardElem().getAttribute("value"), "joand askda");

        // Click on Remember Me Button and Test it
        purchagepage.clickOnrememberMeButton();
        assertEquals(purchagepage.getRememberMeButton().getAttribute("value"), "on");

        // Click on Purchase Button
        purchagepage.clickOnPurchaseButtonButton();

        // Create object of ConfirmationPage Class and Test it
        ConfirmationPage confirmationpage = new ConfirmationPage(driver);
        assertEquals(confirmationpage.getTitle(), "BlazeDemo Confirmation");
    }

    @AfterEach
    public void close(){
        driver.close();
    }
}
