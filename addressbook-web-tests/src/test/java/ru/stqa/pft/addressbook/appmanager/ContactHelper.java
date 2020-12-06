package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ContactHelper extends HelperBase {


    private WebElement element;
    private Iterable<? extends WebElement> elements;
    private ApplicationManager app;

    public ContactHelper(WebDriver wd) {
        super( wd );
    }

    public int getContactCount() {
        return wd.findElements( By.name( "selected[]" ) ).size();
    }


    public void returnToHomepage() {
        click( By.linkText( "home" ) );
    }

    public void submitContactCreation() {
        click( By.xpath( "(//input[@name='submit'])[2]" ) );
    }

    public void gotoNewContactPage() {
        wd.findElement( By.linkText( "add new" ) ).click();
    }

    public void modify(int index, ContactData contact) {
        initContactModification( index );
        fillContactForm( contact, false );
        submitContactModification();
        returnToHomepage();
    }


    public void initContactModification(int index) {
        wd.findElements( By.cssSelector( "img[alt=\"Edit\"]" ) ).get( index ).click();
    }

    public void submitContactModification() {
        click( By.xpath( "(//input[@name='update'])[2]" ) );
    }

    public  void selectContact(int index) {
        wd.findElements( By.name( "selected[]" ) ).get( index ).click();
    }


    public  void deleteSelectedContact() {
        click ( By.xpath( "//input[@value='Delete']" ) );
    }


    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }

    public void create(ContactData contactData, boolean b) {
        gotoNewContactPage();
        fillContactForm( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
        submitContactCreation();
        returnToHomepage();
    }


    public void delete(int index, ApplicationManager app) {
        selectContact( index );
        app.acceptNextAlert=true;
        deleteSelectedContact();
        app.AlertAndGetItsText();
        app.returnHomePage();
    }

    public void create( ContactData contact) {
        fillContactForm( contact, true);
        submitContactCreation();
        returnToHomepage();
    }

    @Override
    public String toString() {
        return "ContactHelper{}";
    }

    public List<ContactData> list() {
        List<ContactData> contacts=new ArrayList<ContactData>();
        List<WebElement> elements=wd.findElements( By.cssSelector( "tr[name='entry']" ) );
        for (WebElement element : elements) {
            List<WebElement> cells=element.findElements( By.tagName( "td" ) );
            String surname=cells.get( 1 ).getText();
            String firstname=cells.get( 2 ).getText();
            int id=Integer.parseInt( element.findElement( By.tagName( "input" ) ).getAttribute( "value" ) );
            ContactData contact=new ContactData( id, surname, firstname, null, null, null, null );
            contacts.add( contact );
        }
        return contacts;
    }

    public void fillContactForm(ContactData contact, boolean creation) {

        type( By.name( "firstname" ), contact.getFirstname() );
        type( By.name( "lastname" ), contact.getSurname() );
        type( By.name( "address" ), contact.getAddress() );
        type( By.name( "mobile" ), contact.getPhone() );
        type( By.name( "email" ), contact.getEmail() );

        findNew_group( By.name( "new_group" ) );

        if (creation) {
            new Select( wd.findElement( By.name( "new_group" ) ) ).selectByVisibleText( "test 1" );
        } else {
            Assert.assertFalse( findNew_group( By.name( "new_group" ) ) );
        }
    }

    public boolean findNew_group(By locator) {
        try {
            wd.findElement( locator );
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}






