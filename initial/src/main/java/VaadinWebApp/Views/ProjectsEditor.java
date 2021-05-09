package VaadinWebApp.Views;

import VaadinWebApp.ProjectRepository;
import VaadinWebApp.Projects;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;


/**

     */
    @SpringComponent
    @UIScope
    public class ProjectsEditor extends VerticalLayout implements KeyNotifier {

        private ProjectRepository prepo;

        /**
         * The currently edited student
         */
        private Projects project;

        /* Fields to edit properties in Student entity */
        TextField pname = new TextField("Name");
        TextField topic = new TextField("Topic");



        /* Action buttons */
        Button save = new Button("Save", VaadinIcon.CHECK.create());
        Button cancel = new Button("Go",VaadinIcon.ARROW_RIGHT.create());
        Button delete = new Button("Delete", VaadinIcon.TRASH.create());
        HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

        Binder<Projects> binder = new Binder<>(Projects.class);
        private ChangeHandler changeHandler;

        @Autowired
        public ProjectsEditor(ProjectRepository repository) {
            this.prepo = repository;

            add(pname, topic, actions);

            // bind using naming convention
            binder.bindInstanceFields(this);

            // Configure and style components
            setSpacing(true);

            save.getElement().getThemeList().add("primary");
            delete.getElement().getThemeList().add("error");

            addKeyPressListener(Key.ENTER, e -> save());

            // wire action buttons to save, delete and reset
            save.addClickListener(e -> save());
            delete.addClickListener(e -> delete());
            cancel.addClickListener(e -> UI.getCurrent().navigate(FlashcardView.class));
            setVisible(false);
        }

        void delete() {
            prepo.delete(project);
            changeHandler.onChange();
        }

        void save() {
            prepo.save(project);
            changeHandler.onChange();
        }

        public interface ChangeHandler {
            void onChange();
        }

        public final void editProject(Projects p) {
            if (p == null) {
                setVisible(false);
                return;
            }
            final boolean persisted = p.getProject_id() != null;
            if (persisted) {
                // Find fresh entity for editing
                project = prepo.findById(p.getProject_id()).get();
            }
            else {
                project = p;
            }
            cancel.setVisible(persisted);

            // Bind student properties to similarly named fields
            // Could also use annotation or "manual binding" or programmatically
            // moving values from fields to entities before saving
            binder.setBean(project);

            setVisible(true);

        }

        public void setChangeHandler(ChangeHandler h) {
            // ChangeHandler is notified when either save or delete
            // is clicked
            changeHandler = h;
        }


}
