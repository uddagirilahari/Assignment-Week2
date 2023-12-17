package model;

import Annotation.ClassDocumentation;
import Annotation.MethodDocumentation;

/**
 * This is a class
 *
 * @ClassDocumentation "This is a class "
 */
@ClassDocumentation("This is a class ")
public class AnnotationExample {

    /**
     * This is a method  inside class.
     *
     * @MethodDocumentation "This is a method inside class"
     */
    @MethodDocumentation("This is a method inside")
    public void sum() {
        System.out.println(2+3);
    }

    /**
     * This is a method subtract inside class.
     *
     * @MethodDocumentation "This is a method Subtract inside class"
     */
    @MethodDocumentation("This is a method AB inside A")
    public void sub() {
        System.out.println(7-3);
    }

    @MethodDocumentation("This is a method AC inside A")
    public void multiply() {
        System.out.println(3*2);
    }

    /**
     * This is a method division inside AnnotationExampleClass.
     *
     * @MethodDocumentation "This is a method AD inside A"
     */
    public void division() {
        System.out.println(6/2);
    }
}