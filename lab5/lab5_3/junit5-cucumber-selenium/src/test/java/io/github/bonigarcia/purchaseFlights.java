package io.github.bonigarcia;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class purchaseFlights {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.firefoxdriver().create();
        driver.get(url);
    }

    @And("I choose departure city {string} and password {string}")
    public void iChooseDepartureCityAndPassword(String arg0, String arg1) {
        String value = driver.findElement(By.name("fromPort")).getAttribute("value");
        assertEquals(value, arg0);
        value = driver.findElement(By.name("toPort")).getAttribute("value");
        assertEquals(value, arg1);
    }

    @And("I clickFind Flights")
    public void iClickFindFlights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the flight number {string}")
    public void iChooseTheFlightNumber(String arg0) {
        driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    }

    @And("I choose the name {string}")
    public void iChooseTheName(String arg0) {
        driver.findElement(By.id("inputName")).sendKeys("Tricia Dire");
    }

    @And("I choose the Address {string}")
    public void iChooseTheAddress(String arg0) {
        driver.findElement(By.id("address")).sendKeys("751 Yeager St");
    }

    @And("I choose the City {string}")
    public void iChooseTheCity(String arg0) {
        driver.findElement(By.id("city")).sendKeys("Vallejo");
    }

    @And("I choose the State {string}")
    public void iChooseTheState(String arg0) {
        driver.findElement(By.id("state")).sendKeys("Wyoming");
    }

    @And("I choose the Zip Code {string}")
    public void iChooseTheZipCode(String arg0) {
        driver.findElement(By.id("zipCode")).sendKeys("43912");
    }

    @And("I choose the Card Type {string}")
    public void iChooseTheCardType(String arg0) {
        String value = driver.findElement(By.id("cardType")).getAttribute("value");
        assertEquals(value, arg0);
    }

    @And("I choose the Credit Card Number {string}")
    public void iChooseTheCreditCardNumber(String arg0) {
        driver.findElement(By.id("creditCardNumber")).sendKeys("4554963859117344");
    }

    @And("I choose the Month {string}")
    public void iChooseTheMonth(String arg0) {
        String value = driver.findElement(By.id("creditCardMonth")).getAttribute("value");
        assertEquals(value, arg0);
    }

    @And("I choose Year {string}")
    public void iChooseYear(String arg0) {
        String value = driver.findElement(By.id("creditCardYear")).getAttribute("value");
        assertEquals(value, arg0);
    }

    @And("I choose the Name on Card {string}")
    public void iChooseTheNameOnCard(String arg0) {
        driver.findElement(By.id("nameOnCard")).sendKeys("joand askda");
    }

    @And("I click Remember me")
    public void iClickRememberMe() {
        driver.findElement(By.id("rememberMe")).click();
    }

    @And("I click Purchase Flight")
    public void iClickPurchaseFlight() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should be see the title {string}")
    public void iShouldBeSeeTheTitle(String arg0) {
        try {
        assertEquals(driver.getTitle(), arg0);
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + arg0 + "\" not available in results");
        } finally {
            driver.quit();
        }
    }
}
