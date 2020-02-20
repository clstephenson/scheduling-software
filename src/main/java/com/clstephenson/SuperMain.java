package com.clstephenson;

/**
 * This class was not part of the original project.  I had to add it when I converted to a Maven project and
 * attempted to generate an executable JAR package. This class works as a main class because it does not extend
 * any class from an outside dependency (i.e. Application from the JavaFX library).
 */
public class SuperMain {

    public static void main(String[] args) {
        Main.main(args);
    }

}
