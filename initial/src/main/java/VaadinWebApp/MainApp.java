package VaadinWebApp;

import VaadinWebApp.Views.FlashcardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackageClasses = { FlashcardView.class})
public class MainApp extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(VaadinWebApp.MainApp.class);

    public static void main(String[] args) {
        SpringApplication.run(VaadinWebApp.MainApp.class);
    }

    @Bean
    public CommandLineRunner loadData(StudentRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Student("Natalia Zelaya", "Marine Biology", "Bottle Nose Dolphin",
                    "An aquatic mammal"));
            repository.save(new Student("Timothy Kudryn", "Computer Science", "C++",
                    "An object oriented programming language."));
            repository.save(new Student("Keith Edwards", "Geology", "Transistors",
                    "a semiconductor device used to amplify or switch electronic signals and electrical power."));
            repository.save(new Student("Alexis Campos", "Kinesiology", "bicep", "A muscle in your arm"));
            repository.save(new Student("Kalawela Lo", "Physics", "Calculus", "Fun times"));


        };
    }

}
