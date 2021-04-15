package VaadinWebApp.Views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("About | Laulima Major Based Games")
@Route(value = "about", layout = MainView.class)
public class AboutView extends VerticalLayout {

    public AboutView(){
        H1 about = new H1("Made it to about page");
        add(about);
    }


}
