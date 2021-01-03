package ru.stqa.pft.sandbox;

public class MySecondProgram {
    public static void main(String[] args) {
        Point p1 = new Point(1,1);
        Point p2 = new Point(4,5);
        p1.x = 1;
        p1.y = 1;
        p2.x = 4;
        p2.y = 5;

        System.out.println("Distance between p1 and p2 = " + p1.distance(p2));
    }
    
}



