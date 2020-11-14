package ru.stqa.pft.newcontact.contmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationContactHelper extends HelperBase{

    public NavigationContactHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage() {

        click(By.linkText("home"));
    }
}
