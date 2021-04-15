package VaadinWebApp.Views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@CssImport("./styles/styles.css")
public class MainView extends AppLayout{

    public MainView() {

        createHeader();
        createDrawer();

    }

    private void createHeader(){
        H1 logo = new H1("Laulima Major Based Games");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

        header.setDefaultVerticalComponentAlignment(
                FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");


        addToNavbar(header);
    }


    private void createDrawer() {
        RouterLink home = new RouterLink("Home", MenuView.class);
        home.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink flash = new RouterLink("Flashcards", FlashcardView.class);
        RouterLink quiz =  new RouterLink("Quiz Race", QuizView.class);
        RouterLink about = new RouterLink("About", AboutView.class);


        addToDrawer(new VerticalLayout(home),new VerticalLayout(flash),
                new VerticalLayout(quiz), new VerticalLayout(about));
    }

}
