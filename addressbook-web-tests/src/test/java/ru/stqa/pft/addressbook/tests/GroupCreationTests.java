package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        Groups before=groupHelper.all();
        GroupData group=new GroupData().withName( "test2" );
        groupHelper.create( group );
        app.goTo().groupPage();
        Groups after=groupHelper.all();
        assertThat( after.size(), equalTo( before.size() + 1 ) );


        assertThat( after, equalTo
                ( before.withAdded(group.withId( after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt() )) ) );

    }
}