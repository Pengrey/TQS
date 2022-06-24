package com.example.seleniumWebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    private WebDriver driver;

    //Constructor
    public ConfirmationPage(WebDriver driver){
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return this.driver.getTitle();
    }
}
