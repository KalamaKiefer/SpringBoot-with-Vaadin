package VaadinWebApp.Views;

import VaadinWebApp.Student;
import VaadinWebApp.StudentRepository;
import VaadinWebApp.Views.FlashGameView;
import VaadinWebApp.Vocab;
import VaadinWebApp.VocabRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
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
public class FlashcardEditor extends VerticalLayout implements KeyNotifier {

    private VocabRepository vocabRepository;

    /**
     * The currently edited student
     */
    private Vocab vocab;


    TextField word = new TextField("Word");
    TextField definition = new TextField("Definition");


    /* Action buttons */
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    Button start = new Button("Start Game", VaadinIcon.ENTER.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete, start);

    Binder<Vocab> binder = new Binder<>(Vocab.class);
    private ChangeHandler changeHandler;

    @Autowired
    public FlashcardEditor(VocabRepository vocabRepository) {
        this.vocabRepository = vocabRepository;

        add(word, definition, actions);

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
        cancel.addClickListener(e -> editVocab(vocab));
        start.addClickListener(e -> UI.getCurrent().navigate(FlashGameView.class));
        setVisible(false);
    }

    void delete() {
        vocabRepository.delete(vocab);
        changeHandler.onChange();
    }

    void save() {
        vocabRepository.save(vocab);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }


    public final void editVocab(Vocab v){
        if (v == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = v.getIndex() != null;
        if (persisted) {
            // Find fresh entity for editing
            vocab = vocabRepository.findById(v.getIndex()).get();
        }
        else {
            vocab = v;
        }
        cancel.setVisible(persisted);
        start.setVisible(persisted);


        binder.setBean(vocab);

        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}