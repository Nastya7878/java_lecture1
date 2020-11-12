package ru.stqa.pft.newcontact.contmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationContactHelper {
    private WebDriver wd;

    public NavigationContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoHomePage() {
      wd.findElement(By.linkText("home")).click();
    }
}
