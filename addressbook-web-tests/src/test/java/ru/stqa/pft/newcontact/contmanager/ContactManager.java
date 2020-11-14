package ru.stqa.pft.newcontact.contmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class ContactManager {
    private SessionHelper sessionHelper;
    private NavigationContactHelper navigationContactHelper;
    private ContactHelper contactHelper;
    public boolean acceptNextAlert = true;
    public WebDriver wd;


    public void init() {
        wd= new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/delete.php?part=5;");
        contactHelper=new ContactHelper(wd);
        navigationContactHelper=new NavigationContactHelper(wd);
        sessionHelper=new SessionHelper(wd);
        sessionHelper.Login("admin", "secret");
    }


    public void Logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    public void gotoNewContactPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void returnHomePage() {
      wd.findElement(By.xpath("//div[@id='header']/a")).click();
    }


    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }


    public void stop() {
        wd.quit();
    }

    public String closeAlertAndGetItsText() {
      try {
        Alert alert = wd.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }

    public void AlertAndGetItsText() {
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationContactHelper getNavigationContactHelper() {
        return navigationContactHelper;
    }
}
