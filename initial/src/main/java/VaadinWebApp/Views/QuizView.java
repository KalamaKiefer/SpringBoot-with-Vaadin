package VaadinWebApp.Views;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@PageTitle("Quiz Race | Laulima Major Based Games")
@Route(value = "quiz", layout = MainView.class)
public class QuizView extends VerticalLayout {

public QuizView(){
    H1 test = new H1("Made it to quiz race");
    add(test);
}


}
