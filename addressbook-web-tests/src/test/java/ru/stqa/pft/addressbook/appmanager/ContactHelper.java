package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {


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


    public void initContactModification() {
        click( By.cssSelector( "img[alt=\"Edit\"]" ) );
    }

    public void submitContactModification() {
        click( By.xpath( "(//input[@name='update'])[2]" ) );
    }

    public void selectContact(int index) {
        wd.findElements( By.name( "selected[]" ) ).get( index ).click();
    }


    public void deleteSelectedContact() {
        click( By.xpath( "//input[@value='Delete']" ) );
    }


    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }

    public void createContact(ContactData contactData, boolean b) {
        gotoNewContactPage();
        fillContactForm( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
        submitContactCreation();
        returnToHomepage();
    }

    @Override
    public String toString() {
        return "ContactHelper{}";
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts=new ArrayList<ContactData>();
        List<WebElement> elements=wd.findElements( By.cssSelector( "tr[name='entry']"));
        for (WebElement element : elements) {
            String surname=element.getText().split(" ")[0];
            String firstname=element.getText().split(" ")[1];
            int id=Integer.parseInt( element.findElement( By.tagName( "input" ) ).getAttribute( "value" ));
            ContactData contact=new ContactData( id, surname, firstname,  null, null, null, null );
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





