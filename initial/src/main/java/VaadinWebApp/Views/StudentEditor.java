package VaadinWebApp.Views;

import VaadinWebApp.Student;
import VaadinWebApp.StudentRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**

 */
@SpringComponent
@UIScope
public class StudentEditor extends VerticalLayout implements KeyNotifier {

    private StudentRepository repository;

    /**
     * The currently edited student
     */
    private Student student;

    /* Fields to edit properties in Student entity */
    TextField name = new TextField("Name");
    TextField major = new TextField("Major");
    TextField word = new TextField("Word");
    TextField definition = new TextField("Definition");


    /* Action buttons */
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Student> binder = new Binder<>(Student.class);
    private ChangeHandler changeHandler;

    @Autowired
    public StudentEditor(StudentRepository repository) {
        this.repository = repository;

        add(name, major, word, definition, actions);

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
        cancel.addClickListener(e -> editStudent(student));
        setVisible(false);
    }

    void delete() {
        repository.delete(student);
        changeHandler.onChange();
    }

    void save() {
        repository.save(student);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editStudent(Student s) {
        if (s == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = s.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            student = repository.findById(s.getId()).get();
        }
        else {
            student = s;
        }
        cancel.setVisible(persisted);

        // Bind student properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(student);

        setVisible(true);

        // Focus first name initially
        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}