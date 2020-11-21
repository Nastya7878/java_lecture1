package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver wd;
    private By locator;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        locator.findElement( wd ).click();
    }


    protected void type(By locator, String text) {
        click( locator );
        if (text != null) {
            String existingText=wd.findElement( locator ).getAttribute( "value" );
            if (!text.equals( existingText )) {
                wd.findElement( locator ).clear();
                wd.findElement( locator ).sendKeys( text ); }
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void gotoGroupPage() {
      wd.findElement(By.linkText("group page")).click(); }



    protected boolean isElementPresent(By by) {
        try {
            wd.findElement( by );
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }
}




