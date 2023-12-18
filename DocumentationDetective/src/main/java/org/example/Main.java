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

        String packageName = "model";

        // Set up Reflections library to scan the codebase
        Reflections reflections = new Reflections(packageName);

        // Find classes annotated with @ClassDocumentation
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(ClassDocumentation.class);

        // Process annotated classes


        System.out.println("Annotated Classes are :");
        for (Class<?> clazz : annotatedClasses) {
            System.out.println("Annotated Class: " + clazz.getName());
        }

        System.out.println();
        System.out.println();

        System.out.println("Annotated Methods are :");
        for (Class<?> clazz : annotatedClasses) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                // Check for @MethodDocumentation annotation on the method
                if (method.isAnnotationPresent(MethodDocumentation.class)) {
                    // Process annotated methods
                    System.out.println("Annotated Method: " + clazz.getName() + "." + method.getName());
                }
            }
        }


        System.out.println();
        System.out.println();

        
        findAnnotatedClassesAndMethods();

    }
}
