package VaadinWebApp.Views;




import VaadinWebApp.Student;
import VaadinWebApp.StudentRepository;
import VaadinWebApp.Vocab;
import VaadinWebApp.VocabRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Flashcard | Laulima Major Based Games")
@Route(value = "vocab", layout = MainView.class)
public class FlashcardView extends VerticalLayout{

    private VocabRepository vocabRepository;
    private FlashcardEditor editor;
    Grid<Vocab> grid;

    TextField filter;

    private Button addNewBtn;


    public FlashcardView(VocabRepository vocabRepository, FlashcardEditor editor) {

        this.vocabRepository = vocabRepository;
        this.editor = editor;
        this.grid = new Grid<>(Vocab.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Word", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("index", "word", "definition");
        grid.getColumnByKey("index").setWidth("50px").setFlexGrow(0);



        filter.setPlaceholder("Filter by word");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listStudents(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editVocab(e.getValue());
        });

        // Instantiate and edit new Student the new button is clicked
        addNewBtn.addClickListener(e -> editor.editVocab(new Vocab("", "")));

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
            grid.setItems(vocabRepository.findAll());
        } else{
            grid.setItems(vocabRepository.findByWord(filterText));
        }
    }

}
