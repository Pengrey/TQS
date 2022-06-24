package com.example.seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "inputName")
    WebElement inputNameElem;

    @FindBy(how = How.ID, using = "address")
    WebElement addressElem;

    @FindBy(how = How.ID, using = "city")
    WebElement cityElem;

    @FindBy(how = How.ID, using = "state")
    WebElement stateElem;

    @FindBy(how = How.ID, using = "zipCode")
    WebElement zipCodeElem;

    @FindBy(how = How.ID, using = "cardType")
    WebElement cardTypeElem;

    @FindBy(how = How.ID, using = "creditCardNumber")
    WebElement creditCardNumberElem;

    @FindBy(how = How.ID, using = "creditCardMonth")
    WebElement creditCardMonthElem;

    @FindBy(how = How.ID, using = "creditCardYear")
    WebElement creditCardYearElem;

    @FindBy(how = How.ID, using = "nameOnCard")
    WebElement nameOnCardElem;

    @FindBy(how = How.ID, using = "rememberMe")
    WebElement rememberMeButton;

    @FindBy(how = How.CSS, using = ".btn-primary")
    WebElement PurchaseButton;

    //Constructor
    public PurchasePage(WebDriver driver){
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnPurchaseButtonButton(){
        PurchaseButton.click();
    }
    public void clickOnrememberMeButton(){ rememberMeButton.click(); }

    public void setInputNameElem(String inputNameElemStr) {
        inputNameElem.clear();
        inputNameElem.sendKeys(inputNameElemStr);
    }

    public void setAddressElem(String addressElemStr) {
        addressElem.clear();
        addressElem.sendKeys(addressElemStr);
    }

    public void setCityElem(String cityElemStr) {
        cityElem.clear();
        cityElem.sendKeys(cityElemStr);
    }

    public void setStateElem(String stateElemStr) {
        stateElem.clear();
        stateElem.sendKeys(stateElemStr);
    }

    public void setZipCodeElem(String zipCodeElemStr) {
        zipCodeElem.clear();
        zipCodeElem.sendKeys(zipCodeElemStr);
    }

    public void setCreditCardNumberElem(String creditCardNumberElemStr) {
        creditCardNumberElem.clear();
        creditCardNumberElem.sendKeys(creditCardNumberElemStr);
    }

    public void setCreditCardMonthElem(String creditCardMonthElemStr) {
        creditCardMonthElem.clear();
        creditCardMonthElem.sendKeys(creditCardMonthElemStr);
    }

    public void setCreditCardYearElem(String creditCardYearElemStr) {
        creditCardYearElem.clear();
        creditCardYearElem.sendKeys(creditCardYearElemStr);
    }

    public void setNameOnCardElem(String nameOnCardElemStr) {
        nameOnCardElem.clear();
        nameOnCardElem.sendKeys(nameOnCardElemStr);
    }

    public WebElement getInputNameElem() {
        return inputNameElem;
    }

    public WebElement getAddressElem() {
        return addressElem;
    }

    public WebElement getCityElem() {
        return cityElem;
    }

    public WebElement getStateElem() {
        return stateElem;
    }

    public WebElement getZipCodeElem() {
        return zipCodeElem;
    }

    public WebElement getCardTypeElem() {
        return cardTypeElem;
    }

    public WebElement getCreditCardNumberElem() {
        return creditCardNumberElem;
    }

    public WebElement getCreditCardMonthElem() {
        return creditCardMonthElem;
    }

    public WebElement getCreditCardYearElem() {
        return creditCardYearElem;
    }

    public WebElement getNameOnCardElem() {
        return nameOnCardElem;
    }

    public WebElement getRememberMeButton() {
        return rememberMeButton;
    }
}
