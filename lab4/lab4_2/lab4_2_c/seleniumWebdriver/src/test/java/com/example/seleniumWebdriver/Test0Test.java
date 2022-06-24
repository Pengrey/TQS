package com.example.seleniumWebdriver;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;

@ExtendWith(SeleniumJupiter.class)
public class Test0Test {

  @Test
  public void test0(FirefoxDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(472, 940));
    {
      String value = driver.findElement(By.name("toPort")).getAttribute("value");
      assertEquals(value, "Buenos Aires");
    }
    {
      String value = driver.findElement(By.name("fromPort")).getAttribute("value");
      assertEquals(value, "Paris");
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Tricia Dire");
    {
      String value = driver.findElement(By.id("inputName")).getAttribute("value");
      assertEquals(value, "Tricia Dire");
    }
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("751 Yeager St");
    {
      String value = driver.findElement(By.id("address")).getAttribute("value");
      assertEquals(value, "751 Yeager St");
    }
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Vallejo");
    {
      String value = driver.findElement(By.id("city")).getAttribute("value");
      assertEquals(value, "Vallejo");
    }
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Wyoming");
    {
      String value = driver.findElement(By.id("state")).getAttribute("value");
      assertEquals(value, "Wyoming");
    }
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("43912");
    {
      String value = driver.findElement(By.id("zipCode")).getAttribute("value");
      assertEquals(value, "43912");
    }
    {
      String value = driver.findElement(By.id("cardType")).getAttribute("value");
      assertEquals(value, "visa");
    }
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("4554963859117344");
    {
      String value = driver.findElement(By.id("creditCardNumber")).getAttribute("value");
      assertEquals(value, "4554963859117344");
    }
    driver.findElement(By.id("nameOnCard")).click();
    {
      String value = driver.findElement(By.id("creditCardMonth")).getAttribute("value");
      assertEquals(value, "11");
    }
    {
      String value = driver.findElement(By.id("creditCardYear")).getAttribute("value");
      assertEquals(value, "2017");
    }
    driver.findElement(By.id("nameOnCard")).sendKeys("joand askda");
    {
      String value = driver.findElement(By.id("nameOnCard")).getAttribute("value");
      assertEquals(value, "joand askda");
    }
    driver.findElement(By.id("rememberMe")).click();
    {
      String value = driver.findElement(By.id("rememberMe")).getAttribute("value");
      assertEquals(value, "on");
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertEquals(driver.getTitle(), "BlazeDemo Confirmation");
  }
}
