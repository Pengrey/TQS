package com.example.seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://blazedemo.com/";

    @FindBy(how = How.ID_OR_NAME, using = "toPort")
    private WebElement toPortSelect;

    @FindBy(how = How.ID_OR_NAME, using = "fromPort")
    private WebElement fromPortSelect;

    @FindBy(how = How.CSS, using = ".btn-primary")
    private WebElement FindFlightsButton;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnFindFlightsButton(){
        FindFlightsButton.click();
    }

    public WebElement getToPortSelect() {
        return toPortSelect;
    }

    public WebElement getFromPortSelect() {
        return fromPortSelect;
    }
}
