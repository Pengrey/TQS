package com.example.seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "tr:nth-child(1) .btn")
    private WebElement ChooseThisFlightButton;

    //Constructor
    public ReservePage(WebDriver driver){
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnChooseThisFlightButton(){
        ChooseThisFlightButton.click();
    }
}
