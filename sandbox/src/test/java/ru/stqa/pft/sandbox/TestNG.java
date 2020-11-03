package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TestNG {

    @Test
    public void testArea() {
        Point p1 = new Point();
        Point p2 = new Point();
        p1.x = 1.0;
        p1.y = 1.0;
        p2.x = 4.0;
        p2.y = 5.0;

        double distance = p1.distance(p2);

       Assert.assertEquals(distance, 5);
}
}
