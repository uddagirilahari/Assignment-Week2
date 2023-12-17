package org.example;


import static model.FindAnnotation.findAnnotatedClassesAndMethods;

/**this is a comment to explain main method
 * here it is printing annotated classes and methods
 */

import Annotation.ClassDocumentation;
import Annotation.MethodDocumentation;
import org.reflections.Reflections;
import java.lang.reflect.Method;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Main{
    public static void main(String[] args) {

        findAnnotatedClassesAndMethods();

    }
}