package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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


    public void selectGroup(int index) {
        wd.findElements( By.name( "selected[]" ) ).get( index ).click();
    }

    public void selectGroupByText(String linkText) {
        //WebElement userName = driver.findElement(By.xpath(".//*[text()='Первая ссылка']/.."));
        click( By.linkText( linkText ) );
    }

    public void initGroupModification() {
        click( By.name( "edit" ) );
    }

    public void submitGroupModification() {
        click( By.name( "update" ) );
    }


    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm( group );
        submitGroupCreation();
    }


    public int getGroupCount() {
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


    public boolean isThereAGroup() {
        return isElementPresent( By.name( "selected[]" ) );
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups=new ArrayList<GroupData>();
        List<WebElement> elements =wd.findElements(By.cssSelector( "span.group" ));
        for (WebElement element : elements) {
            String name = element.getText();
            GroupData group = new GroupData( name, null, null );
            groups.add( group );
        }
        return groups;
    }
}




