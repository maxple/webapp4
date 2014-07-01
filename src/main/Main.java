package main;

//import training;

import training.*;

public class Main {

    public static void main(String[] args) {

        OuterClass outerClass = new OuterClass("qwe");

        System.out.println(outerClass.getText());

        OuterClass.InnerClass innerClass = new OuterClass.InnerClass("rty");

        System.out.println(innerClass.getText());

    }
}
