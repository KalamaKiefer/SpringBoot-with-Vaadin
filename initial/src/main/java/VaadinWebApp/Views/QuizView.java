package VaadinWebApp.Views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("Quiz Race | Laulima Major Based Games")
@Route(value = "quiz", layout = MainView.class)
public class QuizView extends VerticalLayout {

    Button sub;

    public QuizView(){
        ProgressBar timer = new ProgressBar();
        timer.setValue(0.35);
     RadioButtonGroup<String> quiz =
             new RadioButtonGroup<>();
        sub = new Button("Submit");

     H3 q = new H3("Which animal is an amphibian?");
     quiz.setItems("Golden Retriever", "Gold Fish", "Alligator",
             "Eagle");

     add(timer, q, quiz, sub);


   }

}
