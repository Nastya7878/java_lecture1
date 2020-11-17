package ru.stqa.pft.newcontact.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.newcontact.contmanager.ContactManager;

public class ContactBase {

    protected final ContactManager app = new ContactManager( BrowserType.CHROME);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    public ContactManager getApp() {
        return app;
    }
}
