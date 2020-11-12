package ru.stqa.pft.addressbook.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testArea() {
        Square s = new Square (5.0);
        Assert.assertEquals(s.area (), 25.0);
    }
}
