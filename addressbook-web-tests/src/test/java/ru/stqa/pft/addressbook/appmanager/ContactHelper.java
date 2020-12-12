package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends HelperBase {


    private WebElement element;
    private Iterable<? extends WebElement> elements;

    public ContactHelper(WebDriver wd) {
        super( wd );
    }

    public int count() {
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

    public void modify(ContactData contact) {
        initContactModificationById( contact.getId() );
        fillContactForm( contact, false );
        submitContactModification();
        returnToHomepage();
    }


    public void initContactModificationById(int id) {
      //  WebElement checkbox =wd.findElement(By.cssSelector( String.format( "input[value='%s']", id ) ));
    //    WebElement row = checkbox.findElement( By.xpath( "./../.." ) );
    //    List<WebElement>cells = row.findElements( By.tagName( "td" ) );
    //    cells.get(7).findElement( By.tagName( "a" ) ).click();

     //   wd.findElement( By.cssSelector( "img[alt=\"Edit\"]" ) ).click();
     //   wd.findElement(By.xpath( String.format( "//input[&value='%s']./../../td[8]/a",id ) ) );
     //   wd.findElement(By.xpath( String.format( "//tr[.//input[&value='%s']./../../td[8]/a",id ) ) );
       wd.findElement(By.cssSelector( String.format( "a[href='edit.php?id=%s']",id ) ) ).click();
    }


    public void submitContactModification() {
        click( By.xpath( "(//input[@name='update'])[2]" ) );
    }

    public  void selectContact(int index) {
        wd.findElements( By.name( "selected[]" ) ).get( index ).click();
    }


    public  void selectContactById(int id) {
        WebElement we = wd.findElement( By.cssSelector( "input[value = '" + id + "']"));
        we.click();
    }


    public  void deleteSelectedContact() {
        click ( By.xpath( "//input[@value='Delete']" ) );
    }


    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }

    public void create(ContactData contactData, boolean b) {
        gotoNewContactPage();
        fillContactForm( new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ).withPhone("+375298641245").withEmail("babaVera@tut.by").withGroup( "test11" ), true );
        submitContactCreation();
        returnToHomepage();
    }



    public void delete(ContactData contact, ApplicationManager app) {
        selectContactById( contact.getId() );
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


    public Contacts all() {
        Contacts contacts=new Contacts();
        List<WebElement> rows =wd.findElements( By.name( "entry" ) );
        for (WebElement row : rows) {
            List<WebElement> cells=row.findElements( By.tagName( "td" ) );
            int id=Integer.parseInt( cells.get(0).findElement( By.tagName( "input" ) ).getAttribute( "value" ) );
            String surname=cells.get(1).getText();
            String firstname=cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String[] address = cells.get(3).getText().split("\n");
            String allEmails = cells.get(4).getText();

            contacts.add( new ContactData().withId( id )
                    .withSurname( surname ).withFirstname( firstname )
                    .withAllPhones( allPhones )
                    .withAddress( address[0] )
                    .withAllEmail(allEmails));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById( contact.getId() );
        String firstname = wd.findElement(By.name("firstname")).getAttribute( "value" );
        String lastname = wd.findElement(By.name("lastname")).getAttribute( "value" );
        String home = wd.findElement(By.name("home")).getAttribute( "value" );
        String mobile = wd.findElement(By.name("mobile")).getAttribute( "value" );
        String work = wd.findElement(By.name("work")).getAttribute( "value" );
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute( "value" );
        String email2 = wd.findElement(By.name("email2")).getAttribute( "value" );
        String email3 = wd.findElement(By.name("email3")).getAttribute( "value" );

        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname( firstname ).withSurname( lastname )
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress( address )
                .withEmail( email ).withEmail2( email2 ).withEmail3( email3 );

        }
    }







