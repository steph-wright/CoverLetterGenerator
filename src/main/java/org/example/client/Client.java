package org.example.client;


import org.example.presentation.Presentation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {


        AnnotationConfigApplicationContext springContainer = new AnnotationConfigApplicationContext(Config.class);
        Presentation presentation = (Presentation) springContainer.getBean("presentation");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            presentation.displayMenu();
            int choice = presentation.getChoice();
            presentation.executeMenu(choice);
        }

    }
}
