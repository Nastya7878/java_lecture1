package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup( new GroupData( "test 1", "null", "null" ) );
            app.getGroupHelper().selectGroup();
            app.getGroupHelper().initGroupModification();
            app.getGroupHelper().fillGroupForm( new GroupData( "test 11", "test 12", "test 13" ) );
            app.getGroupHelper().submitGroupModification();
            app.getNavigationHelper().gotoGroupPage();
        }
    }
}


