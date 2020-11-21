package ru.stqa.pft.newcontact.contmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.newcontact.model1.ContactData;



public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super( wd );
    }


    public void returnToHomepage() {
        click( By.linkText( "home" ) );
    }

    public void submitContactCreation() {
        click( By.xpath( "(//input[@name='submit'])[2]" ) );
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type( By.name( "firstname" ), contactData.getFirstname() );
        type( By.name( "lastname" ), contactData.getSurname() );
        type( By.name( "address" ), contactData.getAddress() );
        type( By.name( "mobile" ), contactData.getPhone() );
        type( By.name( "email" ), contactData.getEmail() );

        findNew_group( By.name( "new_group" ));

        if (creation) {
            new Select ( wd.findElement(By.name("new_group")) ).selectByVisibleText( "test 11" );
        } else {
            Assert.assertFalse( findNew_group( By.name( "new_group" ) ) );
        }
    }


    public void initContactModification() {  click( By.cssSelector( "img[alt=\"Edit\"]" ) );  }

    public void submitContactModification() {
        click ( By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectContact() { click( By.name("selected[]") ); }

    public void deleteSelectedContact () { click( By.xpath( "//input[@value='Delete']" ) );  }
}


