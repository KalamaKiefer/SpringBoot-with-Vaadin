package VaadinWebApp.Views;

import VaadinWebApp.ProjectRepository;
import VaadinWebApp.Projects;
import VaadinWebApp.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;


@PageTitle("Projects | Laulima Major Based Games")
@Route(value = "projects", layout = MainView.class)
public class ProjectView extends VerticalLayout {

    private ProjectRepository prepo;
    private ProjectsEditor peditor;
    Grid<Projects> grid;

    TextField filter;

    private Button addNewBtn;

    public ProjectView(ProjectRepository repository, ProjectsEditor editor){

        this.prepo = repository;
        this.peditor = editor;
        this.grid = new Grid<>(Projects.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Flashcard Set", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("project_id", "pname", "topic");
        grid.getColumnByKey("project_id").setWidth("50px").setFlexGrow(0);


        filter.setPlaceholder("Filter by word");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listStudents(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editProject(e.getValue());
        });

        // Instantiate and edit new Student the new button is clicked

        addNewBtn.addClickListener(e -> editor.editProject(new Projects("", "")));


        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listStudents(filter.getValue());
        });

        // Initialize listing
        listStudents(null);
    }

    private void listStudents(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(prepo.findAll());
        } else{
            grid.setItems(prepo.findByPname(filterText));
        }
    }


}
