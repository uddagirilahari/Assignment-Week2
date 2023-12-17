package model;

import Annotation.ClassDocumentation;
import Annotation.MethodDocumentation;

/**
 * this is an annotated class named ExampleClass
 * with annotated methods
 */

@ClassDocumentation
public class ExampleClass{
    /** this is a method called print
     * used to print a integer
     */
    public void print(int x){
        System.out.println(x);
    }
    /** this is a method called go
     * used to print a String
     */
    public void go(){
        System.out.println("go");
    }
}
