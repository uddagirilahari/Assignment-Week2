package model;

import Annotation.ClassDocumentation;
import Annotation.MethodDocumentation;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileFilter;

public class FindAnnotation{

    static List<String> javaDocs = new ArrayList<String>();

    private static class JavaFileFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            return pathname.isFile() && pathname.getName().endsWith(".java");
        }
    }


    public static void findAnnotatedClassesAndMethods() {


        File directory = new File("C:\\DocumentationDetective\\src\\main\\java\\model");

        if (directory.exists() && directory.isDirectory()) {
            File[] javaFiles = directory.listFiles(new JavaFileFilter());

            if (javaFiles != null) {
                for (File javaFile : javaFiles) {
                    processFile(javaFile);
                }
            } else {
                System.out.println("Error listing files");
            }
        } else {
            System.out.println("Directory does not exist or is not a directory");
        }

    }

    private static void processFile(File path) {
        Path outputPath = Paths.get("javadocs.txt");

        try {
            CompilationUnit unit = JavaParser.parse(path);

            for(TypeDeclaration types : unit.getTypes()) {

                // Check if class is annotated
                if (types.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(ClassDocumentation.class.getSimpleName()))) {
                    System.out.println("Class "+types.getName()+" is annotated with @ClassDocumentation");

                    Comment commentOptions = types.getComment();
                    if (commentOptions instanceof JavadocComment) {
                        JavadocComment comment = (JavadocComment) commentOptions;
                        String javadoc = "Class " + types.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + types.getName() + " has no JavaDoc comment");
                    }

                }else{
                    System.out.println("Class "+types.getName()+" is not annotated with @ClassDocumentation");

                    Comment commentOptions = types.getComment();
                    if (commentOptions instanceof JavadocComment) {
                        JavadocComment comment = (JavadocComment) commentOptions;
                        String javadoc = "Class " + types.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + types.getName() + " has no JavaDoc comment");
                    }
                }

                for (BodyDeclaration member : types.getMembers()) {
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration method = (MethodDeclaration) member;
                        if (method.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(MethodDocumentation.class.getSimpleName()))) {
                            System.out.println("Method "+method.getName()+ " in class "+types.getName()+" is annotated with @MethodDocumentation");

                            Comment commentOptions = method.getComment();
                            if (commentOptions instanceof JavadocComment) {
                                JavadocComment comment = (JavadocComment) commentOptions;
                                String javadoc = "Method " + method.getName() + " in class " + types.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + method.getName() + " in class " + types.getName() + " has no JavaDoc comment");
                            }
                        }else{
                            System.out.println("Method "+method.getName()+ " in class "+types.getName()+" is not annotated with @MethodDocumentation");

                            Comment commentOptions = method.getComment();
                            if (commentOptions instanceof JavadocComment) {
                                JavadocComment comment = (JavadocComment) commentOptions;
                                String javadoc = "Method " + method.getName() + " in class " + types.getName() + " has JavaDoc comment: \n" + comment.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + method.getName() + " in class " + types.getName() + " has no JavaDoc comment");
                            }
                        }
                    }
                }
                System.out.println();
            }

            try {
                Files.write(outputPath, javaDocs, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + path);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}