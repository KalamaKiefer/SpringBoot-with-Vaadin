package VaadinWebApp.Views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

@PageTitle("Home | Laulima Major Based Games")
@Route(value = "", layout = MainView.class)
public class MenuView extends VerticalLayout{

    public MenuView(){


        // laulima banner image
        Image image = new Image("https://laulima.hawaii.edu/lum/fp/laulima_full_banner.jpg", "");
        image.addClassName("banner");
        add(image);

        Paragraph con = new Paragraph("Contact Us!");
        TextField name = new TextField("name");
        TextField email = new TextField("email");
        TextField comment = new TextField("comment");
        Button send = new Button("Send", VaadinIcon.CHECK.create());


        VerticalLayout form = new VerticalLayout(con, name, email, comment, send);
        form.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(form);
    }

}
