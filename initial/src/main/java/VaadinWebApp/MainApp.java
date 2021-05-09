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
    public CommandLineRunner loadData(StudentRepository repository, VocabRepository vocabRepository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Student("Natalia Zelaya", "Marine Biology", 350));
            vocabRepository.save(new Vocab("Bottle Nose Dolphin", "An aquatic animal"));
            vocabRepository.save(new Vocab("Shark", "a fish"));
            vocabRepository.save(new Vocab("Dog", "furry animal"));
            vocabRepository.save(new Vocab("H20", "water"));
            vocabRepository.save(new Vocab("Algae", "green stuff"));


            repository.save(new Student("Timothy Kudryn", "Computer Science", 200));
            repository.save(new Student("Keith Edwards", "Geology",  190));
            repository.save(new Student("Mark Sanchez", "Kinesiology",  150));
            repository.save(new Student("Elon Musk", "Physics", 90));


        };
    }

}
