package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {
    private int i;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        if (groupHelper.all().size()== 0) {
            groupHelper.create( new GroupData().withName( "test1" ) );
        }
    }

    @Test
    public void testGroupDeletion() {
        GroupHelper groupHelper=app.group();
        Groups before=groupHelper.all();
        GroupData deletedGroup=before.iterator().next();
        groupHelper.delete( deletedGroup );
        app.goTo().groupPage();
        Groups after=groupHelper.all();
        assertEquals( after.size(), before.size() - 1 );



        assertThat( after, equalTo( before.without( deletedGroup ) ) );

    }


}




