package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;


public class GroupHelper extends HelperBase {


    public GroupHelper(WebDriver wd) {
        super( wd );
    }

    public void submitGroupCreation() {
        click( By.name( "submit" ) );
    }

    public void fillGroupForm(GroupData groupData) {
        type( By.name( "group_name" ), groupData.getName() );
        type( By.name( "group_header" ), groupData.getHeader() );
        type( By.name( "group_footer" ), groupData.getFooter() );
    }

    public void initGroupCreation() {
        click( By.name( "new" ) );
    }

    public void deleteSelectedGroups() {
        click( By.xpath( "(//input[@name='delete'])[2]" ) );
    }


    public void selectGroupById(int id) {

        wd.findElement( By.cssSelector( "input[value='" + id + "']" ) ).click();
    }

    public void selectGroupByText(String linkText) {
        WebElement userName=wd.findElement( By.xpath( ".//*[text()='Первая ссылка']/.." ) );
        click( By.linkText( linkText ) );
    }

    public void initGroupModification() {
        click( By.name( "edit" ) );
    }

    public void submitGroupModification() {
        click( By.name( "update" ) );
    }


    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm( group );
        submitGroupCreation();
        groupCache = null;
        groupPage();
    }

    public void modify(GroupData group) {
        selectGroupById( group.getId() );
        initGroupModification();
        fillGroupForm( group );
        submitGroupModification();
        groupCache = null;
        groupPage();
    }


    public void delete(GroupData group) {
        selectGroupById( group.getId() );
        deleteSelectedGroups();
        groupCache = null;
        groupPage();
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


    public void fillContactForm(ru.stqa.pft.addressbook.model.ContactData contactData, boolean creation) {

        type( By.name( "firstname" ), contactData.getFirstname() );
        type( By.name( "lastname" ), contactData.getSurname() );
        type( By.name( "address" ), contactData.getAddress() );
        type( By.name( "mobile" ), contactData.getPhone() );
        type( By.name( "email" ), contactData.getEmail() );

        findNew_group();

        if (creation) {
            new Select( wd.findElement( By.name( "new_group" ) ) ).selectByVisibleText( "test 1" );
        } else {
            Assert.assertFalse( findNew_group() );
        }
    }

    public boolean findNew_group() {
        try {
            wd.findElement( By.name( "new_group" ) );
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void groupPage() {
        if (!isElementPresent( By.tagName( "h1" ) )
                && wd.findElement( By.tagName( "h1" ) ).getText().equals( "Groups" )
                && !isElementPresent( By.tagName( "new" ) )) {
            return;
        }
        click( By.linkText( "groups" ) );
    }


    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache !=null) {
            return new Groups (groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements=wd.findElements( By.cssSelector( "span.group" ) );
        for (WebElement element : elements) {
            String name=element.getText();
            int id=Integer.parseInt( element.findElement( By.tagName( "input" ) ).getAttribute( "value" ) );
            groupCache.add( new GroupData().withId( id ).withName( name ) );
        }
        return new Groups (groupCache);
    }

}






