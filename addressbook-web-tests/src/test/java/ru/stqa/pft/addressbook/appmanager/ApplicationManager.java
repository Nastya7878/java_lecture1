package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ApplicationManager {
    public NavigationHelper NavigationHelper;
    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;
    public boolean acceptNextAlert=true;

    public ApplicationManager(String browser) {
        this.browser=browser;
    }

    public void init() {
        if (browser.equals( BrowserType.FIREFOX )) {
            wd=new FirefoxDriver();
        } else if (browser.equals( BrowserType.CHROME )) {
            wd=new ChromeDriver();
        } else if (browser.equals( BrowserType.IE )) {
            wd=new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait( 3, TimeUnit.SECONDS );
        wd.get( "http://localhost/addressbook/" );
        groupHelper=new GroupHelper( wd );
        navigationHelper=new NavigationHelper( wd );
        sessionHelper=new SessionHelper( wd );
        sessionHelper.login( "admin", "secret" );

        wd.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
    }

    public void Logout() {
        wd.findElement( By.linkText( "Logout" ) ).click();
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement( by );
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }


    public void returnHomePage() {
        wd.findElement( By.xpath( "//div[@id='header']/a" ) ).click();
    }

    public void gotoNewContactPage() {
        wd.findElement( By.linkText( "add new" ) ).click();
    }


    public String closeAlertAndGetItsText() {
        try {
            Alert alert=wd.switchTo().alert();
            String alertText=alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert=true;
        }
    }

    public void AlertAndGetItsText() {
        assertTrue( closeAlertAndGetItsText().matches( "^Delete 1 addresses[\\s\\S]$" ) );
    }

    public ContactHelper getContactHelper() {
        return new ContactHelper( wd );
    }

}






