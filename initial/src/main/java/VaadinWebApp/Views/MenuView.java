package VaadinWebApp.Views;


import VaadinWebApp.Student;
import VaadinWebApp.StudentRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

@PageTitle("Home | Laulima Major Based Games")
@Route(value = "", layout = MainView.class)
public class MenuView extends VerticalLayout{

    private StudentRepository repository;
    Grid<Student> grid;


    public MenuView(StudentRepository repo){
        this.repository = repo;


        // laulima banner image
        Image image = new Image("https://laulima.hawaii.edu/lum/fp/laulima_full_banner.jpg", "");
        image.addClassName("banner");
        add(image);

        H3 highsc = new H3("Daily High Scores!");

        this.grid = new Grid<>(Student.class);
        grid.setHeight("300px");
        grid.setColumns("id", "name", "major", "score");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);



        Paragraph con = new Paragraph("Contact Us!");


        TextField name = new TextField("Name");
        name.setPlaceholder("John Smith");
        TextField email = new TextField("Email");
        email.setPlaceholder("JohnSmith@gmail.com");
        TextField comment = new TextField("Comment");
        comment.setPlaceholder("Menu button not working.");
        Button send = new Button("Send", VaadinIcon.CHECK.create());


        HorizontalLayout cont = new HorizontalLayout(con, name, email, comment, send);
        cont.setDefaultVerticalComponentAlignment(Alignment.BASELINE);



        add(highsc, grid, cont);
        listStudents();
    }

    private void listStudents() {

            grid.setItems(repository.findAll());
    }

}
